package FifthExercise;

import java.util.Scanner;

public class Problem0502 {
    /*
    路上的球
    Description
    There are two parallel roads, each containing N and M buckets, respectively. Each bucket may contain some balls.
    The buckets on both roads are kept in such a way that they are sorted according to the number of balls in them.
    Geek starts from the end of the road which has the bucket with a lower number of balls
    (i.e. if buckets are sorted in increasing order, then geek will start from the left side of the road).
    The geek can change the road only at the point of intersection(which means, buckets with the same number of balls on two roads).
    Now you need to help Geek to collect the maximum number of balls.

    Input
    The first line of input contains T denoting the number of test cases.
    The first line of each test case contains two integers N and M, denoting the number of buckets on road1 and road2 respectively.
    2nd line of each test case contains N integers, number of balls in buckets on the first road.
    3rd line of each test case contains M integers, number of balls in buckets on the second road.
    Constraints:1<= T <= 1000，1<= N <= 10^3，1<= M <=10^3，0<= A[i],B[i]<=10^6

    Output
    For each test case output a single line containing the maximum possible balls that Geek can collect.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = scanner.nextInt();
            }
            int[] b = new int[m];
            for (int j = 0; j < m; j++) {
                b[j] = scanner.nextInt();
            }
            System.out.println(getMaxBallCnt(a, b));
        }
    }

    //a: 1 4 5 6 8 -> 1,4 5,6 8
    //b: 2 3 4 6 9 -> 2,3,4 6 9
    //相同值将数组分成不同的段，每次选择最大的部分即可
    public static int getMaxBallCnt(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int i = 0, j = 0;
        int res = 0;
        int sum1 = 0, sum2 = 0;
        while (i < n && j < m) {
            if (a[i] < b[j]) {
                sum1 += a[i];
                i++;
            } else if (a[i] > b[j]) {
                sum2 += b[j];
                j++;
            } else {
                res += Math.max(sum1, sum2) + a[i];
                i++;
                j++;
                sum1 = sum2 = 0;
            }
        }
        while (i < n) {
            sum1 += a[i];
            i++;
        }
        while (j < m) {
            sum2 += b[j];
            j++;
        }
        res += Math.max(sum1, sum2);
        return res;
    }
}
