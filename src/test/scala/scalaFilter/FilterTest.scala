package scalaFilter
import org.scalatest._
import scalaSupport.poll._

class FilterTest extends FunSpec with ShouldMatchers {

  val rawData = Seq(
    PollSample(party = "Democrat", sex = "Male", position = "Against"),
    PollSample(party = "Republican", sex = "Male", position = "Against"),
    PollSample(party = "Democrat", sex = "Female", position = "For"),
    PollSample(party = "Republican", sex = "Male", position = "Against"),
    PollSample(party = "Republican", sex = "Female", position = "Against"),
    PollSample(party = "Democrat", sex = "Female", position = "For"),
    PollSample(party = "Democrat", sex = "Male", position = "For"),
    PollSample(party = "Democrat", sex = "Female", position = "For"),
    PollSample(party = "Republican", sex = "Female", position = "For"),
    PollSample(party = "Democrat", sex = "Female", position = "Against"))

  it("should filter an unordered list and count results") {
    val femaleDemocrats = rawData.filter(pollSample =>
      pollSample.party == "Democrat" && pollSample.sex == "Female")

    femaleDemocrats.count(_.position == "For") should be(3)
    femaleDemocrats.count(_.position == "Against") should be(1)
  }

  it("refactor 1:  Avoid iterating female democrats twice") {
    val femaleDemocrats = rawData.filter(pollSample =>
      pollSample.party == "Democrat" && pollSample.sex == "Female")

    val forAndAgainst = femaleDemocrats.partition(_.position == "For")
    forAndAgainst._1.size should be(3)
    forAndAgainst._2.size should be(1)
  }

  it("refactor 2:  Avoid creating an intermediate sequence") {
    var femaleDemocratsFor = 0
    var femaleDemocratsAgainst = 0

    def countFemaleDemocratsForAndAgainst(pollSample: PollSample) = {
      if (pollSample.party == "Democrat" && pollSample.sex == "Female") {
        if (pollSample.position == "For") {
          femaleDemocratsFor += 1
        } else {
          femaleDemocratsAgainst += 1
        }
      }
    }

    rawData.foreach(countFemaleDemocratsForAndAgainst)
    femaleDemocratsFor should be(3)
    femaleDemocratsAgainst should be(1)
  }

  it("refactor 3: Avoid mutible vars") {

    def sumFemaleDemocrats(list: Seq[PollSample]): (Int, Int) = list.foldLeft((0, 0))((summary, sample) => {
      if (sample.party == "Democrat" && sample.sex == "Female") {
        if (sample.position == "For") {
          (summary._1 + 1, summary._2)
        } else {
          (summary._1, summary._2 + 1)
        }
      } else {
        (summary._1, summary._2)
      }
    })

    sumFemaleDemocrats(rawData) should be(3, 1)
  }

  it("refactor 4:  Remove clumsy if / else") {

    def sumFemaleDemocrats(list: Seq[PollSample]): (Int, Int) = list.foldLeft((0, 0))((summary, sample) => {
      val incVals = sample match {
        case x if (x.party == "Democrat" && x.sex == "Female") => {
          x.position match {
            case "For" => (1, 0)
            case _ => (0, 1)
          }
        }
        case _ => (0, 0)
      }
      (summary._1 + incVals._1, summary._2 + incVals._2)
    })

    sumFemaleDemocrats(rawData) should be(3, 1)
  }

  it("refactor 5:  Extract nested match") {

    def sumFemaleDemocrats(list: Seq[PollSample]): (Int, Int) = list.foldLeft((0, 0))((summary, sample) => {

      def forOrAgainst(sample: PollSample) =
        sample.position match {
          case "For" => (1, 0)
          case _ => (0, 1)
        }

      val incVals = sample match {
        case x if (x.party == "Democrat" && x.sex == "Female") => forOrAgainst(x)
        case _ => (0, 0)
      }
      (summary._1 + incVals._1, summary._2 + incVals._2)
    })

    sumFemaleDemocrats(rawData) should be(3, 1)
  }

}