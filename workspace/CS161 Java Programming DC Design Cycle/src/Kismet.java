/*
 * Kismet v1.2
 * Created by Eric Fan
 * Updated 6/10/17
 * --- USER MANUAL ---
 * Virtual implementation of the board game Kismet.
 * Game can be played by 1 to 4 players. Players will take a total of 15 turns rolling 5 dice. Each player gets 3 rolls per turn.
 * With each roll, the user can choose to hold certain dice and roll the rest. Held dice can be unheld after a subsequent roll.
 * Click on a dice in the interface to hold it, click it again to unhold it. Held dice are highlighted red. Players can score after 
 * any number of rolls if they wish, but must score if they run out of rolls. Their score for the round is determined by their current 
 * dice in hand. If they meet any of the conditions for a category, they get a score for that category. (i.e. Player 1 has hand 1-1-6-6-6, 
 * therefore can score in Full House, Full House Same Color, Flush, Ones, Sixes, Three of a Kind, Two Pairs Same Color, Yarborough, or any 
 * other category for a score of 0). If a player can't score in any category or chooses not to score in a scorable category, they can score 
 * in any open category for a score of 0. Click on a category to score. Other players' categories and previously scored categories will
 * not be selectable. Previously scored categories will be highlighted yellow.
 * --- SCORING DETAILS ---
 * 1s - Amount of 1s
 * 2s - Amount of 2s multiplied by 2
 * 3s - Amount of 3s multiplied by 3
 * 4s - Amount of 3s multiplied by 4
 * 5s - Amount of 3s multiplied by 5
 * 6s - Amount of 3s multiplied by 6
 * Upper Bonus - 75 if all above categories add up to 78 or more
 *               55 if all above categories add up to 71-77
 *               35 if all above categories add up to 63-70
 *               (Computed at end of game)
 * 2 Pairs of Same Color - Sum of Dice
 * 3 of a Kind - Sum of Dice
 * Straight (5 in a Row) - 30
 * Flush (All Dice Same Color) - 35
 * Full House (Pair and 3 of a Kind) - Sum of Dice + 15
 * Full House Same Color - Sum of Dice + 20
 * 4 of a Kind - Sum of Dice + 25
 * Yarborough (Any combination of dice) - Sum of Dice
 * Kismet (5 of a Kind) - Sum of Dice + 50
 * --- GAME CONCLUSION ---
 * The winner is determined by each player's score at the end of the game. The player with the highest total score wins. A player's total
 * score is the sum of all their scores in their categories plus any additional bonuses that they recieved.
 */

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class Kismet implements ActionListener, Runnable {
	
	JFrame frame = new JFrame("Kismet");
	JLabel stats = new JLabel();
	JButton[][] scoreGrid;
	JButton[] diceButtons = new JButton[5];
	JButton roll = new JButton("Roll Dice");
	JTextField[][] scoreDisplay;
	Container gridHolder = new Container();
	Container labels = new Container();
	Container south = new Container();
	Container dice = new Container();
	int numPlayers, round, rolls;
	
	public static void main(String[] args) {
		new Kismet();
	}
	
	public Kismet() { // initial build
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600, 1047));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		labels.setLayout(new GridLayout(18, 1));
		// initializing all scoring labels
		for (int i = 0; i < 6; i++) labels.add(new JLabel("   " + (i + 1) + "s   "));
		labels.add(new JLabel("   Upper Sum   "));
		labels.add(new JLabel("   Bonus   "));
		labels.add(new JLabel("   2 Pairs Same Color   "));
		labels.add(new JLabel("   3 Of A Kind   "));
		labels.add(new JLabel("   Straight   "));
		labels.add(new JLabel("   Flush   "));
		labels.add(new JLabel("   Full House   "));
		labels.add(new JLabel("   Full House Same Color   "));
		labels.add(new JLabel("   4 Of A Kind   "));
		labels.add(new JLabel("   Yarborough   "));
		labels.add(new JLabel("   Kismet   "));
		labels.add(new JLabel("   Total Score   "));
		while (true) { // determines number of players and builds layout accordingly
			String s = (String)JOptionPane.showInputDialog(frame, "Select number of players:", null, JOptionPane.PLAIN_MESSAGE, null, new String[]{"1", "2", "3", "4"}, "1");
			if (s != null) {
				numPlayers = Integer.parseInt(s);
				break;
			}
		} frame.add(labels, BorderLayout.WEST);
		scoreGrid = new JButton[15][numPlayers];
		scoreDisplay = new JTextField[3][numPlayers];
		gridHolder.setLayout(new GridLayout(18, numPlayers));
		// add upper scoring section
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < scoreGrid[0].length; j++) {
				scoreGrid[i][j] = new JButton("0");
				scoreGrid[i][j].addActionListener(this);
				scoreGrid[i][j].setBackground(Color.WHITE);
				scoreGrid[i][j].setEnabled(false);
				gridHolder.add(scoreGrid[i][j]);
			} // add upper score display text fields
		} for (int i = 0; i < scoreDisplay.length - 1; i++) {
			for (int j = 0; j < scoreDisplay[0].length; j++) {
				scoreDisplay[i][j] = new JTextField();
				scoreDisplay[i][j].setEditable(false);
				gridHolder.add(scoreDisplay[i][j]);
			} // add lower scoring section
		} for (int i = 6; i < scoreGrid.length; i++) {
			for (int j = 0; j < scoreGrid[0].length; j++) {
				scoreGrid[i][j] = new JButton("0");
				scoreGrid[i][j].addActionListener(this);
				scoreGrid[i][j].setBackground(Color.WHITE);
				scoreGrid[i][j].setEnabled(false);
				gridHolder.add(scoreGrid[i][j]);
			} // add lower score display text fields
		} for (int i = 0; i < scoreDisplay[0].length; i++) {
			scoreDisplay[scoreDisplay.length - 1][i] = new JTextField();
			scoreDisplay[scoreDisplay.length - 1][i].setEditable(false);
			gridHolder.add(scoreDisplay[scoreDisplay.length - 1][i]);
		} frame.add(gridHolder, BorderLayout.CENTER);
		south.setLayout(new GridLayout(2, 1));
		dice.setLayout(new GridLayout(1, 6));
		roll.addActionListener(this);
		// initialize dice buttons and roll
		for (int i = 0; i < diceButtons.length; i++) {
			try {
				diceButtons[i] = new JButton(new ImageIcon(ImageIO.read(new File(Integer.toString(i + 1) + ".png")), Integer.toString(i + 1)));
			} catch (Exception e) {
				e.printStackTrace();
			} dice.add(diceButtons[i]);
			diceButtons[i].setEnabled(false);
			diceButtons[i].setBackground(Color.WHITE);
			diceButtons[i].addActionListener(this);
		} south.add(roll);
		south.add(dice);
		frame.add(south, BorderLayout.SOUTH);
		frame.add(stats, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(roll)) { // this will also be the new game button
			if (roll.getText().equals("New Game")) { // if end of game
				for (int i = 0; i < scoreGrid.length; i++) { // reset grid
					for (int j = 0; j < scoreGrid[0].length; j++) {
						scoreGrid[i][j].setBackground(Color.WHITE);
						scoreGrid[i][j].setText("0");
					} // reset score display
				} for (int i = 0; i < scoreDisplay.length; i++) for (int j = 0; j < scoreDisplay[0].length; j++) scoreGrid[i][j].setText("");
				round = 0; // reset round
			} else { // if not end of game roll dice
				rolls++;
				updateStats();
				Thread t = new Thread(this);
				t.start();
				return;
			}
		} for (int i = 0; i < diceButtons.length; i++) {
			if (e.getSource().equals(diceButtons[i])) { // if one of the dice were pressed, lock/unlock it
				diceButtons[i].setBackground(diceButtons[i].getBackground().equals(Color.WHITE) ? new Color(0xFF7A7A) : Color.WHITE);
				return;
			} // otherwise the click was from the scoring grid
		} for (JButton b: diceButtons) { // need to disable all dice
			b.setEnabled(false);
			b.setBackground(Color.WHITE);
		} roll.setEnabled(true); // allow next player to roll
		for (int i = 0; i < scoreGrid.length; i++) { // disable scoring
			scoreGrid[i][round % numPlayers].setEnabled(false);
			// if the score section was not clicked set the score back to 0 for next player
			if (e.getSource().equals(scoreGrid[i][round % numPlayers])) scoreGrid[i][round % numPlayers].setBackground(new Color(0xF6FF67));
			else if (scoreGrid[i][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[i][round % numPlayers].setText("0");
		} if (round / numPlayers == 14 && round % numPlayers == numPlayers - 1) { // game over
			int winner = -1, maxScore = Integer.MIN_VALUE;
			for (int j = 0; j < numPlayers; j++) { // final scores
				int upperSum = 0, lowerSum = 0;
				for (int i = 0; i < 6; i++) upperSum += Integer.parseInt(scoreGrid[i][j].getText());
				scoreDisplay[0][j].setText(Integer.toString(upperSum));
				if (upperSum >= 78) scoreDisplay[1][j].setText("75");
				else if (upperSum >= 71) scoreDisplay[1][j].setText("55");
				else scoreDisplay[1][j].setText(upperSum >= 63 ? "35" : "0");
				for (int i = 6; i < scoreGrid.length; i++) lowerSum += Integer.parseInt(scoreGrid[i][j].getText());
				scoreDisplay[2][j].setText(Integer.toString(upperSum + lowerSum + Integer.parseInt(scoreDisplay[1][j].getText())));
				if (Integer.parseInt(scoreDisplay[2][j].getText()) > maxScore) {
					maxScore = Integer.parseInt(scoreDisplay[2][j].getText());
					winner = j + 1;
				}
			} JOptionPane.showMessageDialog(frame, "Game Over, Player " + winner + " wins!");
			roll.setText("New Game");
			return;
		} if (e.getSource().equals(roll) && roll.getText().equals("New Game")) roll.setText("Roll Dice");
		else round++;
		rolls = 0;
		updateStats();
	}
	
	public void run() { // takes care of dice roll
		for (JButton b: diceButtons) b.setEnabled(false); // disables all dice while rolling
		roll.setEnabled(false); // disable rolling again until roll is finished
		for (int i = 0; i < scoreGrid.length; i++) scoreGrid[i][round % numPlayers].setEnabled(false); // also disable score updating while roll is in progress
		long start = System.currentTimeMillis(); // roll for 0.7 sec
		while (System.currentTimeMillis() - start < 700) {
			for (int i = 0; i < diceButtons.length; i++) {
				if (diceButtons[i].getBackground().equals(new Color(0xFF7A7A))) continue;
				int roll = (int)Math.round(Math.random() * 5) + 1;
				try { // roll works by randomly assigning values to dice
					diceButtons[i].setIcon(new ImageIcon(ImageIO.read(new File(Integer.toString(roll) + ".png")), Integer.toString(roll)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} try {
				Thread.sleep(15);
			} catch (Exception e) {
				e.printStackTrace();
			} // roll is finished, enable dice clicking
		} for (JButton b: diceButtons) b.setEnabled(true);
		if (rolls < 3) roll.setEnabled(true); // if the player still has rolls, enable roll
		// enable scoring
		for (int i = 0; i < scoreGrid.length; i++) if (scoreGrid[i][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[i][round % numPlayers].setEnabled(true);
		updateScores();
	}

	public void updateStats() { // updates the label at the top of the frame
		stats.setText("   Round " + (round / numPlayers + 1) + " --- Player " + (round % numPlayers + 1) + "'s turn --- Rolls left: " + (3 - rolls));
	}
	
	public void updateScores() {
		// get a dice count and sum of dice
		int[] diceCount = new int[6];
		int sumOfDice = 0;
		boolean sameColor = true;
		for (int i = 0; i < diceButtons.length; i++) {
			diceCount[Integer.parseInt(((ImageIcon)(diceButtons[i].getIcon())).getDescription()) - 1]++;
			sumOfDice += Integer.parseInt(((ImageIcon)(diceButtons[i].getIcon())).getDescription());
			// see if all dice are same color for flush & full house same color
			if (i != diceButtons.length - 1) if (!getColor(Integer.parseInt(((ImageIcon)(diceButtons[i].getIcon())).getDescription()) - 1).equals(getColor(Integer.parseInt(((ImageIcon)(diceButtons[i + 1].getIcon())).getDescription()) - 1))) sameColor = false;
		} // before updating scores, set every category that hasn't been scored in to 0
		for (int i = 6; i < scoreGrid.length; i++) if (scoreGrid[i][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[i][round % numPlayers].setText("0");
		for (int i = 0; i < diceCount.length; i++) { // iterate through the counts of all dice 1 through 6
			// if unlocked, set the subsequent upper category to the dice count multiplied by their value (if dice count for 3s is 4, score is 3 * 4 = 12)
			if (scoreGrid[i][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[i][round % numPlayers].setText(Integer.toString(diceCount[i] * (i + 1)));
			// at the same time, check to see if this dice count is eligible for 3 of a kind or 4 of a kind
			if (diceCount[i] >= 3) {
				if (scoreGrid[7][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[7][round % numPlayers].setText(Integer.toString(sumOfDice));
				// if there is a 3 of a kind, check for a pair for full house.
				for (int j = 0; j < diceCount.length; j++) {
					if (diceCount[j] == 2) {
						// regular full house
						if (scoreGrid[10][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[10][round % numPlayers].setText(Integer.toString(sumOfDice + 15));
						// full house same color
						if (sameColor && scoreGrid[11][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[11][round % numPlayers].setText(Integer.toString(sumOfDice + 20));
					}
				}
			} if (diceCount[i] >= 4 && scoreGrid[12][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[12][round % numPlayers].setText(Integer.toString(sumOfDice + 25));
			// if there is 5 of the same dice, score it in the kismet section
			if (diceCount[i] == 5 && scoreGrid[14][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[14][round % numPlayers].setText(Integer.toString(sumOfDice + 50));
			// 2 pair same color
			if (diceCount[i] >= 2) {
				// check to see if there is another pair with same color
				for (int j = 0; j < diceCount.length; j++) if (diceCount[j] >= 2 && getColor(i).equals(getColor(j)) && i != j && scoreGrid[6][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[6][round % numPlayers].setText(Integer.toString(sumOfDice));
			}
		} // flush 
		if (sameColor && scoreGrid[9][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[9][round % numPlayers].setText("35");
		StringBuilder s = new StringBuilder(); // get String representation of diceCounts to check for straight
		for (int i: diceCount) s.append(Integer.toString(i));
		// if the String's 0s are at the ends of the String and the String only contains one 0, then there is a straight
		if ((s.indexOf("0") == 0 || s.indexOf("0") == s.length() - 1) && !s.deleteCharAt(s.indexOf("0")).toString().contains("0") && scoreGrid[8][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[8][round % numPlayers].setText("30");
		// if unlocked, set the yarborough category
		if (scoreGrid[13][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[13][round % numPlayers].setText(Integer.toString(sumOfDice));
	}
	
	// offset by 1, dieValue = 0 represents die value of 1
	public String getColor(int dieValue) {
		switch (dieValue) {
			case 0: return "Black";
			case 1: return "Red";
			case 2: return "Green";
			case 3: return "Green";
			case 4: return "Red";
			case 5: return "Black";
			default: return null;
		}
	}

}
