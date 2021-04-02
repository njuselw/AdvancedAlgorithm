package FifthExercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem0505 {
    /*
    时间分隔
    Description
    Given arrival and departure times of all trains that reach a railway station.
    Your task is to find the minimum number of platforms required for the railway station so that no train waits.
    Note: Consider that all the trains arrive on the same day and leave on the same day.
    Also, arrival and departure times must not be same for a train.

    Input
    The first line of input contains T, the number of test cases.
    For each test case, first line will contain an integer N, the number of trains.
    Next two lines will consist of N space separated time intervals denoting arrival and departure times respectively.
    Note: Time intervals are in the 24-hour format(hhmm), preceding zeros are insignificant. 200 means 2:00.
    Consider the example for better understanding of input.
    Constraints:1 <= T <= 100，1 <= N <= 1000，1 <= A[i] < D[i] <= 2359

    Output
    For each test case, print the minimum number of platforms required for the trains to arrive and depart safely.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] arrive = new int[n];
            for (int j = 0; j < n; j++) {
                arrive[j] = scanner.nextInt();
            }
            int[] leave = new int[n];
            for (int j = 0; j < n; j++) {
                leave[j] = scanner.nextInt();
            }
            System.out.println(getMinPlatformNum(arrive, leave));
        }
    }

    public static int getMinPlatformNum(int[] arrive, int[] leave) {
        int n = arrive.length;
        //将车的到达记为1，离开记为-1
        //那么将数组排序后，整个过程中停留的车辆最多数量就是最小所需站台数
        int[][] a = new int[2 * n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = arrive[i];
            a[i][1] = 1;
        }
        for (int i = 0; i < n; i++) {
            a[i + n][0] = leave[i];
            a[i + n][1] = -1;
        }
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            }
        });
        int res = 0;
        int cur = 0;
        for (int i = 0; i < 2 * n; i++) {
            cur += a[i][1];
            res = Math.max(cur, res);
        }
        return res;
    }
}
