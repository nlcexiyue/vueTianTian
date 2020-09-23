package com.tiantian.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.*.*.config
 * @ClassName: LoginConfig
 * @Description:拦截器配置
 * @author: zk
 * @date: 2019年9月19日 下午2:18:35
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/loginPage",//登录
                "/login",
                "/shiduPage",
                "/shidu",
                "/novelPage",
                "/novel",
                "/admin/add",
                "/admin/addPage",
                "/static/**"                        //放行所有资源
//                "/static/css/*.html",            //html静态资源
//                "/static/js/*.js",              //js静态资源
//                "/static/css/*.css",             //css静态资源
//                "/static/fonts/*.woff",
//                "/static/fonts/*.ttf",
//                "/static/fonts/*.eot",
//                "/static/fonts/*.svg"

        );
    }
}