package dbtest;


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

@SuppressWarnings("serial")
public class Frame_DB extends JFrame {
   // 전역변수 사용하기 static String sst = null;
   public Frame_DB() {
      
	   
      
      setTitle("o2b2 DB");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container contentPane = getContentPane();
 
      
      contentPane.setLayout(null);
      
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
      
      
      JTextArea txta1 = new JTextArea();
      JScrollPane scroll1 = new JScrollPane(txta1);
      scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scroll1.setBounds(190, 60, 300, 100);
      contentPane.add(scroll1);
      
      JTextArea txta2 = new JTextArea();
      JScrollPane scroll2 = new JScrollPane(txta2);
      scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scroll2.setBounds(190,235, 300, 100);
      contentPane.add(scroll2);
      
      JTextArea txta3 = new JTextArea();
      JScrollPane scroll3 = new JScrollPane(txta3);
      scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scroll3.setBounds(190,410, 300, 100);
      contentPane.add(scroll3);
          
      
      /////////////////////    JButton    //////////////////////////////////////////////
      
      //---------------------    불러오기    -------------------------------------------
      JButton btn_selP = new JButton("회원정보");
      btn_selP.setLocation(40,60);
      btn_selP.setSize(120,80);
      contentPane.add(btn_selP);
      
      btn_selP.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	      	
        	txta1.setText("");
            Select_Profile mP = new Select_Profile();
            mP.loadProfile(txta1);
         }
      });
      
      JButton btn_selR = new JButton("실제 학습 시간");
      btn_selR.setLocation(40,235);
      btn_selR.setSize(120,80);
      contentPane.add(btn_selR);
      
      btn_selR.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 txta2.setText("");
        	 Select_RealStudyTime mR = new Select_RealStudyTime();
             mR.loadRealStudyTime(txta2);
         }
      });
      
      JButton btn_selS = new JButton("계획 학습시간");
      btn_selS.setLocation(40,410);
      btn_selS.setSize(120,80);
      contentPane.add(btn_selS);
      btn_selS.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 txta3.setText("");
        	 Select_ScheduleStudyTime mS = new Select_ScheduleStudyTime();
             mS.loadScheduleStudytime(txta3);
         }
      });
      
    //---------------------    데이터 넣기    -------------------------------------------
      
      JButton btn_insP = new JButton("회원정보 넣기");
      btn_insP.setLocation(510,60);
      btn_insP.setSize(120,40);
      contentPane.add(btn_insP);
      btn_insP.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	
        	txta1.setText("");
        	String Insert_pro = "3,01012345673,asd1233";
         	String[] array = Insert_pro.split(",");
         	String serialnum = array[0];
       		String phonenum = array[1];
       		String password1 = array[2];
       		
        	Insert_Profile.insert( serialnum, phonenum, password1, txta1);
         }
      });
      
      JButton btn_insR = new JButton("실제학습 넣기");
      btn_insR.setLocation(510,235);
      btn_insR.setSize(120,40);
      contentPane.add(btn_insR);
      btn_insR.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	txta2.setText("");
        	String Insert_rst = "2,3,2020-02-11,3";
         	String[] array = Insert_rst.split(",");
         	String serialnum = array[0];
       		String studytime = array[1];
       		String date = array[2];
       		String subject = array[3];
     		
        	Insert_RealStudyTime.insert(serialnum, studytime, date, subject, txta2);
         }
      });
      
      JButton btn_insS = new JButton("계획학습 넣기");
      btn_insS.setLocation(510,410);
      btn_insS.setSize(120,40);
      contentPane.add(btn_insS);
      btn_insS.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	
        	txta3.setText("");
        	String Insert_sst = "2,3,2020-02-11,3";
        	String[] array = Insert_sst.split(",");
        	String serialNum = array[0];
      		String studytime = array[1];
      		String date = array[2];
      		String subject = array[3];
      		
         	Insert_ScheduleStudyTime.insert(serialNum, studytime, date, subject, txta3);
         }
      });
      
    //---------------------    데이터 삭제하기    -----------------------------------------
      
      JButton btn_delP = new JButton("회원정보 삭제");
      btn_delP.setLocation(510,105);
      btn_delP.setSize(120,40);
      contentPane.add(btn_delP);
      btn_delP.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 txta1.setText("");
        	 String Delete_pro = "01012345673,asd1233";
        	 String[] array = Delete_pro.split(",");
        	 String delphonenum = array[0];
        	 String delpassword = array[1];
        	 Delete_Profile.delete(delphonenum, delpassword, txta1);
         }
      });
      
      JButton btn_delR = new JButton("실제학습 삭제");
      btn_delR.setLocation(510,280);
      btn_delR.setSize(120,40);
      contentPane.add(btn_delR);
      btn_delR.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	txta2.setText("");
         	String Delete_rst = "2,3,2020-02-11,3";
          	String[] array = Delete_rst.split(",");
          	String serialNum = array[0];
        	String studytime = array[1];
        	String date = array[2];
        	String subject = array[3];
        		
        	Delete_RealStudyTime.delete(serialNum, studytime, date, subject, txta2);
         }
      });
      
      JButton btn_delS = new JButton("계획학습 삭제");
      btn_delS.setLocation(510,455);
      btn_delS.setSize(120,40);
      contentPane.add(btn_delS);
      btn_delS.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	txta3.setText("");
        	String Delete_sst = "2,3,2020-02-11,3";
         	String[] array = Delete_sst.split(",");
         	String serialNum = array[0];
       		String studytime = array[1];
       		String date = array[2];
       		String subject = array[3];
       		
          	Delete_ScheduleStudyTime.delete(serialNum, studytime, date, subject, txta3);
         }
      });
      
      
      JButton btn_clr = new JButton("값 지우기");
      btn_clr.setLocation(660,60);
      btn_clr.setSize(100,200);
      contentPane.add(btn_clr);
      btn_clr.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            txta1.setText("");
            txta2.setText("");
            txta3.setText("");
         }
      });
      
      JButton btn_exit = new JButton("종료");
      btn_exit.setLocation(660,300);
      btn_exit.setSize(100,200);
      contentPane.add(btn_exit);
      btn_exit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });
      
      /////////////////////////////////////////////////////////
      setSize(800,600);
      setVisible(true);
      
      
   }
//   public static void main(String[] args) {
//      Frame_DB m = new Frame_DB();
//   }
}
