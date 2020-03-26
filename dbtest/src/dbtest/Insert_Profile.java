package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextArea;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class Insert_Profile {
	public static void insert(String serialnum, String phonenum, String password1, JTextArea txtArea) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2", "root", "1234");

			String sql = "INSERT INTO profile VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, serialnum);
			pstmt.setString(2, phonenum);
			pstmt.setString(3, password1);
			
			
			
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("������ �Է� ����");
				txtArea.append("�߰��� ���� �ٽ��ѹ� Ȯ���� �ּ���.\n");

			} else {
				System.out.println("������ �Է� ����");
				txtArea.append("�߰��Ǿ����ϴ�. \n");
			}
		
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			txtArea.append("�̹� ����� �����Ͱ� �����մϴ�.\n");
			e.printStackTrace();

		}catch (Exception e) {
			txtArea.append("�Է°��� �ٽ��ѹ� Ȯ���� �ּ���.\n");
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
