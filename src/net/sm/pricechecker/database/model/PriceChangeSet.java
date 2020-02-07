package net.sm.pricechecker.database.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceChangeSet {

    private Integer id;
    private Integer productID;
    private BigDecimal price;
    private LocalDateTime changeDate;
    private Integer changeType;

    public PriceChangeSet(Integer id, Integer productID, BigDecimal price, LocalDateTime changeDate, Integer changeType) {
        this.id = id;
        this.productID = productID;
        this.price = price;
        this.changeDate = changeDate;
        this.changeType = changeType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }
}
