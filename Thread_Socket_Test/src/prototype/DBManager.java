package prototype;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
	static private DBManager mDbManager = null;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	int id_index = 0;

	private ArrayList<String> mPH = new ArrayList<String>();
	private ArrayList<String> mPW = new ArrayList<String>();

	private DBManager() {

	}

	public static DBManager getInstance() {
		if (mDbManager == null)
			mDbManager = new DBManager();
		return mDbManager;
	}

	void DB_Join() { // DB ����..
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	int DB_Select_countNum(String sql) { // DB ȸ�� ���̺��� ��ü �� ����(ȸ�� ���� �ο� ��������)
		int Profile_count = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Profile_count = rs.getInt(1);
			}
		} catch (Exception e) {
		}

		return Profile_count;
	}

	/*
	 * // mID, mPW ArrayList�� ���̺��� ID PW ���� ���� �������.
	 */
	
	int DB_Select_value(String sql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				mPH.add(rs.getString(2));
				mPW.add(rs.getString(3));

			}
			System.out.println(mPH.size());
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	boolean ID_Match(String id) {
		System.out.println("isID �Լ� ����");
		System.out.println("mPH ������ : " + mPH.size());

		for (int i = 0; i < mPH.size(); i++) {
			System.out.println("isID �Լ�  for�� ����");
			if (mPH.get(i).equals(id)) { // Ŭ���̾�Ʈ ID �� mID ArrayList �� ���������� ���� ������ �ش� �ε��� ���� ����.					
				id_index = i;
				System.out.println("ID ����");
				return true;
			}
		}
		System.out.println("ID Ʋ��");
		return false;
	}

	boolean PW_Match(String pw) {
		System.out.println("isPW �Լ� ����");
		if (mPW.get(id_index).equals(pw)) { // DB_ID ������ �޾ƿ� �ε��� ���� �̿��� �ش�
			System.out.println("PW ����");
			return true;
		}
		System.out.println("PW Ʋ��");
		return false;
	}

	ArrayList<String> getID() {
		return mPH;
	}

	ArrayList<String> getPW() {
		return mPW;
	}
}
