package FirstExercise;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem0123 {
    /*
    棋盘覆盖问题
    Description
    棋盘覆盖问题：给定一个大小为2^n*2^n个小方格的棋盘，其中有一个位置已经被填充，现在要用一个L型（2*2个小方格组成的大方格中去掉其中一个小方格）形状去覆盖剩下的小方格。
    求出覆盖方案，即哪些坐标下的小方格使用同一个L型格子覆盖。
    注意：坐标从0开始。左上方的第一个格子坐标为(0,0)，第一行第二个坐标为(0,1)，第二行第一个为(1,0)，以此类推。

    Input
    输入第一行为测试用例个数，后面每一个用例有两行，第一行为n值和特殊的格子的坐标（用空格隔开），第二行为需要查找其属于同一个L型格子的格子坐标。

    Output
    输出每一行为一个用例的解，先按照行值从小到大、再按照列值从小到大的顺序输出每一个用例的两个坐标；用逗号隔开。
     */

    static int cnt = 0; //记录L块的编号
    /**
     * 分治的方式进行棋盘覆盖，每次将棋盘划分为四个大小相等的正方形块
     * @param board 棋盘
     * @param leftUpX 当前块左上角在整个棋盘的x坐标
     * @param leftUpY 当前块左上角在整个棋盘的y坐标
     * @param specialX 特殊格子的x坐标
     * @param specialY 特殊各自的y坐标
     * @param size 分治后当前处理的棋盘块的大小
     */
    public static void setLBlock(int[][] board, int leftUpX, int leftUpY, int specialX, int specialY, int size) {
        if (size == 1) return;
        int s = size / 2; //将棋盘划分为左上，右上，左下，右下四个部分
        int curNum = ++cnt;

        //左上
        if (specialX < leftUpX + s && specialY < leftUpY + s) {
            //如果特殊格子在左上，则对左上继续划分，其他部分同理
            setLBlock(board, leftUpX, leftUpY, specialX, specialY, s);
        } else {
            //如果特殊格子不在左上，则将左上的右下角格子设置为特殊格子，即设定为一个L块的一部分，再进行划分
            board[leftUpX + s - 1][leftUpY + s - 1] = curNum;
            setLBlock(board, leftUpX, leftUpY, leftUpX + s - 1, leftUpY + s - 1, s);
        }

        //右上
        if (specialX < leftUpX + s && specialY >= leftUpY + s) {
            setLBlock(board, leftUpX, leftUpY + s, specialX, specialY, s);
        } else {
            //如果特殊格子不在右上，则将右上的左下角格子设置为特殊格子
            board[leftUpX + s - 1][leftUpY + s] = curNum;
            setLBlock(board, leftUpX, leftUpY + s, leftUpX + s - 1, leftUpY + s, s);
        }

        //左下
        if (specialX >= leftUpX + s && specialY < leftUpY + s) {
            setLBlock(board, leftUpX + s, leftUpY, specialX, specialY, s);
        } else {
            //如果特殊格子不在左下，则将左下的右上角格子设置为特殊格子
            board[leftUpX + s][leftUpY + s - 1] = curNum;
            setLBlock(board, leftUpX + s, leftUpY, leftUpX + s, leftUpY + s - 1, s);
        }

        //右下
        if (specialX >= leftUpX + s && specialY >= leftUpY + s) {
            setLBlock(board, leftUpX + s, leftUpY + s, specialX, specialY, s);
        } else {
            //如果特殊格子不在右下，则将右下的左上角格子设置为特殊格子
            board[leftUpX + s][leftUpY + s] = curNum;
            setLBlock(board, leftUpX + s, leftUpY + s, leftUpX + s, leftUpY + s, s);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            cnt = 0;
            int n = scanner.nextInt();
            int specialX = scanner.nextInt();
            int specialY = scanner.nextInt();
            int targetX = scanner.nextInt();
            int targetY = scanner.nextInt();
            int size = (int) Math.pow(2, n);
            int[][] board = new int[size][size];
            setLBlock(board, 0, 0, specialX, specialY, size);
            int targetCnt = board[targetX][targetY];
            //根据目标格子中的L块编号寻找与其处于同一个L块的其他格子
            //又L块为2*2缺一块的形状，所以只需遍历目标格子周围一圈的格子即可
            ArrayList<int[]> res = new ArrayList<>();
            for (int j = Math.max(0, targetX - 1); j <= Math.min(size - 1, targetX + 1); j++) {
                for (int k = Math.max(0, targetY - 1); k <= Math.min(size - 1, targetY + 1); k++) {
                    if (j == targetX && k == targetY) continue;//跳过目标格子
                    if (board[j][k] == targetCnt) {
                        res.add(new int[]{j, k});
                    }
                }
            }
            System.out.println(res.get(0)[0] + " " + res.get(0)[1] + "," + res.get(1)[0] + " " + res.get(1)[1]);
        }
    }

}
