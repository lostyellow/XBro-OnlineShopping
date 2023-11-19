package bean;

import java.sql.Date;

public class Goods {
	private Integer id;
	private Integer sellerId;
	private String itemName;
	private String itemDescription;
	private String imgURL;
	private Float price;
	private String number;//生产批次号
	private String date;//有效期
	private Boolean isPres;//是否为处方药
	private Boolean isFrozen;//是否被冻结
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Boolean getIsPres() {
		return isPres;
	}
	public void setIsPres(Boolean isPres) {
		this.isPres = isPres;
	}
	public Boolean getIsFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(Boolean isFrozen) {
		this.isFrozen = isFrozen;
	}
	
	
}
