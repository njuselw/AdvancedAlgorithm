package ThirdExercise;

import java.util.Scanner;

public class Problem0311 {
    /*
    对称子字符串
    Description
    Given a string ‘str’ of digits, find length of the longest substring of ‘str’,
    such that the length of the substring is 2k digits and sum of left k digits is equal to the sum of right k digits.

    Input
    输入第一行是测试用例的个数，后面每一行表示一个数字组成的字符串，例如："123123"

    Output
    输出找到的满足要求的最长子串的长度。例如，给定的例子长度应该是 6。每行对应一个用例的结果。
     */

    public static int getMaxSubLength(String s) {
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            nums[i] = s.charAt(i) - '0';
        }
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int leftSum = 0, rightSum = 0;
            int left = i, right = i + 1;
            //两边扩散
            while (left >= 0 && right < s.length()) {
                leftSum += nums[left];
                rightSum += nums[right];
                if (leftSum == rightSum) {
                    maxLen = Math.max(maxLen, right - left + 1);
                }
                left--;
                right++;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String s = scanner.nextLine();
            System.out.println(getMaxSubLength(s));
        }
    }
}
