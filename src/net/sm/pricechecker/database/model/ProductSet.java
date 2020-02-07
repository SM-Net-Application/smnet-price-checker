package net.sm.pricechecker.database.model;

public class ProductSet {

    private Integer id;
    private String code;
    private String name;
    private Integer categoryID;
    private Integer sellerID;
    private String url;
    private String imageURL;

    public ProductSet(Integer id, String code, String name, Integer categoryID, Integer sellerID, String url, String imageURL) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.categoryID = categoryID;
        this.sellerID = sellerID;
        this.url = url;
        this.imageURL = imageURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getSellerID() {
        return sellerID;
    }

    public void setSellerID(Integer sellerID) {
        this.sellerID = sellerID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
