package dbtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Raspberry_Socket {

	public Raspberry_Socket() {
		
		SingleTon sR = SingleTon.getInsTanse();
		
		while (true) {

			try {
				ServerSocket serversock = new ServerSocket(8888);
				System.out.println("클라이언트 접속 대기 중...");
				Socket socket = serversock.accept();
				System.out.println("클라이언트 접속");

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				sR.raspData_singleTon = in.readLine();
				System.out.println("Received : " + sR.raspData_singleTon);
				out.println("Echo : " + sR.raspData_singleTon);
				System.out.println("Send : " + sR.raspData_singleTon);
				socket.close();
				serversock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
