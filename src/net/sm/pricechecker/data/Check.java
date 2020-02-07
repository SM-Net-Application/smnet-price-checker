package net.sm.pricechecker.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Check {

	private int id;
	private int idProduct;
	private LocalDateTime checkTime;
	private int status;
	private BigDecimal price;

	public Check(int id, int idProduct, LocalDateTime checkTime, int status, BigDecimal price) {
		super();
		this.id = id;
		this.idProduct = idProduct;
		this.checkTime = checkTime;
		this.status = status;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public LocalDateTime getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(LocalDateTime checkTime) {
		this.checkTime = checkTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
