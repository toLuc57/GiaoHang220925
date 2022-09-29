package hm.GiaoHang.entity;

public class Receipt {
	private String id;
	private String idCustomer;
	private String idShip;
	private String date;
	private double distance;
	private int status;
	
	public Receipt(String id, String idCustomer, String idShip,
			String date, double distance, int status) {
		this.id = id;
		this.idCustomer = idCustomer;
		this.idShip = idShip;
		this.date = date;
		this.distance = distance;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getIdShip() {
		return idShip;
	}
	public void setIdShip(String idShip) {
		this.idShip = idShip;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
