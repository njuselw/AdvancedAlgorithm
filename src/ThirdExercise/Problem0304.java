package ThirdExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0304 {
    /*
    能否成环
    Description
    Given an array of strings A[ ], determine if the strings can be chained together to form a circle.
    A string X can be chained together with another string Y if the last character of X is same as first character of Y.
    If every string of the array can be chained, it will form a circle.
    For example, for the array arr[] = {"for", "geek", "rig", "kaf"}
    the answer will be Yes as the given strings can be chained as "for", "rig", "geek" and "kaf".

    Input
    The first line of input contains an integer T denoting the number of test cases. Then T test cases follow.
    The first line of each test case contains a positive integer N, denoting the size of the array.
    The second line of each test case contains a N space seprated strings, denoting the elements of the array A[ ].
    1 <= T; 0 < N; 0 < A[i]

    Output
    If chain can be formed, then print 1, else print 0.
     */

    public static boolean findCircle(List<String> list, String[] strings, boolean[] isVisited, int index) {
        list.add(strings[index]);
        isVisited[index] = true;

        if (list.size() == strings.length) {
            String first = list.get(0);
            String last = list.get(list.size() - 1);
            //成环要求最后一个字符串的末尾字符和第一个字符串的首字符相同
            return first.charAt(0) == last.charAt(last.length() - 1);
        }

        char c = strings[index].charAt(strings[index].length() - 1);
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            if (!isVisited[i] && s.charAt(0) == c) {
                if (findCircle(list, strings, isVisited, i)) {
                    return true;
                }
            }
        }
        list.remove(list.size() - 1);
        isVisited[index] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(scanner.nextLine());
            String[] strings = scanner.nextLine().split(" ");
            List<String> list = new ArrayList<>();
            boolean[] isVisited = new boolean[n];
            if (findCircle(list, strings, isVisited, 0)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
