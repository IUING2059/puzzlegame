package com.itheima.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrameSeconeWay extends JFrame implements ActionListener {
    //创建按钮1
    JButton jbt1 = new JButton("1点击");
    //创建按钮2
    JButton jbt2 = new JButton("2点击");

    public MyJFrameSeconeWay() {
        //设置界面宽高
        this.setSize(500, 500);
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


        //给按钮1设置宽高
        jbt1.setBounds(0, 0, 100, 50);
        //给按钮1添加点击事件
        jbt1.addActionListener(this);


        //给按钮2设置宽高
        jbt2.setBounds(100, 0, 100, 50);
        //给按钮2添加点击事件
        jbt2.addActionListener(this);
        // 把按钮添加到整个界面
        this.getContentPane().add(jbt1);
        this.getContentPane().add(jbt2);



        //让界面显示
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //对当前按钮进行判断

        //获取当前点击的按钮
        Object obj = e.getSource();
        if (obj == jbt1) {
            jbt1.setSize(150, 200);
        } else if (obj == jbt2) {
            Random random = new Random();
            int x = random.nextInt(550);
            jbt2.setLocation(x,x);

        }

    }
}
