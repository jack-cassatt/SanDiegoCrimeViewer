package crimeApplication;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;


/**
 * Lead Author(s):
 * @author Jack Cassatt
 * 
 * References:
 * Oracle Java Documentation - DocumentListener
 * https://docs.oracle.com/javase/tutorial/uiswing/events/documentlistener.html
 * 
 * Oracle Java Documentation - BoxLayout
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 * 
 * Oracle Java Documentation - JScrollPane
 * https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html
 * 
 * Oracle Java Documentation - FocusListener
 * https://docs.oracle.com/javase/tutorial/uiswing/events/focuslistener.html
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * The primary interface for searching for crimes
 */
public class CrimeSearchInterface extends JFrame implements ActionListener
{
	// Instance variables
	CrimeMap crimeMap;
	JLabel title;
	JButton searchButton;
	JButton zoomInButton;
	JButton zoomOutButton;
	JTextField startDateField;
	JTextField endDateField;
	JTextArea crimeText;
	// Check boxes for crime categories
	JCheckBox[] crimeCategoryCheckBoxes =
	{
		new JCheckBox("All Crimes"),
		new JCheckBox("Aggravated Assault"), 
		new JCheckBox("Alcohol Violations"),
		new JCheckBox("Animal Cruelty"), 
		new JCheckBox("Burglary/Breaking & Entering"),
		new JCheckBox("Curfew/Loitering"),
		new JCheckBox("Disorderly Conduct"),
		new JCheckBox("Drug/Narcotic Violations"),
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
	// Check boxes for area codes
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
	
	/**
	 * Constructor
	 */
	CrimeSearchInterface()
	{			
		// Establish the layout of the interface
		this.setTitle("San Diego Crime Search Application");
		this.setSize(1100, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setForeground(Color.CYAN);
		//this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		// Create a panel for the title
		JPanel titlePanel = new JPanel();
		title = new JLabel("San Diego Crime Search Application");
		title.setFont(new Font("Serif", Font.BOLD, 50));
		titlePanel.add(title);
		this.add(titlePanel, BorderLayout.NORTH);
		
		// Create panel for map and date
		JPanel mapDatePanel = new JPanel();
		mapDatePanel.setLayout(new BoxLayout(mapDatePanel, BoxLayout.Y_AXIS));
		mapDatePanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		// Create date panel
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(2, 6, 10, 7));
		datePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 10));
		// Create Labels for date fields to show error
		JLabel startDateLabel = new JLabel("Start Date:");
		startDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel startDateErrorLabel = new JLabel("MM/DD/YYYY");
		startDateErrorLabel.setForeground(Color.RED);
		startDateErrorLabel.setVisible(false);
		JLabel endDateLabel = new JLabel("End Date:");
		endDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel endDateErrorLabel = new JLabel("MM/DD/YYYY");
		endDateErrorLabel.setForeground(Color.RED);
		endDateErrorLabel.setVisible(false);
		// Create text fields for start and end date
		startDateField = new JTextField("01/01/2022");
		startDateField.setColumns(11);
		endDateField = new JTextField("01/01/2023");
		endDateField.setColumns(11);
		endDateField.setHorizontalAlignment(SwingConstants.LEFT);
		// Add document listener to date fields
		startDateField.getDocument().addDocumentListener(new DateFieldListener(startDateField, startDateErrorLabel));
		endDateField.getDocument().addDocumentListener(new DateFieldListener(endDateField, endDateErrorLabel));
		// Add labels and fields to date panel
		// Create zoom panel
		JPanel zoomPanel = new JPanel();
		zoomPanel.setLayout(new BoxLayout(zoomPanel, BoxLayout.X_AXIS));
		zoomInButton = new JButton("+");
		zoomInButton.addActionListener(this);
		zoomOutButton = new JButton("-");
		zoomOutButton.addActionListener(this);
		zoomPanel.add(zoomOutButton);
		zoomPanel.add(zoomInButton);
		datePanel.add(new JLabel());
		datePanel.add(new JLabel());
		datePanel.add(startDateErrorLabel);
		datePanel.add(new JLabel());
		datePanel.add(endDateErrorLabel);
		datePanel.add(new JLabel());
		datePanel.add(zoomPanel);
		datePanel.add(startDateLabel);
		datePanel.add(startDateField);
		datePanel.add(endDateLabel);
		datePanel.add(endDateField);
		datePanel.add(new JLabel());
		// Add date panel to map date panel
		mapDatePanel.add(datePanel);
		
		// Add map panel to map date panel
		crimeMap = new CrimeMap(this);
		JScrollPane crimeMapScrollPane = new JScrollPane(crimeMap);
		crimeMapScrollPane.setPreferredSize(crimeMap.getPreferredSize(500));
		crimeMapScrollPane.setViewportView(crimeMap);
		JScrollBar mapVerticalScrollSpeed = crimeMapScrollPane.getVerticalScrollBar();
		mapVerticalScrollSpeed.setUnitIncrement(16);
		JScrollBar mapHorizontalScrollSpeed = crimeMapScrollPane.getHorizontalScrollBar();
		mapHorizontalScrollSpeed.setUnitIncrement(16);
		mapDatePanel.add(crimeMapScrollPane);
		// Add map date panel to frame
		this.add(mapDatePanel, BorderLayout.CENTER);
		
