package com.yz.entity;

import java.util.Date;

public class Order {
    private String id;
    private int uid;
    private int money;
    private String status;
    private Date time;
    private int aid;

    public Order() {
    }

    public Order(String id, int uid, int money, String status, Date time, int aid) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.status = status;
        this.time = time;
        this.aid = aid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", uid=" + uid +
                ", money=" + money +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", aid=" + aid +
                '}';
    }
}
