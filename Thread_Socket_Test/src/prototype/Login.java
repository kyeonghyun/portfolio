package prototype;

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

public class Login {
	static private Login mLogin = null;
	String Match_ID = "1234";
	String Match_PW = "5678";

	private Login() {
	}

	public static Login getInstance() {
		if (mLogin == null)
			mLogin = new Login();
		return mLogin;
	}

	void login_start() {
		try {
			SocketThread mSocketThread = SocketThread.get();

			System.out.println("�α��� ��ư�� �������ϴ�.");
			mSocketThread.write("Login button pushed.");

			String readData = null;
			while (true) {
				try {
					readData = mSocketThread.readData();

					if (readData != null) { // �����޾� null�� �ƴϸ� break;
						break;
					}
					Thread.sleep(100);
				} catch (Exception e) {

				}
			}

			if (readData.equals(Match_ID)) {
				System.out.println("ID�� �ùٸ��� �Է��Ͽ����ϴ�.");
				mSocketThread.write("ID Matched");

				readData = null;

				while (true) {
					try {
						readData = mSocketThread.readData();

						if (readData != null) { // �����޾� null�� �ƴϸ� break;
							break;
						}
						Thread.sleep(100);
					} catch (Exception e) {

					}
				}
				if (readData.equals(Match_PW)) {
					System.out.println("�н����带 �ùٸ��� �Է��Ͽ����ϴ�.");
					mSocketThread.write("PW Matched");
				}
			}
		} catch (Exception e) {

		}
	}
}
