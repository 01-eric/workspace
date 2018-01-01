import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class TriangleSolverGUI implements ActionListener, DocumentListener {
	
	JFrame appWindow = new JFrame("Triangle Calculator");
	Container buttonList = new Container();
	Container typeField = new Container();
	JButton[] buttons = new JButton[5];
	JTextField[] txt = new JTextField[3];
	JTextArea display = new JTextArea("  No data to display.\n\n\n");
	
	public TriangleSolverGUI() {
		appWindow.setVisible(true);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.setSize(600, 400);
		appWindow.setLayout(new BorderLayout());
		buttons[0] = new JButton("SSS");
		buttons[1] = new JButton("SAS");
		buttons[2] = new JButton("ASS");
		buttons[3] = new JButton("ASA");
		buttons[4] = new JButton("AAS");
		buttonList.setLayout(new GridLayout(5, 1));
		typeField.setLayout(new GridLayout(1, 3));
		for (int i = 0; i < buttons.length; i++) {
			buttonList.add(buttons[i]);
			buttons[i].addActionListener(this);
			buttons[i].setEnabled(false);
			buttons[i].setBackground(new Color(238, 238, 238));
		} for (int i = 0; i < txt.length; i++) {
			txt[i] = new JTextField();
			txt[i].getDocument().addDocumentListener(this);
			typeField.add(txt[i]);
		} appWindow.add(typeField, BorderLayout.NORTH);
		appWindow.add(buttonList, BorderLayout.CENTER);
		appWindow.add(display, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		new TriangleSolverGUI();
	}

	public void insertUpdate(DocumentEvent e) {
		if (isNumber(txt[0].getText()) && isNumber(txt[1].getText()) && isNumber(txt[2].getText())) {
			for (JButton b: buttons) b.setEnabled(true);
		}
	}
	
	public void removeUpdate(DocumentEvent e) {
		if (!isNumber(txt[0].getText()) || !isNumber(txt[1].getText()) || !isNumber(txt[2].getText())) {
			for (JButton b: buttons) b.setEnabled(false);
		}
	}
	
	public void changedUpdate(DocumentEvent e) {
		if (isNumber(txt[0].getText()) && isNumber(txt[1].getText()) && isNumber(txt[2].getText())) {
			for (JButton b: buttons) b.setEnabled(true);
		} else if (!isNumber(txt[0].getText()) || !isNumber(txt[1].getText()) || !isNumber(txt[2].getText())) {
			for (JButton b: buttons) b.setEnabled(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttons[0])) {
			display.setText((new Triangle(Double.parseDouble(txt[0].getText()), Double.parseDouble(txt[1].getText()), Double.parseDouble(txt[2].getText()), "SSS").getData()));
			for (JButton b: buttons) b.setBackground(new Color(238, 238, 238));
			buttons[0].setBackground(Color.YELLOW);
		} else if (e.getSource().equals(buttons[1])) {
			display.setText((new Triangle(Double.parseDouble(txt[0].getText()), Double.parseDouble(txt[1].getText()), Double.parseDouble(txt[2].getText()), "SAS").getData()));
			for (JButton b: buttons) b.setBackground(new Color(238, 238, 238));
			buttons[1].setBackground(Color.YELLOW);
		} else if (e.getSource().equals(buttons[2])) {
			display.setText((new Triangle(Double.parseDouble(txt[0].getText()), Double.parseDouble(txt[1].getText()), Double.parseDouble(txt[2].getText()), "ASS").getData()));
			for (JButton b: buttons) b.setBackground(new Color(238, 238, 238));
			buttons[2].setBackground(Color.YELLOW);
		} else if (e.getSource().equals(buttons[3])) {
			display.setText((new Triangle(Double.parseDouble(txt[0].getText()), Double.parseDouble(txt[1].getText()), Double.parseDouble(txt[2].getText()), "ASA").getData()));
			for (JButton b: buttons) b.setBackground(new Color(238, 238, 238));
			buttons[3].setBackground(Color.YELLOW);
		} else if (e.getSource().equals(buttons[4])) {
			display.setText((new Triangle(Double.parseDouble(txt[0].getText()), Double.parseDouble(txt[1].getText()), Double.parseDouble(txt[2].getText()), "AAS").getData()));
			for (JButton b: buttons) b.setBackground(new Color(238, 238, 238));
			buttons[4].setBackground(Color.YELLOW);
		}
	}
	
	public boolean isNumber(String s) {
		if (s.length() == 0 || s.equals(".")) return false;
		for (int i = 0; i < s.length(); i++) if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.') return false;
		return true;
	}
}

class Triangle {
	
	double largeAngle;
	double medAngle;
	double smallAngle;
	double longSide;
	double medSide;
	double shortSide;
	double area;
	String data = "";
	
	String getData() {
		return data;
	}
	
	Triangle(double data1, double data2, double data3, String modifier) {
		if (modifier.equals("SSS")) {
			double[] sides = {data1, data2, data3};
			Arrays.sort(sides);
			longSide = sides[2];
			medSide = sides[1];
			shortSide = sides[0];
			smallAngle = Math.toDegrees(Math.acos((Math.pow(shortSide, 2) - Math.pow(medSide, 2) - Math.pow(longSide, 2)) / (-2 * medSide * longSide)));
			largeAngle = Math.toDegrees(Math.acos((Math.pow(longSide, 2) - Math.pow(medSide, 2) - Math.pow(shortSide, 2)) / (-2 * medSide * shortSide)));
			medAngle = 180 - largeAngle - smallAngle;
		} else if (modifier.equals("SAS")) {
			double side, angle1, angle2;
			side = Math.sqrt(Math.pow(data1, 2) + Math.pow(data3, 2) - 2 * data1 * data3 * Math.cos(Math.toRadians(data2)));
			angle1 = Math.toDegrees(Math.acos((Math.pow(data1, 2) - Math.pow(data3, 2) - Math.pow(side, 2)) / (-2 * data3 * side)));
			angle2 = 180 - data2 - angle1;
			double[] sides = {data1, data3, side};
			Arrays.sort(sides);
			longSide = sides[2];
			medSide = sides[1];
			shortSide = sides[0];
			double[] angles = {data2, angle1, angle2};
			Arrays.sort(angles);
			largeAngle = angles[2];
			medAngle = angles[1];
			smallAngle = angles[0];
		} else if (modifier.equals("ASS")) {
			double angle1, angle2, side;
			double height = Math.sin(Math.toRadians(data1)) * data2;
			angle1 = Math.toDegrees(Math.asin(data2 * Math.sin(Math.toRadians(data1)) / data3));
			angle2 = 180 - angle1 - data1;
			side = Math.sqrt(Math.pow(data2, 2) + Math.pow(data3, 2) - 2 * data2 * data3 * Math.cos(Math.toRadians(angle2)));
			if (data1 < 90 && height < data3 && data2 > data3) {
				double ambiguousAngle1, ambiguousAngle2, ambiguousSide;
				ambiguousAngle1 = 180 - angle1;
				ambiguousAngle2 = 180 - ambiguousAngle1 - data1;
				ambiguousSide = Math.sqrt(Math.pow(data2, 2) + Math.pow(data3, 2) - 2 * data2 * data3 * Math.cos(Math.toRadians(ambiguousAngle2)));
				double[] angles = {data1, ambiguousAngle1, ambiguousAngle2};
				Arrays.sort(angles);
				largeAngle = angles[2];
				medAngle = angles[1];
				smallAngle = angles[0];
				double[] sides = {data2, data3, ambiguousSide};
				Arrays.sort(sides);
				longSide = sides[2];
				medSide = sides[1];
				shortSide = sides[0];
				area = 0.5 * shortSide * medSide * Math.sin(Math.toRadians(largeAngle));
				data = "TRIANGLE 1:\nSides: " + longSide + ", " + medSide + ", " + shortSide + "\nAngles: " 
				+ largeAngle + ", " + medAngle + ", " + smallAngle + "\nArea: " + area + "\nTRIANGLE 2:\n";
			} double[] angles = {data1, angle1, angle2};
			Arrays.sort(angles);
			largeAngle = angles[2];
			medAngle = angles[1];
			smallAngle = angles[0];
			double[] sides = {data2, data3, side};
			Arrays.sort(sides);
			longSide = sides[2];
			medSide = sides[1];
			shortSide = sides[0];
			area = 0.5 * shortSide * medSide * Math.sin(Math.toRadians(largeAngle));
			data += "Sides: " + longSide + ", " + medSide + ", " + shortSide +
			"\nAngles: " + largeAngle + ", " + medAngle + ", " + smallAngle + "\nArea: " + area;
		} else if (modifier.equals("AAS")) {
			double side1, side2, angle = 180 - data1 - data2;
			side1 = Math.sin(Math.toRadians(data2)) * data3 / Math.sin(Math.toRadians(data1));
			side2 = Math.sin(Math.toRadians(angle)) * side1 / Math.sin(Math.toRadians(data2));
			double[] angles = {data1, data2, angle};
			Arrays.sort(angles);
			largeAngle = angles[2];
			medAngle = angles[1];
			smallAngle = angles[0];
			double[] sides = {data3, side1, side2};
			Arrays.sort(sides);
			longSide = sides[2];
			medSide = sides[1];
			shortSide = sides[0];
		} else if (modifier.equals("ASA")) {
			double side1, side2, angle = 180 - data1 - data3;
			side1 = Math.sin(Math.toRadians(data3)) * data2 / Math.sin(Math.toRadians(angle));
			side2 = Math.sin(Math.toRadians(data1)) * side1 / Math.sin(Math.toRadians(data3));
			double[] angles = {data1, data3, angle};
			Arrays.sort(angles);
			largeAngle = angles[2];
			medAngle = angles[1];
			smallAngle = angles[0];
			double[] sides = {data2, side1, side2};
			Arrays.sort(sides);
			longSide = sides[2];
			medSide = sides[1];
			shortSide = sides[0];
		} area = 0.5 * shortSide * medSide * Math.sin(Math.toRadians(largeAngle));
		if (!modifier.equals("ASS")) data = "Sides: " + longSide + ", " + medSide + ", " + shortSide +
		"\nAngles: " + largeAngle + ", " + medAngle + ", " + smallAngle + "\nArea: " + area;
		if (!(largeAngle > 0) || !(medAngle > 0) || !(smallAngle > 0) || !(longSide > 0) || !(medSide > 0) || !(shortSide > 0)) data = "Triangle cannot be formed.";
	}
	
}
