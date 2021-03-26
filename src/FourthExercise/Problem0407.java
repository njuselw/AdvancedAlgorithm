package FourthExercise;

import java.util.Scanner;

public class Problem0407 {
    /*
    数组查询
    Description
    Given an array, the task is to complete the function which finds the maximum sum subarray,
    where you may remove at most one element to get the maximum sum.

    Input
    第一行为测试用例个数T；后面每两行表示一个用例，第一行为用例中数组长度N，第二行为数组具体内容。

    Output
    每一行表示对应用例的结果。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }
            System.out.println(getMaxSum(arr));
        }
    }

    //刚开始理解错题意了
    //题目的意思是，找最大和的连续子数组，计算最大和的时候允许从数组中至多删除一个元素
    public static int getMaxSum(int[] arr) {
        int n = arr.length;
        int max = arr[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
        }
        //如果全部非正数，则返回最大值
        if (max <= 0) {
            return max;
        }
        //记录当前位置左侧的最大子数组和
        int[] left = new int[n];
        //记录当前位置右侧的最大子数组和
        int[] right = new int[n];
        for (int i = 0; i < n - 1; i++) {
            left[i + 1] = Math.max(left[i] + arr[i], 0);
        }
        for (int i = n - 1; i > 0; i--) {
            right[i - 1] = Math.max(right[i] + arr[i], 0);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, Math.max(left[i] + right[i], left[i] + arr[i] + right[i]));
        }
        return res;
    }
}
