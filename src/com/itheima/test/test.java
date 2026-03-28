package com.itheima.test;

import java.util.Random;

public class test {
    static void main() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random random = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = random.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        for (int i = 0; i < tempArr.length; i++) {
            System.out.print(tempArr[i] + " ");
        }
        System.out.println();

        //创建二维数组，存入数据
        int[][] date = new int[4][4];
        for (int i = 0; i < tempArr.length; i++) {
            date[i / 4][i % 4] = tempArr[i];
        }
        for (int i = 0; i < date.length; i++){
            for (int j = 0; j < date[i].length; j++){
                System.out.print(date[i][j] + " ");
            }
            System.out.println();
        }
    }
}
