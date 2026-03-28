package com.itheima.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJFrameTest {
    public static void main(String[] args){
        //1. 创建窗口和按钮
        JFrame jFrame = new JFrame();
        JButton jb1 = new JButton("点击");
        JButton jb2 = new JButton("第二次点击");

        //2. 窗口基础设置
        jFrame.setTitle("拼图游戏 v1.0");
        jFrame.setSize(603,680);  // 单独设置大小
        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //3. ✅ 开启绝对布局（必须写在最前面，才能自由调整按钮位置）
        jFrame.setLayout(null);

        //4. ✅ 设置按钮位置和大小（想改哪里改哪里！）
        jb1.setBounds(0,0,100,50);
        // 测试：改这个数字，按钮立刻动！比如(200,200,100,50)
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1点击了");
            }
        });

        //5. 添加按钮到窗口
        jFrame.getContentPane().add(jb1);

        //6. 最后设置窗口可见 + 居中
        jFrame.setLocationRelativeTo(null); // 屏幕居中
        jFrame.setVisible(true);
    }
}