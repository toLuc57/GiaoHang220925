package hm.GiaoHang.entity;

public class Receipt {
	private String id;
	private String idCustomer;
	private String idShip;
	private String idFee;
	private String date;
	private String origin;
	private String destination;
	private int duration;
	private int status;
	private int price;
	
	public Receipt(String idCustomer, String idShip, String idFee,
			String date,String origin, String destination,
			int duration, int status, int price) {
		this.idCustomer = idCustomer;
		this.idShip = idShip;
		this.idFee = idFee;
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		this.duration = duration;
		this.status = status;
		this.price = price;
	}
	public Receipt(String id, String idCustomer, String idShip, String idFee,
			String date,String origin, String destination,
			int duration, int status, int price) {
		this.id = id;
		this.idCustomer = idCustomer;
		this.idShip = idShip;
		this.idFee = idFee;
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		this.duration = duration;
		this.status = status;
		this.price = price;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIdFee() {
		return idFee;
	}
	public void setIdFee(String idFee) {
		this.idFee = idFee;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatusNotice() {
		return status == 0 ? "Not finish" : "Finished";
	}
	public String getDurationNotice() {
		return (duration/60) + " hour " 
				+ (duration%60) + " min.";
	}
}
