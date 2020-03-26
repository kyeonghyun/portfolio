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

			System.out.println("로그인 버튼을 눌렀습니다.");
			mSocketThread.write("Login button pushed.");

			String readData = null;
			while (true) {
				try {
					readData = mSocketThread.readData();

					if (readData != null) { // 값을받아 null이 아니면 break;
						break;
					}
					Thread.sleep(100);
				} catch (Exception e) {

				}
			}

			if (readData.equals(Match_ID)) {
				System.out.println("ID를 올바르게 입력하였습니다.");
				mSocketThread.write("ID Matched");

				readData = null;

				while (true) {
					try {
						readData = mSocketThread.readData();

						if (readData != null) { // 값을받아 null이 아니면 break;
							break;
						}
						Thread.sleep(100);
					} catch (Exception e) {

					}
				}
				if (readData.equals(Match_PW)) {
					System.out.println("패스워드를 올바르게 입력하였습니다.");
					mSocketThread.write("PW Matched");
				}
			}
		} catch (Exception e) {

		}
	}
}
