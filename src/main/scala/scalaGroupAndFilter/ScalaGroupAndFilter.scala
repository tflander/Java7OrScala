package scalaGroupAndFilter

case class Sample(
  party: String,
  sex: String,
  position: String)

case class Summary(value: String, count: Int)

object Aggregator {

  def femalesByParty(rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterGroupByAndSummerize(_.sex == "Female", "party", "position", rawData)
  }

  def malesByParty(rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterGroupByAndSummerize(_.sex == "Male", "party", "position", rawData)
  }

  def democratsBySex(rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterGroupByAndSummerize(_.party == "Democrat", "sex", "position", rawData)
  }

  def republicansBySex(rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    filterGroupByAndSummerize(_.party == "Republican", "sex", "position", rawData)
  }

  private def valueForFieldName(sample: Sample, fieldName: String): String = {
    fieldName match {
      case "party" => sample.party
      case "sex" => sample.sex
      case "position" => sample.position
      case _ => throw new IllegalArgumentException("invalid sample field name " + fieldName)
    }
  }

  def filterGroupByAndSummerize(rawDataFilter: Sample => Boolean, groupFieldName: String, summaryFieldName: String, rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    groupByAndSummerize(groupFieldName, summaryFieldName, rawData.filter(rawDataFilter))
  }

  def groupByAndSummerize(groupFieldName: String, summaryFieldName: String, rawData: Seq[Sample]): Map[String, Seq[Summary]] = {
    
    val groups = rawData.groupBy(valueForFieldName(_, summaryFieldName)).keys.toSeq
    
    rawData.groupBy(valueForFieldName(_, groupFieldName))
      .mapValues(summarizeSamples(summaryFieldName, _, groups))
  }
  
  def summarizeSamples(fieldName: String, samples: Seq[Sample], groups: Seq[String]): Seq[Summary] = {

    val filteredGroups = samples.groupBy(valueForFieldName(_, fieldName))
      .mapValues(samples => samples.size)
      
    groups.map { group =>
      val count = filteredGroups.getOrElse(group, 0)
      Summary(group, count)
    }
    
  }

}