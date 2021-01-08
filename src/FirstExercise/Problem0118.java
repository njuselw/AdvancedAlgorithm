package FirstExercise;

import java.util.Scanner;

public class Problem0118 {
    /*
    Description
    实现计数排序，通过多次遍历数组，统计比每一个元素小的其它元素个数，根据该统计量对数据进行排序。

    Input
    输入的每一行表示一个元素为正整数的数组，所有值用空格隔开，第一个值为数值长度，其余为数组元素值。

    Output
    输出的每一行为排序结果，用空格隔开，末尾不要空格。
     */

    //计数排序
    public static void countSort(int[] nums) {
        int max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) { //求数组最大最小值
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        int[] cnt = new int[max - min + 1]; //数组取值范围为 min ~ max
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i] - min]++; //求得每个取值的个数
        }
        int index = 0;
        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                nums[index++] = min + i;
            }
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
            countSort(nums);
            for (int i = 0; i < N - 1; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println(nums[N - 1]);
        }
    }
}
