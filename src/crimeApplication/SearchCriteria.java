package crimeApplication;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Lead Author(s):
 * @author Jack Cassatt
 * 
 * References:
 * Java Read CSV File - Bro Code
 * https://www.youtube.com/watch?v=zKDmzKaAQro
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * Parses User input into search criteria for the CSVCrimeDataParser
 * and matches the criteria to the crime data
 */
public class SearchCriteria
{
	private List<String> categories;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private List<Integer> areaCodes;
	
	public SearchCriteria(LocalDateTime startDate, LocalDateTime endDate,
			List<String> categories, List<Integer> areaCode)
	{
		this.categories = categories;
		this.startDate = startDate;
		this.endDate = endDate;
		this.areaCodes = areaCode;
	}
	
	/**
	 * Purpose: Match the search criteria to the crime data
	 * 
	 * @param crime
	 * @return boolean
	 */
	public boolean match(Crime crime)
	{
		// Check if the crime date is within the start and end date
		if (crime.getDate().isBefore(startDate)
				|| crime.getDate().isAfter(endDate))
		{
			return false;
		}
		
		if (areaCodes.contains(0))
		{
			System.out.println("Area contain all area codes");
		}
		
		// Check if the crime area code is in the list of area codes
		if (!areaCodes.contains(crime.getAreaCode()) && !areaCodes.contains(0))
		{
			
			return false;
		}
		
		// Check if the crime category is in the list of categories
		if (!categories.contains("All Crimes"))
		{
			for (String category : categories)
			{
				if (category.contains(crime.getCategory()))
				{
					System.out.println(crime.toString());
					return true;
				}
			}
			//System.out.println("Fail: " + crime.getCategory() + " is not in the list of categories");
			//System.out.println(crime.toString());
			return false;
		}
		else
		{
			return true;
		}
	}
	
}
