package o2b2;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

class ResultPanel extends JPanel{
	SingleTon s = SingleTon.getInstanse();
	private JButton jButton_select;
	static boolean isFirstBoot = true;

	public void paint(Graphics g) {
		
		////	��ư	////////////////////////////////////////////////////////////////
		jButton_select = new JButton("����");
		jButton_select.setSize(100, 40);
		jButton_select.setLocation(610, 455);
		add(jButton_select);
		////	�޺��ڽ����� ���õ� �� ��������	////
		select_serial();
		
		//////////////////////////////////////////////////////////////////////
		
		jButton_select.requestFocus();
		
		if(isFirstBoot) {
			makeCombo();
			isFirstBoot =false;
		}else {
			c.requestFocus();
		}
		
		setLayout(null);
		
		g.clearRect(0,0,getWidth(),getHeight());
		g.drawLine(50,  400,  700,  400);
		
		for (int i = 1; i< 11 ; i++) {
			g.drawString(i*2 + "", 25, 400-(35*i));
			g.drawLine(50,  400-(35*i), 700, 400-(35*i));
		}
		
		g.drawLine(50, 20, 50, 400);
		g.setColor(Color.BLUE);
		
		///////////////////////////////////		���� �н��ð� �׸���	////////////////////////////////////////////////////////////
		int x_data=70;
		int x_p =65;
		System.out.println("mListGraph : "+ s.mListGraph);
		for(int i=0; i< s.mListGraph.size(); i++)
		{
			g.drawString((i+1) + "����", x_p, 430);
			g.fillRect(x_data, 400 - s.mListGraph.get(i)*17, 10, s.mListGraph.get(i)*17);
			x_data+=100;
			x_p +=100;
		}
		

		///////////////////////////////////////		��ȹ�н��ð� �׸���	////////////////////////////////////////////////
		//	��ȹ�н� �ҷ�����
		
		g.setColor(Color.green);
		int x_data1=85;
		System.out.println("mListGraph_Schedule : "+ s.mListGraph_Schedule);
		for(int i=0; i< s.mListGraph_Schedule.size(); i++)
		{
			g.fillRect(x_data1, 400 - s.mListGraph_Schedule.get(i)*17, 10, s.mListGraph_Schedule.get(i)*17);
			x_data1+=100;
		}
//		setVisible(true); // ȭ�鿡 ���̱�
		
		////////////////////////////////////	��ư �׼�		///////////////////////////////////////////////////////////
		jButton_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////	��ư�� ������ �� repaint�Ͽ� �ٷ� �׷����� �������� �Ͽ���.	////
				revalidate();
	            repaint();
				
				loadRealStudyTime_sel();
				loadScheduleStudyTime_sel();
				
				System.out.println("���õ� �ø��� �ѹ�: " + s.select_serial);
				

				ArrayList<Integer> m = new ArrayList<Integer>();
				System.out.println("�����н� ī��Ʈ"+s.RealGraph_count);
				System.out.println("�����н� ��"+s.SelectRealstudytime2_singleTon);
				if (s.RealGraph_count < 7 ) {
					for (int i = s.RealGraph_count; i > 0; i--) {
						m.add(Integer.parseInt(s.SelectRealstudytime2_singleTon.get(s.SelectRealstudytime2_singleTon.size() - i)));
					}
				} else {
					for (int i = 7; i > 0; i--) {
						m.add(Integer
								.parseInt(s.SelectRealstudytime2_singleTon.get(s.SelectRealstudytime2_singleTon.size() - i)));
					}
				}

				
				ArrayList<Integer> mSchedule = new ArrayList<Integer>();
				System.out.println("�������н� ī��Ʈ"+s.ScheduleGraph_count);
				System.out.println("������ ��"+s.SelectSchedulestudytime2_singleTon);
				if (s.ScheduleGraph_count < 7 ) {
					for (int i = s.ScheduleGraph_count; i > 0; i--) {
						mSchedule.add(Integer.parseInt(s.SelectSchedulestudytime2_singleTon.get(s.SelectSchedulestudytime2_singleTon.size() - i)));
					}
				} else {
					for (int i = 7; i > 0; i--) {
						mSchedule.add(Integer
								.parseInt(s.SelectSchedulestudytime2_singleTon.get(s.SelectSchedulestudytime2_singleTon.size() - i)));
					}
				}
				s.setGraph(m);
				s.setGraph_S(mSchedule);
				setVisible(true); // ȭ�鿡 ���̱�
			}
		});
		
	}
	
	////////		�޺��ڽ� �����κ�	////////////
	JComboBox<String> c = new JComboBox();
	void combo_loadData() {
	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2", "root", "1234");
		
			stmt = conn.createStatement();
		
			String sql = "SELECT * FROM profile ORDER BY serialnum asc;";
		
			rs = stmt.executeQuery(sql);
			//
//			if(c.getItemCount() > 0) {
//				c.removeAllItems();
//			}
			while (rs.next()) {				
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
				String serialnum = rs.getString(1);
				s.SelectRealserialnum_singleTon.add(rs.getString(1));
		
				c.addItem(serialnum);
				System.out.println("c.addItem( !!! ");
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
		//	â�� ������� �� �ٷ� ���̰� �ϱ�
		c.requestFocus();
		
	}
	////		�޺��ڽ� �� ��������
	void select_serial() {
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c.getSelectedItem().toString() != null) {
					s.select_serial = c.getSelectedItem().toString();
				}
				
				
			}
		});
	}
	////////

	////////////////	�޺��ڽ� �����
	void makeCombo() { // select �ڽ�
		
		c.setSize(470, 40);
		c.setLocation(100, 455);
		combo_loadData();



	}
	
	////////////////		RealStudytime	���νð� �� ��������		//////////////////////////
public void loadRealStudyTime_sel() {
		
		SingleTon s =SingleTon.getInstanse();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		s.RealGraph_count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2", "root", "1234");
		
			stmt = conn.createStatement();
		
			String sql = "SELECT * FROM realstudytime where serialnum = " + s.select_serial;
		
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.


				s.SelectRealstudytime2_singleTon.add(rs.getString(2));
		
				s.RealGraph_count++;
				System.out.println("RealGraph_count: " + s.RealGraph_count);
				
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
	}

/////////////////////////////////			ScheduleStudytime	��������		/////////////////////////////////////

public void loadScheduleStudyTime_sel() {
	
	SingleTon s =SingleTon.getInstanse();
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	s.ScheduleGraph_count = 0;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2", "root", "1234");
	
		stmt = conn.createStatement();
	
		String sql = "SELECT * FROM schedulestudytime where serialnum = " + s.select_serial;
	
		rs = stmt.executeQuery(sql);
	
		while (rs.next()) {
			// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
			// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.

			s.SelectSchedulestudytime2_singleTon.add(rs.getString(2));

			s.ScheduleGraph_count++;
			System.out.println("ScheduleGraph_count: " + s.ScheduleGraph_count);
			
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
}



	
	
	
}

