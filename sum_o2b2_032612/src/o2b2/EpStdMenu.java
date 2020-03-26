package o2b2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import o2b2.JPanel01.JPanelTest;

class JPanel03 extends JPanel {

	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JLabel jlabel1;
	private JTextField jtextfield1;
	private JTextField jtextfield2;
	private JTextField jtextfield3;
	private JTextField jtextfield4;
	
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	
	SingleTon s =SingleTon.getInstanse();
	
	JComboBox<String> c = new JComboBox();
	JTable table;
	
	static final int TABLE_MENU = 0;
	
	static final int TABLE_SERIALNUM = 0;
	static final int TABLE_PHONENUM = 1;
	static final int TABLE_DATE = 2;
	static final int TABLE_SUBJECT = 3;
    final String[] columnType = {"�ø���ѹ�", "���νð�", "��¥", "�����"};
    static boolean DBGTest = false;
    
	void makeCombo() { // select �ڽ�
		c.setSize(300, 40);
		c.setLocation(300, 680);
		c.setFont(new Font("�������",Font.BOLD,20));
//		c.addItem("data1"); // �����Ͱ� �ֱ�

		
  	    ImageIcon img1 = new ImageIcon("image/book5.png");  //�̹��� ���
	    JLabel imagelJLabel1 = new JLabel (img1);     
	    imagelJLabel1.setSize(200,200);
	    imagelJLabel1.setLocation(70,90);
	    add(imagelJLabel1);
	    

		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2", "root", "1234");
		
			stmt = conn.createStatement();
		
			String sql = "SELECT * FROM schedulestudytime ORDER BY serialnum asc;";
		
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
				String serialnum = rs.getString(1);
				s.SelectScheduleserialnum_singleTon.add(rs.getString(1));
				String studytime = rs.getString(2);
				s.SelectSchedulestudytime_singleTon.add(rs.getString(2));
				String date = rs.getString(3);
				s.SelectScheduledate_singleTon.add(rs.getString(3));
				String subject = rs.getString(4);
				s.SelectSchedulesubject_singleTon.add(rs.getString(4));
		
				s.schedulestudytimeval = serialnum + "/" + studytime + "/" + date + "/"+ subject + "\n";
				c.addItem(s.schedulestudytimeval);
				
			}
	
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		add(c);
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.Delete_sst = c.getSelectedItem().toString();
			}
		});
	}

////

	public void init() {

		// Panel p = new Panel();

	}

	public JPanel03(JPanelTest win) {
		
		
		SingleTon s = SingleTon.getInstanse();
		
		makeCombo();
		// init();

		setLayout(null);

		uploadJtable();
		
		ImageIcon img = new ImageIcon("image/oba-study2.jpg"); // �̹��� ���
		JLabel imagelJLabel = new JLabel(img);
		imagelJLabel.setSize(170, 60);
		imagelJLabel.setLocation(1000, 650);
		
		ImageIcon img1 = new ImageIcon("image/exp1.png");  //�̹��� ���
	    JLabel imagelJLabel1 = new JLabel (img1);     
	    imagelJLabel1.setSize(300,60);
	    imagelJLabel1.setLocation(400,42);
	    
	    add(imagelJLabel1);

		add(imagelJLabel);
		setVisible(true); // // ȭ�鿡 ���̱�

//		jlabel1 = new JLabel("��ȹ �н��ð�");
//		jlabel1.setSize(200, 40);
//		jlabel1.setLocation(365, 10);
//		jlabel1.setForeground(Color.white); // �� ����
//		jlabel1.setFont(jlabel1.getFont().deriveFont(20.0f));
//		add(jlabel1);

		jButton1 = new JButton(new ImageIcon("image/UP.PNG"));
		jButton1.setSize(170, 40);
		jButton1.setLocation(65, 310);
		add(jButton1);
		//
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextArea1.setText("");
				Select_ScheduleStudyTime mS = new Select_ScheduleStudyTime();
				mS.loadScheduleStudytime(jTextArea1);
				uploadJtable();
			}
		});
		//

		jButton2 = new JButton(new ImageIcon("image/inbtn.PNG"));
		jButton2.setSize(170, 40);
		jButton2.setLocation(65, 580);
		add(jButton2);
		//
