package com.itheima.test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrameKeylisener extends JFrame implements KeyListener {
    JButton jbt = new JButton("按钮");

    public MyJFrameKeylisener() {
        //设置界面宽高
        this.setSize(603, 680);
        //设置界面标题
        this.setTitle("拼图游戏 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消默认的居中放置，只有取消才能按照XY轴的形式添加组件
        this.setLayout(null);

        //给按钮设置宽高
        jbt.setBounds(0, 0, 100, 50);

        //给按钮添加鼠标事件
        jbt.addKeyListener(this);

        //把按钮添加到界面中
        this.getContentPane().add(jbt);

        //把界面设置可见
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开");
        int code = e.getKeyCode();
        System.out.println(code);
    }
}
