package javaDisplayList;

import java.util.ArrayList;

public class PrettyListDisplayer {

	public String englishPrint(ArrayList<String> strings) {

		if (strings.isEmpty()) {
			return "";
		}

		if (strings.size() == 1) {
			return strings.get(0) + '.';
		}

		if (strings.size() == 2) {
			return strings.get(0) + " and " + strings.get(1) + '.';
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.size(); ++i) {
			sb.append(strings.get(i));
			if (i < strings.size() - 2) {
				sb.append(", ");
			} else if (i == strings.size() - 2) {
				sb.append(", and ");
			}
		}
		sb.append('.');
		return sb.toString();
	}
}
