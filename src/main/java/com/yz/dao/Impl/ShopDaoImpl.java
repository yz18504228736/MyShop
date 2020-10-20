package com.yz.dao.Impl;

import com.yz.dao.ShopDao;
import com.yz.entity.*;
import com.yz.utils.DatabaseUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ShopDaoImpl implements ShopDao {
    private JdbcTemplate jdbcTemplate = DatabaseUtils.getJdbcTemplate();

    //    插入注册信息
    @Override
    public int insert(User user) {
        String sql = "insert into tb_user(username,password,email,gender,flag,role,code)values(?,?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getGender(), user.getFlag(), user.getRole(), user.getCode());
        return result;
    }

    //注册 验证用户名是否存在
    @Override
    public int queryUser(String username) {
        String sql = "SELECT COUNT(*) from tb_user WHERE username=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, username);
    }

    //注册，验证邮箱是否存在
    @Override
    public int queryUserEm(String email) {
        String sql = "SELECT COUNT(*) from tb_user WHERE email=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }

    //修改激活码
    @Override
    public int queryCode(String activeCode) {
        String sql = "update tb_user set flag=2 where code=?";
        int result = jdbcTemplate.update(sql, activeCode);
        return result;
    }

    //登录，查询用户名密码是否存在
    @Override
    public User updateLogin(String username, String password) {
        String sql = "SELECT * FROM tb_user WHERE username=? AND password=?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {
            user = null;
        }
        return user;
    }

    @Override
    public User adminLogin(String username, String password) {
        String sql = "select * from  tb_user WHERE role =0 AND username=? AND password=?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {
            user = null;
        }
        return user;
    }

    //查询所有分类
    @Override
    public List<GoodsType> selectAllClassify() {
        String sql = "select * from tb_goods_type";
        List<GoodsType> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>());
        return query;
    }

    //  增加商品
    @Override
    public int addGoods(Goods goods) {
        String sql = "insert into tb_goods(name,pubdate,picture,price,star,intro,typeid)values(?,?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql,
                goods.getName(),
                goods.getPubdate(),
                goods.getPicture(),
                goods.getPrice(),
                goods.getStar(),
                goods.getIntro(),
                goods.getTypeid());
        return result;
    }

    //查询商品
    @Override
    public List<Goods> showGoods() {
        String sql = "select * from tb_goods";
        List<Goods> goods = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Goods.class));
        return goods;
    }


    @Override
    public int addCart(String uid, String pid, String price) {
        int result = 0;
        Cart cart = new Cart();
                cart.setNum("1");
                cart.setMoney(price);
                cart.setId(uid);
                cart.setPid(pid);
                String sql = "insert into tb_cart(uid,pid,Num,money)values(?,?,?,?)";
                  return    result = jdbcTemplate.update(sql,
                             cart.getId(),
                             cart.getPid(),
                             cart.getNum(),
                             cart.getMoney());

    }
//查询所有商品
    @Override
    public List<Cart> addCart() {
        String sql = "select * from tb_cart";
        List<Cart> goods = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cart>(Cart.class));
        return goods;
    }

    @Override
    public List<Cart> query(String uid) {
        String sql = "select tb_cart.*,tb_goods.name as goodsName from  tb_cart inner join tb_goods on tb_goods.id=tb_cart.pid where uid = ?";
        return jdbcTemplate.query(sql , new BeanPropertyRowMapper<>(Cart.class) , uid);
    }

    @Override
    public int sumMoney(String uid) {
        String sql = "select count(money) from tb_cart where uid = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, uid);
    }

    @Override
    public void deleteCart(String aid) {
        String sql="DELETE FROM tb_cart WHERE uid=(SELECT uid FROM tb_address WHERE id=?)";
        jdbcTemplate.update(sql,aid);
    }

    @Override
    public void addOrder(String aid) {
        Order order = new Order();
        String sql = "insert into tb_cart(uid,pid,Num,money)values(?,?,?,?)";
//        return    result = jdbcTemplate.update(sql,
//                order.getUid(),
//                cart.getPid(),
//                cart.getNum(),
//                cart.getMoney());

    }
//查询所有钱数
    @Override
    public int sum(String aid) {
//TODO
    String sql = "select sum(money) from tb_cart where uid=(SELECT uid FROM tb_address WHERE id=?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, aid);
    }


}
