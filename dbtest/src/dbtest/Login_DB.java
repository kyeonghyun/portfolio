package dbtest;

public class Login_DB {
	void Login() {

		// 오버라이딩 하여 매개변수가 없는 loadProfile 을 사용한다.
		Select_Profile m = new Select_Profile();
		m.loadProfile();

		// 싱글톤 콘솔창에 띄우기===========================================
		SingleTon s = SingleTon.getInsTanse();

		for (int i = 0; i < s.phonenum_singleTon.size(); i++) {
			System.out.println("phonenum : " + s.phonenum_singleTon.get(i));

		}
		for (int i = 0; i < s.password1_singleTon.size(); i++) {
			System.out.println("password : " + s.password1_singleTon.get(i));

		}
	}
}
