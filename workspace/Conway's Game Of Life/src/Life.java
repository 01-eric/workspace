import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Life implements MouseListener, ActionListener, Runnable, DocumentListener {
	
	boolean[][] cells = new boolean[200][200];
	boolean[][] saved = new boolean[cells.length][cells[0].length];
	boolean running = false;
	int[][] neighbors = new int[cells.length][cells[0].length];
	int generations = 0;
	int step = 50;
	JFrame frame = new JFrame("Life Simulation");
	Panel panel = new Panel(cells);
	Container south = new Container();
	Container north = new Container();
	Container northeast = new Container();
	JButton Start = new JButton("Start");
	JButton Step = new JButton("Step");
	JButton Randomize = new JButton("Randomize");
	JButton Save = new JButton("Save");
	JButton Open = new JButton("Open Saved");
	JButton Clear = new JButton("Clear");
	JButton changeStepTime = new JButton("Change");
	JLabel Generations = new JLabel("Generation: " + generations);
	JLabel Alive = new JLabel("Cells Alive: " + alive());
	JLabel Dead = new JLabel("Cells Dead: " + (cells.length * cells[0].length - alive()));
	JLabel Time = new JLabel("Time Between Generations: " + step + " ms");
	JLabel Milleseconds = new JLabel("(ms)");
	JTextField changeTimeField = new JTextField();
	
	public Life() {
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panel.addMouseListener(this);
		northeast.setLayout(new GridLayout(1,3));
		northeast.add(changeStepTime);
		northeast.add(changeTimeField);
		northeast.add(Milleseconds);
		north.setLayout(new GridLayout(1,5));
		north.add(Generations);
		north.add(Alive);
		north.add(Dead);
		north.add(Time);
		north.add(northeast);
		south.setLayout(new GridLayout(1,5));
		south.add(Start);
		south.add(Step);
		south.add(Randomize);
		south.add(Save);
		south.add(Open);
		south.add(Clear);
		Start.addActionListener(this);
		Step.addActionListener(this);
		Randomize.addActionListener(this);
		Save.addActionListener(this);
		Open.addActionListener(this);
		Clear.addActionListener(this);
		changeStepTime.addActionListener(this);
		Open.setEnabled(false);
		changeStepTime.setEnabled(false);
		changeTimeField.getDocument().addDocumentListener(this);
		frame.add(north, BorderLayout.NORTH);
		frame.add(south, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		new Life();
	}

	public void mouseClicked(MouseEvent e) {
	} public void mousePressed(MouseEvent e) {
	} public void mouseEntered(MouseEvent e) {	
	} public void mouseExited(MouseEvent e) {
	} public void mouseReleased(MouseEvent e) {
		double width = (double)panel.getWidth() / cells[0].length;
		double height = (double)panel.getHeight() / cells.length;
		// making sure the click is within boundaries otherwise selecting the last square in bounds
		int column = Math.min(cells[0].length - 1, (int)(e.getX() / width));
		int row = Math.min(cells.length - 1, (int)(e.getY() / height));
		cells[row][column] = !cells[row][column];
		frame.repaint();
		Alive.setText("Cells Alive: " + alive());
		Dead.setText("Cells Dead: " + (cells.length * cells[0].length - alive()));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(Step)) {
			Step();
		} else if (e.getSource().equals(Start)) {
			if (!running) {
				running = true;
				Step.setEnabled(false);
				Thread t = new Thread(this);
				t.start();
				Start.setText("Stop");
			} else {
				running = false;
				Step.setEnabled(true);
				Start.setText("Start");
			}
		} else if (e.getSource().equals(Randomize)) {
			for (int rows = 0; rows < cells[0].length; rows++) {
				for (int columns = 0; columns < cells.length; columns++) {
					int n = (int)Math.round(Math.random());
					if (n == 0) {
						cells[rows][columns] = false;
					} else if (n == 1) {
						cells[rows][columns] = true;
					}
				}
			} frame.repaint();
			Alive.setText("Cells Alive: " + alive());
			Dead.setText("Cells Dead: " + (cells.length * cells[0].length - alive()));
		} else if (e.getSource().equals(Save)) {
			for (int rows = 0; rows < cells[0].length; rows++) {
				for (int columns = 0; columns < cells.length; columns++) {
					saved[rows][columns] = cells[rows][columns];
				}
			} Open.setEnabled(true);
		} else if (e.getSource().equals(Open)) {
			for (int rows = 0; rows < cells[0].length; rows++) {
				for (int columns = 0; columns < cells.length; columns++) {
					cells[rows][columns] = saved[rows][columns];
				}
			} frame.repaint();
			Alive.setText("Cells Alive: " + alive());
			Dead.setText("Cells Dead: " + (cells.length * cells[0].length - alive()));
		} else if (e.getSource().equals(Clear)) {
			running = false;
			Step.setEnabled(true);
			Start.setText("Start");
			for (int rows = 0; rows < cells[0].length; rows++) {
				for (int columns = 0; columns < cells.length; columns++) {
					cells[rows][columns] = false;
				}
			} frame.repaint();
			generations = 0;
			Generations.setText("Generations: " + generations);
			Alive.setText("Cells Alive: " + alive());
			Dead.setText("Cells Dead: " + (cells.length * cells[0].length - alive()));
		} else if (e.getSource().equals(changeStepTime)) {
			String time = changeTimeField.getText();
			boolean isNumber = true;
			for (int index = 0; index < time.length(); index++) {
				if (!Character.isDigit(time.charAt(index))) {
					isNumber = false;
				}
			} if (!isNumber) {
				JOptionPane.showMessageDialog(frame, "Please input a number.");
			} else {
				step = Integer.parseInt(time);
				Time.setText("Time Between Generations: " + step + " ms");
			} changeTimeField.setText("");
		}
	}
	
	public void Step(){
		for (int rows = 0; rows < cells[0].length; rows++) {
			for (int columns = 0; columns < cells.length; columns++) {
				neighbors[rows][columns] = 0;
				// condition and array bound checking before incrementing neighbors
				if (rows - 1 >= 0 && columns - 1 >= 0 && cells[rows - 1][columns - 1]) neighbors[rows][columns]++; // northwest cell
				if (rows - 1 >= 0 && cells[rows - 1][columns]) neighbors[rows][columns]++; // north cell
				if (rows - 1 >= 0 && columns + 1 < cells[0].length && cells[rows - 1][columns + 1]) neighbors[rows][columns]++; // northeast cell
				if (columns + 1 < cells[0].length && cells[rows][columns + 1]) neighbors[rows][columns]++; // east cell
				if (rows + 1 < cells.length && columns + 1 < cells[0].length && cells[rows + 1][columns + 1]) neighbors[rows][columns]++; // southeast cell
				if (rows + 1 < cells.length && cells[rows + 1][columns]) neighbors[rows][columns]++; // south cell
				if (rows + 1 < cells.length && columns - 1 >= 0 && cells[rows + 1][columns - 1]) neighbors[rows][columns]++; // southwest vell
				if (columns - 1 >= 0 && cells[rows][columns - 1]) neighbors[rows][columns]++; // west cell
			}
		} 
		/* checks neighbors to determine whether each cell will live, die, or revive
		 * outside of previous for loop because surroundings cannot change before counting next neighbor */
		for (int rows = 0; rows < cells[0].length; rows++) {
			for (int columns = 0; columns < cells.length; columns++) {
				if (cells[rows][columns]) {
					if (neighbors[rows][columns] == 0 || neighbors[rows][columns] == 1 || neighbors[rows][columns] >= 4) {
						cells[rows][columns] = !cells[rows][columns];
					}
				} else {
					if (neighbors[rows][columns] == 3) {
						cells[rows][columns] = !cells[rows][columns];
					}
				}
			}
		} frame.repaint();
		generations++;
		Generations.setText("Generation: " + generations);
		Alive.setText("Cells Alive: " + alive());
		Dead.setText("Cells Dead: " + (cells.length * cells[0].length - alive()));
	}
	
	public int alive() {
		int count = 0;
		for (int rows = 0; rows < cells[0].length; rows++) {
			for (int columns = 0; columns < cells.length; columns++) {
				if (cells[rows][columns]) {
					count++;
				}
			}
		} return count;
	}

	// separate method to run so actionPerformed doesn't get stuck in loop
	public void run() {
		while (running) {
			Step();
			try {
				Thread.sleep(step);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertUpdate(DocumentEvent e) {
		if (!changeTimeField.getText().equals("")) {
			changeStepTime.setEnabled(true);
		}
	}
	
	public void removeUpdate(DocumentEvent e) {
		if (changeTimeField.getText().equals("")) {
			changeStepTime.setEnabled(false);
		}
	}
	
	public void changedUpdate(DocumentEvent e) {
		if (!changeTimeField.getText().equals("")) {
			changeStepTime.setEnabled(true);
		} else {
			changeStepTime.setEnabled(false);
		}
	}
}
