package com.tiantian.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.tiantian.dto.ResultData;
import com.tiantian.entity.Admin;
import com.tiantian.entity.Book;
import com.tiantian.entity.Chapter;
import com.tiantian.entity.Message;
import com.tiantian.service.AdminService;
import com.tiantian.service.BookService;
import com.tiantian.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:xiyue
 * @date:Created at 2020/05/04
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MessageService messageService;

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "page/login/login";
    }


    @RequestMapping(value = "/novelPage", method = RequestMethod.GET)
    public String novelPage(Integer bookId, Model model) {


        Book book = bookService.findById(bookId);
        List<Message> messageList = messageService.findByBookId(bookId);

        model.addAttribute("book", book);
        model.addAttribute("messageList", messageList);
        return "novel";
    }

    @RequestMapping(value = "/shiduPage", method = RequestMethod.GET)
    public String shiduPage(Integer bookId, Integer page, Model model) {
        Book book = bookService.findById(bookId);
        Chapter chapter = book.getChapterList().get(page - 1);
        int totalPage = book.getChapterList().size();
        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);
        book.setChapterList(chapterList);
        model.addAttribute("book", book);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        return "shidu";
    }

    /**
     * 验证码的生成
     */
    @GetMapping("/verificationCode")
    @ResponseBody
    public void verificationCode(HttpServletResponse response , HttpSession session) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        //图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write(response.getOutputStream());
        //输出code
        String code = lineCaptcha.getCode();
        session.setAttribute("userVerificationCode",code);
        //验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");
    }


    /**
     * 登陆操作
     *
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultData login(Admin admin, HttpServletRequest request,  String verificationCode) {
        //从session中取出验证码

        HttpSession session = request.getSession();
        session.getAttribute("userVerificationCode");
        // 登陆操作
        ResultData resultData = adminService.login(admin);
        // 判断登陆成功,将用户数据保存到 session中
        // 如何获取session对象?
        if (resultData.getCode() == 0) {

            session.setAttribute("admin", resultData.getData());
        }

        return resultData;
    }


    @RequestMapping("/rloginPage")
    public String rloginPage() {
        return "index1";
    }


    /**
     * 退出登陆的方法
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 只需要去将保存到session的数据干掉即可
        request.getSession().setAttribute("admin", null);

        // 重定向和转发的区别?
        // 重定向: 1. 客户端行为 2. 两次请求 3. 不能携带前一次请求的数据
        // 转发: 1. 服务器端行为 2. 一次请求 3. 能够携带前一次请求的数据
        return "redirect:/loginPage";
    }
}
