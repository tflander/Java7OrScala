package javaGroupAndFilter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class GroupAndFilterTest {

	@Test
	public void itShouldGroupAndFilterAnUnorderedList() {
		List<Sample> rawData = buildSampleData();
		Map<String, List<Summary>> summary = Aggregator.femalesByParty(rawData);
		
		List<Summary> democrats = summary.get("Democrat");
		List<Summary> republicans = summary.get("Republican");
		
		assertEquals(2, democrats.size());
		assertEquals(2, republicans.size());
		Summary democratsFor = democrats.get(0);
		Summary democratsAgainst = democrats.get(1);
		Summary republicansFor = republicans.get(0);
		Summary republicansAgainst = republicans.get(1);
		assertEquals("For", democratsFor.getValue());
		assertEquals(2, democratsFor.getCount());
		assertEquals("Against", democratsAgainst.getValue());
		assertEquals(0, democratsAgainst.getCount());
		assertEquals("For", republicansFor.getValue());
		assertEquals(1, republicansFor.getCount());
		assertEquals("Against", republicansAgainst.getValue());
		assertEquals(1, republicansAgainst.getCount());
	}
	
	@Test 
	public void itShouldSummarizeMalesByParty() {
		List<Sample> rawData = buildSampleData();
		Map<String, List<Summary>> summary = Aggregator.malesByParty(rawData);
		
		List<Summary> democrats = summary.get("Democrat");
		List<Summary> republicans = summary.get("Republican");
		
		assertEquals(2, democrats.size());
		assertEquals(2, republicans.size());
		Summary democratsFor = democrats.get(0);
		Summary democratsAgainst = democrats.get(1);
		Summary republicansFor = republicans.get(0);
		Summary republicansAgainst = republicans.get(1);
		assertEquals("For", democratsFor.getValue());
		assertEquals(1, democratsFor.getCount());
		assertEquals("Against", democratsAgainst.getValue());
		assertEquals(1, democratsAgainst.getCount());
		assertEquals("For", republicansFor.getValue());
		assertEquals(0, republicansFor.getCount());
		assertEquals("Against", republicansAgainst.getValue());
		assertEquals(2, republicansAgainst.getCount());
	}
	
	@Test 
	public void itShouldSummarizeDemocratsBySex() {
		List<Sample> rawData = buildSampleData();
		Map<String, List<Summary>> summary = Aggregator.democratsBySex(rawData);
		
		List<Summary> men = summary.get("Male");
		List<Summary> women = summary.get("Female");
		
		assertEquals(2, men.size());
		assertEquals(2, women.size());
		Summary menFor = men.get(0);
		Summary menAgainst = men.get(1);
		Summary womenFor = women.get(0);
		Summary womenAgainst = women.get(1);
		assertEquals("For", menFor.getValue());
		assertEquals(1, menFor.getCount());
		assertEquals("Against", menAgainst.getValue());
		assertEquals(1, menAgainst.getCount());
		assertEquals("For", womenFor.getValue());
		assertEquals(2, womenFor.getCount());
		assertEquals("Against", womenAgainst.getValue());
		assertEquals(0, womenAgainst.getCount());
	}
	/*
  it("Should summarize Democrats by sex") {
    val summary = Aggregator.democratsBySex(rawData)

    summary.get("Male").head should be(Seq(
      Summary("For", 1),
      Summary("Against", 1)))
    
    summary.get("Female").head should be(Seq(
      Summary("For", 2),
      Summary("Against", 0)))
  }
  
  it("Should summarize Republicans by sex") {
    val summary = Aggregator.republicansBySex(rawData)

    summary.get("Male").head should be(Seq(
      Summary("For", 0),
      Summary("Against", 2)))
    
    summary.get("Female").head should be(Seq(
      Summary("For", 1),
      Summary("Against", 1)))
  }
  
  it("can use generic method to group by position and summarize by sex") {
    val summary = Aggregator.groupByAndSummerize("position", "sex", rawData)
    
    summary.get("For").head should be(Seq(
      Summary("Male", 1),
      Summary("Female", 3)))
    
    summary.get("Against").head should be(Seq(
      Summary("Male", 3),
      Summary("Female", 1)))
  }
  
  it("can use generic method to filter by party, group by position, and summarize by sex") {
    val summary = Aggregator.filterGroupByAndSummerize(_.party == "Democrat", "position", "sex", rawData)
    
    summary.get("For").head should be(Seq(
      Summary("Male", 1),
      Summary("Female", 2)))
    
    summary.get("Against").head should be(Seq(
      Summary("Male", 1),
      Summary("Female", 0)))
  }

	 */
	private List<Sample> buildSampleData() {
		ArrayList<Sample> samples = new ArrayList<Sample>();
		samples.add(new Sample("Democrat", "Male", "Against"));
		samples.add(new Sample("Republican", "Male", "Against"));
		samples.add(new Sample("Republican", "Male", "Against"));
		samples.add(new Sample("Republican", "Female", "Against"));
		samples.add(new Sample("Democrat", "Female", "For"));
		samples.add(new Sample("Democrat", "Male", "For"));
		samples.add(new Sample("Democrat", "Female", "For"));
		samples.add(new Sample("Republican", "Female", "For"));
		return samples;
	}

}
