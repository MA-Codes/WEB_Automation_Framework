package com.web.qa.util;

import java.io.FileInputStream;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class LoadExcel 
{
	public static String[] Excel_cellvalue(String FileName,String req) throws Exception
    {
		String[] req1=null; 
        FileInputStream fis = new FileInputStream(FileName);
        @SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);        
        XSSFSheet dataSheet = workbook.getSheetAt(0);
        int rowCount = dataSheet.getLastRowNum()-dataSheet.getFirstRowNum();
        req1 = new String[rowCount];
        String celltext="";
        int cellvalue;
        if(req.equalsIgnoreCase("username"))
        {
        	cellvalue=0;
        }
        else if(req.equalsIgnoreCase("password"))
        {
        	cellvalue=1;
        }
        else
        {
        	cellvalue=2;
        }
        for(int i=1;i<rowCount+1;i++) 
        {
        XSSFRow row = dataSheet.getRow(i);
        XSSFCell cell = row.getCell(cellvalue);
        switch (cell.getCellType()) 
        {
	        case BOOLEAN:
	        	celltext=String.valueOf(cell.getBooleanCellValue());
	            break;
	        case STRING:
	        	celltext=cell.getRichStringCellValue().getString();
	            break;
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	            	celltext=String.valueOf(cell.getDateCellValue());
	            } else {
	            	celltext=new BigDecimal(cell.getNumericCellValue()).toPlainString();
	            }
	            break;
	        case FORMULA:
	        	celltext =cell.getCellFormula();
	            break;
	        case BLANK:
	        	celltext ="";
	            break;
	        default:
	            System.out.print("");
	    	}
        req1[i-1]=celltext;
        }
     return req1;   
    }	
}
