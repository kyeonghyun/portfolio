package o2b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Raspberry_Socket {

	public Raspberry_Socket() {
		
		SingleTon sR = SingleTon.getInstanse();
		//현재 시간 가져오기 부분
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		String nowDate = format1.format(time.getTime());
		System.out.println(nowDate);
		
		while (true) {

			try {
				ServerSocket serversock = new ServerSocket(8888);
				System.out.println("클라이언트 접속 대기 중...");
				Socket socket = serversock.accept();
				System.out.println("클라이언트 접속");

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				sR.raspStudyTime_singleTon = in.readLine();
				System.out.println("Received : " + sR.raspStudyTime_singleTon);
				out.println("Echo : " + sR.raspStudyTime_singleTon);
				System.out.println("Send : " + sR.raspStudyTime_singleTon);
				socket.close();
				serversock.close();
				
				String studyTime = sR.raspStudyTime_singleTon;
				String[] array;
				if(studyTime !=null) {
					studyTime = studyTime.trim();
					array = studyTime.split(":");
					String serialnum ="1";
					String subject = "0";
					Insert_RealStudyTime.insert(serialnum, array[1], nowDate, subject);
				}
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
