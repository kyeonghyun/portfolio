package o2b2;



class Rasp_Main extends Thread {

	public void run() {
		super.run();
		try {
			while (true) {
				Rasp_Socket_Thread mRasp_Socket_Thread = Rasp_Socket_Thread.get();
				mRasp_Socket_Thread.Join();

				// ���� �д� �κ� �ʱ�ȭ �� �־�� �Ѵ�.
				String readData = null;

				while (true) { // Ŭ���̾�Ʈ���� ���� �޴� while��
					try {
						readData = mRasp_Socket_Thread.readData();

						if (readData != null) {
							System.out.println("readData �����޾� while �� ��������");
							break;
						}
						Thread.sleep(100);
					} catch (Exception e) {
					}
				}

				//�̱��� �߰��κ�================================
				SingleTon s = SingleTon.getInstanse();
				s.readData_singleTon = readData;
				
//				Frame mFrame = new Frame();
//				mFrame.txta1.append(s.readData_singleTon);
			
				System.out.println("Ŭ���̾�Ʈ���� �޾ƿ� �� : " + readData);
				System.out.println("�̱��� �׽�Ʈ �� : " + s.readData_singleTon);
				String sendmsg = readData;
				// Ŭ���̾�Ʈ�� ���� ������ ����
				mRasp_Socket_Thread.write("send : " + sendmsg);
				
				
//				SocketThread mSocketThread = SocketThread.get();
//				mSocketThread.write("Rasp to Server: " +readData);
			}

		} catch (Exception e) {

		}

	}
}
