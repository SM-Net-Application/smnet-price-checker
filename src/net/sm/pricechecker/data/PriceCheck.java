package net.sm.pricechecker.data;

import java.math.BigDecimal;

public class PriceCheck {

	private PriceCheckStatus status;
	private String productTitle;
	private String link;
	private String imageLink;
	private BigDecimal price;

	public PriceCheck(String amazonLink) {
		super();

		initFields();
		this.link = amazonLink;
	}

	private void initFields() {

		this.status = PriceCheckStatus.CHECK_IN_PROGRESS;
		this.productTitle = "";
		this.link = "";
		this.imageLink = "";
		this.price = BigDecimal.ZERO;
	}

	public boolean isValid() {

		switch (this.status) {
		case CHECK_IN_PROGRESS:
		case DONE:
			return true;
		default:
			return false;
		}
	}

	public boolean isProductValid() {

		switch (this.status) {
		case CONNECTION_ERROR:
		case PAGE_SOURCE_ERROR:
		case BOT_CHECK_ERROR:
		case PRODUCT_TITLE_ERROR:
		case PRODUCT_TITLE_EMPTY:
			return false;
		default:
			return true;
		}
	}

	public boolean isCheckValid() {

		switch (this.status) {
		case CONNECTION_ERROR:
		case PAGE_SOURCE_ERROR:
		case BOT_CHECK_ERROR:
		case PRODUCT_PRICE_ERROR:
		case PRODUCT_PRICE_EMPTY:
		case PRODUCT_PRICE_FORMAT_NOT_VALID:
		case PRODUCT_PRICE_CONVERSION_ERROR:
			return false;
		default:
			return true;
		}
	}

	public PriceCheckStatus getStatus() {
		return status;
	}

	public void setStatus(PriceCheckStatus status) {
		this.status = status;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
