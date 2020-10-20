package com.yz.service;

import com.yz.entity.Cart;
import com.yz.entity.Goods;
import com.yz.entity.GoodsType;
import com.yz.entity.User;

import java.util.List;

public interface ShopService {



    //    增加。注册功能
    boolean addUser(User user);
//    查询用户名是否存在
    Boolean queryUser(String username);
    //    验证邮箱
    Boolean queryUserEm(String email);

    Boolean queryCode(String activeCode);


    User updateLogin(String username, String password);

    User adminLogin(String username, String password);

    User selectClassify(String username, String password);

//    查询全部商品分类
    List<GoodsType> selectAllClassify();

    boolean addGoods(Goods goods);

    List<Goods> showGoods();


    boolean addCart(String uid, String pid, String price);
//    查询购物车
    List<Cart> addCart();

    List<Cart> query(String uid);

    int sumPrice(String uid);
//点击添加订单,删除购物车商品
    void deleteCart(String aid);

    void addOrder(String aid);

    int sum(String aid);

}
