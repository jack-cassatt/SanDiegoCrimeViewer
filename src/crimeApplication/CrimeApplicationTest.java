package crimeApplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CrimeApplicationTest
{

	@Test
	void testSetDateTime()
	{
		Crime crime = new Crime();
        crime.setDateAndTime("01/01/2020 12:00");
        assertEquals("2020-01-01T12:00", crime.getDate().toString());
        assertEquals("2020-01-01", crime.getDate().toLocalDate().toString());
	}

}
