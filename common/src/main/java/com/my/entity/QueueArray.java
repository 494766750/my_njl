package com.my.entity;

/**
 * java实现循环队列
 *
 * @author wydream
 */

public class QueueArray {

    Object[] arr = new Object[10];//对象数组，队列最多存储a.length-1个对象
    int front = 0;//队首下标
    int rear = 0;//队尾下标

    /**
     *  将一个对象追加到队列尾部
     */
    public boolean enqueue(Object obj) {
        if ((rear + 1) % arr.length == front) {
            return false;
        }
        arr[rear] = obj;
        rear = (rear + 1) % arr.length;
        return true;
    }

    //出队列
    public Object dequeue() {
        if (rear == front) {
            return null;
        }
        Object obj = arr[front];
        front = (front + 1) % arr.length;
        return obj;
    }

    public static void main(String[] args) {
        QueueArray q = new QueueArray();
        System.out.println(q.enqueue("北京"));
        System.out.println(q.enqueue("上海"));
        System.out.println(q.enqueue("广东"));
        System.out.println(q.enqueue("深圳"));
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            System.out.println(q.dequeue());
        }
    }
}

