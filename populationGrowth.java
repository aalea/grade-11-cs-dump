/**
 * 
 */
package com;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * @author Owner
 *
 */
public class populationGrowth {
	
	public static void main (String[] args){
		
		//variables
		Scanner input = new Scanner(System.in);
		String country;
		double population, populationGrowth;
		
		//Input
		System.out.println("Please enter the country");
		country = input.next();
		
		//Process
		FileInputStream file = new FileInputStream(new File("Population Growth.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		Iterator<Cell> cellIterator = Row.cellIterator();
		
		XSSFRow row = findRow(sheet, country);
	    XSSFCell cell = row.getCell(0);
		population = cell.getNumericCellValue();
		
		XSSFCell cell = row.getCell(0);
		
		
			
		
	}

	private static XSSFRow findRow(XSSFSheet sheet, String country) {
	    for (Row row : sheet) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	                if (cell.getRichStringCellValue().getString().trim().equals(country)) {
	                    return row.getRowNum();  
	                }
	            }
	        }
	    }               
	    return 0;
	}
	
	
	
	
}
