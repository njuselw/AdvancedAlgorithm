package FourthExercise;

import java.util.Scanner;

public class Problem0401 {
    /*
    最小化初始点
    Description
    Given a grid with each cell consisting of positive, negative or no points i.e, zero points.
    We can move across a cell only if we have positive points ( > 0 ).
    Whenever we pass through a cell, points in that cell are added to our overall points.
    We need to find minimum initial points to reach cell (m-1, n-1) from (0, 0) by following these certain set of rules :
    1.From a cell (i, j) we can move to (i+1, j) or (i, j+1).
    2.We cannot move from (i, j) if your overall points at (i, j) is <= 0.
    3.We have to reach at (n-1, m-1) with minimum positive points i.e., > 0.

    Input
    The first line contains an integer 'T' denoting the total number of test cases.
    In each test cases, the first line contains two integer 'R' and 'C' denoting the number of rows and column of array.
    The second line contains the value of the array i.e the grid, in a single line separated by spaces in row major order.
    Constraints:1 ≤ T ≤ 30; 1 ≤ R,C ≤ 10; -30 ≤ A[R][C] ≤ 30
    Input: points[m][n] = { {-2, -3, 3},{-5, -10, 1},{10, 30, -5}};

    Output
    Print the minimum initial points to reach the bottom right most cell in a separate line.
    7
    Explanation:
    7 is the minimum value to reach destination with positive throughout the path. Below is the path.
    (0,0) -> (0,1) -> (0,2) -> (1, 2) -> (2, 2)
    We start from (0, 0) with 7, we reach(0, 1) with 5, (0, 2) with 2, (1, 2) with 5, (2, 2)with
    and finally we have 1 point (we needed greater than 0 points at the end).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] grid = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    grid[j][k] = scanner.nextInt();
                }
            }
            System.out.println(getMinPoint(grid));
        }
    }

    //dp[i][j]表示到(i, j)位置的最小值应为多少，由题意值该值至少为1
    //min = min(dp[i + 1][j], dp[i][j + 1])
    //dp[i][j] = max(min - grid[i][j], 1)
    public static int getMinPoint(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1] > 0 ? 1 : (Math.abs(grid[m - 1][n - 1]) + 1);
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - grid[m - 1][i], 1);
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - grid[i][n - 1], 1);
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int point = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(point - grid[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
