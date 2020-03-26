package o2b2;

import java.util.ArrayList;

public class Main_DB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame_DB mFrame_DB = Frame_DB.getInstance();
		
		And_Server mAnd_Server = new And_Server();
		mAnd_Server.start();
		
		Rasp_Main mRasp_Main = new Rasp_Main();
		mRasp_Main.start();
		
		
		StartSelect mLogin = new StartSelect();
		mLogin.Login();
		
		new Graph_Db();		

//		new App_Socket();
//		new Raspberry_Socket();
	}
}
