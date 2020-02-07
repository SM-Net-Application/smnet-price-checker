package net.sm.pricechecker.data;

public enum PriceCheckStatus {

	CHECK_IN_PROGRESS(1),
	CONNECTION_ERROR(2),
	PAGE_SOURCE_ERROR(3),
	BOT_CHECK_ERROR(4),
	PRODUCT_TITLE_ERROR(5),
	PRODUCT_TITLE_EMPTY(6),
	PRODUCT_PRICE_ERROR(7),
	PRODUCT_PRICE_EMPTY(8),
	PRODUCT_PRICE_FORMAT_NOT_VALID(9),
	PRODUCT_PRICE_CONVERSION_ERROR(10),
	DONE(11),
	INITIAL_PRICE(12),
	PRICE_INCREASED(13),
	PRICE_REDUCED(14);
	
	private int id;

	private PriceCheckStatus(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
