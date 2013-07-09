package scalaGroupAndFilter

case class Sample(
  party: String,
  sex: String,
  position: String)

case class Summary(name: String, value: String, count: Int)

object Aggregator {

  def femalesByParty(rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterGroupByAnySummerizeByPosition(_.sex == "Female", "party", rawData)
  }

  def malesByParty(rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterGroupByAnySummerizeByPosition(_.sex == "Male", "party", rawData)
  }
  
  def democratsBySex(rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterGroupByAnySummerizeByPosition(_.party == "Democrat", "sex", rawData)
  }
  
  def republicansBySex(rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterGroupByAnySummerizeByPosition(_.party == "Republican", "sex", rawData)
  }
  
  def groupByValue(sample: Sample, fieldName: String): String = {
    fieldName match {
      case "party" => sample.party
      case "sex" => sample.sex
      case "position" => sample.position
      case _ => throw new IllegalArgumentException("invalid sample field name " + fieldName)
    }
  }

  def filterAndGroupBy(rawDataFilter: Sample => Boolean, groupFieldName: String, rawData: Seq[Sample]): Map[String, Seq[Sample]] = {
    rawData.filter(rawDataFilter)
      .groupBy(groupByValue(_, groupFieldName))
  }

  def filterGroupByAnySummerizeByPosition(rawDataFilter: Sample => Boolean, groupFieldName: String, rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterAndGroupBy(rawDataFilter, groupFieldName, rawData)
      .mapValues(summarizeSamplesByPosition(_))
  }
  
  private def summarizeSamplesByPosition(samples: Seq[Sample]): Seq[Summary] = {
    val filteredSampleCount = samples.filter(_.position == "For").size
    Seq(
      Summary("position", "For", filteredSampleCount),
      Summary("position", "Against", samples.size - filteredSampleCount))
  }
  
  def summarizeBySex(samples: Seq[Sample]): Seq[Summary] = {
    val filteredSampleCount = samples.filter(_.sex == "Male").size
    Seq(
      Summary("sex", "Male", filteredSampleCount),
      Summary("sex", "Female", samples.size - filteredSampleCount))
  }
  
}