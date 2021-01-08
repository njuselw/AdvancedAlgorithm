package Test;

import java.util.Scanner;

public class Problem0103 {
    /*
    Description
    Your task is to Calculate a + b.
    Input
    Input contains multiple test cases. Each test case contains a pair of integers a and b, one pair of integers per line.
    A test case containing 0 0 terminates the input and this test case is not to be processed.
    Output
    For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!(input = scanner.nextLine()).contains("0 0")) {
            int sum = 0;
            String[] nums = input.split(" ");
            for (String num: nums) {
                sum += Integer.parseInt(num);
            }
            System.out.println(sum);
        }
    }
}
