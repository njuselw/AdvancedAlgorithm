package FirstExercise;

import java.util.Scanner;

public class Problem0122 {
    /*
    实现Shell排序

    Description
    实现Shell排序，对给定的无序数组，按照给定的间隔变化（间隔大小即同组数字index的差），打印排序结果，注意不一定是最终排序结果！

    Input
    输入第一行表示测试用例个数，后面为测试用例，每一个用例有两行，第一行为给定数组，第二行为指定间隔，每一个间隔用空格隔开。

    Output
    输出的每一行为一个用例对应的指定排序结果。
     */

    /**
     *
     * @param nums 要排序的数组
     * @param gaps 给定的间隔
     */
    public static void shellSort(int[] nums, int[] gaps) {
        for (int i = 0; i < gaps.length; i++) {
            int temp, index;
            for (int j = gaps[i]; j < nums.length; j++) {
                temp = nums[j];
                for (index = j - gaps[i]; index >= 0; index -= gaps[i]) {
                    if (nums[index] > temp) {
                        nums[index + gaps[i]] = nums[index];
                    } else {
                        break;
                    }
                }
                nums[index + gaps[i]] = temp;
            }
        }
    }

    public static int[] toIntArray(String[] strs) {
        int[] arr = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            String[] num_str = scanner.nextLine().split(" ");
            String[] gap_str = scanner.nextLine().split(" ");
            int[] nums = toIntArray(num_str);
            int[] gaps = toIntArray(gap_str);
            shellSort(nums, gaps);
            for (int j = 0; j < nums.length - 1; j++) {
                System.out.print(nums[j] + " ");
            }
            System.out.println(nums[nums.length - 1]);
        }
    }
}
