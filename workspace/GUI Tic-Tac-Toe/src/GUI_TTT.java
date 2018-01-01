/*
 * Eric Fan
 * GUI Tic-Tac-Toe v 1.0
 * 10/08/2016
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI_TTT implements ActionListener {
	int turn = 1;
	int[][] board = new int[3][3];
	final int BLANK = 0;
    final int X = 1;
    final int O = 2;
    int x_score = 0;
    int o_score = 0;
    String x_name = "Player X";
    String o_name = "Player O";
    String x_old_name;
    String o_old_name;
	JFrame frame = new JFrame();
	JButton[][] buttons = new JButton[3][3];
	JButton x_change_name = new JButton("Change Player X's name");
	JButton o_change_name = new JButton("Change Player O's name");
	JTextField x_change_field = new JTextField();
	JTextField o_change_field = new JTextField();
	JLabel x_label = new JLabel(x_name + ": " + x_score + " game(s) won");
	JLabel o_label = new JLabel(o_name + ": " + o_score + " game(s) won");
	Container grid = new Container();
	Container north = new Container();
	// setting conditions for the JFrame, giving all buttons a function
	public GUI_TTT() {
		grid.setLayout(new GridLayout(3,3));
		frame.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());;
		frame.setLayout(new BorderLayout());
		for (int rows = 0; rows < buttons.length; rows++ ){
			for (int columns = 0; columns < buttons[0].length; columns++) {
				buttons[rows][columns] = new JButton((""));
				grid.add(buttons[rows][columns]);
				buttons[rows][columns].addActionListener(this);
			}
		} frame.add(grid, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		north.setLayout(new GridLayout(3,2));
		north.add(x_label);
		north.add(o_label);
		north.add(x_change_field);
		north.add(o_change_field);
		north.add(x_change_name);
		north.add(o_change_name);
		x_change_name.addActionListener(this);
		o_change_name.addActionListener(this);
		frame.add(north, BorderLayout.NORTH);
	}
	// main method
	public static void main(String[] args){
		new GUI_TTT();
	}
	// used in actionPerformed to check for wins
	boolean win(int xo) {
        for (int all_rows = 0; all_rows < 3; all_rows++) if (board[all_rows][0] == xo && board[all_rows][1] == xo && board[all_rows][2] == xo) return true;
        for (int all_columns = 0; all_columns < 3; all_columns++) if (board[0][all_columns] == xo && board[1][all_columns] == xo && board[2][all_columns] == xo) return true;
        if ((board[0][0] == xo && board[1][1] == xo && board[2][2] == xo) || (board[2][0] == xo && board[1][1] == xo && board[0][2] == xo)) return true;
        return false;
    }
	// use in actionPerformed to check for ties
	boolean tie() {
		int space_count = 0;
		for (int rows = 0; rows < 3; rows++) for (int columns = 0; columns < 3; columns++) space_count += board[rows][columns] != BLANK ? 1 : 0;
		return space_count == 9;
	}
	// in the case that a button in JFrame frame is clicked, this method will run
	public void actionPerformed(ActionEvent event) {
		JButton current;
		boolean checkgrid = false;
		/* first check to see if any of the 9 buttons on the game board were clicked
		 * if a click is found, it'll place a move on that button and disable the button
		 * set checkgrid to true so the name change condition won't run */
		for (int rows = 0; rows < buttons.length; rows++) {
			for (int columns = 0; columns < buttons[0].length; columns++){
				if(event.getSource().equals(buttons[rows][columns])){
					current = buttons[rows][columns];
					if (board[rows][columns] == BLANK){
						if (turn % 2 == 1) {
							current.setText("X");
							current.setEnabled(false);
							board[rows][columns] = X;
						} else {
							current.setText("O");
							current.setEnabled(false);
							board[rows][columns] = O;
						} checkgrid = true;
						turn++;
						if (win(X)) {
							x_score++;
							x_label.setText(x_name + ": " + x_score + " game(s) won");
							JOptionPane.showMessageDialog(frame, "Player X Wins!");
							reset_board();
						} else if (win(O)) {
							o_score++;
							o_label.setText(o_name + ": " + o_score + " game(s) won");
							JOptionPane.showMessageDialog(frame, "Player O Wins!");
							reset_board();
						} else if (tie()) {
							JOptionPane.showMessageDialog(frame, "Draw.");
							reset_board();
						}
					}
				}
			}
		} 
		// if the click wasn't from any of the 9 buttons on the game board, it was a name change
		if (checkgrid == false) {
			if (event.getSource().equals(x_change_name)) {
				// check to see if no text was entered. If text was entered, update name
				if (x_change_field.getText().equals("") == false) {
					x_old_name = x_name;
					x_name = x_change_field.getText();
					x_label.setText(x_name + ": " + x_score + " game(s) won");
					x_change_field.setText("");
					JOptionPane.showMessageDialog(frame, x_old_name + "'s name has been changed to: " + x_name);
				} else {
					JOptionPane.showMessageDialog(frame, "No text has been detected!");
				}
			} else if (event.getSource().equals(o_change_name)) {
				// check to see if no text was entered. If text was entered, update name
				if (o_change_field.getText().equals("") == false) {
					o_old_name = o_name;
					o_name = o_change_field.getText();
					o_label.setText(o_name + ": " + o_score + " game(s) won");
					o_change_field.setText("");
					JOptionPane.showMessageDialog(frame, o_old_name + "'s name has been changed to: " + o_name);
				} else {
					JOptionPane.showMessageDialog(frame, "No text has been detected!");
				}			
			}
		}
	}
	// runs inside actionPerformed at the end of each game, resets and enables all buttons on game board
	public void reset_board(){
		for (int reset_rows = 0; reset_rows < board.length; reset_rows++) {
			for (int reset_columns = 0; reset_columns < board[0].length; reset_columns++) {
				buttons[reset_rows][reset_columns].setText("");
				board[reset_rows][reset_columns] = BLANK;
				buttons[reset_rows][reset_columns].setEnabled(true);
				turn = 1;
			}
		}
	}
}


