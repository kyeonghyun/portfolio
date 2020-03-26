package prototype;

class Schedule {
	static private Schedule mSchedule = null;

	private Schedule() {
	}

	public static Schedule getInstance() {
		if (mSchedule == null)
			mSchedule = new Schedule();
		return mSchedule;
	}

	void Schedule_Start() {
		try {
			SocketThread mSocketThread = SocketThread.get();

			System.out.println("스케줄 입력버튼을 눌렀습니다.");
			mSocketThread.write("Schedule button pushed.");

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
			mSocketThread.write("get Schedule Study Time");

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
			mSocketThread.write("get Schedule Sub Num");

			

		} catch (Exception e) {

		}
	}
}
