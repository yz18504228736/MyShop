package com.yz.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yz.entity.*;
import com.yz.service.Impl.ShopServiceImpl;
import com.yz.service.ShopService;
import com.yz.utils.EmailUtils;
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
import java.util.UUID;

// 内聚
// 高内聚，低耦合
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    ShopService service = new ShopServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
//        1.接受数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String vcode = request.getParameter("vcode");
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute("captcha");
//        2.处理数据
//        数据库判断，
//        查询用户名和密码是否正确
        ShopService shopService = new ShopServiceImpl();
        User user = shopService.updateLogin(username, password);
        // 如果查到了，代表登录成功，如果没查到，代表登录失败
//        定义map存储数据库查询之后判断之后的结果
        HashMap<String, Object> responseObj = new HashMap<>();
       if (!vcode.equalsIgnoreCase(captcha)) {
            responseObj.put("success", false);
            responseObj.put("msg", "验证码错误，请重新输入");
        }else if (user==null){
            responseObj.put("success", false);
            responseObj.put("msg", "用户名或密码错误，请重新输入");
       } else if (user.getFlag() == 1){
           responseObj.put("success", false);
           responseObj.put("msg", "您的账号未激活，请前往邮箱激活");
        }else {
           responseObj.put("success", true);
           responseObj.put("msg", "operation success");
           session.setAttribute("loginUser", user);
        }
//        3.响应数据
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(responseObj));
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");

        // 1. 接收数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 2. 处理数据
        // 1) 生成激活码
        UUID uuid = UUID.randomUUID();
        String activeCode = uuid.toString();
//        2）发送邮件
        EmailUtils.sendRegisterSuccess(request.getParameter("email"), activeCode);
        // 2) 创建用户的表记录，并且添加激活码、设置用户角色为普通用户、设置用户状态为: 未激活
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
//            0为超级用户，1位普通用户
            user.setRole(1);
//            flag 1为未激活，2为激活状态
            user.setFlag(1);
//            添加激活码
            user.setCode(activeCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3) 将处理好的数据，添加到数据表当中
        ShopService shopService =  new ShopServiceImpl();
//        添加到数据库
        shopService.addUser(user);
        // 3. 响应数据  没什么可响应的
    }

    protected void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out = response.getWriter();

        // 1. 接收数据
        String email = request.getParameter("email");

        // 2. 处理数据
        ShopService shopService = new ShopServiceImpl();
        Boolean queryEm=shopService.queryUserEm(email);
        if (queryEm) {
            // 3. 响应数据
            out.write("false");
        } else {
            // 3. 响应数据
            out.write("true");
        }
    }

    protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out = response.getWriter();
        // 1. 接收数据
        String username = request.getParameter("username");
        // 2. 处理数据
        //查询数据库
        ShopService shopService = new ShopServiceImpl();
        Boolean queryResult = shopService.queryUser(username);

        if (queryResult) {
            // 3. 响应数据
            out.write("false");
        } else {
            // 3. 响应数据
            out.write("true");
        }
    }

    protected void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out = response.getWriter();
        String activeCode = request.getParameter("code");
//        处理数据
        ShopService shopService = new ShopServiceImpl();
        Boolean queryCode=shopService.queryCode(activeCode);
        if (queryCode) {
            //          响应数据
            request.getRequestDispatcher("CodeSuccess.jsp").forward(request,response);
        }else {
            //          响应数据
            request.getRequestDispatcher("CodeFailure.jsp").forward(request,response);
        }
    }
//    管理员登录
    protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out = response.getWriter();
//        1.接受数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
//        2.处理数据
//        数据库判断，
//        查询用户名和密码是否正确
        ShopService shopService = new ShopServiceImpl();
        User user = shopService.adminLogin(username, password);
        // 如果查到了，代表登录成功，如果没查到，代表登录失败
//        定义map存储数据库查询之后判断之后的结果
        HashMap<String, Object> responseObj = new HashMap<>();
         if (user == null) {
            responseObj.put("success", false);
            responseObj.put("msg", "用户名或密码错误，请重新输入");
        } else {
             responseObj.put("success", true);
             responseObj.put("msg", "");
             session.setAttribute("admin", user);
        }
