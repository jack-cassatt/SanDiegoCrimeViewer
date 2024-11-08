package crimeApplication;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lead Author(s):
 * @author Jack Cassatt
 * 
 * References:
 * Oracle Java Documentation - Graphics
 * https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
 * 
 * Oracle Java Documentation - JPanel (paintComponent)
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#paintComponent-java.awt.Graphics-
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * Map to display crimes
 */
public class CrimeMap extends JPanel
{
	private ArrayList<MapMarker> markers;
	private double LATITUDE_TOP = 32.8666;
	private double LATITUDE_BOTTOM = 32.6625;
	private double LONGITUDE_LEFT = -117.2825;
	private double LONGITUDE_RIGHT = -116.9316;
	private int IMAGE_WIDTH = 2044;
	private int IMAGE_HEIGHT = 1410;
	private double IMAGE_RATIO = IMAGE_HEIGHT / (double) IMAGE_WIDTH;
	private int panelHeight;
	private Image mapImage;
	JButton zoomInButton;
	JButton zoomOutButton;
	
	public CrimeMap()
	{
		this.setLayout(new BorderLayout());
		markers = new ArrayList<MapMarker>();
		setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
	    // Load the map image
		try {
            // Load the image as a BufferedImage
            mapImage = ImageIO.read(new File("map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		this.setPreferredSize(getPreferredSize(600));
	}
	
	public Dimension getPreferredSize(int panelHeight)
    {
        this.panelHeight = panelHeight;
        int panelWidth = (int) (panelHeight / IMAGE_RATIO);
        return new Dimension(panelWidth, panelHeight);
    }
	
	public void displayCrimes(ArrayList<Crime> crimes)
	{
		for (Crime crime : crimes)
		{
			MapMarker marker = new MapMarker(crime, this);
			markers.add(marker);
		}
		repaint();
	}
	
	public int getImageWidth()
	{
		double panelRatio = this.getHeight() / (double) this.getWidth();
		if (panelRatio > IMAGE_RATIO)
		{
			return this.getWidth();
		}
		else
		{
			return (int) (this.getWidth() * IMAGE_RATIO);
		}
	}

	
	public int getImageHeight()
	{
		return (int) (getImageWidth() * IMAGE_RATIO);
	}
	
	public double getLatitudeTop()
	{
		return LATITUDE_TOP;
	}
	
	public double getLatitudeBottom()
	{
		return LATITUDE_BOTTOM;
	}
	
	public double getLongitudeLeft()
	{
		return LONGITUDE_LEFT;
	}
	
	public double getLongitudeRight()
	{
		return LONGITUDE_RIGHT;
	}
	
	public void clearMap()
	{
		markers.clear();
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		// Draw the map image
        super.paintComponent(g);
        
        // Draw the image with calculated dimensions
        g.drawImage(mapImage, 0, 0, this.getWidth(), this.getHeight(), this);
        // Draw all markers
		if (markers != null)
		{
	        g.setColor(Color.RED);
	        for (MapMarker marker : markers) 
	        {
	        	g.fillOval(marker.getX(), marker.getY(), 10, 10);
	        }
        }
    }
	
	public void zoomIn()
	{
		panelHeight += 50;
		this.setPreferredSize(getPreferredSize(panelHeight));
		revalidate();
	}
	
	public void zoomOut()
	{
		panelHeight -= 50;
		this.setPreferredSize(getPreferredSize(panelHeight));
		revalidate();
	}
}
