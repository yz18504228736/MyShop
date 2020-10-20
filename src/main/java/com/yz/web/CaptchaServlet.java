package com.yz.web;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        生成图片，发送给浏览器
        ValidateCode validateCode = new ValidateCode(90,30,4,25);
        validateCode.write(response.getOutputStream());
//        将图片所对应的字符串，保存到session中
        String code = validateCode.getCode();
        HttpSession session = request.getSession();
        session.setAttribute("captcha",code);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
