package o2b2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;

public class Select_ScheduleStudyTime {
	public void loadScheduleStudytime() {
		
		SingleTon s =SingleTon.getInstanse();
		
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
		
				s.schedulestudytimeval = serialnum + " / " + studytime + " / " + date + " / "+ subject + "\n";
				
				
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
	

public void loadScheduleStudytime(JTextArea txtArea) {
		
		SingleTon s =SingleTon.getInstanse();
		
		String schedulestudytimeval = null;
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
		
				schedulestudytimeval = serialnum + " / " + studytime + " / " + date + " / "+ subject + "\n";
				txtArea.append(schedulestudytimeval);
				
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
