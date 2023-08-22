package com.sec.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 11:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements Serializable {

    @TableId
    private Long roleId;

    // 角色名
    private String roleName;

    // 角色权限字符串
    private String roleKey;

    // 角色状态 0正常 1停用
    private String status;

    // 删除标志 0存在 1删除
    private String delFlag;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private String remark;

}
