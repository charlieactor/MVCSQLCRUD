package data;

public class Hike {
	private String name;
	private double length;
	private String difficulty;
	private double distanceFromDenver;
	private String fact;
	private int Id;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setDistanceFromDenver(double distanceFromDenver) {
		this.distanceFromDenver = distanceFromDenver;
	}

	public Hike() {
	}
	
	public Hike(String name, double length, String difficulty, double distanceFromDenver, String fact, int id) {
		super();
		this.name = name;
		this.length = length;
		this.difficulty = difficulty;
		this.distanceFromDenver = distanceFromDenver;
		this.fact = fact;
		this.Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public double getDistanceFromDenver() {
		return distanceFromDenver;
	}
	public void setDistanceFromDenver(int distanceFromDenver) {
		this.distanceFromDenver = distanceFromDenver;
	}
	public String getFact() {
		return fact;
	}
	public void setFact(String fact) {
		this.fact = fact;
	}
	
	
	

}
