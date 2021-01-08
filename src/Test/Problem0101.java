package Test;

import java.util.Scanner;

public class Problem0101 {
    /*
    Description
    our task is to Calculate a + b. Too easy?! Of course! I specially designed the problem for acm beginners.
    You must have found that some problems have the same titles with this one,
    yes, all these problems were designed for the same aim

    Input
    The input will consist of a series of pairs of integers a and b, separated by a space, one pair of integers per line.

    Output
    For each pair of input integers a and b you should output the sum of a and b in one line,
    and with one line of output for each line in input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] nums = scanner.nextLine().split(" ");
            int sum = 0;
            for (String num: nums) {
                sum += Integer.parseInt(num);
            }
            System.out.println(sum);
        }
    }
}
