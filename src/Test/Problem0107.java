package Test;

import java.util.Scanner;

public class Problem0107 {
    /*
    Description
    Your task is to Calculate a + b.
    Input
    The input will consist of a series of pairs of integers a and b, separated by a space, one pair of integers per line.
    Output
    For each pair of input integers a and b you should output the sum of a and b, and followed by a blank line.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //boolean flag = true;
        while (scanner.hasNext()) {
            String[] nums = scanner.nextLine().split(" ");
            int sum = 0;
            for (String num: nums) {
                sum += Integer.parseInt(num);
            }
            System.out.println(sum);
            System.out.println();
        }

    }
}
