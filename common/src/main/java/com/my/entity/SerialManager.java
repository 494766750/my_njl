package com.my.entity;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class SerialManager {
    private static SerialManager serialManager;
    private static PriorityBlockingQueue<SerialModel> priorityQueue;

    public static SerialManager getManager() {
        if (serialManager == null) {
            serialManager = new SerialManager();
        }
        if (priorityQueue == null) {
            priorityQueue = new PriorityBlockingQueue<>();
        }
        return serialManager;
    }

    public void addSerial(SerialModel model) {
        priorityQueue.add(model);
    }

    public Queue<SerialModel> getQueue() {
        return priorityQueue;
    }

}