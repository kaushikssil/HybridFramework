package Rough;
import com.hybrid.utils.Xls_Reader;

public class ExcelWork {

	public static void main(String[] args) {
		
		//Rules for the Xls_Reader
		//Row number starts with 1
		//Column number starts with 0
		//When reading can have the excel file open
		//When writing, excel file should be closed
		//All data should be in String format


		Xls_Reader xl = new Xls_Reader("C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\src\\Rough\\DataSheet.xlsx");

		//To get Sam
		String x = xl.getCellData("Data", 0, 2);
		System.out.println(x);
		
		//To get Moore
		String y = xl.getCellData("Data", "LastName", 3);
		System.out.println(y);
		
		//To write data:
		//To add a Column
		//boolean z = xl.addColumn("Data", "Phone");
		//System.out.println(z);
		
		//TO write in Row number 2
		boolean a = xl.setCellData("Data", "Phone", 2, "987654321");
		System.out.println(a);
	}

}
