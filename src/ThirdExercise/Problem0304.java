package ThirdExercise;

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(scanner.nextLine());
            String[] strings = scanner.nextLine().split(" ");

        }
    }
}
