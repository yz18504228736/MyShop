package com.yz.entity;

import com.yz.dao.GoodsTypeDAO;
import com.yz.dao.Impl.GoodsTypeDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class GoodsTypeManger {
    private static List<GoodsType> goodsTypes;

    // Manager内部自动地获取数据库当中的内容，并填充到goodsTypes的静态成员内部
    static {
        GoodsTypeDAO dao = new GoodsTypeDAOImpl();
        goodsTypes = dao.queryAll();
    }

    // 根据等级查询
    // 场景: 列举所有的1级类别
    public static List<GoodsType> queryByLevel(int level) {
        // 返回值空数组
        List<GoodsType> result = new ArrayList<>();
        // 遍历所有的goodsTypes，将满足条件的结果，添加到result内部
        for (GoodsType goodsType : goodsTypes) {
            if (goodsType.getLevel() == level) {
                result.add(goodsType);
            }
        }

        return result;
    }

    // 根据父类别查询
    // 场景: 1级类别选中了家用电器，第2个select应该列出所有家用电器的子类别
    public static List<GoodsType> queryByParent(String parentId) {
        // 返回值空数组
        List<GoodsType> result = new ArrayList<>();

        // 遍历所有的goodsTypes，将满足条件的结果，添加到result内部
        if (parentId != null) {
            for (GoodsType goodsType : goodsTypes) {
                // 如果传进来的parentId为null
                if (parentId.equals(goodsType.getParent())) {
                    result.add(goodsType);
                }
            }
        }

        return result;
    }
    // 数据库的内容和Manager内部的内容，要一致

    // 1数据库，2Manager，要保证两个地方的数据是一致的
    public static void add(GoodsType goodsType) {
        // 1. 插入到数据库当中
        GoodsTypeDAOImpl dao = new GoodsTypeDAOImpl();
        int count = dao.insert(goodsType);

        // 2. 添加到categories静态成员当中
        if (count == 1) {
            // 插入成功
            goodsTypes.add(goodsType);
        }
    }

    //    无条件查询所有商品分类
    public static List<GoodsType> queryAll() {
        return goodsTypes;
    }

    public static List<GoodsType> querySearch(String userType, String pubdate) {
        List<GoodsType> serchType = new ArrayList<>();
        for (GoodsType goodsType : goodsTypes) {
            Integer a = goodsType.getLevel();
            String str = Integer.toString(a);
            if ((userType.equals(str)) && pubdate.equals(goodsType.getName())) {
                serchType.add(goodsType);
            }
        }
        return serchType;
    }
}

