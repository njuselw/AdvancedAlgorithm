package FirstExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem0113 {
    /*
    和为定值的子数组
    Description
    Given an array A of size N, find all combination of four elements in the array whose sum is equal to a given value K.
    For example, if the given array is {10, 2, 3, 4, 5, 9, 7, 8} and K = 23, one of the quadruple is “3 5 7 8” (3 + 5 + 7 + 8 = 23).

    Input
    The first line of input contains an integer T denoting the no of test cases.
    Then T test cases follow. Each test case contains two lines. The first line of input contains two integers N and K.
    Then in the next line are N space separated values of the array.（1<=T<=100；1<=N<=100；-1000<=K<=1000；-100<=A[]<=100）

    Output
    For each test case in a new line print all the quadruples present in the array separated by space which sums up to value of K.
    Each quadruple is unique which are separated by a delimeter "$" and are in increasing order.
     */

    //四数之和，求出所有和为target的包含四个元素的子数组
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res; //如果数组长度不足4，则返回空结果
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            //对于重复值，不需要再次计算，最终结果不重复
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //数组升序排序后，当连续四个元素和超过target时，后续不再可能出现和为target的子数组，跳出循环
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            //如果当前元素与最大的三个元素相加结果小于target，也不可能出现包含该元素的最终结果，跳过此次计算
            if (nums[i] + nums[len - 3] + nums[len - 2] + nums[len - 1] < target) continue;
            //将四数之和问题转化为三数之和
            for (int j = i + 1; j < len - 2; j++) {
                //与四数之和问题相同的处理方式
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[len - 2] + nums[len - 1] < target) continue;
                //将三数之和问题转化为两数之和，用二分法解决
                int left = j + 1, right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    //和为target，则将四数加入结果集中
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        //跳过重复值，继续寻找下一个解
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    } else if (sum > target) { //和大于target，则将right指针左移
                        right--;
                    } else { //和小于target，则将left指针右移
                        left++;
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int T = scanner.nextInt();
            for (int i = 0; i < T; i++) {
                int N = scanner.nextInt();
                int target = scanner.nextInt();
                int[] nums = new int[N];
                for (int j = 0; j < N; j++) {
                    nums[j] = scanner.nextInt();
                }
                List<List<Integer>> res = fourSum(nums, target);
                for (List<Integer> subArr : res) {
                    for (Integer element : subArr) {
                        System.out.print(element + " ");
                    }
                    System.out.print("$");
                }
                System.out.println();
            }
        }
    }
}
