package hm.GiaoHang.entity;

public class Places {
	private String idplaces;
	private String origin;
	private String destination;
	private double distance; 
	private int duration;
	
	public Places ( String origin, String destination,
			double distance, int duration) {
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
		this.duration = duration;
	}
	public Places (String idplaces, String origin, String destination,
			double distance, int duration) {
		this.idplaces = idplaces;
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
		this.duration = duration;
	}
	public String getIdplaces() {
		return idplaces;
	}
	public void setIdplaces(String idplaces) {
		this.idplaces = idplaces;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
