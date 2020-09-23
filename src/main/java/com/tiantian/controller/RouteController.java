package com.tiantian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
    @RequestMapping(value = "/1")
    public String login1(){
        return "/index";
    }
    @RequestMapping(value = "/2")
    public String login2(){
        return "/index2";
    }

    //主页面登录
    @RequestMapping(value = "/")
    public String login(){
        return "/page/login/login";
    }
    //后台首页
    @RequestMapping(value = "/main")
    public String mainPage(){
        return "/page/main";
    }

    //文章列表
    @RequestMapping(value = "/newsList")
    public String newsList(){
        return "/page/news/newsList";
    }

    //添加文章
    @RequestMapping(value = "/newsAdd")
    public String newsAdd(){
        return "/page/news/newsAdd";
    }

    //编辑文章
    @RequestMapping(value = "/newsUpdate")
    public String newsUpdate(){
        return "/page/news/newsUpdate";
    }

    //图片管理
    @RequestMapping(value = "/images")
    public String images(){
        return "/page/img/images";
    }

    //404页面
    @RequestMapping(value = "/cantFind")
    public String cantFind(){
        return "/page/404";
    }



    //用户中心
    @RequestMapping(value = "/userList")
    public String userList(){
        return "/page/user/userList";
    }

    //添加用户userAdd
    @RequestMapping(value = "/userAdd")
    public String userAdd(){
        return "/page/user/userAdd";
    }

    //用户修改页面
    @RequestMapping(value = "/userUpdate")
    public String userUpdate(){
        return "/page/user/userUpdate";
    }

    //会员中心
    @RequestMapping(value = "/userGrade")
    public String userGrade(){
        return "/page/user/userGrade";
    }

    //会员图书
    @RequestMapping(value = "/userBook")
    public String userBook(){
        return "/page/user/userBook";
    }

    //作者中心
    @RequestMapping(value = "/authorList")
    public String authorList(){
        return "/page/user/authorList";
    }

    //系统基本参数
    @RequestMapping(value = "/basicParameter")
    public String kk(){
        return "/page/systemSetting/basicParameter";
    }

    //系统日志
    @RequestMapping(value = "/logs")
    public String logs(){
        return "/page/systemSetting/logs";
    }

    //友情链接
    @RequestMapping(value = "/linkList")
    public String linkList(){
        return "/page/systemSetting/linkList";
    }

    //图标管理
    @RequestMapping(value = "/icons")
    public String k(){
        return "/page/systemSetting/icons";
    }

    //三级联动模块
    @RequestMapping(value = "/addressDoc")
    public String addressDoc(){
        return "/page/doc/addressDoc";
    }

    //bodyTab模块
    @RequestMapping(value = "/bodyTabDoc")
    public String bodyTabDoc(){
        return "/page/doc/bodyTabDoc";
    }

    //三级菜单
    @RequestMapping(value = "/navDoc")
    public String navDoc(){
        return "/page/doc/navDoc";
    }

    //个人资料
    @RequestMapping(value = "/userInfo")
    public String userInfo(){
        return "/page/user/userInfo";
    }

    //修改密码
    @RequestMapping(value = "/changePwd")
    public String changePwd(){
        return "/page/user/changePwd";
    }


}
