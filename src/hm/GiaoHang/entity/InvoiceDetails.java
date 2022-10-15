package hm.GiaoHang.entity;

public class InvoiceDetails {
	private String id;
	private String goodsId;
	private String goodsName;
	private int amount;
	private double price;
	
	public InvoiceDetails(String id, String goodsId,
			String goodsName, int amount, double price) {
		this.id =id;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.amount = amount;
		this.price = price;
	}	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}	
}
