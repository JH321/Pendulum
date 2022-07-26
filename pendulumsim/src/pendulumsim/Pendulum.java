package pendulumsim;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;

/**
 * This file holds the Pendulum class, which holds all the operations necessary
 * to draw and update the pendulum to the screen.
 * @author John Ho
 *
 */
public class Pendulum extends Canvas{
	
	private static final JFrame frame = new JFrame("Pendulum Simulation"); //frame on which everythings drawn
	public static final int WIDTH = 600; //width of frame
	public static final int HEIGHT = 400; //height of frame
	
	private static boolean initializeFrame = true; //determines whether to create the frame
	public static final double BOB_SIZE = 40.0; //Bob radius
	public static final double GRAV_CONSTANT = 1e-11; //arbitrary gravitational acceleration value (not si unit)
	
	private double originX; //starting x position of pendulum bob
	private double originY; //starting y position of pendulum bob
	
	private double pendulumX; //actual x position of pendulum bob on the screen
	private double pendulumY; //actual y position of pendulum bob on the screen
	
	private double aVel; //angular velocity
	private double aAcc; //angular acceleration
	
	private double theta;
	private int length;
	
	/**
	 * Constructs a Pendulum object that initializes a frame on which the pendulum is drawn.
	 * @param x the initial x position of the bob.
	 * @param y the initial y position of the bob.
	 * @param length the length of the string holding the bob.
	 * @param theta the initial angle of the pendulum
	 */
	public Pendulum(double x, double y, int length, double theta)
	{
		originX = x;
		originY = y;
		this.theta = theta;
		this.length = length;
		drawPendulum();
	}
	
	/**
	 * Initializes the JFrame and begins drawing and updating pendulum on the screen.
	 * 
	 */
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
	
	/**
	 * Updates the pendulum location on the frame.
	 */
	private void update()
	{
		aAcc = -GRAV_CONSTANT * Math.sin(theta);
		theta += aVel;
		aVel += aAcc;
		
		pendulumX = originX + length * Math.sin(theta);
		pendulumY = originY + length * Math.cos(theta);
		repaint();
	}
	
	/**
	 * Contains instructions for painting onto the canvas. Draws the pendulum.
	 * @param g graphics context.
	 */
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Line2D.Double(originX, originY, pendulumX, pendulumY));
		g2.fill(new Ellipse2D.Double(pendulumX - BOB_SIZE / 2, pendulumY - BOB_SIZE / 2, BOB_SIZE, BOB_SIZE));
	}
}
