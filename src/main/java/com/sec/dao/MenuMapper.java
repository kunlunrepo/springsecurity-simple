package com.sec.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sec.domain.Menu;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-18 11:23
 */
public interface MenuMapper extends BaseMapper<Menu> {

    // 根据用户编号查询权限
    List<String> selectPermsByUserId(Long id);

}
