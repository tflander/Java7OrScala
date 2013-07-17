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

  describe("We want to use foldLeft()() to avoid mutable variables, so let's explore foldLeft()()") {

    it("demonstrates methods with multiple parameter lists") {
      def multiParamAdd(i: Int)(j: Int): Int = i + j
      multiParamAdd(1)(2) should be(3)
    }

    it("demonstrates simple foldLeft usage (long version with no placeholders") {
      val x = Seq(1, 2, 3).foldLeft(0)(
        (accumulated, current) => accumulated + current)
      x should be(6)
    }

    it("demonstrates simple foldLeft usage (short version with placeholders") {
      Seq(1, 2, 3).foldLeft(0)(_ + _) should be(6)
    }

    it("demonstrates foldLeft on a sequence of Tuples") {

      def addAndMult(accumulated: (Int, Int), current: (Int, Int)): (Int, Int) = {
        (accumulated._1 + current._1, accumulated._2 * current._2)
      }

      val seqOfTuples = Seq((1, 1), (2, 2), (3, 3), (4, 4))
      val initialValsForAddAndMult = (0, 1)
      val addAndMultTuple: (Int, Int) = seqOfTuples.foldLeft(initialValsForAddAndMult)(addAndMult(_, _))
      addAndMultTuple should be((10, 24))
    }
  }

  it("refactor 3: Use FoldLeft to avoid mutible vars") {

    def tallyForOrAgainst(forAndAgainst: (Int, Int), sample: PollSample): (Int, Int) = {
      if (sample.party == "Democrat" && sample.sex == "Female") {
        if (sample.position == "For") {
          (forAndAgainst._1 + 1, forAndAgainst._2)
        } else {
          (forAndAgainst._1, forAndAgainst._2 + 1)
        }
      } else {
        (forAndAgainst._1, forAndAgainst._2)
      }
    }

    def sumFemaleDemocrats(list: Seq[PollSample]): (Int, Int) = list.foldLeft((0, 0))(tallyForOrAgainst(_, _))
    sumFemaleDemocrats(rawData) should be(3, 1)
  }

  it("refactor 4:  Remove clumsy if / else") {

    def tallyForOrAgainst(forAndAgainst: (Int, Int), sample: PollSample): (Int, Int) = {
      sample match {
        case x if (x.party == "Democrat" && x.sex == "Female") => {
          x.position match {
            case "For" => (forAndAgainst._1 + 1, forAndAgainst._2)
            case _ => (forAndAgainst._1, forAndAgainst._2 + 1)
          }
        }
        case _ => forAndAgainst
      }
    }

    def sumFemaleDemocrats(list: Seq[PollSample]): (Int, Int) = list.foldLeft((0, 0))(tallyForOrAgainst(_, _))
    sumFemaleDemocrats(rawData) should be(3, 1)
  }

}