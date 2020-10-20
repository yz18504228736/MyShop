package com.yz.entity;

public class GoodsType {
   private String id;
   private String name;
   private int level;
   private String Parent;

    public GoodsType() {
    }

    public GoodsType(String id, String name, int level, String parent) {
        this.id = id;
        this.name = name;
        this.level = level;
        Parent = parent;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParent() {
        return Parent;
    }

    public void setParent(String parent) {
        Parent = parent;
    }

    @Override
    public String toString() {
        return "Goods_type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", Parent=" + Parent +
                '}';
    }
}
