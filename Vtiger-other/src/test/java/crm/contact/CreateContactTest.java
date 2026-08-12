package crm.contact;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.BaseClass;
import generic_utility.JavaUtility;
import junit.framework.Assert;
import object_repository.ContactPage;
import object_repository.HomePage;

/**
 * Test Script: Create Contact Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Contacts module
 *   3. Click Create Contact
 *   4. Fill in the contact form (first/last name, phone, mobile, email, title)
 *   5. Save and verify
 *   6. Logout
 */

public class CreateContactTest extends BaseClass {
	@Test(groups="smoke")
	public void ContactTest() throws IOException, ParseException, InterruptedException {
//		generate unique last name using random number
		String lastName = "TestContact" + JavaUtility.generateRandomNumber();
		String firstName = "Auto";

//		========== POM: HomePage ==========
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

//		========== POM: ContactPage ==========
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactButton().click();

//		fill the Create Contact form
//		select salutation from dropdown
		Select salSelect = new Select(cp.getSalutation());
		salSelect.selectByVisibleText("Mr.");

		cp.getFirstName().sendKeys(firstName);
		cp.getLastName().sendKeys(lastName);
		cp.getOfficePhone().sendKeys("080-12345678");
		cp.getMobile().sendKeys("9988776655");
		cp.getEmail().sendKeys("autocontact@test.com");
		cp.getTitle().sendKeys("QA Engineer");
		cp.getDepartment().sendKeys("Quality Assurance");

//		select lead source
		Select lsSelect = new Select(cp.getLeadSource());
		lsSelect.selectByVisibleText("Web Site");

//		save the record
		cp.getSaveButton().click();

//		verification
		String actLastName = cp.getDetailViewLastName().getText();
		Assert.assertEquals(actLastName, lastName);

		System.out.println("Create Contact Test Completed.");
	}
}
