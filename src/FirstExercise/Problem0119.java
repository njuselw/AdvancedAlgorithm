package FirstExercise;

import java.util.Scanner;
import java.util.Stack;

public class Problem0119 {
    /*
    非递归快排
    Description
    快速排序的核心思想是使用元素的值对数组进行划分。实现其非递归方案。

    Input
    输入的每一行表示一个元素为正整数的数组，所有值用空格隔开，第一个值为数值长度，其余为数组元素值。

    Output
    输出的每一行为排序结果，用空格隔开，末尾不要空格。
     */

    //非递归快排，使用栈
    public static void quickSort(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(nums.length - 1);
        while (!stack.isEmpty()) {
            int right = stack.pop();  //数组右端位置后入先出
            int left = stack.pop();
            if (left >= right) continue;
            int index = partition(nums, left, right);
            stack.push(left);
            stack.push(index - 1);
            stack.push(index + 1);
            stack.push(right);
        }
    }

    //模拟一趟快排，并返回基准元素的最终位置，以将数组进行划分
    public static int partition(int[] nums, int left, int right) {
        //以开始位置元素为基准，将比其小的置于左边，大的置于右边
        int base = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= base) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= base) {
                left++;
            }
            nums[right] = nums[left];
        }
        //此时，left == right，这个坑位就是之前的基准元素
        nums[left] = base;
        return left;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            int[] nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = scanner.nextInt();
            }
            quickSort(nums);
            for (int i = 0; i < N - 1; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println(nums[N - 1]);
        }
    }
}
