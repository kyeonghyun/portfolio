package o2b2;

public class And_SingUp {
	static private And_SingUp mSignup = null;

	private And_SingUp() {
	}

	public static And_SingUp getInstance() {
		if (mSignup == null)
			mSignup = new And_SingUp();
		return mSignup;
	}
/////////////////////////////////////
	void Sign_up() {					
		try {
	
			And_DBManager mDB = And_DBManager.getInstance();
			And_SocketThread mSocketThread = And_SocketThread.get();
			mSocketThread.write("Login button pushed.");

			while (true) {
				/*-------------------------ID--------------------------------*/

				System.out.println("----아이디, 비밀번호 입력을 기다리는 중----");
				String readData = null;

				while (true) { // ID 입력을 받을때까지 기다림.
					try {
						readData = mSocketThread.readData();          

						if (readData != null) { // 값을받아 null이 아니면 break;
							break;
						}
						Thread.sleep(100);
					} catch (Exception e) {

					}
				}

				System.out.println("----아이디, 비밀번호 입력을 받아 빠져나옴----");
				System.out.println("받은 ID 값 : " + readData);

				if (mDB.ID_Match(readData)) {
					mSocketThread.write("1"); // 아이디가 겹치면 1 값 App client 에 전송
					//
					SingleTon s = SingleTon.getInstanse();
					s.Insert_pro = readData;
		         	String[] array = s.Insert_pro.split(",");
		         	String serialnum = array[0];
		       		String phonenum = array[1];
		       		String password1 = array[2];
		       		Insert_Profile.insert(serialnum, phonenum, password1, s.txta1);
					//
				}  else {
					mSocketThread.write("0"); // ID가 맞지않으면 0 값 App client 에 전송    
				}

				Thread.sleep(200);
				break;
			}
			System.out.println("아이디 중복확인 완료");
		} catch (Exception e) {

		}
	}
}
