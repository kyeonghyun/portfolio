package prototype;

class Singup {
	static private Singup mSingup = null;

	private Singup() {
	}

	public static Singup getInstance() {
		if (mSingup == null)
			mSingup = new Singup();
		return mSingup;
	}

	void Singup_Start() {
		try {
			SocketThread mSocketThread = SocketThread.get();

			System.out.println("회원가입 버튼을 눌렀습니다.");
			mSocketThread.write("Sing Up button pushed.");

			String readData = null;

			while (true) {
				try {
					readData = mSocketThread.readData();

					if (readData != null) {
						break;
					}
					Thread.sleep(100);

				} catch (Exception e) {

				}
			}
			mSocketThread.write("get PhonNum");

			readData = null;

			while (true) {
				try {
					readData = mSocketThread.readData();

					if (readData != null) {
						break;
					}
					Thread.sleep(100);

				} catch (Exception e) {

				}
			}
			mSocketThread.write("get PW1");

			readData = null;

			while (true) {
				try {
					readData = mSocketThread.readData();

					if (readData != null) {
						break;
					}
					Thread.sleep(100);

				} catch (Exception e) {

				}
			}
			mSocketThread.write("get PW2");

			readData = null;

			while (true) {
				try {
					readData = mSocketThread.readData();

					if (readData != null) {
						break;
					}
					Thread.sleep(100);

				} catch (Exception e) {

				}
			}
			mSocketThread.write("get SerialNum");

		} catch (Exception e) {

		}
	}
}
