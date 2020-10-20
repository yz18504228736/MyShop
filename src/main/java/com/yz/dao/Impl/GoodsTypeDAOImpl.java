package com.yz.dao.Impl;

import com.yz.dao.GoodsTypeDAO;
import com.yz.entity.GoodsType;
import com.yz.utils.DatabaseUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GoodsTypeDAOImpl implements GoodsTypeDAO {
    private JdbcTemplate jdbcTemplate = DatabaseUtils.getJdbcTemplate();

    @Override
    public int insert(GoodsType GoodsType) {
        String sql = "INSERT INTO tb_goods_type(name, parent, level) VALUES(?,?,?)";
        int result = jdbcTemplate.update(sql, GoodsType.getName(), GoodsType.getParent(), GoodsType.getLevel());

        String lastIdSql = "SELECT last_insert_id()";
        String lastId = jdbcTemplate.queryForObject(lastIdSql, String.class);
        GoodsType.setId(lastId);
        return result;
    }

    @Override
    public List<GoodsType> queryAll() {
        String sql = "SELECT * FROM tb_goods_type";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GoodsType.class));
    }
}
