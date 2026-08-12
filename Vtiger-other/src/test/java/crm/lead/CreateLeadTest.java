package crm.lead;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.BaseClass;
import generic_utility.JavaUtility;
import junit.framework.Assert;
import object_repository.HomePage;
import object_repository.LeadPage;

/**
 * Test Script: Create Lead Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Leads module
 *   3. Click Create Lead
 *   4. Fill in the lead form (name, company, phone, email, lead source, status)
 *   5. Save and verify
 *   6. Logout
 */

public class CreateLeadTest extends BaseClass{
@Test(groups="smoke")
	public void LeadTest() throws IOException, ParseException, InterruptedException {

//		generate unique last name using random number
		String lastName = "LeadTest" + JavaUtility.generateRandomNumber();
		String companyName = "TestCompany" + JavaUtility.generateRandomNumber();

//		========== POM: HomePage ==========
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();

//		========== POM: LeadPage ==========
		LeadPage leadPage = new LeadPage(driver);
		leadPage.getCreateLeadButton().click();

//		fill the Create Lead form
//		select salutation
		Select salSelect = new Select(leadPage.getSalutation());
		salSelect.selectByVisibleText("Mr.");

		leadPage.getFirstName().sendKeys("AutoLead");
		leadPage.getLastName().sendKeys(lastName);
		leadPage.getCompany().sendKeys(companyName);
		leadPage.getPhone().sendKeys("044-98765432");
		leadPage.getMobile().sendKeys("7890123456");
		leadPage.getEmail().sendKeys("autolead@testcompany.com");
		leadPage.getTitle().sendKeys("Manager");
		leadPage.getWebsite().sendKeys("www.testcompany.com");
		leadPage.getAnnualRevenue().clear();// edited
		leadPage.getAnnualRevenue().sendKeys("500000");
		leadPage.getNoOfEmployees().sendKeys("50");

//		select lead source
		Select lsSelect = new Select(leadPage.getLeadSource());
		lsSelect.selectByVisibleText("Web Site");

//		select lead status
		Select statusSelect = new Select(leadPage.getLeadStatus());
		statusSelect.selectByVisibleText("Hot");//edited

//		select industry
		Select indSelect = new Select(leadPage.getIndustry());
		indSelect.selectByVisibleText("Technology");

//		fill address
		leadPage.getStreet().sendKeys("123 Test Street");
		leadPage.getCity().sendKeys("Bangalore");
		leadPage.getState().sendKeys("Karnataka");
		leadPage.getZipCode().sendKeys("560001");
		leadPage.getCountry().sendKeys("India");

//		save the record
		leadPage.getSaveButton().click();

//		verification
		String actLastName = leadPage.getDetailViewLastName().getText();

		Assert.assertEquals(actLastName, lastName);

		System.out.println("Create Lead Test Completed.");
	}
}
