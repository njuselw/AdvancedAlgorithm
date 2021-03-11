package ThirdExercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem0301 {
    /*
    最长公共子序列
    Description
    给定两个字符串，返回两个字符串的最长公共子序列（不是最长公共子字符串），可能是多个。

    Input
    输入第一行为用例个数， 每个测试用例输入为两行，一行一个字符串

    Output
    如果没有公共子序列，不输出，如果有多个则分为多行，按字典序排序。
     */

    /**
     * 寻找两字符串的最长公共子序列，并打印
     * @param s1 字符串一
     * @param s2 字符串二
     */
    public static void findLCS(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int maxLen = dp[len1][len2];
        List<String> lcs = new ArrayList<>();
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (dp[i][j] == maxLen) {
                    getLCS(lcs, s1, s2, i, j, dp, "");
                }
            }
        }
        Collections.sort(lcs);
        for (String s: lcs) {
            System.out.println(s);
        }
    }

    /**
     *
     * @param list 存储最长公共子序列的集合
     * @param s1 字符串1
     * @param s2 字符串2
     * @param i 字符串1的索引位置
     * @param j 字符串2的索引位置
     * @param dp dp[i][j]表示s1前i位和s2前j位的最长公共子序列长度
     * @param s 记录最长公共子序列
     */
    public static void getLCS(List<String> list, String s1, String s2, int i, int j, int[][] dp, String s) {
        if (i == 0 || j == 0) {
            if (!list.contains(s)) {
                list.add(s);
            }
            return;
        }
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            s = s1.charAt(i - 1) + s;
            getLCS(list, s1, s2, i - 1, j - 1, dp, s);
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            getLCS(list, s1, s2, i - 1, j, dp, s);
        } else if (dp[i - 1][j] < dp[i][j - 1]) {
            getLCS(list, s1, s2, i, j - 1, dp, s);
        } else {
            getLCS(list, s1, s2, i - 1, j, dp, s);
            getLCS(list, s1, s2, i, j - 1, dp, s);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            findLCS(s1, s2);
        }
    }
}
