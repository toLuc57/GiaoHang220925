package hm.GiaoHang.entity;

public class UserAccount {
	private String id;
	private String username;
	private String password;
	private String idCustomer;
	private String idStaff;
	private String status;
	
	public UserAccount(){
	}
	
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public UserAccount(String username, String password,
			String idCustomer, String idStaff, String status) {
		this.username = username;
		this.password = password;
		this.idCustomer = idCustomer;
		this.idStaff = idStaff;
		this.status = status;
	}
	
	public UserAccount(String id, String username, String password,
			String idCustomer, String idStaff, String status) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.idCustomer = idCustomer;
		this.idStaff = idStaff;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getIdStaff() {
		return idStaff;
	}

	public void setIdStaff(String idStaff) {
		this.idStaff = idStaff;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
