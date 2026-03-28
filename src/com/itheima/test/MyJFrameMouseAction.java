package com.itheima.test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MyJFrameMouseAction extends JFrame implements MouseListener {
    JButton jbt = new JButton("按钮");

    public MyJFrameMouseAction() {
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
        jbt.addMouseListener(this);

        //把按钮添加到界面中
        this.getContentPane().add(jbt);

        //把界面设置可见
        this.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下不松");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("滑入");
        Random random = new Random();
        int x = random.nextInt(500);
        jbt.setLocation(x, x);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("滑出");
    }
}
