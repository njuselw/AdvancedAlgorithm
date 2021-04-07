package FifthExercise;

import java.util.Scanner;

public class Problem0507 {
    /*
    调整数组使差最小
    Description
    有两个序列 a,b，大小都为 n,序列元素的值任意整数，无序； 要求：通过交换 a,b 中的元素，使[序列 a 元素的和]与[序列 b 元素的和]之间的差最小。

    Input
    输入第一行为用例个数， 每个测试用例输入为两行，分别为两个数组，每个值用空格隔开。

    Output
    输出变化之后的两个数组内元素和的差绝对值。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String[] s1 = scanner.nextLine().split(" ");
            String[] s2 = scanner.nextLine().split(" ");
            int[] num1 = new int[s1.length];
            int[] num2 = new int[s2.length];
            //题目说明了长度一样
            for (int j = 0; j < s1.length; j++) {
                num1[j] = Integer.parseInt(s1[j]);
                num2[j] = Integer.parseInt(s2[j]);
            }
            System.out.println(getRes(num1, num2));
        }
    }

    //动态规划，参考博客：https://blog.csdn.net/qq_33935895/article/details/103029767
    //将num1和num2数组合并，将问题转化为从2n的数组中，取出n个数，是的这些数之和不超过sum/2+2且最大
    public static int getRes(int[] num1, int[] num2) {
        int n = num1.length;
        int[] arr = new int[2 * n];
        int idx = 0, min = num1[0], sum = 0;
        for (int i = 0; i < n; i++) {
            arr[idx++] = num1[i];
            min = Math.min(min, num1[i]);
        }
        for (int i = 0; i < n; i++) {
            arr[idx++] = num2[i];
            min = Math.min(min, num2[i]);
        }
        //只要求两数组之和差最小，所以只要保持两数组和相对大小不变即可，此步目的是将所有数值调整为非负值
        for (int i = 0; i < 2 * n; i++) {
            arr[i] -= min;
            sum += arr[i];
        }
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int i = 0; i <= 2 * n; i++) {
            for (int j = Math.min(i, n); j >= 1; j--) {
                for (int k = sum / 2; k >= arr[i - 1]; k--) {
                    dp[j][k] = Math.max(dp[j - 1][k - arr[i - 1]] + arr[i - 1], dp[j][k]);
                }
            }
        }
        return sum - 2 * dp[n][sum / 2];
    }
}
