package com.link.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.link.common.Constants;
import com.link.common.annotation.PassToken;
import com.link.entity.User;
import com.link.exception.ServiceException;
import com.link.mapper.UserMapper;
import com.link.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Link
 * @Description
 * @date 2022-08-06 9:50
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        //从请求头中取到token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        } else {
            // 执行认证
            if (token == null) {
                //这里其实是登录失效,没token了   这个错误也是我自定义的，读者需要自己修改
                throw new ServiceException(Constants.CODE_500, "无Token");
            }
            String userId = TokenUtil.getAudience(token);
            User user = userMapper.selectUserByUserId(Integer.valueOf(userId));
            if (user == null) {
                throw new ServiceException(Constants.CODE_500, "用户不存在，请重新登录");
            }

            TokenUtil.verifyToken(token, user.getPassword());
            return true;
        }
        return true;
    }
}
