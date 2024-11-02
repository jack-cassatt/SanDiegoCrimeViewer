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
	private int x;
	private int y;
	
	public MapMarker(Crime crime)
	{
		this.crime = crime;
	}

}
