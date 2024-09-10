package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	//Define repository for login
	@FindBy(xpath="//button[@id='btnreset']")
	WebElement objReset;
	@FindBy(id="username")
	WebElement objUser;
	@FindBy(name="password")
	WebElement objPass;
	@FindBy(id="btnsubmit")
	WebElement objLogin;
	
	//write method to perform action
	public void adminLogin(String user,String pass) 
	{
		objReset.click();
		objUser.sendKeys(user);
		objPass.sendKeys(pass);
		objLogin.click();
	}
	
}
