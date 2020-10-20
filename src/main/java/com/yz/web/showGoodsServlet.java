package com.yz.web;

import com.yz.entity.Goods;
import com.yz.entity.GoodsType;
import com.yz.entity.GoodsTypeManger;
import com.yz.service.Impl.ShopServiceImpl;
import com.yz.service.ShopService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/showGoods")
public class showGoodsServlet extends BaseServlet {
    //商品展示
    protected void showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        //        1.处理
        List<GoodsType> goodsTypes = GoodsTypeManger.queryAll();
        ShopService shopService = new ShopServiceImpl();
        List<Goods> goods = shopService.showGoods();
        for (Goods good : goods) {
            for (GoodsType goodsType : goodsTypes) {
                    if (goodsType.getId().equals(good.getTypeid())){
                        good.setTypeid(goodsType.getName());
                    }
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("goodsList",goods);

        request.getRequestDispatcher("admin/showGoods.jsp").forward(request,response);
    }
}
