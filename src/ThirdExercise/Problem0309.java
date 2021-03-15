package ThirdExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0309 {
    /*
    字符串匹配问题
    Description
    Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[])
    that prints all occurrences of pat[] in txt[]. You may assume that n > m.

    Input
    输入第一行是用例个数，后面一行表示一个用例；用例包括两部分，第一部分为给定文本，第二部分为搜索串，两部分使用","隔开。

    Output
    每一个用例输出一行，每行按照找到的位置先后顺序排列，使用空格隔开。
     */

    public static List<Integer> findPatInTxt(String txt, String pat) {
        char[] t = txt.toCharArray();
        char[] p = pat.toCharArray();
        int[] next = getNextArr(p);
        List<Integer> indexes = new ArrayList<>();
        int i = 0, j = 0;
        while (i < txt.length()) {
            if (j == -1 || t[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == pat.length()) {
                indexes.add(i - j);
                j = next[j];
            }
        }
        return indexes;
    }

    public static int[] getNextArr(char[] a) {
        int len = a.length;
        int[] next = new int[len + 1];
        next[0] = -1;
        int k = -1, i = 0;
        while (i < len) {
            if (k == -1 || a[k] == a[i]) {
                i++;
                k++;
                if (i != len && a[i] == a[k]) {
                    next[i] = next[k];
                } else {
                    next[i] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String[] s = scanner.nextLine().split(",");
            String txt = s[0];
            String pat = s[1];
            List<Integer> indexes = findPatInTxt(txt, pat);
            if (indexes.size() == 0) {
                System.out.println();
                continue;
            }
            for (int j = 0; j < indexes.size() - 1; j++) {
                System.out.print(indexes.get(j) + " ");
            }
            System.out.println(indexes.get(indexes.size() - 1));
        }
    }
}
