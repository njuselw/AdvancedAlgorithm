package FifthExercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Problem0511 {
    /*
    子数组的取值范围
    Description
    给定数组arr和整数num，求arr的连续子数组中满足：其最大值减去最小值的结果大于num的个数。请实现一个时间复杂度为O(length(arr))的算法。

    Input
    输入第一行为测试用例个数。每一个用例有若干行，第一行为数组，每一个数用空格隔开，第二行为num。

    Output
    输出一个值。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String[] numStr = scanner.nextLine().split(" ");
            int[] arr = new int[numStr.length];
            for (int j = 0; j < numStr.length; j++) {
                arr[j] = Integer.parseInt(numStr[j]);
            }
            int num = Integer.parseInt(scanner.nextLine());
            System.out.println(getSubArrCnt(arr, num));
        }
    }

    //显然暴力是不满足O(n)的时间复杂度要求的
    //用两个双端队列维护滑动窗口内的最大最小值
    public static int getSubArrCnt(int[] arr, int num) {
        int n = arr.length;
        int left = 0, right = 0;
        int res = 0;
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        while (left < n) {
            while (right < n) {
                while (!maxQueue.isEmpty() && arr[right] >= arr[maxQueue.peekLast()]) {
                    maxQueue.pollLast();
                }
                while (!minQueue.isEmpty() && arr[right] <= arr[minQueue.peekLast()]) {
                    minQueue.pollLast();
                }
                maxQueue.offerLast(right);
                minQueue.offerLast(right);
                //满足最大-最小>num后跳出循环，将窗口右端固定，移动窗口左端
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
                    break;
                }
                right++;
            }
            //如果左端即是最大或最小值，则要将队列更新
            if (!maxQueue.isEmpty() && maxQueue.peekFirst() == left) {
                maxQueue.pollFirst();
            }
            if (!minQueue.isEmpty() && minQueue.peekFirst() == left) {
                minQueue.pollFirst();
            }
            //事实上，只要找到满足条件的第一个子数组，那么只要左端不动，右端不论往后移动多少这个数组都是满足条件的
            //因为这不会使得最大最小值之间的差距变小，只可能变大
            res += n - right;
            left++;
        }
        return res;
    }
}
