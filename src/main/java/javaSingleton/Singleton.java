package javaSingleton;

public class Singleton {
	private static Singleton instance;
	
	public final String x = "testing";

	private Singleton() {
		System.out.println("Singleton Init");
	}

	public synchronized static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
