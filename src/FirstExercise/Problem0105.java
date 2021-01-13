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

    /**
     * 问题转化：将n个元素的数组，划分为k个连续子数组，使得这些子数组的最大和最小
     * @param nums 画板数组，记录每个画板的大小
     * @param n 画板数量
     * @param k 画家数量
     * @return 获取划分后所有子数组之和的最大值的最小
     */
    public static int partition(int[] nums, int n, int k) {
        if (n == 1) { //一个画板，直接返回该画板大小
            return nums[0];
        }
        if (k == 1) { //一个画家，返回n个画板大小之和
            return getSum(nums, 0, n - 1);
        }
        int minTime = Integer.MAX_VALUE;
        //递归解决该问题
        //每次将数组的左边i个元素作为下一次划分给k-1个画家的新数组，而i~n-1部分的元素则分配给第k个画家进行处理
        //例：n = 4, k = 2, nums = {10, 20, 30, 40}
        //第一次划分，{10} <- 下一次划分给k-1个画家，   {20, 30, 40} <- 划分给当前第k个画家
        for (int i = 1; i <= n; i++) {
            minTime = Math.min(minTime, Math.max(partition(nums, i, k - 1), getSum(nums, i, n - 1)));
        }
        return minTime;
    }

    /**
     *
     * @param nums 整个数组
     * @param start 起始位置
     * @param end 结束位置
     * @return 求连续子序列所有元素之和
     */
    public static int getSum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
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
