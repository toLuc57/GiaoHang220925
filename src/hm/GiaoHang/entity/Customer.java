package hm.GiaoHang.entity;

public class Customer {
	private String idcustomer;
	private String name;
	private String telephone;
	private String address;
	private int status;
	
	public Customer(String name,String telephone,String address,int status) {
		this.name = name;
		this.telephone = telephone;
		this.address = address;
		this.status = status;
	}
	public Customer(String id, String name,String telephone,
			String address,int status) {
		this.idcustomer = id;
		this.name = name;
		this.telephone = telephone;
		this.address = address;
		this.status = status;
	}
	public String getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(String idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
