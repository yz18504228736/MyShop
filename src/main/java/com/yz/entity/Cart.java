package com.yz.entity;

public class Cart {
    private String id;
    private String pid;
    private String Num;
    private String money;
    private String goodsName;
    private  String sumMoney;
    public Cart() {
    }

    public Cart(String id, String pid, String num, String money, String goodsName, String sumMoney) {
        this.id = id;
        this.pid = pid;
        Num = num;
        this.money = money;
        this.goodsName = goodsName;
        this.sumMoney = sumMoney;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", Num='" + Num + '\'' +
                ", money='" + money + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", sumMoney='" + sumMoney + '\'' +
                '}';
    }

    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}
