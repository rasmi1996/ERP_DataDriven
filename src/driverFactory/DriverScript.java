package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cofig.AppUtil;
import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil
{
	ExtentReports reports;
	ExtentTest logger;
	String inputpath="./FileInput/TestData.xlsx";
	String outputpath="./FileOutput/DataDrivenResults.xlsx";

	@Test
	public void startTest() throws Throwable 
	{
		//define path of html report
		reports=new ExtentReports("./Reports/Login.html");
		//create reference object for excel file util class
		ExcelFileUtil xl= new ExcelFileUtil(inputpath);
		//count no of rows in a login sheet
		int rc= xl.rowCount("Login");
		Reporter.log("No of rows are : "+rc,true);
		//Iterate all rows in login sheet
		for(int i=1;i<=rc;i++)
		{
			logger= reports.startTest("Login Test");
			logger.assignAuthor("Rasmi");
			//read user name and password cells
			String username1=xl.getCellData("Login", i, 0);
			String password=xl.getCellData("Login", i, 1);
			logger.log(LogStatus.INFO,username1+" "+ password);
			//call login method and assign parameters
			boolean res= FunctionLibrary.adminLogin(username1, password);
			if(res)
			{
				//if res is true write as login success into result cell
				xl.setCellData("Login", i, 2,"Login pass",outputpath);
				//if res is true write pass into status cell
				xl.setCellData("Login", i, 3,"pass",outputpath);
				logger.log(LogStatus.PASS,"Valid User name and password");
			}
			else
			{
				//Adding screen shot for fail test
				File screen= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"Login.png"));
				//if res is false write login fail into results cell
				xl.setCellData("Login", i, 2,"Login fail",outputpath);
				//if res is false write as fail into status
				xl.setCellData("Login", i, 3,"fail",outputpath);
				logger.log(LogStatus.FAIL,"InValid User name and password");
			}
			reports.endTest(logger);
			reports.flush();

		}
	}

}
