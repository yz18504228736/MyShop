package com.yz.dao.Impl;

import com.yz.dao.AddaddressDao;
import com.yz.entity.Address;
import com.yz.utils.DatabaseUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AddaddressDaoImpl implements AddaddressDao {
    private JdbcTemplate jdbcTemplate = DatabaseUtils.getJdbcTemplate();
//查询所有
    @Override
    public List<Address> queryAddress() {
        String sql = "SELECT * FROM tb_address";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Address.class));
    }
//插入一条数据
    @Override
    public int addAddress(String uid, String username, String phone, String detail) {
        String sql = "insert into tb_address(uid,name,phone,detail,level)values(?,?,?,?,?)";
        int  result = jdbcTemplate.update(sql,
                uid, username, phone, detail, "0");
        return result;
    }
}
