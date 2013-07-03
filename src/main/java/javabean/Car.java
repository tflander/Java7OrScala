package javabean;

public class Car {
	private String model;
	private int miles;
	
	public Car(String model, int miles) {
		if (miles < 0) {
			throw new IllegalArgumentException("Error constructing car.  Expected miles >= 0, found " + miles);
		}
		validateModel(model);
		this.model = model;
		this.miles = miles;
	}
	
	public Car(String model) {
		this(model, 0);
	}
	
	public Car(Car car1) {
		this(car1.model, car1.miles);
	}

	private void validateModel(String model) {
		if (model.length() == 0) {
			throw new IllegalArgumentException("Error constructing car.  Model cannot be empty");
		}
	}
	
	@Override
	public int hashCode() {
		int result = model.hashCode();
		return 1009 * result + miles;
    }

	@Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Car))
            return false;

        Car rhs = (Car) obj;
        return model.equals(rhs.model) && miles == rhs.miles;
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Car(\"").append(model).append("\", ").append(miles).append(')');
		return sb.toString();
	}
	
	public String getModel() {
		return model;
	}

	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}
}
