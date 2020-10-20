package com.yz.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz.entity.GoodsType;
import com.yz.entity.GoodsTypeManger;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/category")
public class GoodsTypeServlet extends BaseServlet {
//    根据level查询所需要的的商品分类
    protected void queryByLevel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        // 1. 获取数据
        String levelParam = request.getParameter("level");
        int level = Integer.parseInt(levelParam);
        // 2. 处理数据
        List<GoodsType> goodsTypes = GoodsTypeManger.queryByLevel(level);


        // 3. 响应数据
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(goodsTypes);
        out.write(jsonString);
    }

//    根据父分类,查询子分类
    protected void queryByParent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        // 1. 获取数据
        String parent = request.getParameter("parent");
        // 2. 处理数据
        List<GoodsType> goodsTypes = GoodsTypeManger.queryByParent(parent);
        // 3. 响应数据
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(goodsTypes);
        out.write(jsonString);
    }

//    增加商品分类
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        // 1. 获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        HttpSession session = request.getSession();
        GoodsType goodsType = new GoodsType();
        try {
            BeanUtils.populate(goodsType, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. 处理数据
        GoodsTypeManger.add(goodsType);
        HashMap<String, Object> responseObj = new HashMap<>();
            responseObj.put("success", true);
//        3.响应数据
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(responseObj));
    }
}
