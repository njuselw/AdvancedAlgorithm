package ThirdExercise;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem0302 {
    /*
    区间第k最小
    Description
    找到给定数组的给定区间内的第K小的数值。

    Input
    输入第一行为用例个数， 每个测试用例输入的第一行为数组，每一个数用空格隔开；第二行是区间（第几个数到第几个数，两头均包含），两个值用空格隔开；第三行为K值。

    Output
    结果。
     */

    public static int getKthSmallest(int[] nums, int start, int end, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        for (int i = start; i <= end; i++) {
            pq.offer(nums[i]);
        }
        for (int i = 0; i < end - start + 1 - k; i++) {
            pq.poll();
        }
        return pq.peek();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            String[] num_str = scanner.nextLine().split(" ");
            int[] nums = new int[num_str.length];
            for (int j = 0; j < num_str.length; j++) {
                nums[j] = Integer.parseInt(num_str[j]);
            }
            String[] index_str = scanner.nextLine().split(" ");
            int start = Integer.parseInt(index_str[0]) - 1;
            int end = Integer.parseInt(index_str[1]) - 1;
            int k = Integer.parseInt(scanner.nextLine());
            System.out.println(getKthSmallest(nums, start, end, k));
        }
    }
}
