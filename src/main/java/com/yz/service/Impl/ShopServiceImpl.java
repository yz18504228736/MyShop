package com.yz.service.Impl;

import com.yz.dao.Impl.ShopDaoImpl;
import com.yz.dao.ShopDao;
import com.yz.entity.Cart;
import com.yz.entity.Goods;
import com.yz.entity.GoodsType;
import com.yz.entity.User;
import com.yz.service.ShopService;

import java.util.List;

public class ShopServiceImpl implements ShopService {
    private ShopDao dao = new ShopDaoImpl();


    @Override
    public boolean addUser(User user) {
        int num = dao.insert(user);
        return num == 1;
    }

    @Override
    public Boolean queryUser(String username) {
        int result = dao.queryUser(username);
        return result > 0 ? true : false;
    }

    @Override
    public Boolean queryUserEm(String email) {

        int result = dao.queryUserEm(email);
        return result > 0 ? true : false;
    }

    //激活操作
    @Override
    public Boolean queryCode(String activeCode) {
        int result = dao.queryCode(activeCode);
        return result == 1 ? true : false;
    }
    //登录操作
    @Override
    public User updateLogin(String username, String password) {
        return dao.updateLogin(username, password);
    }
//  管理员登录
    @Override
    public User adminLogin(String username, String password) {

        return dao.adminLogin(username,password);
    }

    @Override
    public User selectClassify(String username, String password) {
        return null;
    }
//查询所有商品分类
    @Override
    public List<GoodsType> selectAllClassify() {
        return dao.selectAllClassify();
    }

    @Override
    public boolean addGoods(Goods goods) {
        return dao.addGoods(goods)==1?true:false;
    }
//查询商品
    @Override
    public List<Goods> showGoods() {
        ShopDao dao = new ShopDaoImpl();
        return dao.showGoods();
    }

    @Override
    public boolean addCart(String uid,String pid, String price) {
        return dao.addCart(uid,pid, price)==1?true:false;
    }

    @Override
    public List<Cart> addCart() {
        return dao.addCart();
    }

    @Override
    public List<Cart> query(String uid) {
        List<Cart> query = dao.query(uid);
        return query;
    }

    @Override
    public int sumPrice(String uid) {
        int result=dao.sumMoney(uid);
        return result;
    }

    @Override
    public void deleteCart(String aid) {
        dao.deleteCart(aid);
    }
//添加订单
    @Override
    public void addOrder(String aid) {
        dao.addOrder(aid);
    }

    @Override
    public int sum(String aid) {
        return dao.sum(aid);
    }


}
