package com.my.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.entity.SysDept;

import java.util.List;
import java.util.Map;

public interface SysDeptMapper extends BaseMapper<SysDept> {

    List<Map<String,Object>> selectSysUserByParams();
}
