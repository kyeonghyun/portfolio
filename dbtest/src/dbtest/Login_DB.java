package dbtest;

public class Login_DB {
	void Login() {

		// �������̵� �Ͽ� �Ű������� ���� loadProfile �� ����Ѵ�.
		Select_Profile m = new Select_Profile();
		m.loadProfile();

		// �̱��� �ܼ�â�� ����===========================================
		SingleTon s = SingleTon.getInsTanse();

		for (int i = 0; i < s.phonenum_singleTon.size(); i++) {
			System.out.println("phonenum : " + s.phonenum_singleTon.get(i));

		}
		for (int i = 0; i < s.password1_singleTon.size(); i++) {
			System.out.println("password : " + s.password1_singleTon.get(i));

		}
	}
}
