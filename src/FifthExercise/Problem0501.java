package FifthExercise;

import java.util.Arrays;
import java.util.Scanner;

public class Problem0501 {
    /*
    格子里的整数
    Description
    Given a square grid of size n, each cell of which contains integer cost which represents a cost to traverse through that cell,
    we need to find a path from top left cell to bottom right cell by which total cost incurred is minimum.
    Note : It is assumed that negative cost cycles do not exist in input matrix.

    Input
    The first line of input will contain number of test cases T. Then T test cases follow .
    Each test case contains 2 lines. The first line of each test case contains an integer n denoting the size of the grid.
    Next line of each test contains a single line containing N*N space separated integers depecting cost of respective cell from (0,0) to (n,n).
    Constraints:1<=T<=50，1<= n<= 50

    Output
    For each test case output a single integer depecting the minimum cost to reach the destination.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[][] matrix = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    matrix[j][k] = scanner.nextInt();
                }
            }
            System.out.println(getMinCost(matrix));
        }
    }

    public static int getMinCost(int[][] matrix) {
        int n = matrix.length;
        int[][] minMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minMatrix[i], Integer.MAX_VALUE);
        }
        dfs(0, 0, minMatrix, matrix, matrix[0][0]);
        return minMatrix[n - 1][n - 1];
    }

    public static void dfs(int i, int j, int[][] minMatrix, int[][] matrix, int cur) {
        int n = matrix.length;
        int[][] dir = new int[][]{{i + 1, j}, {i, j + 1}, {i - 1, j}, {i, j - 1}};
        for (int[] d: dir) {
            int x = d[0], y = d[1];
            if (x >= 0 && x < n && y >= 0 && y < n && minMatrix[x][y] > matrix[x][y] + cur) {
                minMatrix[x][y] = matrix[x][y] + cur;
                dfs(x, y, minMatrix, matrix, minMatrix[x][y]);
            }
        }
    }
}
