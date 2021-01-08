package Test;

import java.util.Scanner;

public class Problem0102 {
    /*
    Description
    The first line integer means the number of input integer a and b. Your task is to Calculate a + b.
    Input
    Your task is to Calculate a + b. The first line integer means the numbers of pairs of input integers.
    Output
    For each pair of input integers a and b you should output the sum of a and b in one line,
    and with one line of output for each line in input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] nums = scanner.nextLine().split(" ");
            int sum = 0;
            for (String num: nums) {
                sum += Integer.parseInt(num);
            }
            System.out.println(sum);
        }
    }
}
