package crimeApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Crime
{
	private int caseID;
	private LocalDateTime date;
	private String category;
	private String description;
	private int areaCode;
	private double latitude;
	private double longitude;
	
	public Crime()
    {
        this.caseID = 0;
        this.date = null;
        this.category = null;
        this.description = null;
        this.latitude = 0;
        this.longitude = 0;
    }
	
	public Crime(int caseID, LocalDateTime date, String category,
			String description, double latitude, double longitude)
	{
		this.caseID = caseID;
		this.date = date;
		this.category = category;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setCaseID(int caseID)
	{
		this.caseID = caseID;
	}

	public int getCaseID()
	{
		return caseID;
	}
	
	public void setDateAndTime(String strDate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		this.date = LocalDateTime.parse(strDate, formatter);
	}
	
	public LocalDateTime getDate()
	{
		return date;
	}
	
}
