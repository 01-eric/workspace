/*
 * Eric Fan
 * Traffic Simulation v 1.0
 * Created 5/10/17
 * An interactive interface simulating a road
 * with traffic on it. Traffic will intelligently
 * slow down and avoid other traffic :D
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Traffic implements ActionListener, Runnable {
	
	JFrame frame = new JFrame("Traffic Simulation");
	JButton start = new JButton("Start");
	JButton semi = new JButton("Add Semi");
	JButton suv = new JButton("Add SUV");
	JButton sportsCar = new JButton("Add Sports Car");
	JLabel throughput = new JLabel("Throughput: 0");
	Container west = new Container();
	Container south = new Container();
	Road road = new Road();
	boolean running = false;
	long startTime = 0;
	
	public Traffic() {
		frame.setVisible(true);
		frame.setSize(new Dimension(Road.LENGTH, 550));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		start.addActionListener(this);
		west.setLayout(new GridLayout(3, 1));
		south.setLayout(new GridLayout(1, 2));
		west.add(semi);
		west.add(suv);
		west.add(sportsCar);
		south.add(start);
		south.add(throughput);
		semi.addActionListener(this);
		suv.addActionListener(this);
		sportsCar.addActionListener(this);
		frame.add(west, BorderLayout.WEST);
		frame.add(south, BorderLayout.SOUTH);
		frame.add(road, BorderLayout.CENTER);
		// road.addCar(new Semi(10, 50), new SUV(10, 170), new SportsCar(10, 290)); no repaint? when referring to superclass, are child class methods visible?
	}
	
	public static void main(String[] args) {
		new Traffic();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton)e.getSource();
		
		/*
		 * start will change into stop after
		 * it is pressed.
		 */
		if (eventSource.equals(start)) {
			running = !running;
			startTime = System.currentTimeMillis();
			if (eventSource.getText().equals("Start")) eventSource.setText("Stop");
			else eventSource.setText("Start");
			new Thread(this).start();
		} 
		/*
		 * based on what other button is clicked a new
		 * vehicle of that type will be created and added
		 * to the nearest available space.
		 */
		else {
			Vehicle newVehicle;
			if (e.getSource().equals(semi)) newVehicle = new Semi(0, Road.LANE_HEIGHT / 2 - Semi.HEIGHT / 2);
			else newVehicle = e.getSource().equals(suv) ? new SUV(0, Road.LANE_HEIGHT / 2 - SUV.HEIGHT / 2) : new SportsCar(0, Road.LANE_HEIGHT / 2 - SportsCar.HEIGHT / 2);
			road.addCar(newVehicle);
			for (int x = 0; x < Road.LENGTH; x += 20) {
				for (int y = Road.LANE_HEIGHT / 2 - newVehicle.getHeight() / 2; y < Road.LANE_HEIGHT * 4; y += Road.LANE_HEIGHT) {
					newVehicle.setX(x);
					newVehicle.setY(y);
					if (!road.collision(x, y, newVehicle)) {
						frame.repaint();
						return;
					}
				}
			}
		}
		
	}

	@Override
	public void run() {
		while (running) {
			road.step();
			// updates the throughput constantly as runnable is running
			throughput.setText("Throughput: " + road.getCarCount() / (double)(System.currentTimeMillis() - startTime));
			frame.repaint();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
