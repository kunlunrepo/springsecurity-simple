package com.sec.controller;

import com.sec.domain.ResponseResult;
import com.sec.utils.RedisCache;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 14:41
 */
@RestController
public class CaptchaController {

    private static final String CAPTCHA_CODE_KEY = "sec:captcha:";

    @Autowired
    private RedisCache redisCache;

    // 生成验证码
    @GetMapping("/captchaImage")
    public ResponseResult getCode(HttpServletResponse response) {
        SpecCaptcha specCaptcha = new SpecCaptcha(130,48,4);

        // 生成验证码，及验证码唯一标识
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String key = CAPTCHA_CODE_KEY + uuid;
        String code = specCaptcha.text().toLowerCase();

        // 保存到redis
        redisCache.setCacheObject(key, code, 1000, TimeUnit.SECONDS);

        // 创建map
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", uuid);
        map.put("img", specCaptcha.toBase64());

        return new ResponseResult(200, "验证码获取成功", map);

    }

}
