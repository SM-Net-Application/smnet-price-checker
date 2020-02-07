package net.sm.pricechecker.data;

public class Product {

	private int id;
	private int sellerCode;
	private int countryCode;
	private int category;
	private String code;
	private String name;
	private String link;
	private String imageLink;

	private Check lastCheck;

	public Product(int id, int sellerCode, int countryCode, int category, String code, String name, String link,
			String imageLink) {
		super();
		this.id = id;
		this.sellerCode = sellerCode;
		this.countryCode = countryCode;
		this.category = category;
		this.code = code;
		this.name = name;
		this.link = link;
		this.imageLink = imageLink;
		this.lastCheck = null;
	}

	public Product(int id, int sellerCode, int countryCode, int category, String code, String name, String link,
			String imageLink, Check lastCheck) {
		super();
		this.id = id;
		this.sellerCode = sellerCode;
		this.countryCode = countryCode;
		this.category = category;
		this.code = code;
		this.name = name;
		this.link = link;
		this.imageLink = imageLink;
		this.lastCheck = lastCheck;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(int sellerCode) {
		this.sellerCode = sellerCode;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Check getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(Check lastCheck) {
		this.lastCheck = lastCheck;
	}

}
