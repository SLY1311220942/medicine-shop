package com.sly.medicineshop.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sly.medicineshop.business.shop.model.Clerk;
import com.sly.medicineshop.common.redis.service.RedisService;
import com.sly.medicineshop.result.BaseResult;
import com.sly.medicineshop.result.ResultStatus;
import com.sly.medicineshop.system.constant.SystemConstant;
import com.sly.medicineshop.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author SLY
 * @date 2020/10/7
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @DubboReference
    private RedisService redisService;

    /**
     * 访问前拦截
     *
     * @param request []
     * @param response []
     * @param handler []
     * @return boolean
     * @author SLY
     * @date 2020/10/7
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!SystemConstant.LOGIN_CHECK){
            return true;
        }

        String token = request.getHeader(JwtUtils.JWT_TOKEN);
        if(StringUtils.isBlank(token)){
            response.getWriter().write(JSON.toJSONString(BaseResult.getInstance(ResultStatus.LOGIN_OUTTIME)));
            return false;
        }

        Clerk clerk = JwtUtils.getUser(token);
        BaseResult loginTokenResult = redisService.getLoginToken(clerk.getId());
        if(!loginTokenResult.isSuccess()){
            response.getWriter().write(JSON.toJSONString(loginTokenResult));
            return false;
        }
        String existToken = loginTokenResult.getData(String.class);
        if(StringUtils.isBlank(existToken)){
            response.getWriter().write(JSON.toJSONString(BaseResult.getInstance(ResultStatus.LOGIN_OUTTIME)));
            return false;
        }

        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(String.valueOf(clerk.getAccount()))).build();
            jwtVerifier.verify(existToken);
        } catch (TokenExpiredException e) {
            response.getWriter().write(JSON.toJSONString(BaseResult.getInstance(ResultStatus.LOGIN_OUTTIME)));
            return false;
        }

        redisService.putLoginToken(clerk.getId(), JwtUtils.refreshTime(token, 7 * 24 * 60 * 60 * 1000));
        return true;
    }

    /**
     * 拦截后操作
     *
     * @param request []
     * @param response []
     * @param handler []
     * @param modelAndView []
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
     * @param request []
     * @param response []
     * @param handler []
     * @param ex []
     * @author SLY
     * @date 2020/10/7
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
