package com.yz.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz.entity.GoodsType;
import com.yz.entity.GoodsTypeManger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet("/showGoodsType")
public class showGoodsTypeServlet extends BaseServlet {
    //  商品分类展示
    protected void showType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        //        1.处理
        List<GoodsType> goodsTypes = GoodsTypeManger.queryAll();
        HttpSession session = request.getSession();
        session.setAttribute("goodsTypeList",goodsTypes);
        request.getRequestDispatcher("admin/showGoodsType.jsp").forward(request,response);
    }

    protected void searchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        //        1.接受数据
        String userType = request.getParameter("userType");
        String pubdate = request.getParameter("pubdate");
//        获得查询结果
        List<GoodsType> SearchTypes = GoodsTypeManger.querySearch(userType,pubdate);
        System.out.println("进来了");
        HashMap<String, Object> responseObj = new HashMap<>();
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        if (SearchTypes==null) {
            responseObj.put("success", false);
            responseObj.put("msg", "输入有误,查询不到");
        } else {
            responseObj.put("success", true);
            session.removeAttribute("goodsTypeList");
            session.setAttribute("goodsTypeList", SearchTypes);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(responseObj));
    }

}
