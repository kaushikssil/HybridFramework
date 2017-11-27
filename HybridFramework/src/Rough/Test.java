package Rough;


import com.hybrid.utils.TestUtils;
import com.hybrid.utils.Xls_Reader;

public class Test {
	
	static Xls_Reader xls = new Xls_Reader("C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\src\\com\\hybrid\\config\\TestData.xlsx");
	
	public static void main(String [] args) {
		
		TestUtils.getData("SendEmail", xls);
		
		System.out.println("************************************");
		TestUtils.getData("SaveEmail", xls);
		
		System.out.println("************************************");
		TestUtils.getData("DeleteEmail", xls);
	}

}
