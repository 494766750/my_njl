package com.my.entity;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门表 sys_dept
 *
 * @author change
 */
@Data
public class SysDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private String orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 父部门名称 */
    private String parentName;

    //存标签，逗号拼接
    private String deptLabel;

    //存标签值，逗号拼接
    private String deptLabelValue;

    //暂不用
    private String[] deptLabelByDict;

    //存标签值
    private String[] deptLabelValueByDict;

    private String ncorgid;

    private String ncorgcode;

    private String ncorgpid;

    private String ncorgpcode;

    private String ncorgpname;
    /**  detp_short_name   */
    private String depeShortName;

    //子部门集合
    private List<SysDept> sysDepts;
}
