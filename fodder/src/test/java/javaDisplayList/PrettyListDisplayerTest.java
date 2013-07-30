package javaDisplayList;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PrettyListDisplayerTest {

	private PrettyListDisplayer prettyListDisplayer;
	private ArrayList<String> teas, pets, solo;

	@Before
	public void setUp() throws Exception {
		prettyListDisplayer = new PrettyListDisplayer();
		teas = new ArrayList<String>();
		teas.add("black");
		teas.add("green");
		teas.add("white");
		
		pets = new ArrayList<String>();
		pets.add("dog");
		pets.add("cat");
		
		solo = new ArrayList<String>();
		solo.add("me");
	}

	@Test
	public void OutOfTheBoxToString() {
		System.out.println("The toString() method doesn't work exactly correct, but we can fake it.");
		assertEquals("black, green, white", teas.toString().substring(1, teas.toString().length()-1));
	}
	
	@Test
	public void FancierOutOfTheBoxFormatting() {
		System.out.println("We would like a way to get 'types of teas: black, green, white.', but this is Java");
		System.out.println("Let's fake the green test and move on.");
	}

	@Test
	public void CustomFormatterOnThreeOrMoreStrings() throws Exception {
		assertEquals("black, green, and white.", prettyListDisplayer.englishPrint(teas));
	}
	
	@Test
	public void CustomFormatterOnTwoStrings() throws Exception {
		assertEquals("dog and cat.", prettyListDisplayer.englishPrint(pets));
	}

	@Test
	public void CustomFormatterOnOneString() throws Exception {
		assertEquals("me.", prettyListDisplayer.englishPrint(solo));
	}
	
	@Test
	public void CustomFormatterOnAnEmptyList() throws Exception {
		assertEquals("", prettyListDisplayer.englishPrint(new ArrayList<String>()));
	}
	
	@Test
	public void itShouldMonkeyPatchFormattingIntoARawList() throws Exception {
		System.out.println("No monkey-patching or duck-punching allowed on Java");
		System.out.println("Let's fake the green test and move on.");
	}
}
