package dbtest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class App_Socket {
	public App_Socket() {

		try {

			ServerSocket server = new ServerSocket(3154);
			System.out.println("Wating Connect ..");

			Socket sock = server.accept();
			InetAddress inetaddr = sock.getInetAddress();
			System.out.println(inetaddr.getHostAddress() + " 로부터 접속했습니다.");

			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String line = null;

			String ID = "1234";
			String PW = "5678";
			String CN = "Client Message.";

			while ((line = br.readLine()) != null) {

				////////////////////// 입력한 ID 가 맞을경우///////////////////////////////////
				if (line.equals(ID)) {
					System.out.println("ID Success");
					pw.println("1");
					pw.flush();
					while ((line = br.readLine()) != null) {
						if (line.equals(PW)) {
							System.out.println("PW Success"); // 패스워드가 맞을경우
							pw.println("1");
							pw.flush();

						} else if (line.equals("")) {
							System.out.println("PW 공백"); // 패스워드 란이 공백일 경우
							pw.println("");
							pw.flush();
						} else {
							System.out.println("PW Failed"); // 패스워드가 틀렸을 경우
							pw.println("0");
							pw.flush();
							break;
						}
						break;
					}
				}

				/////////////////////////// 소켓통신 접속 알림//////////////////////////////////
				else if (line.equals(CN)) {
					System.out.println("connected");
					pw.println("connected");
					pw.flush();
				}

				/////////////////////////// ID입력란이 공백일 경우///////////////////////////////
				else if (line.equals("")) {
					System.out.println("ID 공백");
					pw.println("");
					pw.flush();
				}
				///////////////////////// 입력한 ID가 틀릴경우///////////////////////////////////
				else {
					System.out.println("ID Failed");
					pw.println("0");
					pw.flush();
				}
			}

			pw.close();
			br.close();
			sock.close();
			Thread.sleep(5);
			System.out.println("disconnect");

		} catch (Exception e) {

			System.out.println(e);
		}
	}
}
