package o2b2;

class And_Server extends Thread {
	public void run() {
		super.run();
		try {
			And_DBManager mDB = And_DBManager.getInstance();
			mDB.DB_Join();
			System.out.println("ȸ�� �� : " + mDB.DB_Select_countNum("select count(*) From profile"));
			mDB.DB_RealStudyTime_countNum("select count(*) From realstudytime");
			mDB.DB_Select_value("select * From profile");
			
			And_SocketThread mSocketThread = And_SocketThread.get();
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
				if (readData.equals("1")) { // �α��� ��ư"1"
					And_Login mlogin = And_Login.getInstance();
					mlogin.login_start();
				} else if (readData.contentEquals("2")) { // ȸ������ ��ư"2"
//					And_SingUp mSingup = And_SingUp.getInstacne();
					System.out.println("�ȵ���̵忡�� ���� ��ư �Ǻ� �� : " + readData);
		            And_SingUp mSingup = And_SingUp.getInstance();
		            mSingup.singup_start();
				}

			}

		} catch (Exception e) {

		}
	}
}