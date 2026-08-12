package crm.activity;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.BaseClass;
import generic_utility.JavaUtility;
import object_repository.ActivityPage;

/**
 * Test Script: Create Activity (Event/Meeting) Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Activities (Calendar) module
 *   3. Click Add Event
 *   4. Fill in the event form (subject, type, status, start/end date, location)
 *   5. Save and verify
 *   6. Logout
 */


public class CreateActivityTest extends BaseClass{

	@Test(groups="smoke")
		public void ActivityTest() throws InterruptedException, IOException, org.json.simple.parser.ParseException {

//		generate a unique activity subject
		String activitySubject = "AutoMeeting" + JavaUtility.generateRandomNumber();

//		========== POM: ActivityPage ==========
		ActivityPage ap = new ActivityPage(driver);

//		fill the Create Event form
		ap.getSubject().sendKeys(activitySubject);

//		select activity type
		Select typeSelect = new Select(ap.getActivityType());
		typeSelect.selectByVisibleText("Meeting");

//		select event status
		Select statusSelect = new Select(ap.getEventStatus());
		statusSelect.selectByVisibleText("Planned");

//		set start date and time
		ap.getStartDate().clear();
		ap.getStartDate().sendKeys("07/25/2026");

//		set end date
		ap.getEndDate().clear();
		ap.getEndDate().sendKeys("07/25/2026");

//		set location
		ap.getLocation().sendKeys("Conference Room A");

//		add description
		ap.getDescription().sendKeys("Automated test meeting created by Selenium POM script.");

//		save the record
		ap.getSaveButton().click();
		
		//apply alert to accept 
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

//		verification - check subject in detail view
		String actSubject = ap.getDetailViewSubject().getText();
		Assert.assertEquals(actSubject, activitySubject);

	}
}
