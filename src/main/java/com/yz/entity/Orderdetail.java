package com.yz.entity;

public class Orderdetail {
   private int id;
   private String Oid;
   private int pid;
   private int num;
   private int Money;

    public Orderdetail() {
    }

    public Orderdetail(int id, String oid, int pid, int num, int money) {
        this.id = id;
        Oid = oid;
        this.pid = pid;
        this.num = num;
        Money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    @Override
    public String toString() {
        return "Orderdetail{" +
                "id=" + id +
                ", Oid='" + Oid + '\'' +
                ", pid=" + pid +
                ", num=" + num +
                ", Money=" + Money +
                '}';
    }
}
