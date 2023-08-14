package com.sec.password;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 15:52
 */
@SpringBootTest
public class PasswordTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testBcryp() {

        // 编码
        String e1 = passwordEncoder.encode("123456");
        String e2 = passwordEncoder.encode("123456");
        System.out.println(e1);
        System.out.println(e2);

        System.out.println(e1.equals(e2));

        // 判断
        boolean b = passwordEncoder.matches("123456", e1);
        System.out.println("============" + b);

    }
}
