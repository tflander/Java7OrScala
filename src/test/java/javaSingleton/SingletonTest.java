package javaSingleton;

import org.junit.Test;

public class SingletonTest {

	@Test
	public void itShouldLazyInit() {
		System.out.println("this comes before Singleton init");
		Singleton instance = Singleton.getInstance();
		System.out.println(instance.getX());
		System.out.println(instance.getY());

		/*
		 * Output:
		 * 
		 * this comes before Singleton init 
		 * Singleton Init Started 
		 * performing expensive operation one 
		 * Singleton Init Done 
		 * expensive operation one
		 * performing expensive operation two 
		 * expensive operation two
		 */
	}

}
