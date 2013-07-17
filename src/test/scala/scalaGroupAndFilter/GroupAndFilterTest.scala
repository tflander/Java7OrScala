package scalaGroupAndFilter
import org.scalatest._
import scalaSupport.poll._

class GroupAndFilterTest extends FunSpec with ShouldMatchers {
  
  val rawData = Seq(
    PollSample(party = "Democrat", sex = "Male", position = "Against"),
    PollSample(party = "Republican", sex = "Male", position = "Against"),
    PollSample(party = "Republican", sex = "Male", position = "Against"),
    PollSample(party = "Republican", sex = "Female", position = "Against"),
    PollSample(party = "Democrat", sex = "Female", position = "For"),
    PollSample(party = "Democrat", sex = "Male", position = "For"),
    PollSample(party = "Democrat", sex = "Female", position = "For"),
    PollSample(party = "Republican", sex = "Female", position = "For"))

  it("should group and filter an unordered list") {
    val summary = Aggregator.femalesByParty(rawData)

    summary.get("Democrat").head should be(Seq(
      PollSummary("For", 2),
      PollSummary("Against", 0)))
    
    summary.get("Republican").head should be(Seq(
      PollSummary("For", 1),
      PollSummary("Against", 1)))
    
  }
  
  it("Should summarize males by party") {
    val summary = Aggregator.malesByParty(rawData)

    summary.get("Democrat").head should be(Seq(
      PollSummary("For", 1),
      PollSummary("Against", 1)))
    
    summary.get("Republican").head should be(Seq(
      PollSummary("For", 0),
      PollSummary("Against", 2)))
    
  }
  
  it("Should summarize Democrats by sex") {
    val summary = Aggregator.democratsBySex(rawData)

    summary.get("Male").head should be(Seq(
      PollSummary("For", 1),
      PollSummary("Against", 1)))
    
    summary.get("Female").head should be(Seq(
      PollSummary("For", 2),
      PollSummary("Against", 0)))
  }
  
  it("Should summarize Republicans by sex") {
    val summary = Aggregator.republicansBySex(rawData)

    summary.get("Male").head should be(Seq(
      PollSummary("For", 0),
      PollSummary("Against", 2)))
    
    summary.get("Female").head should be(Seq(
      PollSummary("For", 1),
      PollSummary("Against", 1)))
  }
  
  it("can use generic method to group by position and summarize by sex") {
    val summary = Aggregator.groupByAndSummerize("position", "sex", rawData)
    
    summary.get("For").head should be(Seq(
      PollSummary("Male", 1),
      PollSummary("Female", 3)))
    
    summary.get("Against").head should be(Seq(
      PollSummary("Male", 3),
      PollSummary("Female", 1)))
  }
  
  it("can use generic method to filter by party, group by position, and summarize by sex") {
    val summary = Aggregator.filterGroupByAndSummerize(_.party == "Democrat", "position", "sex", rawData)
    
    summary.get("For").head should be(Seq(
      PollSummary("Male", 1),
      PollSummary("Female", 2)))
    
    summary.get("Against").head should be(Seq(
      PollSummary("Male", 1),
      PollSummary("Female", 0)))
  }
  
}