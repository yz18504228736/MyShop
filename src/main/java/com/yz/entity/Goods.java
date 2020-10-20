package com.yz.entity;

public class Goods {
    private String id;
    private String name;
    private String pubdate;
    private String picture;
    private String price;
    private String star;
    private String intro;
    private String typeid;

    public Goods() {
    }

    public Goods(String id, String name, String pubdate, String picture, String price, String star, String intro, String typeid) {
        this.id = id;
        this.name = name;
        this.pubdate = pubdate;
        this.picture = picture;
        this.price = price;
        this.star = star;
        this.intro = intro;
        this.typeid = typeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pubdate=" + pubdate +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", star=" + star +
                ", intro='" + intro + '\'' +
                ", typeid=" + typeid +
                '}';
    }
}
