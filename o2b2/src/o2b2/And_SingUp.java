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

				System.out.println("----���̵�, ��й�ȣ �Է��� ��ٸ��� ��----");
				String readData = null;

				while (true) { // ID �Է��� ���������� ��ٸ�.
					try {
						readData = mSocketThread.readData();          

						if (readData != null) { // �����޾� null�� �ƴϸ� break;
							break;
						}
						Thread.sleep(100);
					} catch (Exception e) {

					}
				}

				System.out.println("----���̵�, ��й�ȣ �Է��� �޾� ��������----");
				System.out.println("���� ID �� : " + readData);

				if (mDB.ID_Match(readData)) {
					mSocketThread.write("1"); // ���̵� ��ġ�� 1 �� App client �� ����
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
					mSocketThread.write("0"); // ID�� ���������� 0 �� App client �� ����    
				}

				Thread.sleep(200);
				break;
			}
			System.out.println("���̵� �ߺ�Ȯ�� �Ϸ�");
		} catch (Exception e) {

		}
	}
}
