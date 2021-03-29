package FifthExercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Problem0502 {
    /*
    数组和窗口
    Description
    给定一个整型数组arr和一个大小为w的窗口，窗口从数组最左边滑动到最右边，每次向右滑动一个位置，求出每一次滑动时窗口内最大元素的和。

    Input
    输入第一行为用例个数， 每个测试用例输入的第一行为数组，每一个元素使用空格隔开；第二行为窗口大小。

    Output
    输出每个测试用例结果。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String[] numStr = scanner.nextLine().split(" ");
            int[] nums = new int[numStr.length];
            for (int j = 0; j < numStr.length; j++) {
                nums[j] = Integer.parseInt(numStr[j]);
            }
            int k = Integer.parseInt(scanner.nextLine());
            System.out.println(getMaxSum(nums, k));
        }
    }

    //经典单调队列问题
    //维护一个单调递减的双端队列
    public static int getMaxSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            //先清除队列中不在窗口范围内的下标
            while (!queue.isEmpty() && i - queue.peekFirst() >= k) {
                queue.pollFirst();
            }
            //比较新加入的元素与队列末尾元素的大小，保持队列的单调性
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if (i >= k - 1) {
                sum += nums[queue.peekFirst()];
            }
        }
        return sum;
    }
}
