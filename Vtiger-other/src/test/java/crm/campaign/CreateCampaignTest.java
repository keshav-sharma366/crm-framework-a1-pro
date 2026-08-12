package crm.campaign;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.BaseClass;
import generic_utility.JavaUtility;
import generic_utility.WebDriverUtility;
import object_repository.CampaignPage;

/**
 * Test Script: Create Campaign Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Campaigns module
 *   3. Click Create Campaign
 *   4. Fill in the campaign form (name, type, status, budget, dates)
 *   5. Save and verify
 *   6. Logout
 */
public class CreateCampaignTest extends BaseClass{
	@Test(groups="smoke")
	public void CampaignTest() throws IOException, ParseException, InterruptedException {

//		generate a unique campaign name
		String campaignName = "AutoCampaign" + JavaUtility.generateRandomNumber();

//		========== POM: CampaignPage ==========
		CampaignPage cp = new CampaignPage(driver);
		//  Hover over 'More' tab
		WebDriverUtility wdUtil=new WebDriverUtility(driver);
		wdUtil.hover(cp.getMoreLink());

		// Click on 'Campaigns' link
		cp.getCampaignsLink().click();
//		clcik on create button
		cp.getCreateCampaignBtn().click();
		
//		fill the Create Campaign form
		cp.getCampaignName().sendKeys(campaignName);//campaignName //Qspiders

//		select campaign type
		Select typeSelect = new Select(cp.getCampaignType());
		typeSelect.selectByVisibleText("Email");

//		select campaign status
		Select statusSelect = new Select(cp.getCampaignStatus());
		statusSelect.selectByVisibleText("Active");

//		set start and end dates
		//cp.getStartDate().sendKeys("07/01/2026");
		//cp.getEndDate().sendKeys("12/31/2026");
		cp.getClosingDate().clear();
		cp.getClosingDate().sendKeys("2026/12/31");

//		fill budget and cost fields
		cp.getBudget().clear();
		cp.getBudget().sendKeys("10000");
//		select campaign status
		 statusSelect = new Select(cp.getExpectedResponse());
		statusSelect.selectByVisibleText("Excellent");

		cp.getActualCost().clear();
		cp.getActualCost().sendKeys("7500");
		cp.getExpectedRevenue().clear();
		cp.getExpectedRevenue().sendKeys("50000");
		//cp.getActualRevenue().clear();
		//cp.getActualRevenue().sendKeys("0");
		cp.getExpectedResponseCount().clear();
		cp.getExpectedResponseCount().sendKeys("200");
		cp.getExpectedROI().clear();
		cp.getExpectedROI().sendKeys("10");
		cp.getActualROI().clear();
		cp.getActualROI().sendKeys("14");

//		add description
		cp.getDescription().sendKeys("Automated test campaign created by Selenium POM script. "
				+ "This is an email marketing campaign for testing.");

//		save the record
		cp.getSaveButton().click();

//		verification
		String actCampaignName = cp.getDetailViewCampaignName().getText();
		Assert.assertEquals(actCampaignName,campaignName);

	}
}
