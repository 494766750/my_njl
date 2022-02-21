package com.my.web.controller;

import com.my.service.SysUserService;
import my.upload.client.UploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    
    @GetMapping("/upload")
    public Object upload(@RequestParam Map<String,String> map){
//        new UploadClient("F:\\vx\\WeChat Files\\wxid_itxjadm1sbtn51\\FileStorage\\File\\2022-02\\CESOFT_XMLJ20211208.bak","127.0.0.1",8099).execute();
//        new UploadClient(map.get("fileUrl"),map.get("ip"), Integer.parseInt(map.get("port"))).execute();
        return "";
    }
}
