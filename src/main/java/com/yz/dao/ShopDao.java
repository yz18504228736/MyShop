package com.yz.dao;

import com.yz.entity.Cart;
import com.yz.entity.Goods;
import com.yz.entity.GoodsType;
import com.yz.entity.User;

import java.util.List;

public interface ShopDao {
//    插入记录
    int insert(User user);
//查询用户名是否存在
    int queryUser(String username);

    int queryUserEm(String email);

    int queryCode(String activeCode);

    User updateLogin(String username, String password);

    User adminLogin(String username, String password);
//    查询全部分类
    List<GoodsType>selectAllClassify();

    int addGoods(Goods goods);

    List<Goods>showGoods();
//购物车添加
    int addCart(String uid, String pid, String price);
//    购物车查询
    public List<Cart> addCart();

    List<Cart> query(String uid);

    int sumMoney(String uid);


    void deleteCart(String aid);

    void addOrder(String aid);

    int sum(String aid);
}
