package com.itheima.ui;

import javax.crypto.interfaces.PBEKey;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;
import java.awt.event.ActionListener;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //创建二维数组，保存图片的位置
    int[][] data = new int[4][4];
    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //表示第几个 图片
    int numPicture=1;
    //表示哪个部分大类
    String pathType = "girl";



    //定义一个二维数组，存储正确的数据
    int[][] win = {{1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}};

    //定义变量，统计步数
    int step = 0;

    //创建选项下面的条目对象
    JMenuItem replayJMenuItem = new JMenuItem("重新游戏");
    JMenuItem reloginJMenuItem = new JMenuItem("重新登陆");
    JMenuItem closeJMenuItem = new JMenuItem("关闭游戏");

    JMenuItem accountJMenuItem = new JMenuItem("公众号");

    //添加更换图片下面的内容
    JMenuItem girlJMenuItem = new JMenuItem("美女");
    JMenuItem animalJMenuItem = new JMenuItem("动物");
    JMenuItem sportsJMenuItem = new JMenuItem("运动");

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


    //胜利音效
    // 胜利音效播放方法
    private void playVictorySound() {
        try {
            // 1. 获取音效文件路径（和win.png同目录）
            File soundFile = new File("win.wav");
            // 2. 获取音频输入流
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            // 3. 获取音频剪辑对象
            Clip clip = AudioSystem.getClip();
            // 4. 打开音频流
            clip.open(audioStream);
            // 5. 开始播放
            clip.start();
        } catch (Exception e) {
            // 音效加载失败不影响游戏，打印错误即可
            System.out.println("胜利音效播放失败：" + e.getMessage());
        }
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
            }
            data[i / 4][i % 4] = tempArr[i];


        }

    }

    //初始化图片
    private void initImage() {
        //清空原本已经出现的图片
        this.getContentPane().removeAll();

        //记录图片路径
        String pathAll = "image/"+pathType + "/"+pathType + numPicture + "/";

        if (victory()) {
            //显示胜利界面
            JLabel winJLabel = new JLabel((new ImageIcon("image/win.png")));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

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

        //创建菜单上面的个选项的对象（功能，关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //创建更换图片的选项
        JMenu changeImageJMenu = new JMenu("更换图片");
        changeImageJMenu.add(girlJMenuItem);
        changeImageJMenu.add(sportsJMenuItem);
        changeImageJMenu.add(animalJMenuItem);


        //更换图片部分（新）
        functionJMenu.add(changeImageJMenu);
        //将每一个选项下面的条目添加到对应选项下面
        functionJMenu.add(replayJMenuItem);
        functionJMenu.add(reloginJMenuItem);
        functionJMenu.add(closeJMenuItem);

        aboutJMenu.add(accountJMenuItem);

        //给条目绑定事件
        replayJMenuItem.addActionListener(this);
        reloginJMenuItem.addActionListener(this);
        closeJMenuItem.addActionListener(this);
        accountJMenuItem.addActionListener(this);

        //新增更换图片功能(新)
        girlJMenuItem.addActionListener(this);
        animalJMenuItem.addActionListener(this);
        sportsJMenuItem.addActionListener(this);


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

    //胜利之后，再次点击更换图片，会重新刷新图片


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        //记录图片路径
        String pathAll = "image/"+pathType + "/"+pathType + numPicture + "/";
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
            //每移动一次，计数器加一
            step++;
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
            //每移动一次，计数器加一
            step++;
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
            //每移动一次，计数器加一
            step++;
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
            //每移动一次，计数器加一
            step++;
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
                if (data[i][j] != win[i][j]) {
                    //数组不一致
                    return false;
                }
            }
        }
        playVictorySound();
        //数组一致
        return true;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //判断
        if (obj == replayJMenuItem) {
            System.out.println("重新开始");

            //再次打乱数组
            initDate();
            //计数器清零
            step = 0;
            //重新加载图片
            initImage();


        } else if (obj == reloginJMenuItem) {
            System.out.println("重新登录");
            //返回登陆界面
            //关闭当前界面
            this.setVisible(false);
            //返回登陆界面
            new LoginJFrame();


        } else if (obj == closeJMenuItem) {
            System.out.println("关闭游戏");
            //退出程序
            System.exit(0);
        } else if (obj == accountJMenuItem) {
            System.out.println("关于我们");

            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("image/abount.png"));
            //设置图片的坐标和宽高
            jLabel.setBounds(0, 0, 172, 258);
            //把图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            //给弹框设置宽高
            jDialog.setSize(500, 500);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭，主程序继续执行
            jDialog.setModal(true);
            //弹框显示出来
            jDialog.setVisible(true);
        } else if (obj == girlJMenuItem) {
            System.out.println("美女");
            Random random1=new Random();
            int numTemp1=random1.nextInt(12);
            numPicture=numTemp1+1;
            pathType="girl";
            initImage();

        } else if (obj == animalJMenuItem) {
            System.out.println("动物");
            Random random2=new Random();
            int numTemp1=random2.nextInt(7);
            numPicture=numTemp1+1;
            pathType="animal";
            initImage();

        } else if (obj == sportsJMenuItem) {
            System.out.println("运动");
            Random random3=new Random();
            int numTemp1=random3.nextInt(9);
            numPicture=numTemp1+1;
            pathType="sport";
            initImage();

        }
    }
}
