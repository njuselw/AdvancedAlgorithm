package FirstExercise;

import java.util.Scanner;

public class Problem0108 {
    /*
    数学公式
    Description
    Implement pow(A, B) % C.In other words, given A, B and C, find (A^B)%C

    Input
    The first line of input consists number of the test cases.
    The following T lines consist of 3 numbers each separated by a space and in the following order:A B C
    'A' being the base number, 'B' the exponent (power to the base number) and 'C' the modular.
    Constraints:1 ≤ T ≤ 70,1 ≤ A ≤ 10^5,1 ≤ B ≤ 10^5,1 ≤ C ≤ 10^5

    Output
    In each separate line print the modular exponent of the given numbers in the test case.
     */

    // 由于 (a * b) % c = ((a % c) * (b % c)) % c
    // 那么 pow(a, b) % c ==> (a * a * ... * a) % c ==> ((a % c)(a % c)...(a % c)) % c
    //                       \______________/          \________________________/
    //                            b 个 a                      b 个 a % c
    // 快速幂
    public static int fastPow(int a, int b, int c) {
        int res = 1;
        while (b > 0) {
            if (b % 2 == 1) { //奇数情况下，先乘一个a
                res = ((res % c) * (a % c)) % c;
            }
            a = ((a % c) * (a % c)) % c; //偶数情况，直接平方
            b /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            System.out.println(fastPow(a, b, c));
        }
    }
}
