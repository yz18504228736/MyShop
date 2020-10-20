package com.yz.dao;

import com.yz.entity.GoodsType;

import java.util.List;


// 类别DAO
public interface GoodsTypeDAO {

    // 插入一条DAO数据
    int insert(GoodsType goodsType);

    // 查询所有
    List<GoodsType> queryAll();
}
