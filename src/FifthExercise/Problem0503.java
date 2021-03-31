package FifthExercise;

import java.util.Arrays;
import java.util.Scanner;

public class Problem0503 {
    /*
    硬币最小数量
    Description
    Given the list of coins of distinct denominations and total amount of money.
    Output the minimum number of coins required to make up that amount.
    Output -1 if that money cannot be made up using given coins.
    You may assume that there are infinite numbers of coins of each type.

    Input
    The first line contains 'T' denoting the number of test cases. Then follows description of test cases.
    Each cases begins with the two space separated integers 'n' and 'amount' denoting the total number of distinct coins and total amount of money respectively.
    The second line contains N space-separated integers A1, A2, ..., AN denoting the values of coins.
    Constraints:1<=T<=30，1<=n<=100，1<=Ai<=1000，1<=amount<=100000

    Output
    Print the minimum number of coins required to make up that amount or return -1 if it is impossible to make that amount using given coins.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int amount = scanner.nextInt();
            int[] coins = new int[n];
            for (int j = 0; j < n; j++) {
                coins[j] = scanner.nextInt();
            }
            System.out.println(getMinCoinCnt(coins, amount));
        }
    }

    //这不是无限量的背包问题吗
    //dp[i]表示拿i钱所需的最小钞票数
    public static int getMinCoinCnt(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) { //显然如果这个钞票的面额超过所需钱数，就无法拿来凑
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] >= amount + 1 ? -1 : dp[amount];
    }
}
