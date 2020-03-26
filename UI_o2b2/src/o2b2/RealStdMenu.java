package o2b2;

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import o2b2.JPanel01.JPanelTest;

class JPanel04 extends JPanel {

	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;

	private JLabel jlabel1;

	private JTextField jtextfield1;

	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	SingleTon s =SingleTon.getInstanse();
	JComboBox<String> c = new JComboBox();
	
	
	////////////////	�޺��ڽ��� �� �ֱ�
	void combo_loadData() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	
  	    ImageIcon img1 = new ImageIcon("image/book3.png");  //�̹��� ���
	    JLabel imagelJLabel1 = new JLabel (img1);     
	    imagelJLabel1.setSize(200,200);
	    imagelJLabel1.setLocation(10,60);
	    add(imagelJLabel1);
	    
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/o2b2", "root", "1234");
		
			stmt = conn.createStatement();
		
			String sql = "SELECT * FROM realstudytime ORDER BY serialnum asc;";
		
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
				String serialnum = rs.getString(1);
				s.SelectRealserialnum_singleTon.add(rs.getString(1));
				String studytime = rs.getString(2);
				s.SelectRealstudytime_singleTon.add(rs.getString(2));
				String date = rs.getString(3);
				s.SelectRealdate_singleTon.add(rs.getString(3));
				String subject = rs.getString(4);
				s.SelectRealsubject_singleTon.add(rs.getString(4));
		
				s.realstudytimeval = serialnum + "/" + studytime + "/" + date + "/"+ subject + "\n";
				c.addItem(s.realstudytimeval);
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
		}add(c);
	}
	////////////     ������ ������ ����
	void select_del() {
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c.getSelectedItem().toString() != null) {
					s.Delete_rst = c.getSelectedItem().toString();
				}
				
				
			}
		});
	}
	////////////////	�޺��ڽ� �����
	void makeCombo() { // select �ڽ�
		
		c.setSize(500, 40);
		c.setLocation(200, 385);
		combo_loadData();
		select_del();

	}

	public JPanel04(JPanelTest win) {

		SingleTon s = SingleTon.getInstanse();

		jlabel1 = new JLabel("���� �н��ð�");
		jlabel1.setSize(200, 40);
		jlabel1.setLocation(365, 10);
		jlabel1.setForeground(Color.white); // �� ����
		jlabel1.setFont(jlabel1.getFont().deriveFont(20.0f));
		add(jlabel1);

		ImageIcon img = new ImageIcon("image/oba-study2.jpg"); // �̹��� ���
		JLabel imagelJLabel = new JLabel(img);
		imagelJLabel.setSize(200, 60);
		imagelJLabel.setLocation(8, 45);

		add(imagelJLabel);
		setVisible(true); // ȭ�鿡 ���̱�

		makeCombo();

		setLayout(null);
		
		
		jButton1 = new JButton(new ImageIcon("image/UP.PNG"));
		jButton1.setSize(130, 40);
		jButton1.setLocation(40, 240);
		//
		jButton1.setBorderPainted(false);
	     //JButton�� Border(�ܰ���)�� �����ش�.   
		jButton1.setContentAreaFilled(false);
	     //JButton�� ���뿵�� ä��� ����
		jButton1.setFocusPainted(false);
		add(jButton1);
		//
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				makeCombo();
				
				jTextArea1.setText("");
				Select_RealStudyTime mR = new Select_RealStudyTime();
				int countnum = mR.loadRealStudyTime(jTextArea1);
				if(countnum == 1)
				{
					System.out.println("count num ==1");
//					makeCombo();
				}
			}
		});
		//

		jButton2 = new JButton(new ImageIcon("image/IN.PNG"));
		jButton2.setSize(130, 40);
		jButton2.setLocation(40, 450);
		add(jButton2);
		//
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				try {
					s.get_textfield_realstudy = jtextfield1.getText();
					System.out.println(s.get_textfield_realstudy);
					jTextArea1.append(s.get_textfield_realstudy+"\n");
					jtextfield1.setText(null);
					String[] array = s.get_textfield_realstudy.split("/");
					String serialNum = array[0];
					String studytime = array[1];
					String date = array[2];
					String subject = array[3];

					Insert_RealStudyTime.insert(serialNum, studytime, date, subject, jTextArea1);
					c.addItem(s.get_textfield_realstudy);
					//
//					c.revalidate();
//					c.repaint(); 
					
				} catch (Exception e2) {
					// TODO: handle exception
					jTextArea1.append("�����߽��ϴ�. ���� �ٽ� �ѹ� Ȯ���� �ּ���.");
				}
				//
				}
		});
		//

		jButton3 = new JButton(new ImageIcon("image/DL.PNG"));
		jButton3.setSize(130, 40);
		jButton3.setLocation(40, 385);
		add(jButton3);

		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextArea1.setText("");
//				c.removeAllItems();
//				combo_loadData();
				select_del();
				System.out.println(s.Delete_rst);
				if(s.Delete_rst != null) {
					String[] array = s.Delete_rst.split("/");
					String serialNum = array[0];
					String studytime = array[1];
					String date = array[2];
					String subject = array[3];
	
					Delete_RealStudyTime.delete(serialNum, studytime, date, subject, jTextArea1);
					/////////////////////
					c.removeItem(s.Delete_rst);
					
					if(c.getItemAt(0) ==null)
					{
						System.out.println("c.removeAllItems()");
//						c.removeAllItems();
					}
				}
				else {
					System.out.println("������ ���� �����ϴ�.");
					c.removeAll();
//					remove(c);
				}
			}
		});
		//

		jButton4 = new JButton(new ImageIcon("image/CL.PNG"));
		jButton4.setSize(130, 40);
		jButton4.setLocation(40, 310);
		add(jButton4);
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextArea1.setText("");
			}
		});
		//

		jTextArea1 = new JTextArea();
		jScrollPane1 = new JScrollPane(jTextArea1);
		jScrollPane1.setSize(500, 300);
		jScrollPane1.setLocation(200, 50);
		add(jScrollPane1);

		jtextfield1 = new JTextField();
		jtextfield1.setSize(500, 40);
		jtextfield1.setLocation(200, 450);
		add(jtextfield1);

	}

}

public class RealStdMenu {

}
