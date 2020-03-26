package prototype;

class R_Schedule {
	static private R_Schedule mR_Schedule = null;

	private R_Schedule() {
	}

	public static R_Schedule getInstance() {
		if (mR_Schedule == null)
			mR_Schedule = new R_Schedule();
		return mR_Schedule;
	}

	void R_Schedule_Start() {
		try {
			SocketThread mSocketThread = SocketThread.get();

			System.out.println("���� �н��� ������ �Է¹�ư�� �������ϴ�.");
			mSocketThread.write("Today Real Schedule button pushed.");

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
			mSocketThread.write("get Real Study Time");

		} catch (Exception e) {

		}
	}
}
