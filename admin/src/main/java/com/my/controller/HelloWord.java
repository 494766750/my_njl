package com.my.controller;

import com.my.service.SysUserService;
import com.my.utils.DateUtils;
import com.my.utils.word.ExportWord;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    @GetMapping("/createWord")
    public String createWord(HttpServletResponse response){
        ExportWord ew = new ExportWord();
        XWPFDocument document = ew.createXWPFDocument();
        List<List<Object>> list = new ArrayList<>();
        Map<String, Object> dataList = new HashMap<>();
        dataList.put("TITLE", "我是标题---------------------");
        dataList.put("TABLEDATA", list);
    
        String date = DateUtils.datePath();
        String savePath = "D:/data/uploadPath/autoWord/"+date;
        String name = "/autoWord.docx";
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
           byte[] wordByte = ew.exportCheckWord2(dataList, document);
            response.reset();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment; filename=\"autoWord.docx\"");
            response.addHeader("Content-Length", "" + wordByte.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            org.apache.commons.io.IOUtils.write(wordByte, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String uploadDir = (savePath+name).substring("D:/data/uploadPath".length());
        return uploadDir;
    }
}
