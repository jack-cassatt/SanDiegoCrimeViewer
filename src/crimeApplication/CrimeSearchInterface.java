package crimeApplication;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
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
 * The primary interface for searching for crimes
 */
public class CrimeSearchInterface
{
	CrimeMap crimeMap;
	JButton searchButton;
	JTextField startDateField;
	JTextField endDateField;
	JCheckBox[] crimeCategoryCheckBoxes =
	{
		new JCheckBox("All Crimes"),
		new JCheckBox("Aggravated Assault"), 
		new JCheckBox("Alcohol Violations"),
		new JCheckBox("Animal Cruelty"), 
		new JCheckBox("Burglary/Breaking & Entering"),
		new JCheckBox("Curfew/Loitering"),
		new JCheckBox("Disorderly Conduct"),
		new JCheckBox("Drug Violations"),
		new JCheckBox("Fraud/Counterfeiting"),
		new JCheckBox("Gambling Violations"),
		new JCheckBox("Homicide"),
		new JCheckBox("Kidnapping/Abduction"),
		new JCheckBox("Larceny"),
		new JCheckBox("Vehicle Theft"),
		new JCheckBox("Pornography/Obscene Material"),
		new JCheckBox("Prostitution"),
		new JCheckBox("Robbery"),
		new JCheckBox("Sexual Assault"),
		new JCheckBox("Theft From Motor Vehicle"),
		new JCheckBox("Vandalism"),
		new JCheckBox("Weapon Law Violations")
	};
	JCheckBox[] areaCodeCheckBoxes =
	{
		new JCheckBox("All Area Codes"), 
		new JCheckBox("91902"),
		new JCheckBox("91914"),
		new JCheckBox("91935"), 
		new JCheckBox("91941"), 
		new JCheckBox("91942"), 
		new JCheckBox("91945"), 
		new JCheckBox("91950"), 
		new JCheckBox("91977"), 
		new JCheckBox("91978"), 
		new JCheckBox("92019"), 
		new JCheckBox("92020"), 
		new JCheckBox("92021"), 
		new JCheckBox("92037"),
		new JCheckBox("92040"), 
		new JCheckBox("92071"), 
		new JCheckBox("92093"), 
		new JCheckBox("92101"), 
		new JCheckBox("92102"), 
		new JCheckBox("92103"), 
		new JCheckBox("92104"), 
		new JCheckBox("92105"),  
		new JCheckBox("92106"),  
		new JCheckBox("92107"),  
		new JCheckBox("92108"),  
		new JCheckBox("92109"),  
		new JCheckBox("92110"),  
		new JCheckBox("92111"),  
		new JCheckBox("92113"),  
		new JCheckBox("92114"),  
		new JCheckBox("92115"),   
		new JCheckBox("92116"),  
		new JCheckBox("92117"),  
		new JCheckBox("92118"),  
		new JCheckBox("92119"),  
		new JCheckBox("92120"),   
		new JCheckBox("92121"),  
		new JCheckBox("92122"),  
		new JCheckBox("92123"),  
		new JCheckBox("92124"),  
		new JCheckBox("92134"),   
		new JCheckBox("92136"),  
		new JCheckBox("92139"),  
		new JCheckBox("92140"),  
		new JCheckBox("92145"),  
		new JCheckBox("92154"),  
		new JCheckBox("92161"),  
		new JCheckBox("92173"),  
		new JCheckBox("92182"),       
	};
	
	
    
	CrimeSearchInterface()
	{
		crimeMap = new CrimeMap();
		searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener(this, crimeMap));
		startDateField = new JTextField();
		endDateField = new JTextField();
	}
	
	public List<String> getCrimeCategories()
	{
		List<String> selectedCrimeCategories = new ArrayList<String>();
		// If the "All Crimes" checkbox is selected, add "All Crimes" to the list and return
		if (crimeCategoryCheckBoxes[0].isSelected())
		{
			selectedCrimeCategories.add("All Crimes");
			return selectedCrimeCategories;
		}
		// Skip the first checkbox because it is the "All Crimes" checkbox
		for (int i = 1; i < crimeCategoryCheckBoxes.length; i++)
		{
			if (crimeCategoryCheckBoxes[i].isSelected())
			{
				selectedCrimeCategories.add(crimeCategoryCheckBoxes[i].getText());
			}
		}
		return selectedCrimeCategories;
	}
	
	public List<Integer> getAreaCodes()
	{
		List<Integer> selectedAreaCodes = new ArrayList<Integer>();
		// If the "All Area Codes" checkbox is selected, add 0 to the list and return
		if (areaCodeCheckBoxes[0].isSelected())
		{
			selectedAreaCodes.add(0);
			return selectedAreaCodes;
		}
		// Skip the first checkbox because it is the "All Area Codes" checkbox
		for (int i = 1; i < areaCodeCheckBoxes.length; i++)
		{
			// If the checkbox is selected, add the area code integer to the list
			if (areaCodeCheckBoxes[i].isSelected())
			{
				selectedAreaCodes.add(Integer.parseInt(areaCodeCheckBoxes[i].getText()));
			}
		}
		return selectedAreaCodes;
	}
	
	public LocalDateTime getStartDate()
	{
		String date = startDateField.getText().replace("/", "-");
		try
		{
			return LocalDateTime.parse(date,
					DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		}
		catch (Exception e)
		{
			System.out.println("Invalid date format:" + date );
			return null;
		}
	}
	
	public LocalDateTime getEndDate()
	{
		String date = endDateField.getText().replace("/", "-");
		try
		{
			return LocalDateTime.parse(date,
					DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		}
		catch (Exception e)
		{
			System.out.println("Invalid date format:" + date);
			return null;
		}
	}
}
