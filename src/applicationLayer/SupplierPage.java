package applicationLayer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class SupplierPage {
	WebDriver driver;

	public SupplierPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "(//a[text()='Suppliers'])[2]")
	WebElement objSupplierlink;
	@FindBy(xpath = "(//span[contains(@data-caption,'Add')])[1]")
	WebElement objAddIcon;
	@FindBy(id = "x_Supplier_Number")
	WebElement objSupplierNumber;
	@FindBy(name = "x_Supplier_Name")
	WebElement objSupplierName;
	@FindBy(id = "x_Address")
	WebElement objAdress;
	@FindBy(id = "x_City")
	WebElement objCity;
	@FindBy(xpath = "//input[@id='x_Country']")
	WebElement objCountry;
	@FindBy(name = "x_Contact_Person")
	WebElement objContactPerson;
	@FindBy(xpath = "//input[@id='x_Phone_Number']")
	WebElement objPhoneNumber;
	@FindBy(name = "x__Email")
	WebElement objEmail;
	@FindBy(id = "x_Mobile_Number")
	WebElement objMobileNumber;
	@FindBy(xpath = "//textarea[@id='x_Notes']")
	WebElement objNotes;
	@FindBy(id = "btnAction")
	WebElement objAddBtn;
	@FindBy(xpath = "//button[normalize-space()='OK!']")
	WebElement objConfirm;
	@FindBy(xpath = "(//button[text()='OK'])[6]")
	WebElement objAlert;
	@FindBy(xpath = "//span[@data-caption='Search']")
	WebElement objSearchPannel;
	@FindBy(name = "psearch")
	WebElement objSeachText;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objSearchBtn;
	@FindBy(xpath = "//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")
	WebElement objWebTable;

	// method for supplier creation
	public boolean addSupplier(String SupplierName, String Address, String City, String Country, String contactPerson,
			String PhoneNumber, String email, String MobileNumber, String Notes) throws Throwable {

		Actions ac = new Actions(driver);
		ac.moveToElement(this.objSupplierlink).click().perform();
		ac.moveToElement(this.objAddIcon).click().perform();
		String Exp_Data = this.objSupplierNumber.getAttribute("value");
		this.objSupplierName.sendKeys(SupplierName);
		this.objAdress.sendKeys(Address);
		this.objCity.sendKeys(City);
		this.objCountry.sendKeys(Country);
		this.objContactPerson.sendKeys(contactPerson);
		this.objPhoneNumber.sendKeys(PhoneNumber);
		this.objEmail.sendKeys(email);
		this.objMobileNumber.sendKeys(MobileNumber);
		this.objNotes.sendKeys(Notes);
		this.objAddBtn.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		this.objConfirm.click();
		Thread.sleep(2000);
		this.objAlert.click();
		Thread.sleep(2000);
		if (!this.objSeachText.isDisplayed())
			this.objSearchPannel.click();
		this.objSeachText.clear();
		this.objSeachText.sendKeys(Exp_Data);
		this.objSearchBtn.click();
		Thread.sleep(3000);
		String Act_Data = objWebTable.getText();
		if (Act_Data.equals(Exp_Data)) {
			Reporter.log("Add Supplier is Success:::" + Exp_Data + "      " + Act_Data, true);
			return true;
		} else {
			Reporter.log("Add Supplier is Fail:::" + Exp_Data + "      " + Act_Data, true);
			return false;
		}

	}

}
