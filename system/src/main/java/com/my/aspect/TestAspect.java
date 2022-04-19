//package com.my.aspect;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * 2022/4/15
// * NJL
// */
//@Component
//@Aspect
//public class TestAspect {
//
//    @Pointcut("execution(* com.my.utils.word.ExportWord.exportCheckWord2(..))")
//    public void test(){}
//
//    @Before(value = "test()")
//    public void beforeInsertExamLog(){
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//        System.out.println("---->");
//    }
//}
