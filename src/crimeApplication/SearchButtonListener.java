package crimeApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
		String crimeCategories = searchInterface.getCrimeCategories();
		SearchCriteria searchCriteria = new SearchCriteria(startDate, endDate, crimeCategories);
		ArrayList<Crime> crimes = CSVCrimeDataParser.getCrimes(searchCriteria);
		map.displayCrimes(crimes);
    }
}
