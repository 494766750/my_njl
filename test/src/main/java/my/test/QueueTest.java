package my.test;



import my.entity.LineUpEntity;
import my.entity.SerialManager;
import my.entity.SerialModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 优先级队列
 */
public class QueueTest {
    public static void main(String[] args) {
        SerialModel model1 = new SerialModel(1);
        SerialModel model2 = new SerialModel(1);
        SerialModel model3 = new SerialModel(1);
        SerialModel model4 = new SerialModel(1);
        SerialModel model5 = new SerialModel(2);
        SerialModel model6 = new SerialModel(2);
        SerialModel model7 = new SerialModel(3);
        SerialModel model8 = new SerialModel(3);
        SerialModel model9 = new SerialModel(1);
        SerialModel model10 = new SerialModel(1);
        SerialModel model11 = new SerialModel(1);
        SerialManager.getManager().addSerial(model1);
        SerialManager.getManager().addSerial(model2);
        SerialManager.getManager().addSerial(model3);
        SerialManager.getManager().addSerial(model4);
        SerialManager.getManager().addSerial(model5);
        SerialManager.getManager().addSerial(model6);
        SerialManager.getManager().addSerial(model7);
        SerialManager.getManager().addSerial(model8);
        SerialManager.getManager().addSerial(model9);
        SerialManager.getManager().addSerial(model10);
        SerialManager.getManager().addSerial(model11);

        Queue<SerialModel> queue = SerialManager.getManager().getQueue();

        // timer启动器
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!queue.isEmpty()) {
                    SerialModel model = queue.poll();
                    System.out.println("获取当前model优先级:" + model.getParity());
                } else {
                    System.out.println("当前队列为空");
                }

            }
        };
        timer.schedule(timerTask,0,2000);

        // 图形界面
        JFrame f = new JFrame("Java窗口");
        f.setSize(300, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setLayout(null); // 设置窗体布局为空布局

        JButton button = null;
        button = new JButton("点击添加新优先级");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SerialModel serialModel = new SerialModel(5);
                SerialManager.getManager().addSerial(serialModel);
            }
        });
        JPanel p = new JPanel();
        p.setBackground(Color.BLUE);
        p.setSize(300, 200); // 设置面板对象大小
        p.add(button);
        f.getContentPane().add(p); // 将面板添加到窗体中
        
        /** ================================================================ */
//        LineUpEntity lineUpEntity1 = new LineUpEntity();
//        LineUpEntity lineUpEntity2 = new LineUpEntity();
//        LineUpEntity lineUpEntity3 = new LineUpEntity();
//        LineUpEntity lineUpEntity4 = new LineUpEntity();
//        LineUpEntity lineUpEntity5 = new LineUpEntity();
//        lineUpEntity1.setLineUpCode("A-1");
//        lineUpEntity2.setLineUpCode("V-123");
//        lineUpEntity3.setLineUpCode("V-124");
//        lineUpEntity4.setLineUpCode("A-2");
//        lineUpEntity5.setLineUpCode("A-0");
//        Queue<LineUpEntity> queue = new PriorityQueue<>();
//        queue.add(lineUpEntity1);
//        queue.add(lineUpEntity2);
//        queue.add(lineUpEntity3);
//        queue.add(lineUpEntity4);
//        queue.add(lineUpEntity5);
//        if(!queue.isEmpty()){
//            System.out.println("---->" + queue.poll().getLineUpCode());
//        }
//
//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                if(!queue.isEmpty()){
//                    LineUpEntity lineUpEntity = queue.poll();
//                    System.out.println("---->" + lineUpEntity.getLineUpCode());
//                    LineUpEntity le = new LineUpEntity();
//                    le.setLineUpCode(lineUpEntity.getLineUpCodeArr()[0]+"-"+(Integer.valueOf(lineUpEntity.getLineUpCodeArr()[1])+1));
//                    queue.add(le);
//                } else {
//                    System.out.println("当前队列为空");
//                }
//            }
//        };
//        timer.schedule(timerTask,0,2000);
    }
}