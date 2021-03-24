package FourthExercise;

import java.util.Scanner;

public class Problem0404 {
    /*
    是否能通过考试
    Description
    小张想要通过明天的考试。他知道考题的分值分布，也知道考试中要拿到每一个题目需要耗费的时间。
    假设考试时长为h，共n个题目，需要拿到p分才能通过考试。现在已知每个考题的得分与耗时，
    请你判断小张能否通过合理安排时间，而通过考试，并给出通过考试的最短时间。

    Input
    输入第一行为测试用例个数.每一个用例有若干行，第一行为任务数量n、考试时常h、通过分数p，下面的n行是每一个题目的耗时和得分。所有数值用空格分开。

    Output
    对每一个用例输出一行，如果能够通过考试，则输出“YES”和消耗最短时间，用空格隔开。 否则，输出“NO”。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int h = scanner.nextInt();
            int p = scanner.nextInt();
            int[][] tasks = new int[n][2];
            for (int j = 0; j < n; j++) {
                tasks[j][0] = scanner.nextInt();
                tasks[j][1] = scanner.nextInt();
            }
            int minTime = getMinTime(tasks, n, h, p);
            if (minTime == -1) {
                System.out.println("NO");
            } else {
                System.out.println("YES " + minTime);
            }
        }
    }

    //背包问题(背包容量就是考试时长，物品价值就是题目的分数)
    //dp[i][j]表示在不超过时间j的情况下，做到第i题最多能拿多少分
    //dp[i][j] = max(dp[i-1][j], dp[i-1][j-tasks[i][0]] + tasks[i][1])
    public static int getMinTime(int[][] tasks, int n, int h, int p) {
        int[][] dp = new int[n + 1][h + 1];
        int minTime = h + 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= h; j++) {
                if (tasks[i - 1][0] <= j) { //余下的时间够做这题，做or不做
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - tasks[i - 1][0]] + tasks[i - 1][1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= p) {
                    minTime = Math.min(minTime, j);
                }
            }
        }
        //如果全都做完还无法满足p，说明无法通过考试，返回-1
        return dp[n][h] < p ? -1 : minTime;
    }
}
