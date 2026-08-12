package crm.cases;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import junit.framework.Assert;
import object_repository.CasePage;

/**
 * Test Script: Create Case (Trouble Ticket) Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Cases (Trouble Tickets / HelpDesk) module
 *   3. Click Create Ticket
 *   4. Fill in the case form (title, status, priority, severity, description)
 *   5. Save and verify
 *   6. Logout
 */
public class CreateCaseTest extends BaseClass {
	@Test(groups="smoke")
	public void CaseTest() throws IOException, ParseException, InterruptedException {

//		generate a unique ticket title
		String ticketTitle = "AutoTicket" + JavaUtility.generateRandomNumber();

//		Navigate to HelpDesk/Cases module directly via URL
//		(since 'Cases' might be under 'More' menu)
		FileUtility fUtil = new FileUtility();
		driver.get( fUtil.getDataFromJsonFile("url") + "index.php?module=HelpDesk&action=index");

//		========== POM: CasePage ==========
		CasePage cp = new CasePage(driver);
		cp.getCreateCaseButton().click();

//		fill the Create Ticket form
		cp.getTicketTitle().sendKeys(ticketTitle);

//		select ticket status
		Select statusSelect = new Select(cp.getTicketStatus());
		statusSelect.selectByVisibleText("Open");

//		select ticket priority
		Select prioritySelect = new Select(cp.getTicketPriority());
		prioritySelect.selectByVisibleText("High");

//		select ticket severity
		Select severitySelect = new Select(cp.getTicketSeverity());
		severitySelect.selectByVisibleText("Major");

//		add description
		cp.getDescription().sendKeys("This is an automated test ticket created by Selenium POM script. "
				+ "Testing the create ticket functionality of VTiger CRM.");

//		add solution( no element on web)
//		cp.getSolution().sendKeys("Automated resolution - ticket was created for testing purposes.");

//		save the record
		cp.getSaveButton().click();

//		verification
		String actTitle = cp.getDetailViewTitle().getText();
		Assert.assertEquals(ticketTitle, actTitle);

	}
}
