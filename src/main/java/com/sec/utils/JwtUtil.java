package com.sec.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.kerberos.KerberosKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * description : Jwt工具类
 *
 * @author kunlunrepo
 * date :  2023-08-14 14:19
 */
public class JwtUtil {

    // 有效期为
    public static final Long JWT_TTL = 60*60*1000L; // 一个小时

    // 秘钥
    public static final String JWT_KEY = "secu";

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    // 生成jwt
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    // 生成jwt
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    // 生成jwt
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    public static JwtBuilder getJwtBuilder(String subject, Long ttlMills, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        if (ttlMills == null) {
            ttlMills = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMills + ttlMills;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject) // json数据
                .setIssuer("sg") // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 加密算法、秘钥
                .setExpiration(expDate);
    }

    // 生成加密后的秘钥
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    // 解析jwt
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    // 测试
    public static void main(String[] args) {
        String token = "";

        Claims claims = parseJWT(token);
        System.out.println(claims);
    }
}
