package javaGroupAndFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aggregator {

	public static Map<String, List<Summary>> femalesByParty(List<Sample> rawData) {

		List<Sample> filtered = removeMales(rawData);
		Map<String, List<Summary>> grouped = groupByParty(filtered);
		return grouped;
	}

	private static Map<String, List<Summary>> groupByParty(List<Sample> filtered) {
		HashMap<String, List<Summary>> grouped = new HashMap<String, List<Summary>>();
		for (Sample sample : filtered) {
			String key = sample.getParty();
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

	private static List<Sample> removeMales(List<Sample> rawData) {
		List<Sample> filtered = new ArrayList<Sample>();
		for (Sample sample : rawData) {
			if (sample.getSex().equals("Female")) {
				filtered.add(sample);
			}
		}
		return filtered;
	}

}
