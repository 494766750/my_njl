package my.test;


public class BubbleSortTest {

    public static void main(String[] args) {
        BubbleSortTest bst = new BubbleSortTest();


        Integer[] intArr = {9, 8, 7, 6, 5, 4, 2};
        Integer[] intArrCopy = {9, 8, 7, 6, 5, 4, 2};

        System.out.println("---->初始数组顺序");
        for (int i = 0; i < intArr.length; i++) {
            if (i == intArr.length - 1) {
                System.out.println("---->" + intArr[i]);
            } else {
                System.out.print("---->" + intArr[i]);
            }
        }
        //选择排序
        //bst.selectionSort(intArr);

        //冒泡排序
        //bst.bubbleSort(intArr);

        //插入排序
        //bst.insertionSort(intArr);

        //二分法查找
        //bst.dichotomy(intArr);

        //归并排序
        //bst.mergeSort(intArr);
    }

    /**
     * 遍历输出数组
     *
     * @param intArr
     */
    public void traversal(Integer[] intArr) {
        for (int i = 0; i < intArr.length; i++) {
            if (i == intArr.length - 1) {
                System.out.println("---->" + intArr[i]);
            } else {
                System.out.print("---->" + intArr[i]);
            }
        }
    }

    /**
     * 二分法查找
     *
     * @param intArr
     */
    public void dichotomy(Integer[] intArr) {
        Integer target = 9;
        int middle = intArr.length / 2; //中间值
        int begin = 0;
        int end = intArr.length - 1;
        boolean bl = true;
        System.out.println("---->开始二分查找");
        while (bl) {
            if (target == intArr[middle]) {
                bl = false;
                System.out.println("---->下标为" + middle + "值是target");
                System.out.println("---->二分查找完成");
            } else if (target > intArr[middle]) {
                begin = middle + 1;
                middle = (begin + end) / 2;
            } else if (target < intArr[middle]) {
                end = middle - 1;
                middle = end / 2;
            }
        }
    }

    /**
     * 选择排序法
     *
     * @param intArr
     * @return
     */
    public Integer[] selectionSort(Integer[] intArr) {
        System.out.println("---->选择排序法开始排序");
        Integer puppet = null;
        for (int i = 0; i < intArr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < intArr.length; j++) {
                if (intArr[j] < intArr[minIndex]) {
                    minIndex = j;
                }
            }
            puppet = intArr[minIndex];
            intArr[minIndex] = intArr[i];
            intArr[i] = puppet;
        }
        //遍历输出
        traversal(intArr);
        System.out.println("---->选择排序法排序结束");
        return intArr;
    }

    /**
     * 冒泡排序
     *
     * @param intArr
     * @return
     */
    public Integer[] bubbleSort(Integer[] intArr) {
        System.out.println("---->开始冒泡排序");
        for (int i = 0; i < intArr.length; i++) {
            for (int j = 0; j < intArr.length - 1 - i; j++) {
                if (intArr[j] > intArr[j + 1]) {
                    Integer puppet = intArr[j];
                    intArr[j] = intArr[j + 1];
                    intArr[j + 1] = puppet;
                }
            }
        }
        //遍历输出
        traversal(intArr);
        System.out.println("---->冒泡排序完成");
        return intArr;
    }

    /**
     * 插入排序
     *
     * @param intArr
     * @return
     */
    public Integer[] insertionSort(Integer[] intArr) {
        System.out.println("---->开始插入排序");
        for (int i = 1; i < intArr.length; i++) {
            int beginIndex = i - 1;
            int current = intArr[i];
            while (beginIndex >= 0 && intArr[beginIndex] > current) {
                intArr[beginIndex + 1] = intArr[beginIndex];
                beginIndex--;
            }
            intArr[beginIndex + 1] = current;
        }
        //遍历数组
        traversal(intArr);
        System.out.println("---->结束插入排序");
        return intArr;
    }

    /**
     * 归并排序  分治法
     *
     * @param intArr
     * @return
     */
    public Integer[] mergeSort(Integer[] intArr) {
        sort(intArr, 0, intArr.length - 1, new Integer[intArr.length]);
        traversal(intArr);
        return intArr;
    }

    /** 分-递归拆分数组
     *
     * @param intArr 排序数组
     * @param left 排序区间左节点（起点）
     * @param right 排序区间右节点（终点）
     * @param tmp 排序扩展空间，和原数组等长
     */
    public void sort(Integer[] intArr, int left, int right, Integer[] tmp) {
        //满足数组长度至少大于1，有排序意义
        if (left < right) {
            int mid = (left + right) / 2; //作为数组分界点
            sort(intArr, left, mid, tmp); //左数组递归
            sort(intArr, mid + 1, right, tmp); //右数组递归
            margeSort(intArr, left, mid, right, tmp);//排序
        }
    }

    /** 治-数组递归拆分完毕开始从底层排序
     *
     * @param intArr 排序数组
     * @param left 排序数组起点
     * @param mid 排序数组分组节点（左终点，mid+1为右起点）
     * @param right 排序数组终点
     * @param tmp 排序所需空间，和 intArr 等长
     */
    public void margeSort(Integer[] intArr, int left, int mid, int right, Integer[] tmp) {
        int i = left;//左数组指针
        int j = mid + 1; //右数组指针
        int t = 0;//tmp指针

        //开始比对拆分的左右数组进行排序
        while (i <= mid && j <= right) {
            if (intArr[i] <= intArr[j]) {
                tmp[t++] = intArr[i++];
            } else {
                tmp[t++] = intArr[j++];
            }
        }

        //左数组未比对完,剩余依次写入
        while (i <= mid) {
            tmp[t++] = intArr[i++];
        }

        //右数组未比对完,剩余依次写入
        while (j <= right) {
            tmp[t++] = intArr[j++];
        }

        t = 0;
        //拷贝至原数组
        while (left <= right) {
            intArr[left++] = tmp[t++];
        }
    }
}
