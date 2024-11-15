package crimeApplication;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
 * Oracle Java Documentation - MouseListener
 * https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
 * 
 * Oracle Java Documentation - MouseAdapter
 * https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseAdapter.html
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * Map to display crimes
 */
public class CrimeMap extends JPanel
{
	CrimeSearchInterface frame;
	private ArrayList<MapMarker> markers;
	private ArrayList<Crime> crimes;
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
	
	/**
	 * Constructor
	 * 
	 * @param frame
	 */
	public CrimeMap(CrimeSearchInterface frame)
	{
		this.frame = frame;
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
		this.setPreferredSize(getPreferredSize(500));
		
		// Add mouse listener for marker clicks
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayCrime(e.getX(), e.getY());
            }
        });
	}
	
	/**
	 * Purpose: Get the height of the panel
	 * @return panelHeight
	 */
	public int getPanelHeight()
    {
        return panelHeight;
    }
	
	/**
	 * Purpose: Get the preferred size of the panel
	 * @param panelHeight
	 * @return Dimension
	 */
	public Dimension getPreferredSize(int panelHeight)
    {
        this.panelHeight = panelHeight;
        int panelWidth = (int) (panelHeight / IMAGE_RATIO);
        return new Dimension(panelWidth, panelHeight);
    }
	
	/**
	 * Purpose: Display crimes on the map
	 * @param crimes
	 */
	public void displayCrimes(ArrayList<Crime> crimes)
	{
		this.crimes = crimes;
		if (crimes != null)
		{			
			for (Crime crime : crimes)
			{
				MapMarker marker = new MapMarker(crime, this);
				markers.add(marker);
			}
			repaint();
		}
	}
	
	/**
	 * Purpose: Get the width of the image
	 * @return width
	 */
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

	/**
	 * Purpose: Get the height of the image
	 * @return height
	 */
	public int getImageHeight()
	{
		return (int) (getImageWidth() * IMAGE_RATIO);
	}
	
	/**
	 * Purpose: Get the top latitude
	 * @return LATITUDE_TOP
	 */
	public double getLatitudeTop()
	{
		return LATITUDE_TOP;
	}
	
	/**
	 * Purpose: Get the bottom latitude
	 * @return LATITUDE_BOTTOM
	 */
	public double getLatitudeBottom()
	{
		return LATITUDE_BOTTOM;
	}
	
	/**
	 * Purpose: Get the left longitude
	 * @return LONGITUDE_LEFT
	 */
	public double getLongitudeLeft()
	{
		return LONGITUDE_LEFT;
	}
	
	/**
	 * Purpose: Get the right longitude
	 * @return LONGITUDE_RIGHT
	 */
	public double getLongitudeRight()
	{
		return LONGITUDE_RIGHT;
	}
	
	/**
	 * Purpose: Clear the map of markers
	 */
	public void clearMap()
	{
		markers.clear();
		repaint();
	}

	/**
	 * Purpose: Zoom on the map
	 * @param zoom factor to increase/decrease the height of the panel
	 */
	public void zoom(int zoom)
	{
		// Do not zoom out past the original size of the map
		if (panelHeight + zoom >= 500)
		{
			// Increase the height of the panel
			panelHeight += zoom;
			// Set the preferred size of the panel based on aspect ratio
			this.setPreferredSize(getPreferredSize(panelHeight));
			// Revalidate the panel and repaint
			revalidate();
			clearMap();
			displayCrimes(crimes);
		}	
	}
	
	/**
	 * Purpose: Display the crime information
	 * 
	 * @param mouseX
	 * @param mouseY
	 */
	protected void displayCrime(int mouseX, int mouseY) {
		// Check if a marker was clicked
        for (MapMarker marker : markers) 
        {
        	// If the marker contains the mouse click, display the crime information
            if (marker.contains(mouseX, mouseY)) 
            {
                // Action when a marker is clicked
            	frame.displayCrime(marker.getCrime());
            }
        }
    }
	
	/**
	 * Purpose: Paint the map
	 * 
	 * @param g Graphics object
	 */
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
}
