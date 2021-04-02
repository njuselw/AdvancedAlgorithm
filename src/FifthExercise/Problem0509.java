package FifthExercise;

import java.util.Arrays;
import java.util.Scanner;

public class Problem0509 {
    /*
    固定和的元素对
    Description
    输入一个数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字，统计这样两个数的对数。

    Input
    输入第一行为用例个数， 每个测试用例输入第一行是数组，每一个数用空格隔开；第二行是数字和。

    Output
    输出这样两个数有几对。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String[] numStr = scanner.nextLine().split(" ");
            int[] nums = new int[numStr.length];
            for (int j = 0; j < nums.length; j++) {
                nums[j] = Integer.parseInt(numStr[j]);
            }
            int target = Integer.parseInt(scanner.nextLine());
            System.out.println(getRes(nums, target));
        }
    }

    public static int getRes(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int res = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                res++;
                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }
}
