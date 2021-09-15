package com.my.service;


import com.my.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    List<SysUser> selectList();
    List<Map<String,Object>> selectSysUserByParams();
}
