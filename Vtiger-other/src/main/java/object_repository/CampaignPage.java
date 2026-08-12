package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Model class for the VTiger CRM Campaigns Module.
 * Covers the list view (create button) and the Create/Edit Campaign form.
 * URL: index.php?module=Campaigns&action=index
 */
public class CampaignPage {

	public CampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[contains(text(),'More')]")
    private WebElement moreLink;

    @FindBy(xpath = "//a[@name='Campaigns']")
    private WebElement campaignsLink;
// click on create campaign button
    @FindBy(xpath = "//img[@alt='Create Campaign...']")
    private WebElement createCampaignBtn;

	// ===== Create / Edit Form Elements =====

		@FindBy(name = "campaignname")  
	private WebElement campaignName;

	@FindBy(id = "campaign_no")
	private WebElement campaignNo;

	@FindBy(name = "campaigntype")
	private WebElement campaignType;

	@FindBy(name = "campaignstatus")
	private WebElement campaignStatus;

	@FindBy(id = "start_date")
	private WebElement startDate;

	@FindBy(id = "end_date")
	private WebElement endDate;

	@FindBy(name = "closingdate")
	private WebElement closingDate;

	@FindBy(name = "budgetcost")//edited
	private WebElement budget;

	@FindBy(name = "expectedresponse")
	private WebElement expectedresponse;

	@FindBy(name = "actualcost")//edited
	private WebElement actualCost;

	@FindBy(name = "expectedrevenue")//edited
	private WebElement expectedRevenue;

	@FindBy(id = "actualrevenue")
	private WebElement actualRevenue;

	@FindBy(name = "expectedresponsecount")
	private WebElement expectedResponseCount;

	@FindBy(name = "expectedroi")
	private WebElement expectedROI;

	@FindBy(name = "actualroi")
	private WebElement actualROI;

	// ===== Description =====

	@FindBy(name = "description")
	private WebElement description;

	// ===== Save / Cancel Buttons =====

	@FindBy(css = "input[title='Save [Alt+S]']")
	private WebElement saveButton;

	@FindBy(css = "input[title='Cancel [Alt+X]']")
	private WebElement cancelButton;

	// ===== Detail View Verification =====

	@FindBy(id = "dtlview_Campaign Name")
	private WebElement detailViewCampaignName;

	// ===== Getters =====
	
	

    public WebElement getMoreLink() {
        return moreLink;
    }

    public WebElement getCampaignsLink() {
        return campaignsLink;
    }
    public WebElement getCreateCampaignBtn() {
        return createCampaignBtn;
    }
	public WebElement getCampaignName() {
		return campaignName;
	}

	public WebElement getCampaignNo() {
		return campaignNo;
	}

	public WebElement getCampaignType() {
		return campaignType;
	}

	public WebElement getCampaignStatus() {
		return campaignStatus;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getClosingDate() {
		return closingDate;
	}

	public WebElement getBudget() {
		return budget;
	}

	public WebElement getExpectedResponse() {
		return expectedresponse;
	}

	public WebElement getActualCost() {
		return actualCost;
	}

	public WebElement getExpectedRevenue() {
		return expectedRevenue;
	}

	public WebElement getActualRevenue() {
		return actualRevenue;
	}

	public WebElement getExpectedResponseCount() {
		return expectedResponseCount;
	}

	public WebElement getExpectedROI() {
		return expectedROI;
	}

	public WebElement getActualROI() {
		return actualROI;
	}

	public WebElement getDescription() {
		return description;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getDetailViewCampaignName() {
		return detailViewCampaignName;
	}
}
