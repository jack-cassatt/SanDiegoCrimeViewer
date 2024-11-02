package crimeApplication;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	private String fileName;
	
	public CSVCrimeDataParser()
	{
		this.fileName = "pd_nibrs_datasd.csv";
	}

	public ArrayList<Crime> searchCrimes(SearchCriteria searchCriteria)
	{
		ArrayList<Crime> crimes = new ArrayList<Crime>();
		BufferedReader reader = null;
		// read the file
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			// Declare a string to hold the line and skip the first line
			String line;
			// Loop through CSV file and skip the first line
			reader.readLine();
			while ((line = reader.readLine()) != null)
			{
				// Create array of string holding values in line separated by comma
				String[] fields = line.split(",");
				if (fields.length != 31)
                {
					continue;
                }
                    
				// Get Crime field variables
				
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
				String category = fields[14];
				//System.out.println(category);
				String description = fields[13];
				
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
				Crime crime = new Crime(caseID, date, category, description, areaCode,
						latitude, longitude);
				if (searchCriteria.match(crime))
				{
					crimes.add(crime);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
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
		return crimes;
	}
}
