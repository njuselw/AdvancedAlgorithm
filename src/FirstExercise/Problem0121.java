package FirstExercise;

import java.util.Arrays;
import java.util.Scanner;

public class Problem0121 {

    /*
    Description
    有一个由N个实数构成的数组，如果一对元素A[i]和A[j]是倒序的，即i<j但是A[i]>A[j]则称它们是一个倒置，设计一个计算该数组中所有倒置数量的算法。
    要求算法复杂度为O(nlogn)

    Input
    输入有多行，第一行整数T表示为测试用例个数，后面是T个测试用例，每一个用例包括两行，第一行的一个整数是元素个数，第二行为用空格隔开的数组值。

    Output
    输出每一个用例的倒置个数，一行表示一个用例。
     */

    public static int sort(int[] nums) {
        if (nums.length <= 1) return 0;
        int cnt = 0;

        //划分左右数组
        int[] left = Arrays.copyOfRange(nums, 0, nums.length / 2);
        int[] right = Arrays.copyOfRange(nums, nums.length / 2, nums.length);
        int leftCnt = sort(left);
        int rightCnt = sort(right);

        int index = 0, i = 0, j = 0;
        int leftLen = left.length, rightLen = right.length;
        while (i < leftLen && j < rightLen) {
            if (left[i] > right[j]) {
                nums[index++] = right[j++];
                cnt += leftLen - i;
            } else {
                nums[index++] = left[i++];
            }
        }
        while (i < leftLen) {
            nums[index++] = left[i++];
        }
        while (j < rightLen) {
            nums[index++] = right[j++];
        }
        return leftCnt + rightCnt + cnt;
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
            int cnt = sort(nums);
            System.out.println(cnt);
        }
    }
}
