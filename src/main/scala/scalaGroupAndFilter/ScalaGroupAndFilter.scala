package scalaGroupAndFilter

import scalaSupport.poll._

object Aggregator {

  def femalesByParty(rawData: Seq[PollSample]): Map[String, Seq[PollSummary]] = {
    filterGroupByAndSummerize(_.sex == "Female", "party", "position", rawData)
  }

  def malesByParty(rawData: Seq[PollSample]): Map[String, Seq[PollSummary]] = {
    filterGroupByAndSummerize(_.sex == "Male", "party", "position", rawData)
  }

  def democratsBySex(rawData: Seq[PollSample]): Map[String, Seq[PollSummary]] = {
    filterGroupByAndSummerize(_.party == "Democrat", "sex", "position", rawData)
  }

  def republicansBySex(rawData: Seq[PollSample]): Map[String, Seq[PollSummary]] = {
    filterGroupByAndSummerize(_.party == "Republican", "sex", "position", rawData)
  }

  private def valueForFieldName(sample: PollSample, fieldName: String): String = {
    fieldName match {
      case "party" => sample.party
      case "sex" => sample.sex
      case "position" => sample.position
      case _ => throw new IllegalArgumentException("invalid sample field name " + fieldName)
    }
  }

  def filterGroupByAndSummerize(rawDataFilter: PollSample => Boolean, groupFieldName: String, summaryFieldName: String, rawData: Seq[PollSample]): Map[String, Seq[PollSummary]] = {
    groupByAndSummerize(groupFieldName, summaryFieldName, rawData.filter(rawDataFilter))
  }

  def groupByAndSummerize(groupFieldName: String, summaryFieldName: String, rawData: Seq[PollSample]): Map[String, Seq[PollSummary]] = {
    
    val groups = rawData.groupBy(valueForFieldName(_, summaryFieldName)).keys.toSeq
    
    rawData.groupBy(valueForFieldName(_, groupFieldName))
      .mapValues(summarizeSamples(summaryFieldName, _, groups))
  }
  
  def summarizeSamples(fieldName: String, samples: Seq[PollSample], groups: Seq[String]): Seq[PollSummary] = {

    val filteredGroups = samples.groupBy(valueForFieldName(_, fieldName))
      .mapValues(samples => samples.size)
      
    groups.map { group =>
      val count = filteredGroups.getOrElse(group, 0)
      PollSummary(group, count)
    }
    
  }

}