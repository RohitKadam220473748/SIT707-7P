package sit707_week7;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BodyTemperatureMonitorTest {

	@Test
	public void testStudentIdentity() {
		String studentId = "S220473748";
		Assert.assertNotNull("Student ID is S220473748", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Rohit Rajendra Kadam";
		Assert.assertNotNull("Student name is Rohit Rajendra Kadam", studentName);
	}
	
	@Test
	public void testReadTemperatureNegative() {
        TemperatureSensor temperatureSensor = Mockito.mock(TemperatureSensor.class);
        Mockito.when(temperatureSensor.readTemperatureValue()).thenReturn(-4.0);
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(temperatureSensor, null, null);
        double temperature = monitor.readTemperature();
        Assert.assertEquals(-4.0, temperature, 0.0);
	}
	
	@Test
	public void testReadTemperatureZero() {
		TemperatureSensor temperatureSensor = Mockito.mock(TemperatureSensor.class);
        Mockito.when(temperatureSensor.readTemperatureValue()).thenReturn(0.0);
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(temperatureSensor, null, null);
        double temperature = monitor.readTemperature();
        Assert.assertEquals(0.0, temperature, 0.0);
	}
	
	@Test
	public void testReadTemperatureNormal() {
		
		TemperatureSensor temperatureSensor = Mockito.mock(TemperatureSensor.class);
        Mockito.when(temperatureSensor.readTemperatureValue()).thenReturn(35.0);
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(temperatureSensor, null, null);
        double temperature = monitor.readTemperature();
        Assert.assertEquals(35.0, temperature, 0.0);
		
	}

	@Test
	public void testReadTemperatureAbnormallyHigh() {
		TemperatureSensor temperatureSensor = Mockito.mock(TemperatureSensor.class);
        Mockito.when(temperatureSensor.readTemperatureValue()).thenReturn(40.0);
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(temperatureSensor, null, null);
        double temperature = monitor.readTemperature();
        Assert.assertEquals(40.0, temperature, 0.0);
	}

	/*
	 * CREDIT or above level students, Remove comments. 
	 */	@Test
	public void testReportTemperatureReadingToCloud() {
		 
		    CloudService cloudService = Mockito.mock(CloudService.class);
		    BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(null, cloudService, null);
		    TemperatureReading temperatureReading = new TemperatureReading();
	        monitor.reportTemperatureReadingToCloud(temperatureReading);
	        Mockito.verify(cloudService, Mockito.times(1)).sendTemperatureToCloud(temperatureReading);
	}
	
	
	/*
	 * CREDIT or above level students, Remove comments. 
	 */
	 @Test
	    public void testInquireBodyStatusNormalNotification() {
	        CloudService cloudService = Mockito.mock(CloudService.class);
	        Mockito.when(cloudService.queryCustomerBodyStatus(Mockito.any(Customer.class))).thenReturn("NORMAL");
	        NotificationSender notificationSender = Mockito.mock(NotificationSender.class);
	        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(null, cloudService, notificationSender);
	        monitor.inquireBodyStatus();
	        Mockito.verify(notificationSender, Mockito.times(1)).sendEmailNotification(Mockito.any(Customer.class), Mockito.eq("Thumbs Up!"));
	    }



	
	/*
	 * CREDIT or above level students, Remove comments. 
	 */
	 @Test
	    public void TestInquireBodyStatusAbnormalNotification() {
	        CloudService mockCloudService = Mockito.mock(CloudService.class);
	        Mockito.when(mockCloudService.queryCustomerBodyStatus(Mockito.any(Customer.class))).thenReturn("ABNORMAL");
	        NotificationSender mockNotificationSender = Mockito.mock(NotificationSender.class);
	        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(null, mockCloudService, mockNotificationSender);
	        monitor.inquireBodyStatus();
	        Mockito.verify(mockNotificationSender, Mockito.times(1)).sendEmailNotification(Mockito.any(FamilyDoctor.class), Mockito.eq("Emergency!"));
	    }
	 



	 
	 


}
