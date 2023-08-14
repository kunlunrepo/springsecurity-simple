package com.sec.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 10:52
 */
@SpringBootTest
public class JwtTest {

    /**
     * JWT标准字段说明：
     * jti: jwt的唯一身份标识，主要用来作为一次性token
     * iat: jwt的签发时间
     * exp: jwt的过期时间，这个过期时间必须要大于签发时间
     * nbf: 定义在什么时间之前，该jwt都是不可用的.
     * aud: 接收jwt的一方
     * sub: jwt所面向的用户
     * iss: jwt签发者
     */

    // 创建JWT
    @Test
    public void createJWT() {

        JwtBuilder builder = Jwts.builder()
                .setId("9527") // 设置唯一ID
                .setSubject("hejiayun_community") // 设置主体
                .setIssuedAt(new Date()) // 设置签约时间
                .signWith(SignatureAlgorithm.HS256, "mashibing"); // 设置签名算法和安全key

        // 签名的JWT称为JWS
        String jws = builder.compact();

        System.out.println(jws);


    }

    // 创建JWT 带过期时间
    @Test
    public void createJWT2() {
        long currTime = System.currentTimeMillis();
        Date expTime = new Date(currTime);

        JwtBuilder builder = Jwts.builder()
                .setId("9527") // 设置唯一ID
                .setSubject("hejiayun_community") // 设置主体
                .setIssuedAt(new Date()) // 设置签约时间
                .setExpiration(expTime) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, "mashibing"); // 设置签名算法和安全key

        // 签名的JWT称为JWS
        String jws = builder.compact();

        System.out.println(jws);

    }

    // 创建JWT 存储更多信息
    @Test
    public void createJWT3() {
        long currTime = System.currentTimeMillis();
        Date expTime = new Date(currTime);

        JwtBuilder builder = Jwts.builder()
                .setId("9527") // 设置唯一ID
                .setSubject("hejiayun_community") // 设置主体
                .setIssuedAt(new Date()) // 设置签约时间
                .claim("roles", "admin") // 设置角色  自定义属性，最终在body中
                .claim("test", "test01")
                .signWith(SignatureAlgorithm.HS256, "mashibing"); // 设置签名算法和安全key

        // 签名的JWT称为JWS
        String jws = builder.compact();

        System.out.println(jws);

    }

    // 解析JWT
    @Test
    public void parserJWT(){
        // header.payload.sign
        String JWS = "eyJhbGciOiJIUzI1NiJ9."+
                "eyJqdGkiOiI5NTI3Iiwic3ViIjoiaGVqaWF5dW5fY29tbXVuaXR5IiwiaWF0IjoxNjkxOTg0MTQ2LCJyb2xlcyI6ImFkbWluIiwidGVzdCI6InRlc3QwMSJ9." +
                "QB2C6zfMkyZcQE4TzzwbaOokUzXOIdWpfALAvHNr17k";

        // claims = 载荷
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("mashibing")
                    .parseClaimsJws(JWS)
                    .getBody();
            System.out.println(claims);

        } catch (Exception e) {
            System.out.println("Token验证失败");
            e.printStackTrace();
        }
    }
}
