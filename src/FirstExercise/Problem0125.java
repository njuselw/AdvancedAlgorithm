package FirstExercise;

import java.util.*;

public class Problem0125 {
    /*
    按照数值个数排序
    Description
    对给定数组中的元素按照元素出现的次数排序，出现次数多的排在前面，如果出现次数相同，则按照数值大小排序。
    例如，给定数组为{2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}，则排序后结果为{3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5}。

    Input
    输入的第一行为用例个数；后面每一个用例使用两行表示，第一行为数组长度，第二行为数组内容，数组元素间使用空格隔开。

    Output
    每一个用例的排序结果在一行中输出，元素之间使用空格隔开。
     */

    public static void sort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int[][] cnt = new int[map.keySet().size()][2];  //记录数组中所有值极其相应的出现次数
        int index = 0;
        for (Integer i: map.keySet()) {
            cnt[index][0] = i;
            cnt[index][1] = map.get(i);
            index++;
        }
        Arrays.sort(cnt, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return o1[0] - o2[0]; //排序，如果出现次数相同，则按数值大小排序
                return o2[1] - o1[1];  //出现次数不同，则出现次数多的在前
            }
        });
        index = 0;
        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt[i][1]; j++) {
                nums[index++] = cnt[i][0];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = scanner.nextInt();
            }
            sort(nums);
            for (int j = 0; j < n - 1; j++) {
                System.out.print(nums[j] + " ");
            }
            System.out.println(nums[n - 1]);
        }
    }
}
