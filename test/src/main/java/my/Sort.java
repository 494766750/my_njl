package my;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2022/3/26
 * NJL
 */
public class Sort {
    
    public static void main(String[] args) {
    
        System.out.println("---->" +  54/5);
        System.out.println("---->" +  55%5);
        
        Sort sort = new Sort();
        sort.test();
        
    }
    
    private void test(){
        int columns = 5;
        List<Student> students = new ArrayList<>();
        for(int i = 0;i<25;i++){
            Student stu = new Student();
            stu.setId(i);
            stu.setName("<"+i+">");
            students.add(stu);
        }
//        for(int i = 0;i<25;i++){
//            System.out.println("---->" + students.get(i).getName());
//        }
    }
    
    private void UTest(int columns,List<Student> students){
        Map<Integer,List<Student>>   listMap =  new HashMap<>();
        int rows = students.size()/columns;
        int remainder = students.size()%columns;
        if(remainder != 0) rows++;
        for(int col = 1 ;col<=columns;col++){
            List<Student> rowStu = new ArrayList<>();
            for(int row = 1 ;row<=rows;row++){
                if(col-remainder>0 && rows==row){
                    break;
                }
                Student stu = students.get(col*row-1);
                
            }
            listMap.put(col,rowStu);
        }
        
        
    }
    
    
    @Data
    class Student{
        int id;
        String name;
        String row;
        String col;
    }
}
