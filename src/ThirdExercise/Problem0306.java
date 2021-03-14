package ThirdExercise;

import java.util.Scanner;

public class Problem0306 {
    /*
    牛的繁殖问题
    Description
    Cows in the FooLand city are interesting animals. One of their specialties is related to producing offsprings.
    A cow in FooLand produces its first calve (female calf) at the age of two years and proceeds to produce other calves (one female calf a year).
    Now the farmer Harold wants to know how many animals would he have at the end of N years,
    if we assume that none of the calves die, given that initially, he has only one female calf?
    explanation:At the end of 1 year, he will have only 1 cow, at the end of 2 years he will have 2 animals
    (one parent cow C1 and other baby calf B1 which is the offspring of cow C1).
    At the end of 3 years, he will have 3 animals (one parent cow C1 and 2 female calves B1 and B2, C1 is the parent of B1 and B2).
    At the end of 4 years, he will have 5 animals (one parent cow C1, 3 offsprings of C1 i.e. B1, B2, B3 and one offspring of B1).

    Input
    The first line contains a single integer T denoting the number of test cases.
    Each line of the test case contains a single integer N as described in the problem.

    Output
    For each test case print in new line the number of animals expected at the end of N years modulo 10^9 + 7.
     */

    //这题不就是斐波那契数列
    //矩阵快速幂的方式（动态规划的方式会超时）
    //参考链接：https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
    public static long getNumOfAnimals(long n) {
        long[][] q = {{1, 1}, {1, 0}};
        long[][] res = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                res = multiply(res, q);
            }
            n >>= 1;
            q = multiply(q, q);
        }
        return res[0][0] % 1000000007;
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (a[i][0] * b[0][j] + a[i][1] * b[1][j]) % 1000000007;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            long n = Long.parseLong(scanner.nextLine());
            System.out.println(getNumOfAnimals(n));
        }
    }
}
