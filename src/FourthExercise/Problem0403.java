package FourthExercise;

import java.util.Scanner;

public class Problem0403 {
    /*
    无重复字符子集问题
    Description
    Mike is a lawyer with the gift of photographic memory.
    He is so good with it that he can tell you all the numbers on a sheet of paper by having a look at it without any mistake.
    Mike is also brilliant with subsets so he thought of giving a challenge based on his skill and knowledge to Rachael.
    Mike knows how many subset are possible in an array of N integers. The subsets may or may not have the different sum.
    The challenge is to find the maximum sum produced by any subset under the condition:
    The elements present in the subset should not have any digit in common.
    Note: Subset {12, 36, 45} does not have any digit in common and Subset {12, 22, 35} have digits in common.
    Rachael find it difficult to win the challenge and is asking your help. Can you help her out in winning this challenge?

    Input
    First Line of the input consist of an integer T denoting the number of test cases.
    Then T test cases follow. Each test case consist of a number N denoting the length of the array.
    Second line of each test case consist of N space separated integers denoting the array elements.
    Constraints:1 <= T <= 100;1 <= N <= 100;1 <= array elements <= 100000

    Output
    Corresponding to each test case, print output in the new line.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }
            boolean[] isVisited = new boolean[10];
            System.out.println(getMaxSumOfSubSequence(arr, isVisited, 0, 0));
        }
    }

    public static int getMaxSumOfSubSequence(int[] arr, boolean[] isVisited, int index, int res) {
        if (index == arr.length) {
            return res;
        }
        int skipRes = getMaxSumOfSubSequence(arr, isVisited, index + 1, res);
        int num = arr[index];
        boolean flag = false;
        char[] cArr = Integer.toString(num).toCharArray();
        for (char c: cArr) {
            if (isVisited[c - '0']) {
                flag = true;
                break;
            }
        }
        //有数字重复了，当前数不加
        if (flag) {
            return skipRes;
        } else {
            //没有数字重复，可加可不加
            for (char c: cArr) {
                isVisited[c - '0'] = true;
            }
            int noSkipRes = getMaxSumOfSubSequence(arr, isVisited, index + 1, res + num);
            for (char c: cArr) {
                isVisited[c - '0'] = false;
            }
            return Math.max(skipRes, noSkipRes);
        }
    }
}
