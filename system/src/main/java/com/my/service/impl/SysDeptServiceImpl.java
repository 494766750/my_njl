package com.my.service.impl;

import com.my.entity.SysDept;
import com.my.mapper.SysDeptMapper;
import com.my.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> selectList() {
        return sysDeptMapper.selectList(null);
    }

    @Override
    public List<SysDept> selectListToTree() {
        List<SysDept> depts = selectList();
        Map<String,List<SysDept>> groupMaps = depts.stream().collect(Collectors.groupingBy(sysDept -> sysDept.getParentId().toString()));
        List<String> parentIds = new ArrayList<>(groupMaps.keySet());
//        List<SysDept> depts2 = depts.stream().filter(dept->{
//            return parentIds.contains(dept.getParentId().toString());
//        }).collect(Collectors.toList());
        for(String k : groupMaps.keySet()){
            List<SysDept> depts2 = depts.stream().filter(dept->{
               return k.equals(dept.getParentId().toString());
            }).collect(Collectors.toList());
        }
        return depts;
    }
}
