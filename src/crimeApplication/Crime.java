package crimeApplication;

import java.time.LocalDateTime;

/**
 * Lead Author(s):
 * @author Jack Cassatt
 * 
 * References:
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * Stores the data for a crime
 */
public class Crime
{
	// instance variables
	private String dataLine;
	private int caseID;
	private LocalDateTime date;
	private String category;
	private String description;
	private int areaCode;
	private double latitude;
	private double longitude;
	
	/**
	 * Constructor
	 * 
	 * @param dataLine
	 * @param caseID
	 * @param date
	 * @param category
	 * @param description
	 * @param areaCode
	 * @param latitude
	 * @param longitude
	 */
	public Crime(String dataLine, int caseID, LocalDateTime date, String category, String description,
			int areaCode, double latitude, double longitude)
	{
		this.dataLine = dataLine;
		this.caseID = caseID;
		this.date = date;
		this.category = category;
		this.description = description;
		this.areaCode = areaCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Purpose: Get the caseID
	 * @return caseID
	 */
	public int getCaseID()
	{
		return caseID;
	}
	
	/**
	 * Purpose: Get the date and time
	 * @return date
	 */
	public LocalDateTime getDate()
	{
		return date;
	}
	
	/**
	 * Purpose: Get the category
	 * @return category
	 */
	public String getCategory()
	{
		return category;
	}
	
	/**
	 * Purpose: Get the description
	 * @return description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Purpose: Get the area code
	 * @return areaCode
	 */
	public int getAreaCode()
	{
		return areaCode;
	}
	
	/**
	 * Purpose: Get the latitude
	 * @return latitude
	 */
	public double getLatitude()
	{
		return latitude;
	}
	
	/**
	 * Purpose: Get the longitude
	 * @return longitude
	 */
	public double getLongitude()
	{
		return longitude;
	}
	
	/**
	 * Purpose: Get the data line
	 * 
	 * @return dataLine
	 */
	public String getDataLine()
	{
		return dataLine;
	}
	
	@Override
	public String toString()
	{
		return "Case ID: " + caseID + "; Date: " + date + "; Category: "
				+ category + "; Description: " + description + "; Area Code: "
				+ areaCode + "; Latitude: " + latitude + "; Longitude: "
				+ longitude;
	}
	
}
