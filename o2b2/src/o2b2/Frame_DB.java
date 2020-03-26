package o2b2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//@SuppressWarnings("serial")
public class Frame_DB  {
	private static Frame_DB mFrame_DB =null;
	public JFrame mJFrame = null;
	
	
	public static Frame_DB getInstance() {
		if(mFrame_DB == null)
			mFrame_DB = new Frame_DB();
		return mFrame_DB;
	}
	
   private Frame_DB() {
	   
	   SingleTon s = SingleTon.getInstanse();
	   
	   mJFrame = new JFrame();
	   mJFrame.setTitle("o2b2 DB");
	   mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container contentPane = mJFrame.getContentPane();

      contentPane.setLayout(null);
      
      /////////////////////   JTextField  Test 값 가져오기    /////////////////////////////
      s.textfield = new JTextField();
      s.textfield.setLocation(190, 170);
      s.textfield.setSize(300, 20);
      contentPane.add(s.textfield);
      
      s.btn_textField = new JButton("회원정보");
      s.btn_textField.setLocation(40,170);
      s.btn_textField.setSize(120,20);
      contentPane.add(s.btn_textField);
      
      s.btn_textField.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 s.get_textfield = s.textfield.getText();
        	 System.out.println(s.get_textfield);
        	 s.txta1.append(s.get_textfield);
        	 //TextField 초기화
        	 s.textfield.setText(null);
         }
      });
      
      /////////////////////    JTextLabel    ///////////////////////////////////////
      JLabel la1 = new JLabel("회원정보");
      la1.setLocation(320,30);
      la1.setSize(200,20);
      contentPane.add(la1);
      
      JLabel la2 = new JLabel("실제 학습 시간");
      la2.setLocation(305,185);
      la2.setSize(200,50);
      contentPane.add(la2);
      
      JLabel la3 = new JLabel("계획 학습 시간");
      la3.setLocation(305,370);
      la3.setSize(200,20);
      contentPane.add(la3);
      
      //////////////////////    JTextArea    //////////////////////////////////////////
      
      
      s.txta1 = new JTextArea();
      JScrollPane scroll1 = new JScrollPane(s.txta1);
      scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scroll1.setBounds(190, 60, 300, 100);
      contentPane.add(scroll1);
      
      s.txta2 = new JTextArea();
      JScrollPane scroll2 = new JScrollPane(s.txta2);
      scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scroll2.setBounds(190,235, 300, 100);
      contentPane.add(scroll2);
      
      s.txta3 = new JTextArea();
      JScrollPane scroll3 = new JScrollPane(s.txta3);
      scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scroll3.setBounds(190,410, 300, 100);
      contentPane.add(scroll3);
          
      
      /////////////////////    JButton    //////////////////////////////////////////////
      
      //---------------------    불러오기    -------------------------------------------
      s.btn_selP = new JButton("회원정보");
      s.btn_selP.setLocation(40,60);
      s.btn_selP.setSize(120,80);
      contentPane.add(s.btn_selP);
      
      s.btn_selP.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	      	
        	 s.txta1.setText("");
            Select_Profile mP = new Select_Profile();
            mP.loadProfile(s.txta1);
         }
      });
      
      s.btn_selR = new JButton("실제 학습 시간");
      s.btn_selR.setLocation(40,235);
      s.btn_selR.setSize(120,80);
      contentPane.add(s.btn_selR);
      
      s.btn_selR.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 s.txta2.setText("");
        	 Select_RealStudyTime mR = new Select_RealStudyTime();
             mR.loadRealStudyTime(s.txta2);
         }
      });
      
      s.btn_selS = new JButton("계획 학습시간");
      s.btn_selS.setLocation(40,410);
      s.btn_selS.setSize(120,80);
      contentPane.add(s.btn_selS);
      s.btn_selS.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 s.txta3.setText("");
        	 Select_ScheduleStudyTime mS = new Select_ScheduleStudyTime();
             mS.loadScheduleStudytime(s.txta3);
         }
      });
      
    //---------------------    데이터 넣기    -------------------------------------------
      
      s.btn_insP = new JButton("회원정보 넣기");
      s.btn_insP.setLocation(510,60);
      s.btn_insP.setSize(120,40);
      contentPane.add(s.btn_insP);
      s.btn_insP.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	
        	 s.txta1.setText("");
        	 s.Insert_pro = "3,01012345673,asd1233";
         	String[] array = s.Insert_pro.split(",");
         	String serialnum = array[0];
       		String phonenum = array[1];
       		String password1 = array[2];
       		
        	Insert_Profile.insert( serialnum, phonenum, password1, s.txta1);
         }
      });
      
      s.btn_insR = new JButton("실제학습 넣기");
      s.btn_insR.setLocation(510,235);
      s.btn_insR.setSize(120,40);
      contentPane.add(s.btn_insR);
      s.btn_insR.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 s.txta2.setText("");
        	 s.Insert_rst = "2,3,2020-02-11,3";
         	String[] array = s.Insert_rst.split(",");
         	String serialnum = array[0];
       		String studytime = array[1];
       		String date = array[2];
       		String subject = array[3];
     		
        	Insert_RealStudyTime.insert(serialnum, studytime, date, subject, s.txta2);
         }
      });
      
      s.btn_insS = new JButton("계획학습 넣기");
      s.btn_insS.setLocation(510,410);
      s.btn_insS.setSize(120,40);
      contentPane.add(s.btn_insS);
      s.btn_insS.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	
        	 s.txta3.setText("");
        	 s.Insert_sst = "2,3,2020-02-11,3";
        	String[] array = s.Insert_sst.split(",");
        	String serialNum = array[0];
      		String studytime = array[1];
      		String date = array[2];
      		String subject = array[3];
      		
         	Insert_ScheduleStudyTime.insert(serialNum, studytime, date, subject, s.txta3);
         }
      });
      
    //---------------------    데이터 삭제하기    -----------------------------------------
      
      s.btn_delP = new JButton("회원정보 삭제");
      s.btn_delP.setLocation(510,105);
      s.btn_delP.setSize(120,40);
      contentPane.add(s.btn_delP);
      s.btn_delP.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 s.txta1.setText("");
        	 s.Delete_pro = "01012345673,asd1233";
        	 String[] array = s.Delete_pro.split(",");
        	 String delphonenum = array[0];
        	 String delpassword = array[1];
        	 Delete_Profile.delete(delphonenum, delpassword, s.txta1);
         }
      });
      
      s.btn_delR = new JButton("실제학습 삭제");
      s.btn_delR.setLocation(510,280);
      s.btn_delR.setSize(120,40);
      contentPane.add(s.btn_delR);
      s.btn_delR.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 s.txta2.setText("");
        	 s.Delete_rst = "2,3,2020-02-11,3";
          	String[] array = s.Delete_rst.split(",");
          	String serialNum = array[0];
        	String studytime = array[1];
        	String date = array[2];
        	String subject = array[3];
        		
        	Delete_RealStudyTime.delete(serialNum, studytime, date, subject, s.txta2);
         }
      });
      
      s.btn_delS = new JButton("계획학습 삭제");
      s.btn_delS.setLocation(510,455);
      s.btn_delS.setSize(120,40);
      contentPane.add(s.btn_delS);
      s.btn_delS.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 s.txta3.setText("");
        	 s.Delete_sst = "2,3,2020-02-11,3";
         	String[] array = s.Delete_sst.split(",");
         	String serialNum = array[0];
       		String studytime = array[1];
       		String date = array[2];
       		String subject = array[3];
       		
          	Delete_ScheduleStudyTime.delete(serialNum, studytime, date, subject, s.txta3);
         }
      });
      
      
      s.btn_clr = new JButton("값 지우기");
      s.btn_clr.setLocation(660,60);
      s.btn_clr.setSize(100,200);
      contentPane.add(s.btn_clr);
      s.btn_clr.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 s.txta1.setText("");
        	 s.txta2.setText("");
        	 s.txta3.setText("");
         }
      });
      
      s.btn_exit = new JButton("종료");
      s.btn_exit.setLocation(660,300);
      s.btn_exit.setSize(100,200);
      contentPane.add(s.btn_exit);
      s.btn_exit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });
      
      /////////////////////////////////////////////////////////
      mJFrame.setSize(800,600);
      mJFrame.setVisible(true);
      
      
   }

}
