package ThirdExercise;

import java.util.Scanner;

public class Problem0310 {
    /*
    整除查询
    Description
    Given an array of positive integers and many queries for divisibility.
    In every query Q[i], we are given an integer K , we need to count all elements in the array which are perfectly divisible by K.
    Constraints:1<=T<=1001<=N,M<=1051<=A[i],Q[i]<=105

    Input
    The first line of input contains an integer T denoting the number of test cases.
    Then T test cases follow. Each test case consists of three lines.
    First line of each test case contains two integers N & M, second line contains N space separated array elements and third line contains M space separated queries.

    Output
    For each test case,In new line print the required count for each query Q[i].
     */

    public static int[] getRes(int[] a, int[] q) {
        int[] res = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j] % q[i] == 0) {
                    res[i]++;
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
            int[] a = new int[n];
            int[] q = new int[m];
            for (int j = 0; j < n; j++) {
                a[j] = scanner.nextInt();
            }
            for (int j = 0; j < m; j++) {
                q[j] = scanner.nextInt();
            }
            int[] res = getRes(a, q);
            for (int j = 0; j < res.length - 1; j++) {
                System.out.print(res[j] + " ");
            }
            System.out.println(res[res.length - 1]);
        }
    }
}
