package hm.GiaoHang.entity;

public class Places {
	private String id;
	private String name;
	private double x;
	private double y;
	
	public Places(String id, String name, 
			double x, double y) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public static double calculateDistance(Places a, Places b) {
		double kc = Math.sqrt(Math.pow(b.y - a.y,2) + Math.pow(b.x - a.x, 2));
        return (double)Math.round(kc*100)/100;
	}
}
