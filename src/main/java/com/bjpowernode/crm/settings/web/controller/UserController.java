package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("进入到用户控制台");
        String path = req.getServletPath();
        if ("/settings/user/login.do".equals(path)){
            login(req,resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入到验证操作");

        String loginAct = req.getParameter("loginAct");
        String loginPwd = req.getParameter("loginPwd");

        //将密码的明文形式转化为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收浏览器端的ip地址
        String ip = req.getRemoteAddr();
        System.out.println("ip地址:"+ip);

        //未来业务层开发，统一使用代理类形态的接口对象
        UserService us = (UserService)ServiceFactory.getService(new UserServiceImpl());

        try {

            User user = us.login(loginAct,loginPwd,ip);
            req.getSession().setAttribute("user",user);

            //如果程序执行到此处，说明业务层没有为controller抛出任何异常
            //表示登录成功
            PrintJson.printJsonFlag(resp,true);

        }catch (Exception e){
            e.printStackTrace();

            //一旦程序执行了catch块的信息，说明业务层为我们验证登录失败，为controller抛出异常
            //表示登录失败

            //打包信息
            String msg = e.getMessage();

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(resp,map);
        }
    }
}
