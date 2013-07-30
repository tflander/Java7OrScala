package javaSingleton;

public class Singleton {
	private static Singleton instance;
	
	private String x, y;
	
	private Singleton() {
		System.out.println("Singleton Init Started");
		x = Db.getInstance().expensiveOperationOne();
		System.out.println("Singleton Init Done");
	}

	public synchronized static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	public String getX() {
		return x;
	}

	public synchronized String getY() {
		if(y == null) {
			y = Db.getInstance().expensiveOperationTwo();
		}
		return y;
	}
}
