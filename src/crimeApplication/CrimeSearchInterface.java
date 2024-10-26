package crimeApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrimeSearchInterface
{
	CrimeMap crimeMap;
	JButton searchButton;
	JTextField startDateField;
	JTextField endDateField;
	JCheckBox[] crimeTypeCheckBoxes;
	
    
	CrimeSearchInterface()
	{
		crimeMap = new CrimeMap();
		searchButton = new JButton("Search");
		searchButton.addActionListener(new searchButtonListener(this));
		startDateField = new JTextField();
		endDateField = new JTextField();
	}
}
