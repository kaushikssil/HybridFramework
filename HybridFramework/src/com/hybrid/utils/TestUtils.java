package com.hybrid.utils;

import java.util.Hashtable;

public class TestUtils {
	
	
	//Need to create a function to read TestData.xls excel using the function defined in Xls_Reader.java class file
	public static Object[][] getData(String testCase, Xls_Reader xls){
		
		//1) Rows from which the test cases are starting - Row number at which test cases are present
		int testCaseStartRow = 1;
		while(!xls.getCellData("Data", 0, testCaseStartRow).equals(testCase)){
			
			testCaseStartRow++;
		}
		System.out.println("Test case "+testCase+ " is present in row number "+testCaseStartRow);
		
		//2) Number of rows having data for the particular test case
		int columnInWhichDataIsSeenForEachTestCase = 0;//FOr each test case , column number 0 is filled with data
		int rowNumberFromWhichDataStartsForaTestcase = testCaseStartRow+2;
		int totalRowsInWhichDataIsPresentForEachTestCase   = 0;//We do not at this point how many rows have data for each test case
		while(!xls.getCellData("Data", columnInWhichDataIsSeenForEachTestCase, rowNumberFromWhichDataStartsForaTestcase+totalRowsInWhichDataIsPresentForEachTestCase).equals("")){
			
			totalRowsInWhichDataIsPresentForEachTestCase++;
		}
		System.out.println("Number of rows having data for the test case "+testCase+" is "+totalRowsInWhichDataIsPresentForEachTestCase);
		
		//3) Number of column in the particular test case
		int rowNumberFromWhichColumnStartsForaTestcase = testCaseStartRow+1;
		int totalColumnsInWhichDataIsPresentForEachTestCase   = 0;
		while(!xls.getCellData("Data", totalColumnsInWhichDataIsPresentForEachTestCase, rowNumberFromWhichColumnStartsForaTestcase).equals("")){
			
			totalColumnsInWhichDataIsPresentForEachTestCase++;
		}
		System.out.println("Number of Columns having data for the test case "+testCase+" is "+totalColumnsInWhichDataIsPresentForEachTestCase);
		
		//4.Extracting data and put it to a hashtable of the dataprovider
		System.out.println("Total rows in test -> "+ totalRowsInWhichDataIsPresentForEachTestCase);
		Object[][] data = new Object[totalRowsInWhichDataIsPresentForEachTestCase][1];
		Hashtable<String,String> table=null;
		//Send mail test case   rnum = 2, 2<4+2 = 6
		//rnum = 3; rNum<4+3 = 7==> rNUm<7
		for(int rNum =rowNumberFromWhichDataStartsForaTestcase; rNum<(totalRowsInWhichDataIsPresentForEachTestCase+rowNumberFromWhichDataStartsForaTestcase); rNum++ ){
			table=new Hashtable<String,String>();
			
			//Send mail test case cnum=0 , 0<5
			//cnum = ; cNum<5
			for(int cNum =0; cNum<totalColumnsInWhichDataIsPresentForEachTestCase; cNum++ ){
				table.put(xls.getCellData("Data", cNum, rowNumberFromWhichColumnStartsForaTestcase),xls.getCellData("Data", cNum, rNum));
				//System.out.print(xls.getCellData("Data", cNum, rNum)+" - ");
			}
			data[rNum-rowNumberFromWhichDataStartsForaTestcase][0]=table;
			//System.out.println();
		}
		//return null;
		return data;
		
	}
}
