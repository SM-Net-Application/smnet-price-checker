package net.sm.pricechecker.database.model;

public class CategorySet {

    private Integer id;
    private String name;
    private Integer fatherID;

    public CategorySet(Integer id, String name, Integer fatherID) {
        this.id = id;
        this.name = name;
        this.fatherID = fatherID;
    }

    public CategorySet(String name) {
        this(null, name, -1);
    }

    public CategorySet(String name, Integer fatherID) {
        this(null, name, fatherID);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFatherID() {
        return fatherID;
    }

    public void setFatherID(Integer fatherID) {
        this.fatherID = fatherID;
    }
}
