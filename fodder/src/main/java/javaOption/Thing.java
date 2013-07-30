package javaOption;

public class Thing {

	public static String getThingWithLKey(String key) {
		if(key.equals("goodKey")) {
			return "thing";
		}
		else {
			return null;
		}
	}
}
