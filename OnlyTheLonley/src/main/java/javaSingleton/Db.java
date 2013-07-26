package javaSingleton;

public class Db {
	private static Db instance;

	public String expensiveOperationOne() {
		System.out.println("performing expensive operation one");
		return "expensive operation one";
	}

	public String expensiveOperationTwo() {
		System.out.println("performing expensive operation two");
		return "expensive operation two";
	}

	private Db() {
	}

	public static Db getInstance() {
		if (instance == null) {
			instance = new Db();
		}
		return instance;
	}
}
