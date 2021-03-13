package ThirdExercise;

import java.util.Scanner;

public class Problem0303 {
    /*
    子矩阵问题
    Description
    给定一个矩形区域，每一个位置上都是1或0，求该矩阵中每一个位置上都是1的最大子矩形区域中的1的个数。

    Input
    输入第一行为测试用例个数。每一个用例有若干行，第一行为矩阵行数n和列数m，下面的n行每一行是用空格隔开的0或1。

    Output
    输出一个数值。
     */

    public static int getMaxSubArea(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        //横向记录连续1的个数
        int[][] cnt = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    cnt[i][j] = matrix[i][j];
                    continue;
                }
                if (matrix[i][j] == 1) {
                    cnt[i][j] = cnt[i][j - 1] + 1;
                }
            }
        }
        int res = 0;
        //纵向遍历，若cnt[i][j] ~ cnt[i][k]均>0，则构成举行大小为 (k - j + 1) * min(cnt[i][j]...cnt[i][k])
        for (int i = 0; i < m; i++) {
            int min = Integer.MAX_VALUE, notZero = 0;
            for (int j = 0; j < n; j++) {
                if (cnt[j][i] == 0) {
                    min = Integer.MAX_VALUE;
                    notZero = 0;
                } else {
                    min = Math.min(min, cnt[j][i]);
                    notZero++;
                    res = Math.max(res, min * notZero);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] matrix = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    matrix[j][k] = scanner.nextInt();
                }
            }
            System.out.println(getMaxSubArea(matrix));
        }
    }
}
