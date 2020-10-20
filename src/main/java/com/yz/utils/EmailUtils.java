package com.yz.utils;

import org.apache.commons.mail.HtmlEmail;

public class EmailUtils {
    public static void sendRegisterSuccess(String toString, String activeCode) {
        try {
            // 创建一个email消息
            HtmlEmail email = new HtmlEmail();

            // 设置邮箱服务器
            email.setHostName("smtp.163.com");
            //设置编码集
            email.setCharset("utf-8");
            // 设置发件人的授权信息
            email.setAuthentication("18504228736", "MDXHDAJYOVUVDICS");

            // 设置发件人
            email.setFrom("18504228736@163.com", "我的商城官方");

            // 设置收件人
            email.addTo(toString, "");

            // 设置邮件主题
            email.setSubject("千锋商城激活邮件，请勿回复");

            // 设置消息体
            email.setHtmlMsg("<html><head><meta charset=\"UTF-8\"></head><body><a href='http://localhost:8080/MyShop_war_exploded/user?action=active&code="+activeCode+"'>点击链接激活您的帐号</a></body></html>");

            // 发送邮件
            email.send();
        } catch (Exception e) {

        }
    }
}
