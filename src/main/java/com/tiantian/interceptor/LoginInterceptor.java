package com.tiantian.interceptor;

import com.tiantian.entity.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/3/17
 * \* Time: 9:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return  true:放行, false: 拦截不放行
     * @throws Exception
     */
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        // 先去获取session对象
//        HttpSession session = httpServletRequest.getSession();
//        // 判断session对象中是否保存了 登陆的用户信息?
//        Admin admin = (Admin) session.getAttribute("admin");
//        if(admin == null){
//            // session中没有 用户登陆的信息
//            // 重定向到 登陆页面
//            // 如何重定向呢?
//            String contextPath = httpServletRequest.getContextPath();
//            httpServletResponse.sendRedirect(contextPath+"/loginPage");
//            return false;
//        }

        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
