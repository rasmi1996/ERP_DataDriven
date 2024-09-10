package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.SupplierPage;
import cofig.AppUtil1;
import utilities.ExcelFileUtil;

public class TestScript extends AppUtil1
{
	String inputpath ="./FileInput/SupplierData.xlsx";
	String outputpath="./FileOutput/SupplierResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	String TCsheet ="Supplier";
	@Test
	public void startTest() throws Throwable
	{
		reports = new ExtentReports("./ExtentReports/Supplier.html");
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount(TCsheet);
		Reporter.log("No of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger =reports.startTest("Validate Supplier Test");
			String sname = xl.getCellData(TCsheet, i, 0);
			String address = xl.getCellData(TCsheet, i, 1);
			String city = xl.getCellData(TCsheet, i, 2);
			String country = xl.getCellData(TCsheet, i, 3);
			String cperson = xl.getCellData(TCsheet, i, 4);
			String pnumber = xl.getCellData(TCsheet, i, 5);
			String email = xl.getCellData(TCsheet, i, 6);
			String mnumber = xl.getCellData(TCsheet, i, 7);
			String notes = xl.getCellData(TCsheet, i, 8);
			logger.log(LogStatus.INFO, sname+"  "+address+"   "+city+"   "+country+"   "+cperson+"   "+pnumber+"   "+email+"    "+mnumber+"   "+notes);
			SupplierPage sup =PageFactory.initElements(driver, SupplierPage.class);
			boolean res = sup.addSupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
			if(res)
			{
				xl.setCellData(TCsheet, i, 9, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Add Supplier Success");
			}
			else
			{
				xl.setCellData(TCsheet, i, 9, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Add Supplier Fail");
			}
			reports.endTest(logger);
			reports.flush();

		}

	}
}
