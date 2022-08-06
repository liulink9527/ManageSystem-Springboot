package com.link.utils;


import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.link.common.Constants;
import com.link.exception.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.util.Date;

/**
 * @author Link
 * @Description
 * @date 2022-08-06 9:26
 */
public class TokenUtil {

    /**
     * 创建一个token
     * 有效期为2小时
     *
     * @param userId 用于当作载荷
     * @param sign   password作为token的密匙
     * @return
     */
    public static String getToken(String userId, String sign) {
        return JWT.create().withAudience(userId)   //将userId保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))   //两小时后过期
                .sign(Algorithm.HMAC256(sign));  //以password作为token的密钥

    }

    /**
     * 校验token
     * @param token
     * @param password
     */
    public static void verifyToken(String token, String password) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(password)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "Token校验失败");
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new ServiceException(Constants.CODE_500, "获取签发对象失败");
        }
        return audience;
    }


}
