package dbtest;

import java.sql.*;

public class dbTest_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/o2b2", "root", "1234");

			stmt = conn.createStatement();

			String sql = "SELECT * FROM profile;";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
				String serialnum = rs.getString(1);
				String phonenum = rs.getString(2);
				String password1 = rs.getString(3);

				System.out.println(serialnum + " " + phonenum + " " + password1 + " ");
			}

		} catch (Exception e) {

			e.printStackTrace();

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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
