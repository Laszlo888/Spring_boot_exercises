package com.store.webshop;

public class ShopItem {
	String name;
	String description;
	Double price;
	Integer qstock;
	String type;

	public ShopItem() {
	}

	public ShopItem(String name, String description, Double price, Integer qstock, String type) {
		this.name = name;
		this.description = description;
		this.price = Math.round(price * 100.0) / 100.0;
		this.qstock = qstock;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQstock() {
		return qstock;
	}

	public void setQstock(Integer qstock) {
		this.qstock = qstock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ShopItem [name=" + name + ", description=" + description + ", price=" + price + ", qstock=" + qstock
				+ ", type=" + type + ", getName()=" + getName() + ", getDescription()=" + getDescription()
				+ ", getPrice()=" + getPrice() + ", getQstock()=" + getQstock() + ", getType()=" + getType() + "]";
	}

}