		// Create panel for search options
		JPanel searchPanel = new JPanel();
		// Add space border to search panel
		searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		// Scroll pane for crime categories
		JScrollPane crimeCategoryScrollPane = new JScrollPane();
		crimeCategoryScrollPane.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 20));
		crimeCategoryScrollPane.setPreferredSize(new Dimension(180, 170));
		JScrollBar crimeScrollSpeed = crimeCategoryScrollPane.getVerticalScrollBar();
		crimeScrollSpeed.setUnitIncrement(16);
		// Add crime category check boxes to the scroll pane
		JPanel crimeCategoryPanel = new JPanel();
		crimeCategoryPanel.setLayout(new BoxLayout(crimeCategoryPanel, BoxLayout.Y_AXIS));
		crimeCategoryCheckBoxes[0].setSelected(true);
		for (JCheckBox checkBox : crimeCategoryCheckBoxes)
		{
			crimeCategoryPanel.add(checkBox);
		}
		crimeCategoryScrollPane.setViewportView(crimeCategoryPanel);
		// Add crime category scroll pane to search panel
		searchPanel.add(crimeCategoryScrollPane);
		
		// Scroll pane for area codes
		JScrollPane areaCodeScrollPane = new JScrollPane(); 
		areaCodeScrollPane.setPreferredSize(new Dimension(150, 170));
		areaCodeScrollPane.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 20));
		JScrollBar areaCodeScrollSpeed = areaCodeScrollPane.getVerticalScrollBar();
		areaCodeScrollSpeed.setUnitIncrement(16);
		// Add area code check boxes to the scroll pane
		JPanel areaCodePanel = new JPanel();
		areaCodePanel.setLayout(new BoxLayout(areaCodePanel, BoxLayout.Y_AXIS));
		areaCodeCheckBoxes[0].setSelected(true);
		for (JCheckBox checkBox : areaCodeCheckBoxes)
		{
			areaCodePanel.add(checkBox);
		}
		areaCodeScrollPane.setViewportView(areaCodePanel);
		searchPanel.add(areaCodeScrollPane);
		// Add search panel to frame
		this.add(searchPanel, BorderLayout.EAST);
		
		// Add search button to search panel
		searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener(this, crimeMap));
		searchPanel.add(searchButton);
		
		// Add crime panel to frame
		JPanel crimePanel = new JPanel();
		crimePanel.setLayout(new BoxLayout(crimePanel, BoxLayout.X_AXIS));
		crimePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		crimePanel.setPreferredSize(new Dimension(200, 90));
		crimeText = new JTextArea();
		crimeText.setEditable(false);
		crimeText.setBackground(null);
		crimePanel.add(crimeText);
		this.add(crimePanel, BorderLayout.SOUTH);
		
	
		this.setVisible(true);
		
	}
	
	/**
	 * Purpose: Get the selected crime categories 
	 * 
	 * @return List<String> crime categories
	 */
	public List<String> getCrimeCategories()
	{
		// Create a list to hold the selected crime categories
		List<String> selectedCrimeCategories = new ArrayList<String>();
		
		// If the "All Crimes" check box is selected, add "All Crimes" to the list and return
		if (crimeCategoryCheckBoxes[0].isSelected())
		{
			selectedCrimeCategories.add("All Crimes");
			return selectedCrimeCategories;
		}
		
		// Skip the first check box because it is the "All Crimes" check box
		for (int i = 1; i < crimeCategoryCheckBoxes.length; i++)
		{
			if (crimeCategoryCheckBoxes[i].isSelected())
			{
				selectedCrimeCategories.add(crimeCategoryCheckBoxes[i].getText());
			}
		}
		
		// Return the list of selected crime categories
		return selectedCrimeCategories;
	}
	
	/**
	 * Purpose: Get the selected area codes
	 * 
	 * @return List<Integer> area codes
	 */
	public List<Integer> getAreaCodes()
	{
		// Create a list to hold the selected area codes
		List<Integer> selectedAreaCodes = new ArrayList<Integer>();
		// If the "All Area Codes" checkbox is selected, add 0 to the list and return
		if (areaCodeCheckBoxes[0].isSelected())
		{
			selectedAreaCodes.add(0);
			return selectedAreaCodes;
		}
		
		// Skip the first check box because it is the "All Area Codes" checkbox
		for (int i = 1; i < areaCodeCheckBoxes.length; i++)
		{
			// If the checkbox is selected, add the area code integer to the list
			if (areaCodeCheckBoxes[i].isSelected())
			{
				selectedAreaCodes.add(Integer.parseInt(areaCodeCheckBoxes[i].getText()));
			}
		}
		
		// Return the list of selected area	codes
		return selectedAreaCodes;
	}
	
	/**
	 * Purpose: Get the start date from the text field
	 * 
	 * @return startDate
	 */
	public LocalDateTime getStartDate()
	{
		return parseDate(startDateField.getText());
	}
		
	/**
	 * Purpose: Get the end date from the text field
	 * 
	 * @return endDate
	 */
	public LocalDateTime getEndDate()
	{
		return parseDate(endDateField.getText());
	}
	
	/**
	 * Purpose: Parse the date from string "MM/DD/YYYY" to LocalDateTime
	 * 
	 * @param date
	 * @return LocalDateTime
	 */
	private LocalDateTime parseDate(String date)
	{
		String[] dateParts = date.split("/");
		try
		{
			return LocalDateTime.of(Integer.parseInt(dateParts[2]),
					Integer.parseInt(dateParts[0]),
					Integer.parseInt(dateParts[1]), 0, 0);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Invalid date format:" + date);
			return null;
		}
	}
	
	public void displayCrime(Crime crime)
	{
		crimeText.setText("Date: " + crime.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "\n"
                + "Case ID: " + crime.getCaseID() + "\n"
                + "Category: " + crime.getCategory() + "\n"
                + "Description: " + crime.getDescription());
				

	}
	
	public static void main(String[] args)
	{
		new CrimeSearchInterface();
	}
	
	/*
	 * Purpose: Listener for the date fields to validate the date
	 */
	public class DateFieldListener implements DocumentListener //, FocusListener
	{
		JTextField dateField;
		JLabel errorLabel;
		
		public DateFieldListener(JTextField dateField, JLabel errorLabel)
		{
			this.dateField = dateField;
			this.errorLabel = errorLabel;
		}
		
		@Override
		public void insertUpdate(DocumentEvent e)
		{
			validateDate();
		}

		@Override
		public void removeUpdate(DocumentEvent e)
		{
			validateDate();
        }

		@Override
		public void changedUpdate(DocumentEvent e)
		{
			validateDate();
		}
		
		/**
		 * Purpose: Validate the date in the text field and show error if invalid
		 */
		private void validateDate()
		{		
			// If the date is longer than 10 characters, show error
			if (dateField.getText().length() > 10)
			{
				dateField.setForeground(Color.RED);
				errorLabel.setVisible(true);
				return;
			}
			// Otherwise, check date
			else
			{
				// Split string by "/"
				String[] dateParts = dateField.getText().split("/");
				// Verify all parts are numbers
				for (String part : dateParts)
				{
					try
					{
						Integer.parseInt(part);
					}
					catch (Exception e)
					{
						dateField.setForeground(Color.RED);
						errorLabel.setVisible(true);
						return;
					}
				}
				// Check month is valid
				int month = Integer.parseInt(dateParts[0]);
				if (dateParts[0].length() > 2)
				{
					dateField.setForeground(Color.RED);
					errorLabel.setVisible(true);
					return;					
				}
				else if (month > 12 || month < 1)
				{
					dateField.setForeground(Color.RED);
					errorLabel.setVisible(true);
					return;
				}
				// If there is a day, check it is valid
				if (dateParts.length > 1)
				{
					int day = Integer.parseInt(dateParts[1]);
					if (dateParts[1].length() > 2)
					{
						dateField.setForeground(Color.RED);
						errorLabel.setVisible(true);
						return;					
					}
					else if ((month == 1 || month == 3 || month == 5 || month == 7
							|| month == 8 || month == 10 || month == 12)
							&& (day < 1 || day > 31))
					{
						dateField.setForeground(Color.RED);
						errorLabel.setVisible(true);
						return;
					}
					else if ((month == 4 || month == 6 || month == 9 || month == 11)
                            && (day < 1 || day > 30))
                    {
                        dateField.setForeground(Color.RED);
        				errorLabel.setVisible(true);
                        return;
                    }
                    else if (month == 2 && (day < 1 || day > 29))
					{
						dateField.setForeground(Color.RED);
						errorLabel.setVisible(true);
						return;
					}
				}
				// If there is a year, check it is valid
				if (dateParts.length > 2)
				{
					int year = Integer.parseInt(dateParts[2]);
					// Do not show error if user is still entering date
					if (dateParts[2].length() < 4)
					{
						dateField.setForeground(Color.BLACK);
						errorLabel.setVisible(false);
					}
					else if (year < 2000 || year > 2024)
					{
						dateField.setForeground(Color.RED);
						errorLabel.setVisible(true);
						return;
					}
				}
				// If there are more than 3 parts, show error
				if (dateParts.length > 3)
				{
					dateField.setForeground(Color.RED);
					errorLabel.setVisible(true);
					return;
				}
				// If all checks pass, set text to black
				dateField.setForeground(Color.BLACK);	
				errorLabel.setVisible(false);
			}
			
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == zoomInButton)
		{
			crimeMap.zoom(50);
		}
		else if (e.getSource() == zoomOutButton)
		{
			crimeMap.zoom(-50);
		}
	}
}
