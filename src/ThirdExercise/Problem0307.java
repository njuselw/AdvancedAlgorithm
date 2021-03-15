package ThirdExercise;

import java.util.Scanner;

public class Problem0307 {
    /*
    和最大的连续降序字符
    Description
    Archana is very fond of strings. She likes to solve many questions related to strings.
    She comes across a problem which she is unable to solve. Help her to solve.
    The problem is as follows: Given is a string of length L.
    Her task is to find the longest string from the given string with characters arranged in descending order of their ASCII code and in arithmetic progression.
    She wants the common difference should be as low as possible(at least 1) and the characters of the string to be of higher ASCII value.

    Input
    The first line of input contains an integer T denoting the number of test cases. Each test contains a string s of lengthL.
    1<= T <= 100; 3<= L <=1000; A<=s[i]<=Z
    The string contains minimum three different characters.

    Output
    For each test case print the longest string.Case 1:Two strings of maximum length are possible- “CBA” and “RPQ”.
    But he wants the string to be of higher ASCII value therefore, the output is “RPQ”.Case 2:The String of maximum length is “JGDA”.
     */

    public static String getMaxSumSequence(String s) {
        char[] arr = s.toCharArray();
        boolean[] flag = new boolean[26];
        for (char c: arr) {
            flag[c - 'A'] = true;
        }
        int maxLen = 0;
        String maxSumStr = "";
        for (int i = 0; i < flag.length; i++) {
            //若该字符在字符串中，则+1, +2, +3, ..., +26寻找下一个字符，循环构建字符序列
            if (flag[i]) {
                int step = 1;
                while (step + i < flag.length) {
                    int len = 0;
                    String temp = "";
                    for (int j = i; j < flag.length; j += step) {
                        if (flag[j]) {
                            len++;
                            temp = (char) (j + 'A') + temp;
                        } else {
                            break;
                        }
                    }
                    //选取更长者，或当两字符串相等时，选取首位ASCII码更大者
                    if (len > maxLen || (len == maxLen && temp.charAt(0) > maxSumStr.charAt(0))) {
                        maxLen = len;
                        maxSumStr = temp;
                    }
                    step++;
                }
            }
        }
        return maxSumStr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            String s = scanner.next();
            System.out.println(getMaxSumSequence(s));
        }
    }
}
