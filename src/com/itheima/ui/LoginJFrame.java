package com.itheima.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class LoginJFrame extends JFrame implements ActionListener, MouseListener {
    //loginJFrame表示登陆界面


    //验证码
    String codeStr = CodeUtil.getCode();
    //创建按钮
    JButton rightCode = new JButton(codeStr);

    //注册按钮
    JButton register = new JButton();

    //登录按钮
    JButton login = new JButton();
    // 存储账号的文件
    private static final String FILE_PATH = "user.txt";

    //验证码的输入框
    JTextField code = new JTextField();

    //设置密码输入框
    JPasswordField password = new JPasswordField();
    //设置用户名输入框
    JTextField username = new JTextField();

    public LoginJFrame() {
        //初始化登陆界面（JFrame原始界面）
        initJFrame();

        //用户登陆界面
        initLogin();

        //让当前界面显示出来
        this.setVisible(true);


    }

    private void initLogin() {
        //清空原本已经出现的图片
        this.getContentPane().removeAll();
        //设置用户名图片（JLabel对象）
        JLabel userNameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        userNameText.setBounds(116, 135, 51, 19);
        this.getContentPane().add(userNameText);

        //设置用户名输入框

        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);
        //返回输入框中用户输入的数据
        //细节：如果用户没有输入，返回的是一个长度为0的字符串
        String usernameText =username.getText();

        //设置密码图片（JLabel对象）
        JLabel userPasswordText = new JLabel(new ImageIcon("image/login/密码.png"));
        userPasswordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(userPasswordText);

        //设置密码输入框

        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);
        //返回输入框中用户输入的数据
        //细节：如果用户没有输入，返回的是一个长度为0的字符串
        String passwordText =password.getText();

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框

        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);
        //返回输入框中用户输入的数据
        //细节：如果用户没有输入，返回的是一个长度为0的字符串
        String codeTextInput =code.getText();


        //控制台输出验证码
        System.out.println(codeStr);


        //设置内容
        rightCode.setText(codeStr);
        // 👇 关键：让按钮看起来和文字一模一样（无边框、无背景）
        rightCode.setBorderPainted(false);   // 去掉边框
        rightCode.setContentAreaFilled(false); // 去掉背景
        rightCode.setFocusPainted(false);    // 去掉焦点框
        //设置字体和颜色，保证文字可见
        // 这里特意设置字体大小和样式，防止被挤压
        rightCode.setFont(new java.awt.Font("微软雅黑", java.awt.Font.PLAIN, 14));
        rightCode.setForeground(java.awt.Color.BLACK); // 确保文字可见
        // 设置位置
        rightCode.setBounds(310, 256, 80, 30);

        rightCode.addActionListener(this);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);
        login.addMouseListener(this);

        //6.添加注册按钮

        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);
        register.addMouseListener(this);

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
    //要展示用户名或密码错误
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == rightCode) {
            System.out.println("点击验证码");
            //点击验证码，刷新验证码
            String newCode = CodeUtil.getCode();
            rightCode.setText(newCode);
            System.out.println(newCode);
        }


    }


    //鼠标点击，执行相应登陆注册功能
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("鼠标点击");
        Object obj = e.getSource();
        if(obj==login){
            System.out.println("点击登录");
            loginUser();
        } else if (obj==register) {
            System.out.println("点击注册");
            RegisterJFrame registerJFrame = new RegisterJFrame();
            // 2. 【必须加】让窗口渲染显示到屏幕上！
            registerJFrame.setVisible(true);

            //想隐藏登录窗口（注册时登录窗口暂时消失）
            this.setVisible(false);
        }

    }



    //登陆功能，去文件比对信息
    private void loginUser() {
        String inputUser = username.getText().trim();
        String inputPwd = new String(password.getPassword()).trim();

        if (inputUser.isEmpty() || inputPwd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入完整信息！");
            return;
        }

        // 读取文件比对
        try (FileReader fr = new FileReader(FILE_PATH);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            boolean isSuccess = false;

            // 逐行读取
            while ((line = br.readLine()) != null) {
                // 拆分用户名和密码（用 - 分隔）
                String[] data = line.split("-");
                if (data.length == 2) {
                    String user = data[0];
                    String pwd = data[1];
                    // 比对成功
                    if (user.equals(inputUser) && pwd.equals(inputPwd)) {
                        isSuccess = true;
                        break;
                    }
                }
            }

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "登录成功！");
                // 登录成功后清空输入框（可选）
                username.setText("");
                password.setText("");
                code.setText("");
                GameJFrame gameJFrame = new GameJFrame();
                gameJFrame.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "暂无用户，请先注册！");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "登录失败：" + e.getMessage());
        }
    }

    //鼠标按下，按下不松，更换按下不松图片
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("鼠标按下不松");
        Object obj = e.getSource();
        if(obj==login){
            System.out.println("点击登录");
            login.setIcon(new ImageIcon("image/login/登录按下.png"));

        } else if(obj==register) {
            System.out.println("点击注册");
            register.setIcon(new ImageIcon("image/login/注册按下.png"));
        }

    }

    //鼠标释放
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("鼠标松开");
        Object obj = e.getSource();
        if(obj==login){
            System.out.println("释放登录");
            login.setIcon(new ImageIcon("image/login/登录按钮.png"));

        }
        else if (obj==register){
            System.out.println("释放注册");
            register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        }

    }

    //鼠标滑入
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("鼠标滑入");

    }

    //鼠标滑出
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("鼠标滑出");

    }
}