//		Insert_ScheduleStudyTime Insert_ScheduleStudyTime = new Insert_ScheduleStudyTime();
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ؽ�Ʈ �ʵ忡�� �޾ƿ� ���� INSERT�� ����ϱ� ����ó��???????????????????????
				try {
					jTextArea1.setText("");
					s.get_textfield_schedulestudy = jtextfield1.getText();
					System.out.println(s.get_textfield_schedulestudy);
					jTextArea1.append(s.get_textfield_schedulestudy+"\n");
					jtextfield1.setText(null);					
					String[] array = s.get_textfield_schedulestudy.split("/");
					String serialNum = array[0];
					String studytime = array[1];
					String date = array[2];
					String subject = array[3];

					Insert_ScheduleStudyTime.insert(serialNum, studytime, date, subject, jTextArea1);
					c.addItem(s.get_textfield_schedulestudy);
				} catch (Exception e2) {
					// TODO: handle exception
					jTextArea1.append("�����߽��ϴ�. ���� �ٽ� �ѹ� Ȯ���� �ּ���.");
					JOptionPane.showMessageDialog(null, "�����߽��ϴ�. ���� Ȯ�����ּ���", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				}
			}
	      });
		//

		jButton3 = new JButton(new ImageIcon("image/DL.PNG"));
		jButton3.setSize(170, 40);
		jButton3.setLocation(65, 490);
		add(jButton3);
		//
		jButton3.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	 jTextArea1.setText("");
//	        	 s.Delete_sst = "2,3,2020-02-11,3";
	         	String[] array = s.Delete_sst.split("/");
	         	String serialNum = array[0];
	       		String studytime = array[1];
	       		String date = array[2];
	       		String subject = array[3];
	       		
	          	Delete_ScheduleStudyTime.delete(serialNum, studytime, date, subject, jTextArea1);
	          	c.removeItem(s.Delete_sst);
	         }
	      });
		//

		jButton4 = new JButton(new ImageIcon("image/clbtn.PNG"));
		jButton4.setSize(170, 40);
		jButton4.setLocation(65, 400);
		add(jButton4);
		//
		jButton4.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 jTextArea1.setText("");

	         }
	      });
		//
		////////		�Ⱥ��̰� Location�ű�
		jTextArea1 = new JTextArea();
		jScrollPane1 = new JScrollPane(jTextArea1);
		jScrollPane1.setSize(300, 320);
		jScrollPane1.setLocation(2300, 120);
		jTextArea1.setFont(new Font("�������",Font.BOLD,20));
		add(jScrollPane1);

		jtextfield2 = new JTextField();
		jtextfield2.setSize(300, 40);
		jtextfield2.setLocation(700, 680);
		jtextfield2.setFont(new Font("�������",Font.BOLD,20));
		add(jtextfield2);
		
//		jtextfield2 = new JTextField();
//		jtextfield2.setSize(150, 40);
//		jtextfield2.setLocation(505, 650);
//		add(jtextfield2);
//		
//		jtextfield3 = new JTextField();
//		jtextfield3.setSize(150, 40);
//		jtextfield3.setLocation(710, 650);
//		add(jtextfield3);
//		
//		jtextfield4 = new JTextField();
//		jtextfield4.setSize(150, 40);
//		jtextfield4.setLocation(945, 650);
//		add(jtextfield4);

	}
	
	void uploadJtable() {
    	Select_RealStudyTime sP = new Select_RealStudyTime();
//    	sP.initValue();
    	sP.loadRealStudyTime();
        //Object[][] pro_obj= new Object[s.serialnum_singleTon.size()+1][3];
        Object[][] real_obj= new Object[s.SelectRealserialnum_singleTon.size()+2][4];
//        table = new JTable(pro_obj,columnType);

        int count =0;

        System.out.println("columnType.length : "+columnType.length);
        for(int i=0; i<columnType.length; i++)
        {
        	real_obj[TABLE_MENU][i] = columnType[i];
        }

        for (int i = 0; i < s.SelectRealserialnum_singleTon.size(); i++) {
        	real_obj[i+1][TABLE_SERIALNUM]=s.SelectRealserialnum_singleTon.get(i);
        	real_obj[i+1][TABLE_PHONENUM]=s.SelectRealstudytime_singleTon.get(i);
        	real_obj[i+1][TABLE_DATE]=s.SelectRealdate_singleTon.get(i);
        	real_obj[i+1][TABLE_SUBJECT]=s.SelectRealsubject_singleTon.get(i);
        	//count++;
        	System.out.println("s.serialnum_singleTon.get(i) : " + s.SelectRealserialnum_singleTon.get(i));
        };
//        count++;
//        if(DBGTest == false) {
//	        for(int i=0; i<columnType.length; i++)
//	        {
//	        	pro_obj[count][i] = columnType[i];
//	        }
//	        DBGTest = true;
//        }
		if(table != null)
		{
			System.out.println("table != null");
			remove(table);
	        revalidate();
	        repaint();
		}
        table = new JTable(real_obj,columnType);
//		JTable table = new JTable(pro_obj,columnType);
        table.setRowHeight(25);
        table.setSize(450,500);
        table.setLocation(650,120);
        table.setFont(new Font("�������",Font.BOLD,20));
        add(table);
        revalidate();
        repaint();
        
        
    }

}

public class EpStdMenu {

}
