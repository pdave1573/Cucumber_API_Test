package resource;

import java.io.IOException;

public class testExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(utility.readExcel());
		
		System.out.println(utility.ReadCellData("Sheet1",1,3));
		System.out.println(utility.ReadCellDoubleData("Sheet1",1,5));
		
		System.out.println(utility.ReadRowData("Sheet1",2));
	}

}
