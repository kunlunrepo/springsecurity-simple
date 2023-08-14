package com.sec.config;

import com.sec.domain.LoginUser;
import com.sec.utils.JwtUtil;
import com.sec.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * description : 自定义认证过滤器，校验用户请求中携带的token
 *
 * @author kunlunrepo
 * date :  2023-08-14 16:44
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    // 封装过滤器的执行逻辑
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1.从请求头中获取token
        String token = request.getHeader("token");

        // 2.判断token是否为空，为空直接放行
        if (!StringUtils.hasText(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return; // 避免走下面的逻辑
        }

        // 3.解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("非法token");
        }

        // 4.从redis中获取用户信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        // 5.将用户信息保存到SecurityContextHolder，便于后续访问控制和授权
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 6.放行
        filterChain.doFilter(request, response);

    }
}
