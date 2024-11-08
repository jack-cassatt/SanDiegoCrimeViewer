package crimeApplication;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Lead Author(s):
 * @author Jack Cassatt
 * 
 * References:
 * 
 * Version/date: v.1 01NOV2024
 * 
 * Responsibilities of class:
 * Test the CrimeApplication class
 */
class CrimeApplicationeasTest
{
	
	@Test
	public void testMapMarkerXY()
    {

		double LATITUDE_TOP = 32.8666;
		double LATITUDE_BOTTOM = 32.6625;
		double LONGITUDE_LEFT = -117.2825;
		double LONGITUDE_RIGHT = -116.9316;
		int IMAGE_WIDTH = 1410;
		int IMAGE_HEIGHT = 2044;
		
		CrimeMap map = new CrimeMap();
		
		LocalDateTime date = LocalDateTime.of(2022, 1, 1, 0, 0);
		double latitude = 32.7157;
		double longitude = -117.1611;
		Crime crime = new Crime("dataLine", 1, date, "ASSAULT", "description", 111, latitude, longitude);
		
        MapMarker marker = new MapMarker(crime, map);
        int testX = (int) ((longitude - LONGITUDE_LEFT) / (LONGITUDE_RIGHT - LONGITUDE_LEFT) * IMAGE_WIDTH);
        int testY = (int) ((LATITUDE_TOP - latitude) / (LATITUDE_TOP - LATITUDE_BOTTOM) * IMAGE_HEIGHT);
        System.out.println("testX: " + testX);
        System.out.println("Marker X:" + marker.getX());
        System.out.println("Crime Longitude: " + crime.getLongitude());
        System.out.println("testY: " + testY);
        System.out.println("Marker Y:" + marker.getY());
        System.out.println("Crime Latitude: " + crime.getLatitude());
        assertEquals(testX, marker.getX());
        assertEquals(testY, marker.getY());
    }

}
