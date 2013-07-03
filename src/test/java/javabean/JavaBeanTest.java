package javabean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JavaBeanTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void itShouldRequireAModel() {
		Car car = new Car("HHR");
		assertEquals("HHR", car.getModel());
	}
	
	@Test
	public void itShouldOptionallyAllowMilage() {
		Car car = new Car("HHR", 40000);
		assertEquals(40000, car.getMiles());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void shouldRequireMileageIsZeroOrPositive() throws Exception {
		new Car("HHR", -100);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void itShouldRequireTheModelIsNotAnEmptyString() throws Exception {
		new Car("");
	}

	@Test
	public void itShouldAllowYouToAdjustTheMileageButNotTheModel() throws Exception {
		Car car = new Car("HHR", 40000);
		assertEquals(40000, car.getMiles());
		car.setMiles(50000);
		// car.setModel("Testerosa");   <== error:  The method setModel(String) is undefined for the type Car
		assertEquals(50000, car.getMiles());
	}
	
	@Test
	public void itSupportsDeepCopy() throws Exception {
		Car car1 = new Car("HHR", 40000);
		Car car2 = new Car(car1);
		car2.setMiles(50000);
		assertEquals(40000, car1.getMiles());
		assertEquals(50000, car2.getMiles());
	}
	
	@Test
	public void itSupportsDeepCompare() throws Exception {
		Car car1 = new Car("HHR", 40000);
		Car car2 = new Car("HHR", 40000);
		Car car3 = new Car("HHR", 50000);
		
		assertTrue(car1.equals(car2));
		assertFalse(car1.equals(car3));
		assertFalse(car1 == car2);
	}
	
	@Test
	public void itHasAPrettyToString() throws Exception {
		Car car = new Car("HHR", 40000);
		assertEquals(car.toString(), "Car(\"HHR\", 40000)");
	}	
}
