package hm.GiaoHang.entity;

public class Fee {
	private String feeid;
	private String feename;
	private double distance;
	private int mass;
	private int feeprice;
	
	public Fee(String feeid, String feename, double distance,
			int mass, int feeprice) {
		this.feeid = feeid;
		this.feename = feename;
		this.distance = distance;
		this.mass = mass;
		this.feeprice = feeprice;
	}
	
	public String getFeeid() {
		return feeid;
	}
	public void setFeeid(String feeid) {
		this.feeid = feeid;
	}
	public String getFeename() {
		return feename;
	}
	public void setFeename(String feename) {
		this.feename = feename;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance =  distance;
	}
	public int getMass() {
		return mass;
	}
	public void setMass(int mass) {
		this.mass = mass;
	}
	public int getFeeprice() {
		return feeprice;
	}
	public void setFeeprice(int feeprice) {
		this.feeprice = feeprice;
	}
}
