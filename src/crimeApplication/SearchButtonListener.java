package crimeApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Lead Author(s):
 * @author Jack Cassatt
 * 
 * References:
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * Perform the search when the search button is clicked
 */
public class SearchButtonListener implements ActionListener
{
	// Instance variables
	CrimeSearchInterface searchInterface;
	CrimeMap map;
	
	/**
	 * Constructor
	 * 
	 * @param searchInterface
	 * @param map
	 */
	public SearchButtonListener(CrimeSearchInterface searchInterface,
			CrimeMap map)
	{
		this.searchInterface = searchInterface;
		this.map = map;
	}
	
	/**
	 * Purpose: Perform the search when the search button is clicked
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e)
    {
		// Clear the map
		map.clearMap();
		searchInterface.displayCrimeDetails(null);
		
		// Get the search criteria and create a search criteria object
		LocalDateTime startDate = searchInterface.getStartDate();
		LocalDateTime endDate = searchInterface.getEndDate();
		// Check if the date range is valid
		if ((startDate == null || endDate == null) || startDate.isAfter(endDate) || startDate.isBefore(LocalDateTime.of(2020, 1, 1, 0, 0)) || endDate.isAfter(LocalDateTime.now()))
		{
			JOptionPane.showMessageDialog(searchInterface, "Invalid date range");
			return;
		}
		List<String> selectedCategories = searchInterface.getCrimeCategories();
		List<Integer> areaCodes = searchInterface.getAreaCodes();
		SearchCriteria searchCriteria = new SearchCriteria(startDate, endDate, selectedCategories, areaCodes);
		
		// Create a parser and search for crimes
		CSVCrimeDataParser parser = new CSVCrimeDataParser();
		ArrayList<Crime> crimes = parser.searchCrimes(searchCriteria);
		
		// Display the crimes on the map
		map.displayCrimes(crimes);
    }
}
