package com.my.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author njl
 */
@Data
@TableName("sys_user")
public class SysUser extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 部门ID */
    private Long deptId;

    @TableField(exist = false)
    private Long[] deptIds;

    /** 用户账号 */
    private String userName;

    private String userNameMd5;


    /** 用户昵称 */
    private String nickName;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phonenumber;

    /** 用户性别 */
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 盐加密 */
    @TableField(exist = false)
    private String salt;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    private Date loginDate;

    /** 部门对象 */
//    private SysDept dept;

    /** 角色对象 */
//    private List<SysRole> roles;

    /** 角色组 */
    @TableField(exist = false)
    private Long[] roleIds;

    /** 岗位组 */
    @TableField(exist = false)
    private Long[] postIds;

    private String ncId;

    private String ncOrgcode;

    private String ncOrgname;

    private String ncDeptcode;

    private String ncDeptname;

    @TableField(exist = false)
    private String[] largeScreenOptions;

}
