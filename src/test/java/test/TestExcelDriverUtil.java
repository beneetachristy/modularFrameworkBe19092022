package test;

import org.testng.annotations.Test;

import commonLibs.utils.ExcelDriver;

public class TestExcelDriverUtil {
	

	@Test
	public void verifyTestExcelDriverUtil() throws Exception {
		ExcelDriver excelDriver = new ExcelDriver();
		String excelWorkbook = System.getProperty("user.dir") + "/testData/excelWorkbook4.xlsx";
		String sheetName = "TestData";
		
		
		excelDriver.createWorkbook(excelWorkbook);
		excelDriver.openWorkbook(excelWorkbook);
		excelDriver.createSheet(sheetName);
		excelDriver.setCellData(sheetName, 0, 0, "Beneeta");
		excelDriver.setCellData(sheetName, 0, 1, "Tijo");
		excelDriver.setCellData(sheetName, 1, 0, "Nathan");
		excelDriver.setCellData(sheetName, 1, 1, "Anitta");
		excelDriver.saveFile();
		excelDriver.closeWorkbook();
		
		
	}
}