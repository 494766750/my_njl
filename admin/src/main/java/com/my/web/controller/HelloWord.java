package com.my.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/helloword")
public class HelloWord {

    @GetMapping("/test")
    public String helloWord(){
        StringBuffer stringBuffer = new StringBuffer();
        String user_id = "1";
        for (int i = 0;i<6-user_id.length();i++){
            stringBuffer.append("0");
        }
        stringBuffer.append(user_id);
        user_id = stringBuffer.toString();
        int a = 13;
        int b = 30;
        return new BigDecimal(a)
                .divide(new BigDecimal(b),2,BigDecimal.ROUND_HALF_DOWN)
                .divide(new BigDecimal(0.01),0,BigDecimal.ROUND_HALF_DOWN)
                .toString();
    }
}
