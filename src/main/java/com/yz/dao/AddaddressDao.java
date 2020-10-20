package com.yz.dao;

import com.yz.entity.Address;

import java.util.List;

public interface AddaddressDao {
//    查询所有
    List<Address> queryAddress();
//    插入一条数据
    int addAddress(String uid, String username, String phone, String detail);
}
