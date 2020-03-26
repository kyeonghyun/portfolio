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
            System.out.println("----����ڰ� � ��ư�� �������� �����----");
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
            if (readData.equals("1")) { // �α��� ��ư
               Login mLogin = Login.getInstance();
               mLogin.login_start();

            } else if (readData.equals("2")) { // ȸ������ ��ư
               Singup mSingup = Singup.getInstance();
               mSingup.Singup_Start();

            } else if (readData.equals("3")) { // ������ �Է� ��ư
               Schedule mSchedule = Schedule.getInstance();
               mSchedule.Schedule_Start();

            } else if (readData.equals("4")) { // ���� �н��Ϸ��Ͽ� �н��� ������� �Է��ϴ� ��ư
               R_Schedule mR_Schedule = R_Schedule.getInstance();
               mR_Schedule.R_Schedule_Start();

            }

         }

      } catch (Exception E) {

      }
   }

}