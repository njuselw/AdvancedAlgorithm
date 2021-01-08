package FirstExercise;

import java.util.Scanner;

public class Problem0116 {
    /*
    Description
    实现插入排序。

    Input
    输入第一行为用例个数， 每个测试用例输入的每一行代表一个数组，其中的值用空格隔开，第一个值表示数组的长度。

    Output
    输出排序的数组，用空格隔开，末尾不要空格。
     */

    //插入排序
    public static void insertSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int len = nums.length;
        for (int i = 1; i < len; i++) {  //认定前 i-1 个数已完成排序，遍历找到第 i 个数的位置进行插入
            int temp = nums[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (nums[j] > temp) {
                    nums[j + 1] = nums[j]; //当前位置比第 i 个数大，将其后移
                } else {
                    //找到第 i 个数的位置，进行插入
                    break;
                }
            }
            nums[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int[] nums = new int[N];
            for (int j = 0; j < N; j++) {
                nums[j] = scanner.nextInt();
            }
            insertSort(nums);
            for (int j = 0; j < N - 1; j++) {
                System.out.print(nums[j] + " ");
            }
            System.out.println(nums[N - 1]);
        }
    }
}
