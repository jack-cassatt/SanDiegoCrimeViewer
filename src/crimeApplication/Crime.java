package crimeApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	private int caseID;
	private LocalDateTime date;
	private String category;
	private String description;
	private int areaCode;
	private double latitude;
	private double longitude;
	
	// default constructor
	public Crime()
    {
        this.caseID = 0;
        this.date = null;
        this.category = null;
        this.description = null;
        this.latitude = 0;
        this.longitude = 0;
    }
	
	// Constructor with parameters
	public Crime(int caseID, LocalDateTime date, String category, String description,
			int areaCode, double latitude, double longitude)
	{
		this.caseID = caseID;
		this.date = date;
		this.category = category;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Purpose: Set the caseID
	 * @param caseID
	 */
	public void setCaseID(int caseID)
	{
		this.caseID = caseID;
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
     * Purpose: Set the date and time
     * @param strDate
     */
	public void setDateAndTime(String strDate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		this.date = LocalDateTime.parse(strDate, formatter);
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
	 * Purpose: Set the category
	 * @param category
	 */
	public void setCategory(String category)
	{
		this.category = category;
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
	 * Purpose: Set the description
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
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
	 * Purpose: Set the area code
	 * @param areaCode
	 */
	public void setAreaCode(int areaCode)
	{
		this.areaCode = areaCode;
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
	 * Purpose: Set the latitude
	 * @param latitude
	 */
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
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
     * Purpose: Set the longitude
     * @param longitude
     */
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
	
	/**
	 * Purpose: Get the longitude
	 * @return longitude
	 */
	public double getLongitude()
	{
		return longitude;
	}
	
	
	
}
