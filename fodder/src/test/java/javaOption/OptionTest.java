package javaOption;

import static org.junit.Assert.*;

import org.junit.Test;

public class OptionTest {

	@Test
	public void itShouldCallAMethodWithAPopulatedReturnValue() {
		String thing = Thing.getThingWithLKey("goodKey");
		if(thing == null) {
			throw new IllegalStateException("typical Java null-check code goes here");
		}
		assertEquals(thing, "thing");
	}
	
	@Test
	public void itShouldCallAMethodWithAnEmptyReturnValue() {
		String thing = Thing.getThingWithLKey("badKey");
		assertNull(thing);
	}

	@Test
	public void itShouldBeBraveIfWeKnowWeHaveAReturnValue() {
		String thing = Thing.getThingWithLKey("goodKey");
		assertEquals(thing, "thing");
	}
	
	@Test(expected = NullPointerException.class)
	public void itThrowsIfWeWereWrongInExpectingAReturnValue() {
		String thing = Thing.getThingWithLKey("badKey");
		System.out.println(thing.length());
	}
}
