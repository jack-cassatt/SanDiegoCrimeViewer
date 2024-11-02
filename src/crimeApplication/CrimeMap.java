package crimeApplication;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 * Lead Author(s):
 * @author Jack Cassatt
 * 
 * References:
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * Map to display crimes
 */
public class CrimeMap
{
	private ArrayList<MapMarker> markers;
	private double LATITUDE_TOP = 32.8666;
	private double LATITUDE_BOTTOM = 32.6625;
	private double LONGITUDE_LEFT = -117.2825;
	private double LONGITUDE_RIGHT = -116.9316;
	private int IMAGE_WIDTH = 1410;
	private int IMAGE_HEIGHT = 2044;
	
	public CrimeMap()
	{
		markers = new ArrayList<MapMarker>();
	}
	
	public void displayCrimes(ArrayList<Crime> crimes)
	{
		for (Crime crime : crimes)
		{
			MapMarker marker = new MapMarker(crime, this);
			markers.add(marker);
			plotMarker(marker);
		}
	}
	
	public void plotMarker(MapMarker marker)
	{
		// Plot the marker on the map using the x and y coordinates
	}
	
	public int getWidth()
	{
		return IMAGE_WIDTH;
	}

	
	public int getHeight()
	{
		// TODO Auto-generated method stub
		return IMAGE_HEIGHT;
	}
	
	int getLatitudeTop()
	{
		return (int) LATITUDE_TOP;
	}
	
	int getLatitudeBottom()
	{
		return (int) LATITUDE_BOTTOM;
	}
	
	int getLongitudeLeft()
	{
		return (int) LONGITUDE_LEFT;
	}
	
	int getLongitudeRight()
	{
		return (int) LONGITUDE_RIGHT;
	}
	
}
