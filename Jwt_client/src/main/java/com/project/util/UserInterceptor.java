package com.project.util;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
//@Component//被new了，这里不用注入
public class UserInterceptor implements HandlerInterceptor {

    //---controller处理拦截器前调用，返回false表示中断以后拦截器的执行---返回false则不通过
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求header中的token内容，进行验证，所有一定要header带有token！！！
        String token = request.getHeader("token");
        System.out.println(token);

        //解锁，认证
        if (JwtUtil.verity(token)){
            return true;
        }
        return false;
    }

    //---在controller方法执行后执行，prehandle返回true时执行，在DispatcherServlet进行视图渲染之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //---该方法将整个请求完成后(视图渲染完成)执行，prehandle返回true时执行，也就是DispatcherServlet渲染了视图执行，注意作用是用于清理资源的
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
