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
		mapImage = new Image("Map.png");
	}
	
	public void displayCrimes(ArrayList<Crime> crimes)
	{
		for (Crime crime : crimes)
		{
			MapMarker marker = new MapMarker(crime);
			markers.add(marker);
			plotMarker(marker);
		}
	}
	
	public void plotMarker(MapMarker marker)
	{
		int x = marker.getX();
		int y = marker.getY();
	}

	
	public int getWidth(ImageObserver observer)
	{
		return IMAGE_WIDTH;
	}

	
	public int getHeight(ImageObserver observer)
	{
		// TODO Auto-generated method stub
		return IMAGE_HEIGHT;
	}
}
