package com.itheima.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
    //loginJFrame表示登陆界面

    public LoginJFrame() {
        //创建登陆界面的时候，同时给这个界面去设置一些信息
        this.setSize(488,430);
        //设置界面的标题
        this.setTitle("拼图登陆界面");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
