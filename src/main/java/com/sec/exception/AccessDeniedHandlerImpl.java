package com.sec.exception;

import com.alibaba.fastjson.JSON;
import com.sec.domain.ResponseResult;
import com.sec.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description : 自定义处理授权过程中的异常
 * @author kunlunrepo
 * date :  2023-08-21 17:34
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足，禁止访问");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
