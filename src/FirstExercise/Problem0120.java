package FirstExercise;

import java.util.Scanner;

public class Problem0120 {
    /*
    非递归合并排序
    Description
    合并（归并）排序的核心思想是：每次从中间位置将数组分组再分别排序。请实现其非递归方案。

    Input
    输入的每一行表示一个元素为正整数的数组，所有值用空格隔开，第一个值为数值长度，其余为数组元素值。

    Output
    输出的每一行为排序结果，用空格隔开，末尾不要空格。
     */

    //将两个子序列进行合并
    public static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                temp[k++] = nums[j++];
            } else {
                temp[k++] = nums[i++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        for (int l = left; l < right + 1; l++) {
            nums[l] = temp[l - left];
        }
    }

    //非递归归并排序
    public static void mergeSort(int[] nums) {
        int k = 1;
        int len = nums.length;
        while (k < len) {
            int i = 0;
            while (i <= len - 2 * k) {
                merge(nums, i, i + k - 1, i + 2 * k - 1); //两两合并
                i += 2 * k;
            }
            if (i <= len - k) {
                merge(nums, i, i + k - 1, len - 1);  //余下不足2k的长度也需要进行合并处理
            }
            k <<= 1;  //每次处理的子序列长度成倍扩展
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            int[] nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = scanner.nextInt();
            }
            mergeSort(nums);
            for (int i = 0; i < N - 1; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println(nums[N - 1]);
        }
    }
}
