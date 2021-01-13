package FirstExercise;

import java.util.Arrays;
import java.util.Scanner;

public class Problem0112 {
    /*
    客流量
    Description
    Consider a big party where a log register for guest’s entry and exit times is maintained.
    Find the time at which there are maximum guests in the party. Note that entries in register are not in any order.

    Input
    The first line of input contains an integer T denoting the number of test cases.
    Then T test cases follow. Each test case contains an integer n denoting the size of the entry and exit array.
    Then the next two line contains the entry and exit array respectively.(1<=T<=10^5;1<=N<=10^5;1<=entry[i],exit[i]<=10^5)

    Output
    Print the maximum no of guests and the time at which there are maximum guests in the party.
     */

    public static void getMaxNum(int[] entry, int[] exit) {
        //entry数组并不指定进入顺序，因此对数组进行排序，便于按时间顺序进行处理
        Arrays.sort(entry);
        Arrays.sort(exit);
        int n = entry.length;
        int i = 0, j = 0;
        int maxNum = 0, maxTime = 0;
        int curNum = 0; //记录当前时刻的派对人数
        while (i < n && j < n) {
            if (entry[i] < exit[j]) { //该时刻进入一人，当前人数增加
                curNum++;
                if (curNum > maxNum) {
                    maxNum = curNum;
                    maxTime = entry[i];
                }
                i++;
            } else if (entry[i] > exit[j]) { //该时刻离开一人，当前人数减少
                curNum--;
                j++;
            } else { //该时刻同时有人进出，先处理进入
                while (i < n - 1 && entry[i] == entry[i + 1]) { //将所有该时刻进入人员记录
                    i++;
                    curNum++;
                }
                curNum++;
                if (curNum > maxNum) {
                    maxNum = curNum;
                    maxTime = entry[i];
                }
                i++;
                j++;
                curNum--;
            }
        }
        System.out.println(maxNum + " " + maxTime);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int T = scanner.nextInt();
            for (int i = 0; i < T; i++) {
                int n = scanner.nextInt();
                int[] entry = new int[n];
                int[] exit = new int[n];
                for (int j = 0; j < n; j++) {
                    entry[j] = scanner.nextInt();
                }
                for (int j = 0; j < n; j++) {
                    exit[j] = scanner.nextInt();
                }
                getMaxNum(entry, exit);
            }
        }
    }
}
