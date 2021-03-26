package FourthExercise;

import java.util.Scanner;

public class Problem0408 {
    /*
    订单问题
    Description
    Rahul and Ankit are the only two waiters in Royal Restaurant. Today, the restaurant received N orders.
    The amount of tips may differ when handled by different waiters,
    if Rahul takes the ith order, he would be tipped Ai rupees and if Ankit takes this order, t
    he tip would be Bi rupees.In order to maximize the total tip value they decided to distribute the order among themselves.
    One order will be handled by one person only.
    Also, due to time constraints Rahul cannot take more than X orders and Ankit cannot take more than Y orders.
    It is guaranteed that X + Y is greater than or equal to N, which means that all the orders can be handled by either Rahul or Ankit.
    Find out the maximum possible amount of total tip money after processing all the orders.

    Input
    • The first line contains one integer, number of test cases.
    • The second line contains three integers N, X, Y.
    • The third line contains N integers. The ith integer represents Ai.
    • The fourth line contains N integers. The ith integer represents Bi.

    Output
    Print a single integer representing the maximum tip money they would receive.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int[] aCost = new int[n];
            for (int j = 0; j < n; j++) {
                aCost[j] = scanner.nextInt();
            }
            int[] bCost = new int[n];
            for (int j = 0; j < n; j++) {
                bCost[j] = scanner.nextInt();
            }
            System.out.println(getMaxTipMoney(n, x, y, aCost, bCost));
        }
    }


    //dp[i][j][k]指的是i个任务，A做了j个，B做了k个的最大值情况
    public static int getMaxTipMoney(int n, int x, int y, int[] aCost, int[] bCost) {
        int[][][] dp = new int[n + 1][x + 1][y + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                dp[i][j][0] = dp[i - 1][j - 1][0] + aCost[i - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= y; j++) {
                dp[i][0][j] = dp[i - 1][0][j - 1] + bCost[j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                for (int k = 1; k <= y; k++) {
                    dp[i][j][k] = Math.max(dp[i - 1][j - 1][k] + aCost[i - 1], dp[i - 1][j][k - 1] + bCost[i - 1]);
                }
            }
        }
        return dp[n][x][y];
    }
}
