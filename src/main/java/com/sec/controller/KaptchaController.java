package com.sec.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.sec.domain.CheckCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 13:46
 */
@Controller
public class KaptchaController {

    private final Producer producer;

    @Autowired
    public KaptchaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/code/image")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 创建验证码文本
        String capText = producer.createText();

        // 创建验证码图片
        BufferedImage bufferedImage = producer.createImage(capText);

        // 将验证码文本放进session中
        CheckCode code = new CheckCode(capText);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, code);

        // 将验证码图片返回，禁止验证码图片缓存
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // 设置contentType
        response.setContentType("image/png");
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

    }

}
