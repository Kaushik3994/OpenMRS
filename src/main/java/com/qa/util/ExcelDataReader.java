package com.qa.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class ExcelDataReader {

	public static List<Map<String, String>> readExcelData(String filePath, String sheetName) throws IOException {
		List<Map<String, String>> testDataList = new ArrayList<>();
		String fPath=filePath.replace("\"","");
		FileInputStream fis = new FileInputStream(fPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		// Assuming the first row contains column headers
		Row headerRow = sheet.getRow(0);
		int columnCount = headerRow.getLastCellNum();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row dataRow = sheet.getRow(i);
			Map<String, String> rowData = new HashMap<>();

			for (int j = 0; j < columnCount; j++) {
				Cell headerCell = headerRow.getCell(j);
				Cell dataCell = dataRow.getCell(j);

				String header = headerCell.getStringCellValue();
				String data = getStringValue(dataCell);

				rowData.put(header, data);
			}

			testDataList.add(rowData);
		}

		workbook.close();
		fis.close();

		return testDataList;
	}

	private static String getStringValue(Cell cell) {
		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == CellType.BLANK) {
			return "";
		} else {
			return "";
		}
	}
}