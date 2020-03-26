package dbtest;

import java.sql.*;

public class dbTest_Insert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insert(null, "01023456789","asd1234");
	}
		
		public static void insert(String serialnum, String phonenum, String password1) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/o2b2", "root", "1234");
			
			String sql = "INSERT INTO profile VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, serialnum);
			pstmt.setString(2, phonenum);
			pstmt.setString(3, password1);
			
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("데이터 입력 실패");
			}else {
				System.out.println("데이터 입력 성공");
			}
			
			

		} catch (Exception e) {

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
