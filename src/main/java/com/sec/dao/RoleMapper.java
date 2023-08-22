package com.sec.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sec.domain.Role;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 11:17
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRolesByUserId(Long id);

}
