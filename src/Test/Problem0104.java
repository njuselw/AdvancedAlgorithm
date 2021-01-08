package Test;

import java.util.Scanner;

public class Problem0104 {
    /*
    Description
    Your task is to Calculate the sum of some integers.
    Input
    Input contains multiple test cases.
    Each test case contains a integer N, and then N integers follow in the same line.
    A test case starting with 0 terminates the input and this test case is not to be processed.
    Output
    For each group of input integers you should output their sum in one line, and with one line of output for each line in input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 0;
        while ((N = scanner.nextInt()) != 0) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += scanner.nextInt();
            }
            System.out.println(sum);
        }
    }
}
