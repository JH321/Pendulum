package pendulumsim;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;

public class Pendulum extends Canvas{
	private static final JFrame frame = new JFrame("Pendulum Simulation");
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	private static boolean initializeFrame = true;
	public static final double BOB_SIZE = 40.0;
	public static final double GRAV_CONSTANT = 1e-11;
	
	private double originX;
	private double originY;
	
	private double pendulumX;
	private double pendulumY;
	
	private double aVel;
	private double aAcc;
	
	private double theta;
	private int length;
	
	public Pendulum(double x, double y, int length, double theta)
	{
		originX = x;
		originY = y;
		this.theta = theta;
		this.length = length;
		drawPendulum();
	}
	
	private void drawPendulum()
	{
		if(initializeFrame)
		{
			frame.setSize(WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			initializeFrame = false;
		}
		
		aVel = 0.0;
		aAcc = 0.0;
			
		frame.add(this);
		
		
		
		
		while(true)
			update();
		
	}
	
	private void update()
	{
		aAcc = -GRAV_CONSTANT * Math.sin(theta);
		theta += aVel;
		aVel += aAcc;
		
		pendulumX = originX + length * Math.sin(theta);
		pendulumY = originY + length * Math.cos(theta);
		repaint();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Line2D.Double(originX, originY, pendulumX, pendulumY));
		g2.fill(new Ellipse2D.Double(pendulumX - BOB_SIZE / 2, pendulumY - BOB_SIZE / 2, BOB_SIZE, BOB_SIZE));
	}
}
