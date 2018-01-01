import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	boolean [][] cells;
	double width;
	double height;
	int color = 0;
	
	public Panel(boolean[][] in){
		cells = in;
	}
	
	// drawing the cells' internal filling
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		width = (double)this.getWidth() / cells[0].length;
		height = (double)this.getHeight() / cells.length;
		// g.setColor(Color.BLACK);
		if (color % 3 == 0) {
			g.setColor(Color.cyan);
		} else if (color % 3 == 1){
			g.setColor(Color.MAGENTA);
		} else {
			g.setColor(Color.GREEN);
		} color++;
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++){
				if (cells[row][column] == true) {
					g.fillRect((int)Math.round(column * width), (int)Math.round(row * height), 
							(int)width + 1, (int)height + 1);
				}
			}
		}
		
		// drawing the grid lines
		for (int x = 0; x <= cells[0].length; x++) {
			g.drawLine((int)Math.round(x * width), 0, (int)Math.round(x * width), this.getHeight());
		} for (int y = 0; y <= cells.length; y++) {
			g.drawLine(0, (int)Math.round(y * height), this.getWidth(), (int)Math.round(y * height));
		}
	}
}
