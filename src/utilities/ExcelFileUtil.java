package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil 
{
	XSSFWorkbook wb;

	//creating constructor for reading excel path
	public ExcelFileUtil(String excelpath) throws Throwable 
	{
		FileInputStream fi = new FileInputStream(excelpath);
		wb= new XSSFWorkbook(fi);
	}
	//count no of rows in a sheet

	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
		
	}
	
	//method for reading cellData
	public String getCellData(String sheetname,int row,int column)
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
		{
			int cellData=(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(cellData);
		}
		else
		{
			data= wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	
	//method for writing status into new wb
	public void setCellData(String sheetname,int row,int column,String status,String WriteExcel)throws Throwable
	{
		//get sheet from wb
		XSSFSheet ws = wb.getSheet(sheetname);
		//get row from sheet
		XSSFRow rowNum =ws.getRow(row);
		//create cell
		XSSFCell cell = rowNum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo =new FileOutputStream(WriteExcel);
		wb.write(fo);

	}

	
	/*public static void main(String[] args) throws Throwable {
		ExcelFileUtil ex= new ExcelFileUtil("D:\\SureshSelenium\\SampleData.xlsx");
		int rc=ex.rowCount("Emp");
		System.out.println(rc);
		//iterate all rows
		for(int i=1;i<=rc;i++)
		{
			String fname=ex.getCellData("Emp", i, 0);
			String mname=ex.getCellData("Emp", i, 1);
			String lname=ex.getCellData("Emp", i, 2);
			String eid=ex.getCellData("Emp", i, 3);
			System.out.println(fname+" "+mname+" "+lname+" "+eid);
			
			//write status as pass
			//ex.setCellData("Emp", i, 4, "Pass","D:\\SureshSelenium\\ResultsData.xlsx");
			//ex.setCellData("Emp", i, 4, "Fail","D:\\SureshSelenium\\ResultsData.xlsx");
			ex.setCellData("Emp", i, 4, "Blocked","D:\\SureshSelenium\\ResultsData.xlsx");
		}
	}*/
}
