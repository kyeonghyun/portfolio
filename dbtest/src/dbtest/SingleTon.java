package dbtest;

import java.util.ArrayList;

public class SingleTon {

//	Select_Profile mSp = new Select_Profile();
	
	ArrayList<String> phonenum_singleTon = new ArrayList<String>();
	ArrayList<String> password1_singleTon = new ArrayList<String>();
//	String password1_singleTon = null;
	String raspData_singleTon = null;
	

	private static SingleTon sel_pro = null;
	
	// �̱��� ���Ͽ��� �����ڸ� �ܺο��� ȣ���� �� ������ �Ѵ�.
	private SingleTon() {
	}

	public static SingleTon getInsTanse() {
		if (sel_pro == null) 
			sel_pro = new SingleTon() ;
		return sel_pro;
		}

	}


//class STest {
//	void DBSelect() {
//		SelPro_SingleTon s = SelPro_SingleTon.getInsTanse();
//		for(int i=0; i<s.phonenum_singleTon.size(); i++)
// 		{
// 			System.out.println("data : " + s.phonenum_singleTon.get(i));
// 			String str = "123";
// 			//// String To Int
// 			int a = Integer.parseInt(str);
// 			//// Int To String
// 			String ab = String.valueOf(a);
// 					
// 		}
//		
//	}
//}