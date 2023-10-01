package com.formssi.zengzl.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.enums.UserTypeEnum;
import com.formssi.zengzl.base.exception.ValidateException;
import com.formssi.zengzl.base.utils.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    private final JwtUtil jwtUtil;
    private static long startTime;

    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request URL:{}", request.getRequestURL());
        startTime = System.currentTimeMillis();
        log.info("Start time:{}", startTime);

        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            throw new ValidateException(ResultCode.TOKEN_ERR);
        }

        try {
            DecodedJWT claims = jwtUtil.parseJWT(token);
            int roles = claims.getClaim("roles").asInt();
            if (roles == UserTypeEnum.ATHLETE.ordinal()) {
                request.setAttribute(UserTypeEnum.ATHLETE.name(), token);
            }
            if (roles == UserTypeEnum.RECORDER.ordinal()) {
                request.setAttribute(UserTypeEnum.RECORDER.name(), token);
            }
            if (roles == UserTypeEnum.ADMIN.ordinal()) {
                request.setAttribute(UserTypeEnum.ADMIN.name(), token);
            }
            if (roles == UserTypeEnum.SUPER_ADMIN.ordinal()) {
                request.setAttribute(UserTypeEnum.SUPER_ADMIN.name(), token);
            }
        }catch (Exception e) {
            throw new ValidateException(ResultCode.TOKEN_ERR);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long endTime = System.currentTimeMillis();
        log.info("End time:{}", System.currentTimeMillis());
        long executeTime  = endTime - startTime;
        log.info("Execute time:{}", executeTime);
    }
}
