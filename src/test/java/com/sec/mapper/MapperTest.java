package com.sec.mapper;

import com.sec.dao.UserMapper;
import com.sec.domain.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 14:56
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        List<SysUser> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
