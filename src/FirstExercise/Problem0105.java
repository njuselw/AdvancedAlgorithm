package FirstExercise;

import java.util.Scanner;

public class Problem0105 {
    /*
    宠物屋涂色
    Description
    Dilpreet wants to paint his dog- Buzo's home that has n boards with different lengths[A1, A2,..., An].
    He hired k painters for this work and each painter takes 1 unit time to paint 1 unit of the board.
    The problem is to find the minimum time to get this job done under the constraints that any painter will only paint continuous sections of boards,
    say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.

    Input
    The first line consists of a single integer T, the number of test cases.
    For each test case, the first line contains an integer k denoting the number of painters and integer n denoting the number of boards.
    Next line contains n- space separated integers denoting the size of boards.
    Constraints:1<=T<=100 1<=k<=30 1<=n<=50 1<=A[i]<=500

    Output
    For each test case, the output is an integer displaying the minimum time for painting that house.
     */

    //30 50 的测试用例可能会超时，修改
    //二分吧，递归会超时，同第十题思路
    public static int partition(int[] boards, int n, int k) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, boards[i]);
            sum += boards[i];
        }
        int left = max, right = sum;
        while (left < right) {
            int mid = (left + right) / 2;
            if (getPainters(boards, mid) > k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int getPainters(int[] boards, int cnt) {
        int temp = 0;
        int painters = 1;
        for (int i = 0; i < boards.length; i++) {
            temp += boards[i];
            if (temp > cnt) {
                temp = boards[i];
                painters++;
            }
        }
        return painters;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int T = scanner.nextInt();
            for (int i = 0; i < T; i++) {
                int k = scanner.nextInt();
                int n = scanner.nextInt();
                int[] boards = new int[n];
                for (int j = 0; j < n; j++) {
                    boards[j] = scanner.nextInt();
                }
                System.out.println(partition(boards, n, k));
            }
        }
    }
}
