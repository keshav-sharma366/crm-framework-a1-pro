package crm.vendor;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base_class.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import generic_utility.WebDriverUtility;
import object_repository.VendorPage;

/**
 * Test Script: Create Vendor Test using POM design pattern.
 * Steps:
 *   1. Login to VTiger CRM
 *   2. Navigate to Vendors module
 *   3. Click Create Vendor
 *   4. Fill in the vendor form (name, phone, email, website, address)
 *   5. Save and verify
 *   6. Logout
 */
//@Listeners(listeners_utility.List_Imp.class)
public class CreateVendorTest extends BaseClass{

	@Test(groups="regression")
	public void VendorTest() throws IOException, ParseException, InterruptedException {
		
//		generate a unique vendor name
		String vendorName = "AutoVendor" + JavaUtility.generateRandomNumber();

//		Navigate to Vendors module via URL (may be under More menu)
		FileUtility fUtil = new FileUtility();
		String url = fUtil.getDataFromJsonFile("url");
		driver.get(url + "index.php?module=Vendors&action=index");

//		========== POM: VendorPage ==========
		VendorPage vp = new VendorPage(driver);
		vp.getCreateVendorButton().click();

//		fill the Create Vendor form
		vp.getVendorName().sendKeys(vendorName);
		vp.getPhone().sendKeys("040-88776655");
		vp.getEmail().sendKeys("autovendor@vendortest.com");
		vp.getWebsite().sendKeys("www.autovendor.com");
//		vp.getFax().sendKeys("040-88776600");// no fax element on webpage
		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		wdUtil.select(vp.getGlAccount(), "302-Rental-Income");// edited
//		vp.getGlAccount().sendKeys("GL-001");

//		fill address
		vp.getStreet().sendKeys("456 Vendor Street");
		vp.getCity().sendKeys("Hyderabad");
		vp.getState().sendKeys("Telangana");
		vp.getPostalCode().sendKeys("500001");
		vp.getCountry().sendKeys("India");

//		add description
		vp.getDescription().sendKeys("Automated test vendor created by Selenium POM script.");

//		save the record
		vp.getSaveButton().click();

//		verification
		String actVendorName = vp.getDetailViewVendorName().getText();
		Assert.assertEquals(actVendorName, vendorName);

		System.out.println("Create Vendor Test Completed.");
	}
}
