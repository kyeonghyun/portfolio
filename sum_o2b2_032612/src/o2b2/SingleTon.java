package o2b2;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SingleTon {

//	Select_Profile mSp = new Select_Profile();
	ArrayList<String> serialnum_singleTon = new ArrayList<String>();
	ArrayList<String> phonenum_singleTon = new ArrayList<String>();
	ArrayList<String> password1_singleTon = new ArrayList<String>();
	
	ArrayList<String> SelectRealserialnum_singleTon = new ArrayList<String>();
	ArrayList<String> SelectRealstudytime_singleTon = new ArrayList<String>();
	ArrayList<String> SelectRealdate_singleTon = new ArrayList<String>();
	ArrayList<String> SelectRealsubject_singleTon = new ArrayList<String>();
	
	////�׷��� ���κ�
	ArrayList<String> SelectRealstudytime2_singleTon = new ArrayList<String>();
	int RealGraph_count = 0;
	
	ArrayList<String> SelectSchedulestudytime2_singleTon = new ArrayList<String>();
	int ScheduleGraph_count = 0;
	
	
	
	///
	
	ArrayList<String> SelectScheduleserialnum_singleTon = new ArrayList<String>();
	ArrayList<String> SelectSchedulestudytime_singleTon = new ArrayList<String>();
	ArrayList<String> SelectScheduledate_singleTon = new ArrayList<String>();
	ArrayList<String> SelectSchedulesubject_singleTon = new ArrayList<String>();
	
	String raspStudyTime_singleTon = null;
	String readData_singleTon = null;
	
	String profileval = null;
	String realstudytimeval = null;
	String schedulestudytimeval = null;
	

	private static SingleTon sel_pro = null;
	
	JTextField textfield;
	JButton btn_textField;
	String get_textfield_profile = null;
	String get_textfield_realstudy = null;
	String get_textfield_schedulestudy = null;
	
	JTextArea txta1;
	JTextArea txta2;
	JTextArea txta3;
	
	JButton btn_selP;
	JButton btn_selR;
	JButton btn_selS;
	JButton btn_insP;
	JButton btn_insR;
	JButton btn_insS;
	JButton btn_delP;
	JButton btn_delR;
	JButton btn_delS;
	JButton btn_clr;
	JButton btn_exit;
	
	String Insert_pro = null;
	String Insert_rst = null;
	String Insert_sst = null;
	String Delete_pro = null;
	String Delete_rst = null;
	String Delete_sst = null;
	
	String select_serial = null;
	
	int Profile_count = 0;
	int RealStudyTime_count = 0;
	int ScheduleStudyTime_count = 0;
	/// ������ ��й�ȣ
	String password = null;
	
	//	���� �н��ð�
	ArrayList<Integer> mListGraph = new ArrayList<Integer>();
	
	public void setGraph(ArrayList<Integer> graph) {
		///	�׷����� ������ �׸��� arraylist���� ��� �߰��ǹǷ� �����ִ� �κ�
		mListGraph.clear();
		for(int i=0; i< graph.size(); i++)
		{
			mListGraph.add(graph.get(i));
		}
		
	}
	//	������ �н��ð�
	ArrayList<Integer> mListGraph_Schedule = new ArrayList<Integer>();
	
	public void setGraph_S(ArrayList<Integer> graph) {
		///	�׷����� ������ �׸��� arraylist���� ��� �߰��ǹǷ� �����ִ� �κ�
		mListGraph_Schedule.clear();
		for(int i=0; i< graph.size(); i++)
		{
			mListGraph_Schedule.add(graph.get(i));
		}
		
	}
	
	// �̱��� ���Ͽ��� �����ڸ� �ܺο��� ȣ���� �� ������ �Ѵ�.
	private SingleTon() {
	}

	public static SingleTon getInstanse() {
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