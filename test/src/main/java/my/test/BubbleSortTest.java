package my.test;

public class BubbleSortTest {

    public static void main(String[] args) {
        Integer[] intArr = {9,8,7,6,5,4,2};
        Integer puppet = null;

        for (int i = 0;i<intArr.length;i++){
            System.out.print("---->" + intArr[i]);
        }
        System.out.println("---->");
        for (int i = 0;i<intArr.length-1;i++){
            for(int j = i+1;j<intArr.length;j++){
                if(intArr[i]>intArr[j]){
                    puppet = intArr[i];
                    intArr[i] = intArr[j];
                    intArr[j] = puppet;
                }
            }
        }
        Integer target = 9;
        int middle = intArr.length/2; //中间值
        int begin = 0;
        int end = intArr.length-1;
        boolean bl = true;
        while (bl){
            if(target == intArr[middle]){
                bl = false;
                System.out.println("---->下标为" + middle + "值是target");
            }else if(target>intArr[middle]){
                begin = middle +1;
                middle = (begin+end)/2;
            }else if(target<intArr[middle]){
                end = middle -1 ;
                middle = end/2;
            }
        }


        for (int i = 0;i<intArr.length;i++){
            System.out.print("---->" + intArr[i]);
        }

    }
}
