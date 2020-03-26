package o2b2;

import java.net.*;
import java.io.*;
import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

public class And_Login {
	static private And_Login mLogin = null;

	private And_Login() {
	}

	public static And_Login getInstance() {
		if (mLogin == null)
			mLogin = new And_Login();
		return mLogin;
	}

	void login_start() {					
		try {
	

			And_DBManager mDB = And_DBManager.getInstance();
			And_SocketThread mSocketThread = And_SocketThread.get();
			mSocketThread.write("Login button pushed.");

			while (true) {
				/*-------------------------ID--------------------------------*/

				System.out.println("----���̵�, ��й�ȣ �Է��� ��ٸ��� ��----");
				String readData = null;

				while (true) { // ID �Է��� ���������� ��ٸ�.
					try {
						readData = mSocketThread.readData();          

						if (readData != null) { // �����޾� null�� �ƴϸ� break;
							break;
						}
						Thread.sleep(100);
					} catch (Exception e) {

					}
				}

				System.out.println("----���̵�, ��й�ȣ �Է��� �޾� ��������----");
				System.out.println("���� ID �� : " + readData);

				if (mDB.ID_Match(readData)) {
					mSocketThread.write("1"); // ������ 1 �� App client �� ����

					/*-------------------------PW--------------------------------*/
					readData = null;
					while (true) {
						try {
							readData = mSocketThread.readData();

							if (readData != null) {
								break;
							}
							Thread.sleep(100);
						} catch (Exception e) {

						}
					}
					System.out.println("���� PW �� : " + readData);
					if (mDB.PW_Match(readData)) {
						mSocketThread.write("1");
						break;
					} else if (readData.equals("")) { // PW�� �����̸� ���� �� App client �� ����
						mSocketThread.write("");
					} else {
						mSocketThread.write("0"); // PW�� ���������� 0 �� App client �� ����
					}

				} else if (readData.equals("")) { // ID�� �����̸� ���� �� App client �� ����
					mSocketThread.write("");
				} else {
					mSocketThread.write("0"); // ID�� ���������� 0 �� App client �� ����    
				}

				Thread.sleep(200);
			}
			System.out.println("�α��� �Ϸ�");
		} catch (Exception e) {

		}
	}
}


