package scalaGroup
import org.scalatest._
import scalaSupport.poll._

class GroupTest extends FunSpec with ShouldMatchers {

  val rawData = Seq(
    PollSample(party = "Democrat", sex = "Male", position = "Against"),
    PollSample(party = "Republican", sex = "Male", position = "Against"),
    PollSample(party = "Republican", sex = "Male", position = "Against"),
    PollSample(party = "Republican", sex = "Female", position = "Against"),
    PollSample(party = "Democrat", sex = "Female", position = "For"),
    PollSample(party = "Democrat", sex = "Male", position = "For"),
    PollSample(party = "Democrat", sex = "Female", position = "For"),
    PollSample(party = "Republican", sex = "Female", position = "For"))

  it("should group an unordered list") {
    val byPartyAndPosition = rawData.groupBy(sample => sample.party + ' ' + sample.position)
    byPartyAndPosition.get("Democrat For").get.size should be(3)
    byPartyAndPosition.get("Republican For").get.size should be(1)
    byPartyAndPosition.get("Democrat Against").get.size should be(1)
    byPartyAndPosition.get("Republican Against").get.size should be(3)
  }

  it("can make a tree") {
    val pollTree =
      rawData.groupBy(_.party)
        .mapValues(_.groupBy(_.sex)
          .mapValues(_.groupBy(_.position)))

    pollTree.get("Democrat").get.get("Male").get.get("For").get.size should be(1)
    pollTree.get("Democrat").get.get("Male").get.get("Against").get.size should be(1)
    pollTree.get("Democrat").get.get("Female").get.get("For").get.size should be(2)
    pollTree.get("Democrat").get.get("Female").get.get("Against") should be(None)
    pollTree.get("Republican").get.get("Male").get.get("For") should be(None)
    pollTree.get("Republican").get.get("Male").get.get("Against").get.size should be(2)
    pollTree.get("Republican").get.get("Female").get.get("For").get.size should be(1)
    pollTree.get("Republican").get.get("Female").get.get("Against").get.size should be(1)
  }

  it("Refactor1: First pass at creating XPath-like query") {
    val pollTree =
      rawData.groupBy(_.party)
        .mapValues(_.groupBy(_.sex)
          .mapValues(_.groupBy(_.position)))

    case class PollTree(map: Map[String, Any]) {
      def /(key: String) = map.get(key) match {
        case Some(x) => x
        case None => 0
      }
    }

    val maleDemocrats = ((PollTree(pollTree) / "Democrat").asInstanceOf[Map[String, Any]]).get("Male").get.asInstanceOf[Map[String, Any]]
    maleDemocrats.get("For").get.asInstanceOf[List[PollSample]] should be(
      List(PollSample("Democrat", "Male", "For")))
  }

  it("Refactor2: Move down-cast to the matcher") {
    val pollTree =
      rawData.groupBy(_.party)
        .mapValues(_.groupBy(_.sex)
          .mapValues(_.groupBy(_.position)))

    case class PollTree(map: Map[String, Any]) {
      def /(key: String): Map[String, Any] = map.get(key) match {
        case Some(x) => x.asInstanceOf[Map[String, Any]]
        case None => Map()
      }
    }

    val maleDemocrats = (PollTree(pollTree) / "Democrat").get("Male").get.asInstanceOf[Map[String, Any]]
    maleDemocrats.get("For").get.asInstanceOf[List[PollSample]] should be(
      List(PollSample("Democrat", "Male", "For")))
  }

  it("Refactor3: Use case class implicitly") {
    val pollTree =
      rawData.groupBy(_.party)
        .mapValues(_.groupBy(_.sex)
          .mapValues(_.groupBy(_.position)))

    case class PollTree(map: Map[String, Any]) {
      def /(key: String): Map[String, Any] = map.get(key) match {
        case Some(x) => x.asInstanceOf[Map[String, Any]]
        case None => Map()
      }
    }

    implicit def StringMapToPollTree(map: Map[String, Any]) = PollTree(map)

    val maleDemocrats = (pollTree / "Democrat").get("Male").get.asInstanceOf[Map[String, Any]]
    maleDemocrats.get("For").get.asInstanceOf[List[PollSample]] should be(
      List(PollSample("Democrat", "Male", "For")))
  }

  it("Refactor4: Use PollTree for multiple levels") {
    val pollTree =
      rawData.groupBy(_.party)
        .mapValues(_.groupBy(_.sex)
          .mapValues(_.groupBy(_.position)))

    case class PollTree(map: Map[String, Any]) {
      def /(key: String): Map[String, Any] = map.get(key) match {
        case Some(x) => x.asInstanceOf[Map[String, Any]]
        case None => Map()
      }
    }

    implicit def StringMapToPollTree(map: Map[String, Any]) = PollTree(map)

    val maleDemocrats = pollTree / "Democrat" / "Male"
    maleDemocrats.get("For").get should be(List(PollSample("Democrat", "Male", "For")))

    val maleDemocratsFor = maleDemocrats.get("For").get.asInstanceOf[List[PollSample]].size
    maleDemocratsFor should be(1)
  }

  it("Refactor5: Add the final level to the XPath-like query and add the rest of the tests") {
    val pollTree =
      rawData.groupBy(_.party)
        .mapValues(_.groupBy(_.sex)
          .mapValues(_.groupBy(_.position)))

    case class PollTree(map: Map[String, Any]) {
      def /(key: String): Map[String, Any] = map.get(key) match {
        case Some(x) => x.asInstanceOf[Map[String, Any]]
        case None => Map()
      }

      def whoAre(key: String): List[PollSample] = map.get(key) match {
        case Some(x) => x.asInstanceOf[List[PollSample]]
        case None => Nil
      }
    }

    implicit def StringMapToPollTree(map: Map[String, Any]) = PollTree(map)

    (pollTree / "Democrat" / "Male" whoAre "For").size should be(1)
    (pollTree / "Democrat" / "Male" whoAre "Against").size should be(1)
    (pollTree / "Democrat" / "Female" whoAre "For").size should be(2)
    (pollTree / "Democrat" / "Female" whoAre "Against").size should be(0)
    (pollTree / "Republican" / "Male" whoAre "For").size should be(0)
    (pollTree / "Republican" / "Male" whoAre "Against").size should be(2)
    (pollTree / "Republican" / "Female" whoAre "For").size should be(1)
    (pollTree / "Republican" / "Female" whoAre "Against").size should be(1)
  }
  
}