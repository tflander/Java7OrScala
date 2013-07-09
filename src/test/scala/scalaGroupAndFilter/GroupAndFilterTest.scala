package scalaGroupAndFilter
import org.scalatest._

class GroupAndFilterTest extends FunSpec with ShouldMatchers {
  
  val rawData = Seq(
    Sample(party = "Democrat", sex = "Male", position = "Against"),
    Sample(party = "Republican", sex = "Male", position = "Against"),
    Sample(party = "Republican", sex = "Male", position = "Against"),
    Sample(party = "Republican", sex = "Female", position = "Against"),
    Sample(party = "Democrat", sex = "Female", position = "For"),
    Sample(party = "Democrat", sex = "Male", position = "For"),
    Sample(party = "Democrat", sex = "Female", position = "For"),
    Sample(party = "Republican", sex = "Female", position = "For"))

  it("should group and filter an unordered list") {
    val summary = Aggregator.femalesByParty(rawData)

    summary.get("Democrat").head should be(Seq(
      Summary("For", 2),
      Summary("Against", 0)))
    
    summary.get("Republican").head should be(Seq(
      Summary("For", 1),
      Summary("Against", 1)))
    
  }
  
  it("Should summarize males by party") {
    val summary = Aggregator.malesByParty(rawData)

    summary.get("Democrat").head should be(Seq(
      Summary("For", 1),
      Summary("Against", 1)))
    
    summary.get("Republican").head should be(Seq(
      Summary("For", 0),
      Summary("Against", 2)))
    
  }
  
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
  
}