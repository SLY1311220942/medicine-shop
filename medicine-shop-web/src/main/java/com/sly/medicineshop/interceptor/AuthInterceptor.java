package com.sly.medicineshop.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 *
 * @author SLY
 * @date 2020/10/7
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {


    /**
     * 访问前拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @author SLY
     * @date 2020/10/7
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 拦截后操作
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @author SLY
     * @date 2020/10/7
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 完成时操作
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @author SLY
     * @date 2020/10/7
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
