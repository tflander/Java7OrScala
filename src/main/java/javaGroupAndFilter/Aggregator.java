package javaGroupAndFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aggregator {

	public static Map<String, List<Summary>> femalesByParty(List<Sample> rawData) {
		return filterGroupAndSummarize("sex", "Female", "party", rawData);
	}

	public static Map<String, List<Summary>> malesByParty(List<Sample> rawData) {
		return filterGroupAndSummarize("sex", "Male", "party", rawData);
	}

	public static Map<String, List<Summary>> democratsBySex(List<Sample> rawData) {
		return filterGroupAndSummarize("party", "Democrat", "sex", rawData);
	}

	public static Map<String, List<Summary>> republicansBySex(
			List<Sample> rawData) {
		return filterGroupAndSummarize("party", "Republican", "sex", rawData);
	}

	public static Map<String, List<Summary>> groupByAndSummerize(String groupField,
			String summaryField, List<Sample> rawData) {
		// TODO:  support summary field
		List<String> summaryValues = null;
		return groupByField(groupField, summaryValues, rawData);
	}
	
	private static Map<String, List<Summary>> filterGroupAndSummarize(String filterField, String filterValue, String groupField, List<Sample> rawData) {
		List<Sample> filtered = filterByField(filterField, filterValue, rawData);
		List<String> summaryValues = null;
		return groupByField(groupField, summaryValues, filtered);
	}
	
	private static Map<String, List<Summary>> groupByField(String fieldName, List<String> summaryValues,
			List<Sample> samples) {
		HashMap<String, List<Summary>> grouped = new HashMap<String, List<Summary>>();
		for (Sample sample : samples) {
			String key = getFieldForSample(fieldName, sample);
			List<Summary> summaryList = grouped.get(key);
			if (summaryList == null) {
				summaryList = new ArrayList<Summary>();
				grouped.put(key, summaryList);
				summaryList.add(new Summary("For", 0));
				summaryList.add(new Summary("Against", 0));
			}
			for (Summary summary : summaryList) {
				if (summary.getValue().equals(sample.getPosition())) {
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
