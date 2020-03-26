package prototype;

import java.net.*;
import java.io.*;
import java.sql.*;

class And_Server extends Thread {
   public void run() {
      super.run();
      try {
         SocketThread mSocketThread = SocketThread.get();
         mSocketThread.join();

         while (true) {
            System.out.println("----사용자가 어떤 버튼을 누르는지 대기중----");
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
            if (readData.equals("1")) { // 로그인 버튼
               Login mLogin = Login.getInstance();
               mLogin.login_start();

            } else if (readData.equals("2")) { // 회원가입 버튼
               Singup mSingup = Singup.getInstance();
               mSingup.Singup_Start();

            } else if (readData.equals("3")) { // 스케줄 입력 버튼
               Schedule mSchedule = Schedule.getInstance();
               mSchedule.Schedule_Start();

            } else if (readData.equals("4")) { // 금일 학습완료하여 학습한 과목수를 입력하는 버튼
               R_Schedule mR_Schedule = R_Schedule.getInstance();
               mR_Schedule.R_Schedule_Start();

            }

         }

      } catch (Exception E) {

      }
   }

}