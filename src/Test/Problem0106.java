package Test;

import java.util.Scanner;

public class Problem0106 {
    /*
    Description
    Your task is to calculate the sum of some integers.
    Input
    Input contains multiple test cases, and one case one line.
    Each case starts with an integer N, and then N integers follow in the same line.
    Output
    For each test case you should output the sum of N integers in one line, and with one line of output for each line in input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += scanner.nextInt();
            }
            System.out.println(sum);
        }
    }
}
