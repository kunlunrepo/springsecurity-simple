package com.sec.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description : 字符串渲染工具类
 *
 * @author kunlunrepo
 * date :  2023-08-14 14:46
 */
public class WebUtils {

    // 将字符串渲染到客户端
    public static String renderString (HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
