package o2b2;

public class StartSelect {
	void Login() {
		//���α׷��� �������ڸ��� Select�� ������ �� �ֵ��� �ϴ� �κ�
		// �������̵� �Ͽ� �Ű������� ���� loadProfile �� ����Ѵ�.
		Select_Profile m = new Select_Profile();
		m.loadProfile();
		
		Select_ScheduleStudyTime ss = new Select_ScheduleStudyTime();
		ss.loadScheduleStudytime();
		
		Select_RealStudyTime rs = new Select_RealStudyTime();
		rs.loadRealStudyTime();

		// �̱��� �ܼ�â�� ����===========================================
		SingleTon s = SingleTon.getInstanse();

		for (int i = 0; i < s.phonenum_singleTon.size(); i++) {
			System.out.println("phonenum : " + s.phonenum_singleTon.get(i));

		}
		for (int i = 0; i < s.password1_singleTon.size(); i++) {
			System.out.println("password : " + s.password1_singleTon.get(i));

		}
	}
}
