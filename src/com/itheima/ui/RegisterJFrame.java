package com.itheima.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterJFrame extends JFrame implements MouseListener {
    //registerJFrame为注册界面
    //设置密码输入框
    JPasswordField password = new JPasswordField();
    //设置用户名输入框
    JTextField username = new JTextField();
    //设置二次密码输入框
    JPasswordField passwordSecond = new JPasswordField();


    // 存储账号的文件
    private static final String FILE_PATH = "user.txt";

    //注册按钮
    JButton registerButton = new JButton();

    //重置按钮
    JButton resetButton = new JButton();

    public RegisterJFrame() {
        //初始化界面
        initJFrame();

        //初始化注册界面
        initRegister();

        //让当前界面显示出来
        this.setVisible(true);
    }


    private void initRegister() {
        //清空原本已经出现的图片
        this.getContentPane().removeAll();
        //设置注册用户名名图片（JLabel对象）
        JLabel userNameText = new JLabel(new ImageIcon("image/register/注册用户名.png"));
        userNameText.setBounds(105, 140, 79, 17);
        this.getContentPane().add(userNameText);

        //设置注册用户名输入框

        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);
        //返回输入框中用户输入的数据
        //细节：如果用户没有输入，返回的是一个长度为0的字符串
        String usernameText = username.getText();

        //设置注册密码图片（JLabel对象）
        JLabel userPasswordText = new JLabel(new ImageIcon("image/register/注册密码.png"));
        userPasswordText.setBounds(120, 200, 64, 16);
        this.getContentPane().add(userPasswordText);

        //设置注册密码输入框

        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);
        //返回输入框中用户输入的数据
        //细节：如果用户没有输入，返回的是一个长度为0的字符串
        String passwordText = password.getText();

        //再次输入密码提示
        JLabel passWordSecondText = new JLabel(new ImageIcon("image/register/再次输入密码.png"));
        passWordSecondText.setBounds(90, 262, 96, 17);
        this.getContentPane().add(passWordSecondText);

        //再次输入密码的输入框

        passwordSecond.setBounds(195, 256, 200, 30);
        this.getContentPane().add(passwordSecond);
        //返回输入框中用户输入的数据
        //细节：如果用户没有输入，返回的是一个长度为0的字符串
        String passWordSecondTextInput = passwordSecond.getText();


        //添加注册按钮

        registerButton.setBounds(123, 310, 128, 47);
        registerButton.setIcon(new ImageIcon("image/register/注册按钮.png"));
        //去除按钮的默认边框
        registerButton.setBorderPainted(false);
        //去除按钮的默认背景
        registerButton.setContentAreaFilled(false);
        this.getContentPane().add(registerButton);
        registerButton.addMouseListener(this);

        //6.添加重置按钮

        resetButton.setBounds(256, 310, 128, 47);
        resetButton.setIcon(new ImageIcon("image/register/重置按钮.png"));
        //去除按钮的默认边框
        resetButton.setBorderPainted(false);
        //去除按钮的默认背景
        resetButton.setContentAreaFilled(false);
        this.getContentPane().add(resetButton);
        resetButton.addMouseListener(this);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(10, 10, 470, 390);
        this.getContentPane().add(background);


        //刷新界面
        this.getContentPane().repaint();
    }


    //初始化登陆界面（JFrame原始界面）
    private void initJFrame() {
        //清空原本已经出现的图片
        this.getContentPane().removeAll();
        //创建登陆界面的时候，同时给这个界面去设置一些信息
        this.setSize(500, 450);
        //设置界面的标题
        this.setTitle("拼图登陆界面");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //使用xy坐标去设置界面
        this.setLayout(null);
        //刷新界面
        this.getContentPane().repaint();


    }

    //注册功能，去文件比对信息
    private void registerUser() {
        String usernameTemp = username.getText().trim();
        String passwordTemp = new String(password.getPassword()).trim();

        // 判空
        if (usernameTemp.isEmpty() || passwordTemp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空！");
            return;
        }

        // 判断两次密码输入是否一致
        //如果不一致
        if (!passwordTemp.equals(new String(passwordSecond.getPassword()).trim())) {
            JOptionPane.showMessageDialog(this, "两次密码不一致！");
            // 清空输入框
            username.setText("");
            password.setText("");
            passwordSecond.setText("");
            return;
        }

        // 写入文件（追加模式，不覆盖旧数据）
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            // 格式：用户名-密码，每行一个账号
            bw.write(usernameTemp + "-" + passwordTemp);
            bw.newLine();
            JOptionPane.showMessageDialog(this, "注册成功！");
            // 清空输入框
            username.setText("");
            password.setText("");
            passwordSecond.setText("");
            LoginJFrame loginJFrame = new LoginJFrame();
            loginJFrame.setVisible(true);
            this.setVisible(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "注册失败：" + ex.getMessage());
        }
    }


    //鼠标点击事件
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("点击了");
        Object obj = e.getSource();
        if (obj == registerButton) {
            System.out.println("点击注册");
            registerUser();
        } else if (obj == resetButton) {
            System.out.println("点击重置");
            // 清空输入框
            username.setText("");
            password.setText("");
            passwordSecond.setText("");
        }

    }

    //鼠标按下不松事件
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下了");
        Object obj = e.getSource();
        if (obj == registerButton) {
            System.out.println("按下不松注册");
            registerButton.setIcon(new ImageIcon("image/register/注册按下.png"));
        } else if (obj == resetButton) {
            System.out.println("按下不松重置");
            resetButton.setIcon(new ImageIcon("image/register/重置按下.png"));

        }

    }


    //鼠标松开事件
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开了");
        Object obj = e.getSource();
        if (obj == registerButton) {
            System.out.println("松开注册");
            registerButton.setIcon(new ImageIcon("image/register/注册按钮.png"));
        } else if (obj == resetButton) {
            System.out.println("松开重置");
            resetButton.setIcon(new ImageIcon("image/register/重置按钮.png"));

        }

    }


    //鼠标进入事件
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("滑入");

    }

    //鼠标离开事件
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("滑出");

    }
}
