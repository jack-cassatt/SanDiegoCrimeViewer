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
			while ((line = reader.readLine()) != null)
			{
				// Read line
				line = reader.readLine();
				// Create array of string holding values in line separated by comma
				String[] fields = line.split(",");
				// Get Crime field variables
				int caseID = Integer.parseInt(fields[2]);
				LocalDateTime date = LocalDateTime.parse(fields[3],
						DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
				String category = fields[10];
				String description = fields[13];
				int areaCode = Integer.parseInt(fields[24]);
				double latitude = Double.parseDouble(fields[28]);
				double longitude = Double.parseDouble(fields[29]);
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
