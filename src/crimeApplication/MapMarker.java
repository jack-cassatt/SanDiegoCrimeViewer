package crimeApplication;

/**
 * Lead Author(s):
 * @author Jack Cassatt
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
	// MapMarker has a CrimeMap
	private CrimeMap map;
	// x and y coordinates of the marker
	private int x;
	private int y;
	
	/**
	 * Constructor
	 * 
	 * @param crime
	 * @param map
	 */
	public MapMarker(Crime crime, CrimeMap map)
	{
		this.crime = crime;
		this.map = map;		
	}
	
	/**
	 * Purpose: Set the x and y coordinates of the marker based on the latitude and longitude of the crime
	 */
	public void setImageCoordinates()
	{		
		x = (int) (((crime.getLongitude() - map.getLongitudeLeft()) / (map.getLongitudeRight() - map.getLongitudeLeft())) * map.getWidth());
		y = (int) (((map.getLatitudeTop() - crime.getLatitude()) / (map.getLatitudeTop() - map.getLatitudeBottom())) * map.getHeight());
	}
	
	/**
	 * Purpose: Get the x coordinate
	 * @return x
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Purpose: Get the y coordinate
	 * @return y
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Purpose: Get the crime
	 * @return crime
	 */
	public Crime getCrime()
	{
		return crime;
	}
	
	/**
	 * Purpose: Check if the mouse click contains the marker
	 * @param mouseX
	 * @param mouseY
	 * @return
	 */
	public boolean contains(int mouseX, int mouseY) {
        return (mouseX >= x  && mouseX <= x + 10 && mouseY >= y && mouseY <= y + 10);
    }

}
