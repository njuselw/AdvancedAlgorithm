package FourthExercise;

import java.util.Scanner;

public class Problem0405 {
    /*
    如何花最少的钱购买蔬菜
    Description
    Rahul wanted to purchase vegetables mainly brinjal, carrot and tomato. There are N different vegetable sellers in a line.
    Each vegetable seller sells all three vegetable items, but at different prices.
    Rahul, obsessed by his nature to spend optimally, decided not to purchase same vegetable from adjacent shops.
    Also, Rahul will purchase exactly one type of vegetable item (only 1 kg) from one shop.
    Rahul wishes to spend minimum money buying vegetables using this strategy. Help Rahul determine the minimum money he will spend.

    Input
    First line indicates number of test cases T.
    Each test case in its first line contains N denoting the number of vegetable sellers in Vegetable Market.
    Then each of next N lines contains three space separated integers denoting cost of brinjal, carrot and tomato per kg with that particular vegetable seller.

    Output
    For each test case, output the minimum cost of shopping taking the mentioned conditions into account in a separate line.
    Constraints:1 <= T <= 10，1 <= N <= 100000 Cost of each vegetable(brinjal/carrot/tomato) per kg does not exceed 10^4
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[][] costs = new int[n][3];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    costs[j][k] = scanner.nextInt();
                }
            }
            System.out.println(getMinCost(costs));
        }
    }

    //dp[i][j]表示到第i家店买到j产品的最小成本
    //dp[i][j] = min(dp[i-1][0...2])+costs[i][j]
    //                         |--这里不取j值
    public static int getMinCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n][3];
        for (int i = 0; i < 3; i++) {
            dp[0][i] = costs[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + costs[i][j];
            }
        }
        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }
}
