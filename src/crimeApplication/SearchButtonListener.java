package crimeApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

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
	CrimeSearchInterface searchInterface;
	SearchCriteria searchCriteria;
	CrimeMap map;
	
	public SearchButtonListener(CrimeSearchInterface searchInterface,
			CrimeMap map)
	{
		this.searchInterface = searchInterface;
		this.map = map;
	}
	
	public void actionPerformed(ActionEvent e)
    {
		LocalDateTime startDate = searchInterface.getStartDate();
		LocalDateTime endDate = searchInterface.getEndDate();
		List<String> selectedCategories = searchInterface.getCrimeCategories();
		List<Integer> areaCodes = searchInterface.getAreaCodes();
		SearchCriteria searchCriteria = new SearchCriteria(startDate, endDate, selectedCategories, areaCodes);
		CSVCrimeDataParser parser = new CSVCrimeDataParser();
		ArrayList<Crime> crimes = parser.searchCrimes(searchCriteria);
		map.displayCrimes(crimes);
    }
}
