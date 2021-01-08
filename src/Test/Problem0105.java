package Test;

import java.util.Scanner;

public class Problem0105 {
    /*
    Description
    Your task is to calculate the sum of some integers.
    Input
    Input contains an integer N in the first line, and then N lines follow.
    Each line starts with a integer M, and then M integers follow in the same line.
    Output
    For each group of input integers you should output their sum in one line, and with one line of output for each line in input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            int M = scanner.nextInt();
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += scanner.nextInt();
            }
            System.out.println(sum);
        }
    }
}