//        3.响应数据
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(responseObj));
    }
//  商品查询
    protected void showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/plain; charset=utf-8");
        HttpSession session = request.getSession();
//    查询商品方法
    ShopService shopService = new ShopServiceImpl();
        List<Goods> goods = shopService.showGoods();
        session.setAttribute("glist",goods);
        request.getRequestDispatcher("goodsList.jsp").forward(request,response);


}


//展示商品细节
    protected void goodsDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
//    查询商品方法
        ShopService shopService = new ShopServiceImpl();
        List<Goods> goods = shopService.showGoods();
        for (Goods good : goods) {
            if (good.getId().equals(id)){
                session.setAttribute("goods",good);
            }
        }
        request.getRequestDispatcher("goodsDetail.jsp").forward(request,response);


    }


//    购物车
    protected void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        String pid = request.getParameter("goodsId");
        String price = request.getParameter("price");
        String uid = request.getParameter("uid");
//    添加购物车
        ShopService shopService = new ShopServiceImpl();
        boolean result = shopService.addCart(uid,pid,price);
        if (result){
            request.getRequestDispatcher("/user?action=showGoods").forward(request,response);
        }else{
            request.getRequestDispatcher("/user?action=showGoods").forward(request,response);
        }
    }

    protected void getCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        HttpSession session = request.getSession();
//    添加购物车
        ShopService shopService = new ShopServiceImpl();
//        用户id
        String uid = request.getParameter("id");
        Object loginUser = session.getAttribute("loginUser");
        //2.处理数据
//        结果为 id uid pid num money goodname
        List<Cart> list = shopService.query(uid);
//        结果为钱数
        int money = shopService.sumPrice(uid);
        //3.响应数据
        session.setAttribute("list" , list);
        System.out.println(list);
        session.setAttribute("money" , money);
        System.out.println(money);
        request.getRequestDispatcher("cart.jsp").forward(request,response);
        }
//        展示地址
    protected void showAddress(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        HttpSession session = request.getSession();
//    添加购物车
        AddressManger addressManger = new AddressManger();
//        用户id
        User loginUser = (User) session.getAttribute("loginUser");
        String s = String.valueOf(loginUser.getId());
        List<Address> addresses = AddressManger.queryAll(s);
        session.setAttribute("addList",addresses);
        request.getRequestDispatcher("order.jsp").forward(request,response);
    }


//添加收货地址
    protected void addAddress(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        HttpSession session = request.getSession();
//    添加购物车
        AddressManger addressManger = new AddressManger();
//        用户id
        String username = request.getParameter("name");
        String utf8name = new String(username.getBytes("iso-8859-1"), "utf-8");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        String utf8detail = new String(username.getBytes("iso-8859-1"), "utf-8");
        String uid = request.getParameter("uid");
        User loginUser = (User) session.getAttribute("loginUser");
//        增加收货地址
        boolean result=true;
        if (result){
            addressManger.addAddress(uid,utf8name,phone,utf8detail);
            result=false;
        }
        String s = String.valueOf(loginUser.getId());
        List<Address> addresses = AddressManger.queryAll(s);
        session.setAttribute("addList",addresses);
        Object addList = session.getAttribute("addList");
        request.getRequestDispatcher("order.jsp").forward(request,response);
    }

//添加购物车
    protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        TODO
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        HttpSession session = request.getSession();
        String aid = request.getParameter("aid");
//        通过aid查到uid,通过uid删除cart表中的数据,
//        地址表 uid=15
//        删除购物车里面的uid
        ShopService shopService = new ShopServiceImpl();
        shopService.deleteCart(aid);
//  然后添加order列表，时间本地获取，aid为购物车id，status为状态
//        status 默认未支付,
        Order order = new Order();
        int sumMoney= shopService.sum(aid);
//        添加订单
        List<Address> addresses = AddressManger.addressAll();
        for (Address address : addresses) {
            if (aid.equals(String.valueOf(address.getId()))){
                order.setAid(address.getId());//aid uid
                order.setUid(Integer.parseInt(address.getUid()));

            }
        }
        shopService.addOrder(aid);

    }
}