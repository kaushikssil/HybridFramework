package Rough;

import java.util.Date;

public class UsingDate {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {


		Date d = new Date();
		int x = d.getDate();
		System.out.println(x);
		long y = d.getTime();
		System.out.println(y);
		int z = d.getMonth();
		System.out.println(z);
	}

}
