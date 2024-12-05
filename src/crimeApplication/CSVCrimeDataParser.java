package crimeApplication;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

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
 * Accesses a CSV file and parses the data into Crime objects using search criteria
 */
public class CSVCrimeDataParser
{
	// String to hold the file name
	private String fileName;
	
	/**
	 * Constructor
	 */
	public CSVCrimeDataParser()
	{
		// Set the file name
		this.fileName = "pd_nibrs_datasd.csv";
	}

	/**
	 * Purpose: Search for crimes based on search criteria
	 * 
	 * @param searchCriteria
	 * @return ArrayList<Crime>
	 */
	public ArrayList<Crime> searchCrimes(SearchCriteria searchCriteria)
	{
		// Create an array list to hold the crimes
		ArrayList<Crime> crimes = new ArrayList<Crime>();
		// Create a buffered reader
		BufferedReader reader = null;
		
		// Try to read the file
		try
		{
			// Create a buffered reader to read
			reader = new BufferedReader(new FileReader(fileName));
			// Declare a string to hold the line and skip the first line
			String line;
			
			// Skip the first line and loop through CSV file
			reader.readLine();
			while ((line = reader.readLine()) != null)
			{				
				// Create array of string holding values in line separated by comma
				String[] fields = line.split(",");
				
				// Check if the line is valid
				if (fields.length != 31)
                {
					continue;
                }
				
				// Try to parse the caseID
				int caseID;
				try
				{
					caseID = Integer.parseInt(fields[2]);
				}
				catch (NumberFormatException e)
				{
					caseID = -1;
				}
				
				// Try to parse the date
				LocalDateTime date;
				try
				{
					String[] dateFields = fields[3].split("/");
					date = LocalDateTime.of(
							Integer.parseInt(dateFields[2].substring(0, 4)),
							Integer.parseInt(dateFields[0]),
							Integer.parseInt(dateFields[1]), 0, 0, 0);
				}
				catch (Exception e)
				{
					date = null;
					continue;
				}
				
				// Get the crime category and description
				String category = fields[14];
				String description = fields[13];
				
				// Try to parse the area code
				int areaCode;
				try
                {
					areaCode = Integer.parseInt(fields[25]);
                }
                catch (NumberFormatException e)
                {
                	areaCode = -1;
                }
				
				// Try to parse the latitude and longitude
				double latitude;
				double longitude;
				try
				{
					latitude = Double.parseDouble(fields[29]);
					longitude = Double.parseDouble(fields[30]);
				}
				catch (NumberFormatException e)
				{
					latitude = -1;
					longitude = -1;
					continue;
				}
				
				// Create a new crime object
				Crime crime = new Crime(line, caseID, date, category, description, areaCode,
						latitude, longitude);
				
				// Check if the crime matches the search criteria
				if (searchCriteria.match(crime))
				{
					// Add the crime to the list of search results
					crimes.add(crime);
				}
			}
		}
		// Catch exceptions
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
		// Close the reader
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		// Return the list of crimes
		return crimes;
	}
}
