package my;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 2022/5/23
 * NJL
 */
public class Usort {
    
    public static void main(String[] args) {
        
        List<A> aList = new ArrayList<>();
        
        //设置行列 例：按照 5 行 6 列排
        int row = 5;
        int col = 6;
        
        int[][] chessArr1=new int[row][col];
        //排序方式 u 型
        for(int i = 1;i<=col;i++){
            if(i%2!=0){
                for(int j = 1;j<=row;j++){
                    aList.add(new A(j,i));
                    chessArr1[j-1][i-1] = i;
                }
            }else{
                for(int k = row;k>=1;k--){
                    aList.add(new A(k,i));
                    chessArr1[row-k][i-1] = i;
                }
            }
        }
        int sort = 1;
        Map<Integer,List<A>> m = aList.stream().collect(Collectors.groupingBy(A::getRow));
        for(A a : aList){
            System.out.println("---->学生"+sort+"在:"+"第"+a.row+"排，第"  + a.col+"列" +"\t");
            sort++;
        }
        for(Integer k:m.keySet()){
            for(A a : m.get(k)){
                System.out.print("---->" + a.row + "/" +a.col + " ");
            }
            System.out.println();
        }
    }
    static class A{
        int row;
        int col;
        
        public A(int row, int col) {
            this.row = row;
            this.col = col;
        }
    
        public int getRow() {
            return row;
        }
    
        public void setRow(int row) {
            this.row = row;
        }
    
        public int getCol() {
            return col;
        }
    
        public void setCol(int col) {
            this.col = col;
        }
    }
}
