package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextArea;

public class Delete_Profile {
	
	public static void delete(String delphonenum ,String delpassword , JTextArea txtArea) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2", "root", "1234");
		
		String sql = "Delete from profile where phonenum = ? and password1= ?";
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, delphonenum);
		pstmt.setString(2, delpassword);
		
		
		int count = pstmt.executeUpdate();
		if (count == 0) {
			System.out.println("����� row: "+ count);
			txtArea.append("������ ���� �ٽ��ѹ� Ȯ���� �ּ���.\n");
		}else {
			System.out.println("������ �Է� ����");
			txtArea.append("���� �Ǿ����ϴ�.\n");
		}
		
		
	} catch (Exception e) {
		txtArea.append("�����Դϴ� ������ ���� �ٽ��ѹ� Ȯ���� �ּ���.\n");
		e.printStackTrace();

	} finally {
		try {
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
}
