package com.my.web.controller;

import com.my.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloword")
public class HelloWord {

    @Autowired
    private SysUserService sysUserService;

    /**
     *
     * @return
     */
    @GetMapping("/userTest")
    public Object userTest(){
        return sysUserService.selectList();
    }

    @GetMapping("/deptTest")
    public Object deptTest(){
        return sysUserService.selectList();
    }
}
