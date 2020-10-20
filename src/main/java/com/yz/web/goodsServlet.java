package com.yz.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz.entity.Goods;
import com.yz.entity.GoodsType;
import com.yz.entity.GoodsTypeManger;
import com.yz.service.Impl.ShopServiceImpl;
import com.yz.service.ShopService;
import com.yz.utils.FileUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/product")
@MultipartConfig
public class goodsServlet extends BaseServlet {
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Part picture = request.getPart("picture");
//        文件存储目录(相对路径)
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String format1 = format.format(date);
//        生成文件名和获取文件扩展名
        String extName = FileUtils.getExtName(picture.getSubmittedFileName());
        String fileName = UUID.randomUUID().toString() + extName;
        String picLoc = "goodsImg/" + format1;
//        // 将相对路径转换成绝对路径
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath(picLoc);

        File file = new File(realPath);
        file.mkdirs();
//      文件生成路径--数据库存储
        String LastFilePath = format1 + "/" + fileName;
//        向磁盘输出流写入
        FileOutputStream fis = new FileOutputStream(realPath + "/" + fileName);
        int copy = IOUtils.copy(picture.getInputStream(), fis);
        fis.close();
//        将得到的数据存入到数据库
        Goods goods = new Goods();
        try {
            BeanUtils.populate(goods, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        接收名字和简介转换为utf-8
        String name = request.getParameter("name");
        String utf8name = new String(name.getBytes("iso-8859-1"), "utf-8");
        goods.setName(utf8name);
        String intro = request.getParameter("intro");
        String utf8intro = new String(intro.getBytes("iso-8859-1"), "utf-8");
        goods.setIntro(utf8intro);
        goods.setPicture(LastFilePath);
        ShopService shopService = new ShopServiceImpl();
//        调用方法
        boolean result = shopService.addGoods(goods);
        HashMap<String, Object> responseObj = new HashMap<>();
        if (!result) {
            responseObj.put("success", false);
            responseObj.put("msg", "添加失败");
        } else {
            responseObj.put("success", true);
            responseObj.put("msg", "添加成功");
        }
//        3.响应数据
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(responseObj));
    }

    protected void queryGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        // 2. 处理数据
        List<GoodsType> goods = GoodsTypeManger.queryAll();
        // 3. 响应数据
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(goods);
        out.write(jsonString);

    }
}