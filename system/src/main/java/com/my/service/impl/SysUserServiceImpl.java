package com.my.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.my.entity.SysUser;
import com.my.mapper.SysUserMapper;
import com.my.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectList() {
        SysUser user = new SysUser();
        user.setUserId(1L);
        return sysUserMapper.selectList(null);
    }

    @Override
    public List<Map<String,Object>> selectSysUserByParams() {
        return sysUserMapper.selectSysUserByParams();
    }
}
