package javaGroupAndFilter;

public class Summary {
	private String value;
	private int count;
	
	public Summary(String value, int count) {
		this.value = value;
		this.count = count;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void incCount() {
		++count;
	}

}
