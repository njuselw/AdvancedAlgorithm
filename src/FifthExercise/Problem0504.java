package FifthExercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem0504 {
    /*
    时间与收益
    Description
    Given a set of n jobs where each job i has a deadline and profit associated to it.
    Each job takes 1 unit of time to complete and only one job can be scheduled at a time.
    We earn the profit if and only if the job is completed by its deadline.
    The task is to find the maximum profit and the number of jobs done.

    Input
    The first line of input contains an integer T denoting the number of test cases.
    Each test case consist of an integer N denoting the number of jobs and the next line consist of Job id, Deadline and the Profit associated to that Job.
    Constraints:1<=T<=100，1<=N<=100，1<=Deadline<=100，1<=Profit<=500

    Output
    Output the number of jobs done and the maximum profit.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[][] tasks = new int[n][2];
            for (int j = 0; j < n; j++) {
                int x = scanner.nextInt();
                tasks[x - 1][0] = scanner.nextInt();
                tasks[x - 1][1] = scanner.nextInt();
            }
            int[] res = getMaxProfitAndTaskNum(tasks);
            System.out.println(res[0] + " " + res[1]);
        }
    }

    public static int[] getMaxProfitAndTaskNum(int[][] tasks) {
        //按照profit进行排序，相同的profit按ddl逆序排序
        Arrays.sort(tasks, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o2[1] - o1[1];
                }
                return o2[0] - o1[0];
            }
        });
        int n = tasks.length;
        int maxDDL = 0;
        for (int i = 0; i < n; i++) {
            maxDDL = Math.max(maxDDL, tasks[i][0]);
        }
        //从最大利润任务开始，将其安排在ddl位置，如果已被占用，则往前放
        int[] days = new int[maxDDL + 1];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = tasks[i][0]; j > 0; j--) {
                if (days[j] == 0) {
                    days[j] = tasks[i][1];
                    cnt++;
                    break;
                }
            }
        }
        int profit = 0;
        for (int i = 0; i < days.length; i++) {
            profit += days[i];
        }
        return new int[]{cnt, profit};
    }
}
