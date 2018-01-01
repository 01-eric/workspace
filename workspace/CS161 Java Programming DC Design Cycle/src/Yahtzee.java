import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

public class Yahtzee implements ActionListener, Runnable {
	
	JFrame frame = new JFrame("Yahtzee");
	JLabel stats = new JLabel();
	JButton[][] scoreGrid;
	JButton[] diceButtons = new JButton[5];
	JButton roll = new JButton("Roll Dice");
	JTextPane[][] scoreDisplay;
	Container gridHolder = new Container();
	Container labels = new Container();
	Container south = new Container();
	Container dice = new Container();
	int numPlayers, round, rolls;
	
	public static void main(String[] args) {
		new Yahtzee();
	}
	
	public Yahtzee() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600, 1047));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		labels.setLayout(new GridLayout(16, 1));
		for (int i = 0; i < 6; i++) labels.add(new JLabel("   " + (i + 1) + "s   "));
		labels.add(new JLabel("   Upper Sum   "));
		labels.add(new JLabel("   Bonus   "));
		labels.add(new JLabel("   3 of a Kind   "));
		labels.add(new JLabel("   4 of a Kind   "));
		labels.add(new JLabel("   Full House   "));
		labels.add(new JLabel("   Small Straight   "));
		labels.add(new JLabel("   Large Straight   "));
		labels.add(new JLabel("   Chance   "));
		labels.add(new JLabel("   YAHTZEE   "));
		labels.add(new JLabel("   Total Sum   "));
		while (true) {
			String s = (String)JOptionPane.showInputDialog(frame, "Select number of players:", null, JOptionPane.PLAIN_MESSAGE, null, new String[]{"1", "2", "3", "4"}, "1");
			if (s != null) {
				numPlayers = Integer.parseInt(s);
				break;
			}
		} frame.add(labels, BorderLayout.WEST);
		scoreGrid = new JButton[13][numPlayers];
		scoreDisplay = new JTextPane[3][numPlayers];
		gridHolder.setLayout(new GridLayout(16, numPlayers));
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < scoreGrid[0].length; j++) {
				scoreGrid[i][j] = new JButton("0");
				scoreGrid[i][j].addActionListener(this);
				scoreGrid[i][j].setBackground(Color.WHITE);
				scoreGrid[i][j].setEnabled(false);
				gridHolder.add(scoreGrid[i][j]);
			}
		} for (int i = 0; i < scoreDisplay.length - 1; i++) {
			for (int j = 0; j < scoreDisplay[0].length; j++) {
				scoreDisplay[i][j] = new JTextPane();
				scoreDisplay[i][j].setFont(new Font("SansSerif", Font.BOLD, 14));
				scoreDisplay[i][j].setEditable(false);
				SimpleAttributeSet attributeSet = new SimpleAttributeSet();
			    StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
			    scoreDisplay[i][j].setCharacterAttributes(attributeSet, true);
				gridHolder.add(scoreDisplay[i][j]);
			}
		} for (int i = 6; i < scoreGrid.length; i++) {
			for (int j = 0; j < scoreGrid[0].length; j++) {
				scoreGrid[i][j] = new JButton("0");
				scoreGrid[i][j].addActionListener(this);
				scoreGrid[i][j].setBackground(Color.WHITE);
				scoreGrid[i][j].setEnabled(false);
				gridHolder.add(scoreGrid[i][j]);
			}
		} for (int i = 0; i < scoreDisplay[0].length; i++) {
			scoreDisplay[scoreDisplay.length - 1][i] = new JTextPane();
			scoreDisplay[scoreDisplay.length - 1][i].setFont(new Font("SansSerif", Font.BOLD, 14));
			scoreDisplay[scoreDisplay.length - 1][i].setEditable(false);
			SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		    StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
		    scoreDisplay[scoreDisplay.length - 1][i].setCharacterAttributes(attributeSet, true);
			gridHolder.add(scoreDisplay[scoreDisplay.length - 1][i]);
		} frame.add(gridHolder, BorderLayout.CENTER);
		south.setLayout(new GridLayout(2, 1));
		dice.setLayout(new GridLayout(1, 6));
		roll.addActionListener(this);
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
		updateStats();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(roll)) {
			if (roll.getText().equals("New Game")) {
				for (int i = 0; i < scoreGrid.length; i++) {
					for (int j = 0; j < scoreGrid[0].length; j++) {
						scoreGrid[i][j].setBackground(Color.WHITE);
						scoreGrid[i][j].setText("0");
					}
				} round = 0;
			} else {
				rolls++;
				updateStats();
				Thread t = new Thread(this);
				t.start();
				return;
			}
		} for (int i = 0; i < diceButtons.length; i++) {
			if (e.getSource().equals(diceButtons[i])) {
				diceButtons[i].setBackground(diceButtons[i].getBackground().equals(Color.WHITE) ? new Color(0xFF7A7A) : Color.WHITE);
				return;
			}
		} for (JButton b: diceButtons) {
			b.setEnabled(false);
			b.setBackground(Color.WHITE);
		} roll.setEnabled(true);
		for (int i = 0; i < scoreGrid.length; i++) {
			scoreGrid[i][round % numPlayers].setEnabled(false);
			if (e.getSource().equals(scoreGrid[i][round % numPlayers])) scoreGrid[i][round % numPlayers].setBackground(new Color(0xF6FF67));
			else if (scoreGrid[i][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[i][round % numPlayers].setText("0");
		} if (round / numPlayers == 12 && round % numPlayers == numPlayers - 1) { // game over
			JOptionPane.showMessageDialog(frame, "Game Over");
			roll.setText("New Game");
			return;
		} if (e.getSource().equals(roll) && roll.getText().equals("New Game")) roll.setText("Roll Dice");
		else round++;
		rolls = 0;
		updateStats();
	}
	
	public void run() {
		for (JButton b: diceButtons) b.setEnabled(false);
		roll.setEnabled(false);
		for (int i = 0; i < scoreGrid.length; i++) scoreGrid[i][round % numPlayers].setEnabled(false);
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < 700) {
			for (int i = 0; i < diceButtons.length; i++) {
				if (diceButtons[i].getBackground().equals(new Color(0xFF7A7A))) continue;
				int roll = (int)Math.round(Math.random() * 5) + 1;
				try {
					diceButtons[i].setIcon(new ImageIcon(ImageIO.read(new File(Integer.toString(roll) + ".png")), Integer.toString(roll)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} try {
				Thread.sleep(15);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} for (JButton b: diceButtons) b.setEnabled(true); // is b also reference to diceButtons[i]?
		if (rolls < 3) roll.setEnabled(true);
		for (int i = 0; i < scoreGrid.length; i++) if (scoreGrid[i][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[i][round % numPlayers].setEnabled(true);
		updateScores();
	}

	public void updateStats() {
		stats.setText("   Round " + (round / numPlayers + 1) + " --- Player " + (round % numPlayers + 1) + "'s turn --- Rolls left: " + (3 - rolls));
	}
	
	public void updateScores() {
		int[] diceCount = new int[6];
		int sumOfDice = 0;
		for (int i = 0; i < diceButtons.length; i++) {
			diceCount[Integer.parseInt(((ImageIcon)(diceButtons[i].getIcon())).getDescription()) - 1]++;
			sumOfDice += Integer.parseInt(((ImageIcon)(diceButtons[i].getIcon())).getDescription());
		} for (int i = 6; i < scoreGrid.length; i++) if (scoreGrid[i][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[i][round % numPlayers].setText("0");
		for (int i = 0; i < diceCount.length; i++) {
			if (scoreGrid[i][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[i][round % numPlayers].setText(Integer.toString(diceCount[i] * (i + 1)));
			if (diceCount[i] >= 3 && scoreGrid[6][round % numPlayers].getBackground().equals(Color.WHITE)) {
				scoreGrid[6][round % numPlayers].setText(Integer.toString(sumOfDice));
				for (int j = 0; j < diceCount.length; j++) if (diceCount[j] == 2 && scoreGrid[8][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[8][round % numPlayers].setText("25");
			} if (diceCount[i] >= 4 && scoreGrid[7][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[7][round % numPlayers].setText(Integer.toString(sumOfDice));
			if (diceCount[i] == 5 && scoreGrid[12][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[12][round % numPlayers].setText("50");
		} String s = "";
		for (int i: diceCount) s += Integer.toString(i);
		String[] straights = s.split("0");
		for (String str: straights) {
			if (str.length() >= 4 && scoreGrid[9][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[9][round % numPlayers].setText("30");
			if (str.length() == 5 && scoreGrid[10][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[10][round % numPlayers].setText("40");
		} if (scoreGrid[11][round % numPlayers].getBackground().equals(Color.WHITE)) scoreGrid[11][round % numPlayers].setText(Integer.toString(sumOfDice));
	}
	
}
