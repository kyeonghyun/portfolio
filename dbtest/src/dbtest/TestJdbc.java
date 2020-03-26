package dbtest;

import java.sql.*;


public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/o2b2","root","1234");
		
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("create table test1(id int not null primary key auto_increment, name varchar(10));");
		}catch(Exception e) {
			
		
		e.printStackTrace();

		}
}
}
