package com.my.service;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.entity.SysDept;

import java.util.List;

public interface SysDeptService {

    List<SysDept> selectList();

    List<SysDept> selectListToTree();
}
