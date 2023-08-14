package com.sec.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-14 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {

    @TableId
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("nick_name")
    private String nickName;

    private String password;

    private String phonenumber;

    // 0男 1女 2未知
    private String sex;

    // 0正常 1停用
    private String status;

}
