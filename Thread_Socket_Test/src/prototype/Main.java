package prototype;

public class Main {
	public static void main(String[] args) {

		Frame mFrame= Frame.getInstance();
		
		Rasp_Main mRasp_Main = new Rasp_Main();
		mRasp_Main.start();
//		new Rasp_Main();
		
		And_Server mAnd_Server = new And_Server();
		mAnd_Server.start();	
	}
}
