package javaGroupAndFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aggregator {

	public static Map<String, List<Summary>> femalesByParty(List<Sample> rawData) {
		return filterGroupByAndSummerize("sex", "Female", "party", "position", rawData);
	}

	public static Map<String, List<Summary>> malesByParty(List<Sample> rawData) {
		return filterGroupByAndSummerize("sex", "Male", "party", "position", rawData);
	}

	public static Map<String, List<Summary>> democratsBySex(List<Sample> rawData) {
		return filterGroupByAndSummerize("party", "Democrat", "sex", "position", rawData);
	}

	public static Map<String, List<Summary>> republicansBySex(
			List<Sample> rawData) {
		return filterGroupByAndSummerize("party", "Republican", "sex", "position", rawData);
	}

	public static Map<String, List<Summary>> groupByAndSummerize(String groupField,
			String summaryField, List<Sample> rawData) {
		List<String> summaryValues = getSummaryValues(summaryField, rawData);
		return groupByField(groupField, summaryField, summaryValues, rawData);
	}
	
	public static Map<String, List<Summary>> filterGroupByAndSummerize(String filterField, String filterValue, String groupField, String summaryField, List<Sample> rawData) {
		List<Sample> filtered = filterByField(filterField, filterValue, rawData);
		List<String> summaryValues = getSummaryValues(summaryField, rawData);
		return groupByField(groupField, summaryField, summaryValues, filtered);
	}
	
	private static List<String> getSummaryValues(String summaryField,
			List<Sample> rawData) {
		List<String> summaryValues = new ArrayList<String>();
		for (Sample sample : rawData) {
			String fieldValue =  getFieldForSample(summaryField, sample);
			if(!summaryValues.contains(fieldValue)) {
				summaryValues.add(fieldValue);
				if(summaryValues.size() == 2) {
					break;
				}
			}
		}
		Collections.sort(summaryValues);
		return summaryValues;
	}

	private static Map<String, List<Summary>> groupByField(String groupFieldName, String summaryFieldName, List<String> summaryValues,
			List<Sample> samples) {
		HashMap<String, List<Summary>> grouped = new HashMap<String, List<Summary>>();
		for (Sample sample : samples) {
			String key = getFieldForSample(groupFieldName, sample);
			List<Summary> summaryList = grouped.get(key);
			if (summaryList == null) {
				summaryList = new ArrayList<Summary>();
				grouped.put(key, summaryList);
				summaryList.add(new Summary(summaryValues.get(0), 0));
				summaryList.add(new Summary(summaryValues.get(1), 0));
			}
			for (Summary summary : summaryList) {
				if (summary.getValue().equals(getFieldForSample(summaryFieldName, sample))) {
					summary.incCount();
					break;
				}
			}
		}
		return grouped;
	}

	private static List<Sample> filterByField(String fieldName, String value,
			List<Sample> rawData) {
		List<Sample> filtered = new ArrayList<Sample>();
		for (Sample sample : rawData) {
			if (getFieldForSample(fieldName, sample).equals(value)) {
				filtered.add(sample);
			}
		}
		return filtered;
	}

	private static String getFieldForSample(String fieldName, Sample sample) {
		if (fieldName.equals("sex")) {
			return sample.getSex();
		} else if (fieldName.equals("party")) {
			return sample.getParty();
		} else if (fieldName.equals("position")) {
			return sample.getPosition();
		}
		throw new IllegalArgumentException("invalid sample field name "
				+ fieldName);
	}
}
