package com.sly.medicineshop.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sly.medicineshop.business.shop.model.Clerk;

import java.util.Date;

/**
 * jwt工具类
 *
 * @author sly
 */
public class JwtUtils {

    public static String JWT_TOKEN = "token";

    /**
     * 根据用户生成token
     *
     * @param clerk 店员
     * @return java.lang.String
     */
    public static String getToken(Clerk clerk) {
        return JWT.create()
                // id uuid
                .withJWTId(CommonUtils.genUUID())
                // Subject
                .withSubject(clerk.getAccount())
                // di对应Audience
                .withAudience(String.valueOf(clerk.getId()), clerk.getAccount(), clerk.getName(), String.valueOf(clerk.getShopId()))
                // 前面使用account
                .sign(Algorithm.HMAC256(clerk.getAccount()));
    }

    /**
     * 根据用户生成token
     *
     * @param clerk   店员
     * @param timeOut 超时时间
     * @return java.lang.String
     */
    public static String getToken(Clerk clerk, Long timeOut) {
        return JWT.create()
                // id uuid
                .withJWTId(CommonUtils.genUUID())
                // Subject
                .withSubject(clerk.getAccount())
                // di对应Audience
                .withAudience(String.valueOf(clerk.getId()), clerk.getAccount(), clerk.getName(), String.valueOf(clerk.getShopId()))
                // 配置的有效时间
                .withExpiresAt(new Date(System.currentTimeMillis() + timeOut))
                // 前面使用account
                .sign(Algorithm.HMAC256(clerk.getAccount()));
    }

    /**
     * 刷新token过期时间
     *
     * @param decodeToken 解密token
     * @return java.lang.String
     */
    public static String refreshTime(String decodeToken, long timeOut) {
        DecodedJWT decodedJWT = JWT.decode(decodeToken);

        String sign = JWT.create().withJWTId(decodedJWT.getId())
                .withSubject(decodedJWT.getSubject())
                .withAudience(decodedJWT.getAudience().toArray(new String[decodedJWT.getAudience().size()]))
                .withExpiresAt(new Date(System.currentTimeMillis() + timeOut))
                .sign(Algorithm.HMAC256(decodedJWT.getSubject()));
        return sign;
    }

    /**
     * 根据用户生成加密的token
     *
     * @param clerk 店员
     * @return java.lang.String
     * @author sly
     */
    public static String getEncodeToken(Clerk clerk) {
        return encodeToken(getToken(clerk));
    }

    /**
     * 根据用户生成加密的token
     *
     * @param clerk 店员
     * @return java.lang.String
     * @author sly
     */
    public static String getEncodeToken(Clerk clerk, Long timeOut) {
        return encodeToken(getToken(clerk, timeOut));
    }

    /**
     * 获取用户id
     *
     * @param
     * @return
     * @author SLY
     * @time 2019/9/18
     */
    public static String getUserId(String encodeToken) {
        String decodeToken = decodeToken(encodeToken);
        return JWT.decode(decodeToken).getAudience().get(0);
    }


    /**
     * 获取Id
     *
     * @param encodeToken 加密token
     * @return java.lang.String
     * @author SLY
     */
    public static String getIdWithEncodeToken(String encodeToken) {
        String decodeToken = decodeToken(encodeToken);
        return JWT.decode(decodeToken).getId();
    }

    /**
     * 获取Id
     *
     * @param decodeToken 解密token
     * @return java.lang.String
     */
    public static String getIdWithDecodeToken(String decodeToken) {
        return JWT.decode(decodeToken).getId();
    }


    /**
     * 解密token
     *
     * @param token token
     * @return java.lang.String
     * @author SLY
     */
    public static String decodeToken(String token) {
        return JwtDesUtils.decrypt(token);
    }

    /**
     * 加密token
     *
     * @param token token
     * @return java.lang.String
     * @author SLY
     */
    public static String encodeToken(String token) {
        return JwtDesUtils.encrypt(token);
    }

    /**
     * 获取用户信息
     *
     * @param encodeToken 加密token
     * @return com.sly.medicineshop.business.shop.model.Clerk
     */
    public static Clerk getUser(String encodeToken) {
        String decodeToken = decodeToken(encodeToken);
        DecodedJWT decodedJWT = JWT.decode(decodeToken);
        Clerk clerk = new Clerk();
        clerk.setId(Integer.parseInt(decodedJWT.getAudience().get(0)));
        clerk.setAccount(decodedJWT.getAudience().get(1));
        clerk.setName(decodedJWT.getAudience().get(2));
        clerk.setShopId(Integer.parseInt(decodedJWT.getAudience().get(3)));
        return clerk;
    }
}
