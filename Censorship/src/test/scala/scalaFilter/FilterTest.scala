package scalaFilter
import org.scalatest._

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

  def isFemaleDemocrat(pollSample: PollSample) =
    pollSample.party == "Democrat" && pollSample.sex == "Female"

  it("should filter an unordered list and count results") {
    val femaleDemocrats = rawData.filter(isFemaleDemocrat(_))

    femaleDemocrats.count(_.position == "For") should be(3)
    femaleDemocrats.count(_.position == "Against") should be(1)
  }

  it("refactor 1:  Avoid iterating female democrats twice") {
    val femaleDemocrats = rawData.filter(isFemaleDemocrat(_))
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

    def multiParamAdd(i: Int)(j: Int): Int = i + j

    it("demonstrates methods with multiple parameter lists (for currying)") {
      multiParamAdd(1)(2) should be(3)

      def addToTen: Int => Int = multiParamAdd(10)_
      addToTen(5) should be(15)
    }

    it("demonstrates simple foldLeft usage (long version with no placeholders") {
      val x = Seq(1, 2, 3).foldLeft(0)(
        (accumulated, current) => accumulated + current)
      x should be(6)
    }

    it("can take an anonymous block for the 2nd parameter list") {
      val x = Seq(1, 2, 3).foldLeft(0) { (accumulated, current) =>
        multiParamAdd(accumulated)(current)
      }
      x should be(6)
    }

    it("demonstrates simple foldLeft usage using our multiParamAdd") {
      Seq(1, 2, 3).foldLeft(0)(multiParamAdd(_)(_)) should be(6)
    }

    it("demonstrates simple foldLeft usage (short version with placeholders") {
      Seq(1, 2, 3).foldLeft(0)(_ + _) should be(6)
    }

    it("uses currying for syntactic sugar") {
      val numberFunction = Seq(1, 2, 3).foldLeft(0)_
      def sum(i: Int, j: Int) = i + j
      def subtract(i: Int, j: Int) = i - j
      def multiply(i: Int, j: Int) = i * j

      numberFunction(sum) should be(6)
      numberFunction(subtract) should be(-6)
      numberFunction(multiply) should be(0)  // not 1 * 2 * 3 = 6
    }
    
    it("demonstrates foldLeft on a sequence of Tuples") {

      def calc(accumulated: (Int, Int), current: (Int, Int)): (Int, Int) = {
        (accumulated._1 + current._1, accumulated._2 * current._2)
      }

      val seqOfTuples = Seq((1, 1), (2, 2), (3, 3), (4, 4))
      val initialValsForAddAndMult = (0, 1)
      def addAndMultiplyTuples = seqOfTuples.foldLeft(initialValsForAddAndMult)_
      addAndMultiplyTuples(calc) should be((10, 24))
    }
  }

  it("refactor 3: Use FoldLeft to avoid mutible vars") {

    def tallyForOrAgainst(accumulatedForAndAgainst: (Int, Int), sample: PollSample): (Int, Int) = {
      if (sample.party == "Democrat" && sample.sex == "Female") {
        if (sample.position == "For") {
          (accumulatedForAndAgainst._1 + 1, accumulatedForAndAgainst._2)
        } else {
          (accumulatedForAndAgainst._1, accumulatedForAndAgainst._2 + 1)
        }
      } else {
        (accumulatedForAndAgainst._1, accumulatedForAndAgainst._2)
      }
    }

    def femaleDemocratsForAndAgainst(list: Seq[PollSample]): (Int, Int) = list.foldLeft((0, 0))(tallyForOrAgainst(_, _))
    femaleDemocratsForAndAgainst(rawData) should be(3, 1)
  }

  it("refactor 4:  Remove clumsy if / else") {

    def tallyForOrAgainst(forAndAgainst: (Int, Int), sample: PollSample): (Int, Int) = {
      sample match {
        case pollSample if (pollSample.party == "Democrat" && pollSample.sex == "Female") => {
          pollSample.position match {
            case "For" => (forAndAgainst._1 + 1, forAndAgainst._2)
            case _ => (forAndAgainst._1, forAndAgainst._2 + 1)
          }
        }
        case _ => forAndAgainst
      }
    }

    def femaleDemocratsForAndAgainst(list: Seq[PollSample]): (Int, Int) =
      list.foldLeft((0, 0))(tallyForOrAgainst(_, _))
    femaleDemocratsForAndAgainst(rawData) should be(3, 1)
  }

  it("refactor 5:  currying for syntactic sugar") {

    def femaleDemocratsForAndAgainst(forAndAgainst: (Int, Int), sample: PollSample): (Int, Int) = {
      sample match {
        case pollSample if (pollSample.party == "Democrat" && pollSample.sex == "Female") => {
          pollSample.position match {
            case "For" => (forAndAgainst._1 + 1, forAndAgainst._2)
            case _ => (forAndAgainst._1, forAndAgainst._2 + 1)
          }
        }
        case _ => forAndAgainst
      }
    }

    def summarize = rawData.foldLeft((0, 0))_

    summarize(femaleDemocratsForAndAgainst) should be(3, 1)
  }

  it("refactor 6:  extract filtering functionality") {

    def summarize = rawData.foldLeft((0, 0))_

    def forAndAgainst(filter: PollSample => Boolean)(totalSoFar: (Int, Int), sample: PollSample): (Int, Int) = {
      sample match {
        case pollSample if (filter(pollSample)) => {
          pollSample.position match {
            case "For" => (totalSoFar._1 + 1, totalSoFar._2)
            case _ => (totalSoFar._1, totalSoFar._2 + 1)
          }
        }
        case _ => totalSoFar
      }
    }

    def femaleDemocrats(sample: PollSample) = sample.party == "Democrat" && sample.sex == "Female"
    def maleDemocrats(sample: PollSample) = sample.party == "Democrat" && sample.sex == "Male"
    def femaleRepublicans(sample: PollSample) = sample.party == "Republican" && sample.sex == "Female"
    def maleRepublicans(sample: PollSample) = sample.party == "Republican" && sample.sex == "Male"

    summarize(forAndAgainst(femaleDemocrats)) should be(3, 1)
    summarize(forAndAgainst(maleDemocrats)) should be(1, 1)
    summarize(forAndAgainst(femaleRepublicans)) should be(1, 1)
    summarize(forAndAgainst(maleRepublicans)) should be(0, 2)
  }

}