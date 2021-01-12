package FirstExercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem0115 {
    /*
    最小交换次数
    Description
    Given an array of N distinct elementsA[ ], find the minimum number of swaps required to sort the array.
    Your are required to complete the function which returns an integer denoting the minimum number of swaps, required to sort the array.

    Input
    The first line of input contains an integer T denoting the no of test cases . Then T test cases follow .
    Each test case contains an integer N denoting the no of element of the array A[ ].
    In the next line are N space separated values of the array A[ ] .(1<=T<=100;1<=N<=100;1<=A[] <=1000)

    Output
    For each test case in a new line output will be an integer denoting minimum umber of swaps that are required to sort the array.
     */

    //获取使得数组升序的最小交换次数
    public static int getMinSwapCnt(int[] nums) {
        int[] temp = Arrays.copyOfRange(nums, 0, nums.length);
        //排序并记录每个数的正确位置
        Arrays.sort(temp);
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < temp.length; i++) {
            indexMap.put(temp[i], i);
        }
        //记录该位置是否已访问
        boolean[] isVisited = new boolean[temp.length];
        //寻找循环节个数，即循环节内的数依次交换，即可全部回到正确位置
        int loops = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!isVisited[i]) {
                int j = i;
                while (!isVisited[j]) {
                    isVisited[j] = true;
                    j = indexMap.get(nums[j]);
                }
                loops++;
            }
        }
        //对每一个循环节来说，交换次数为循环节内元素个数减一
        //举例，若nums存在三个循环节，nums元素个数为n，各循环节元素个数为x, y, z
        //则交换次数应为 (x-1)+(y-1)+(z-1)=x+y+z-3=n-3
        //其他情况以此类推，最小交换次数为数组元素个数-循环节个数
        return nums.length - loops;
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
            System.out.println(getMinSwapCnt(nums));
        }
    }
}
