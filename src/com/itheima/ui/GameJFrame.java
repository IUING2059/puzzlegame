package com.itheima.ui;

import javax.crypto.interfaces.PBEKey;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener {

    //创建二维数组，保存图片的位置
    int[][] data = new int[4][4];
    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //记录图片路径
    String pathAll = "image/girl/girl3/";

    //定义一个二维数组，存储正确的数据
    int[][] win = {{1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}};

    //GameJFrame为游戏的主界面
    public GameJFrame() {
        //初始化游戏界面
        initJFrame();


        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱数据）
        initDate();

        //初始化图片
        initImage();


        //让界面显示出来，写在最后
        this.setVisible(true);
    }


    //初始化数据（打乱数据）
    private void initDate() {
        //打乱数据
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random random = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = random.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }


        //创建二维数组，存入打乱后的数据
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            } else {
                data[i / 4][i % 4] = tempArr[i];
            }

        }

    }

    //初始化图片
    private void initImage() {
        //清空原本已经出现的图片
        this.getContentPane().removeAll();

        if (victory()){
            //显示胜利界面
            JLabel  winJLabel=new JLabel((new ImageIcon("image/win.png")));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                //获取加载图片的序号
                int num = data[i][j];
                String path = pathAll + num + ".jpg";
                ImageIcon icon = new ImageIcon(path);
                //创建一个JLable对象（管理容器）
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //给图片加边框
                //0：表示让图片凸起来
                //1：表示让图片凹进去
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));

                //把管理容器添加到界面当中
                this.getContentPane().add(jLabel);
            }
        }
        //创建背景图像对象
        ImageIcon bg = new ImageIcon("image/background.png");
        //添加背景图片
        JLabel background = new JLabel(bg);
        background.setBounds(40, 40, 508, 560);
        //把背景图片添加到界面中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

    }


    private void initJMenuBar() {
        //初始化菜单
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象（功能，关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //创建选项下面的条目对象
        JMenuItem replayJMenuItem = new JMenuItem("重新游戏");
        JMenuItem reloginJMenuItem = new JMenuItem("重新登陆");
        JMenuItem closeJMenuItem = new JMenuItem("关闭游戏");

        JMenuItem accountJMenuItem = new JMenuItem("公众号");

        //将每一个选项下面的条目添加到对应选项下面
        functionJMenu.add(replayJMenuItem);
        functionJMenu.add(reloginJMenuItem);
        functionJMenu.add(closeJMenuItem);

        aboutJMenu.add(accountJMenuItem);

        //将菜单里面的两个选项添加到菜单栏中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消默认的居中放置，只有取消才能按照XY轴的形式添加组件
        this.setLayout(null);
        //添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        //判断游戏是否胜利，此方法结束，不能执行查看完整图片
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 65) {
            //把界面中的所有图片删除
            this.getContentPane().removeAll();
            //加载完整的图片
            JLabel all = new JLabel(new ImageIcon(pathAll + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);

            //把背景图片添加到界面中
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，此方法结束，不能执行移动
        if (victory()) {
            return;
        }


        //对上下左右进行判断
        //左：37 上：38  右：39 下：40
        int code = e.getKeyCode();
        //向左
        if (code == 37) {
            System.out.println("向左");

            //边界判断
            if (y == 3) {
                return;
            }

            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            //刷新图片
            initImage();

        }
        //向上
        else if (code == 38) {
            System.out.println("向上");

            //边界判断
            if (x == 3) {
                return;
            }


            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            //刷新图片
            initImage();

        }
        //向右
        else if (code == 39) {
            System.out.println("向右");

            //边界判断
            if (y == 0) {
                return;
            }

            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            //刷新图片
            initImage();

        }
        //向下
        else if (code == 40) {
            System.out.println("向下");

            //边界判断
            if (x == 0) {
                return;
            }

            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            //刷新图片
            initImage();

        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            //按下W按键，一健通关
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }

    }

    //判断data数组中的数据是否跟win数组一致
    //如果全部相同，返回true，否则返回false
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]){
                    //数组不一致
                    return false;
                }
            }
        }
        //数组一致
        return true;
    }
}
