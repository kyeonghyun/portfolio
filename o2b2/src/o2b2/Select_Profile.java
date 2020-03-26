package o2b2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class Select_Profile {
	String profileval = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// �Ű����� ����  loadProfile �����ε�
	public void loadProfile() {
			//
		SingleTon s = SingleTon.getInstanse();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2", "root", "1234");
			
				stmt = conn.createStatement();
			
				String sql = "SELECT * FROM profile ORDER BY serialnum asc;";
			
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
					// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
					String serialnum = rs.getString(1);
					s.phonenum_singleTon.add(rs.getString(2));
					String phonenum = rs.getString(2);
					s.password1_singleTon.add(rs.getString(3));
					String password1 = rs.getString(3);
			
					s.profileval = serialnum + " / " + phonenum + " / " + password1 + " "+ "\n";
					
					
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
		
	public void loadProfile(JTextArea txtArea) {
		//
		SingleTon s = SingleTon.getInstanse();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2", "root", "1234");
		
			stmt = conn.createStatement();
		
			String sql = "SELECT * FROM profile ORDER BY serialnum asc;";
		
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
				String serialnum = rs.getString(1);
				s.phonenum_singleTon.add(rs.getString(2));
				String phonenum = rs.getString(2);
				s.password1_singleTon.add(rs.getString(3));
				String password1 = rs.getString(3);
		
				s.profileval = serialnum + " / " + phonenum + " / " + password1 + " "+ "\n";
				txtArea.append(s.profileval);
				
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
