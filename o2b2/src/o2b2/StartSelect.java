package o2b2;

public class StartSelect {
	void Login() {
		//프로그램이 시작하자마자 Select를 수행할 수 있도록 하는 부분
		// 오버라이딩 하여 매개변수가 없는 loadProfile 을 사용한다.
		Select_Profile m = new Select_Profile();
		m.loadProfile();
		
		Select_ScheduleStudyTime ss = new Select_ScheduleStudyTime();
		ss.loadScheduleStudytime();
		
		Select_RealStudyTime rs = new Select_RealStudyTime();
		rs.loadRealStudyTime();

		// 싱글톤 콘솔창에 띄우기===========================================
		SingleTon s = SingleTon.getInstanse();

		for (int i = 0; i < s.phonenum_singleTon.size(); i++) {
			System.out.println("phonenum : " + s.phonenum_singleTon.get(i));

		}
		for (int i = 0; i < s.password1_singleTon.size(); i++) {
			System.out.println("password : " + s.password1_singleTon.get(i));

		}
	}
}
