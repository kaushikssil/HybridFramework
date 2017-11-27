package com.hybrid.utils;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	
		private static ExtentReports extent;
		public static ExtentReports getInstance() {
			if(extent == null) {
				Date d = new Date();
				String filename = d.toString().replace(":", "_").replace(" ", "_")+".html";
				extent = new ExtentReports("C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\ExtentReport\\"+filename, true,DisplayOrder.NEWEST_FIRST);
			
				//Load the configuration file of reports which is in xml
				File f = new File("C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\Reporting.xml");
				extent.loadConfig(f);
				
				//Add System Info
				extent.addSystemInfo("Tester Name", "Kaushik");
				extent.addSystemInfo("Selenium Version", "3.7.1");
				
			}
			
			return extent;
		}

	

}
