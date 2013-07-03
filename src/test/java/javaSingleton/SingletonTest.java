package javaSingleton;

import org.junit.Test;

public class SingletonTest {

	@Test
	public void itShouldLazyInit() {
	    System.out.println("this comes before Singleton init");
	    System.out.println(Singleton.getInstance().x);
	}

}
