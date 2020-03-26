package prototype;

public class SIngleTon_Test {
	
	
	
	String readData_singleTon = null;
	
	private static SIngleTon_Test single = null;
	
	private SIngleTon_Test() {
	}
	
	public static SIngleTon_Test getInstance() {
		if (single == null) 
			single = new SIngleTon_Test();
		return single;
	}

}
