package com.my.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends BaseMapper<SysUser> {

    List<Map<String,Object>> selectSysUserByParams();
}
