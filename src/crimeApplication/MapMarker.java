package crimeApplication;

/**
 * Lead Author(s):
 * @author Jack Cassatt
 * 
 * References:
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * Interactive map marker for a crime
 */
public class MapMarker
{
	// MapMarker has a Crime
	private Crime crime;
	private CrimeMap map;
	private int x;
	private int y;
	
	public MapMarker(Crime crime, CrimeMap map)
	{
		this.crime = crime;
		this.map = map;
		// Calculate the x and y coordinates based on the latitude and longitude
		setImageCoordinates();
		
	}
	
	public void setImageCoordinates()
	{
		x = (int) ((crime.getLongitude() - map.getLongitudeLeft()) / (map.getLongitudeLeft() - map.getLongitudeRight()) * map.getWidth());
		y = (int) ((crime.getLatitude() - map.getLatitudeBottom() ) / (map.getLatitudeTop() - map.getLatitudeBottom()) * map.getHeight());
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Crime getCrime()
	{
		return crime;
	}

}
