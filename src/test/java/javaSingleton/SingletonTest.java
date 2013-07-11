package javaSingleton;

import org.junit.Test;

public class SingletonTest {

	@Test
	public void itShouldLazyInit() {
	    System.out.println("this comes before Singleton init");
	    Singleton instance = Singleton.getInstance();
		System.out.println(instance.getX());
	    System.out.println(instance.getY());
	}

}
