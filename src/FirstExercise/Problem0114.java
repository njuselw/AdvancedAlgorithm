package FirstExercise;

import java.util.Scanner;

public class Problem0114 {
    /*
    合并数组
    Description
    Given K sorted arrays arranged in a form of a matrix, your task is to merge them.
    You need to complete mergeKArrays function which takes 2 arguments,
    an arr[k][k] 2D Matrix containing k sorted arrays and an integer k denoting the no. of sorted arrays.
    The function should return a pointer to the merged sorted arrays.
    There are multiple test cases. For each test case, this method will be called individually.

    Input
    The first line of input will denote the no of Test cases then T test cases will follow .
    Each test cases will contain an integer N denoting the no of sorted arrays.
    Then in the next line contains all the elements of the array separated by space.（1<=T<=50；1<=N<=10）

    Output
    The output will be the sorted merged array.
     */

    //分治法解决，将n个数组不断划分，两两合并
    public static int[] merge(int[][] arrays, int left, int right) {
        if (left == right) return arrays[left];
        int mid = (left + right) / 2;
        int[] leftArray = merge(arrays, left, mid);
        int[] rightArray = merge(arrays, mid + 1, right);
        return mergeTwoArray(leftArray, rightArray);
    }

    //对两个排序数组进行合并
    public static int[] mergeTwoArray(int[] leftArray, int[] rightArray) {
        //若其中一个数组为空，则直接返回另一个数组
        if (leftArray == null) return rightArray;
        if (rightArray == null) return leftArray;
        //若两个数组均非空，则进行合并
        int leftLen = leftArray.length, rightLen = rightArray.length;
        int[] res = new int[leftLen + rightLen];
        int i = 0, j = 0, index = 0;
        while (i < leftLen && j < rightLen) {
            if (leftArray[i] < rightArray[j]) {
                res[index++] = leftArray[i++];
            } else if (leftArray[i] > rightArray[j]) {
                res[index++] = rightArray[j++];
            } else {
                res[index++] = leftArray[i++];
                res[index++] = rightArray[j++];
            }
        }
        //将未遍历的剩余数组进行遍历
        while (i < leftLen) {
            res[index++] = leftArray[i++];
        }
        while (j < rightLen) {
            res[index++] = rightArray[j++];
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int T = scanner.nextInt();
            for (int i= 0; i < T; i++) {
                int n = scanner.nextInt();
                int[][] arrays = new int[n][n];
                int index = 0;
                while (index < n * n) {
                    arrays[index / n][index % n] = scanner.nextInt();
                    index++;
                }
                int[] res = merge(arrays, 0, n - 1);
                for (int j = 0; j < res.length - 1; j++) {
                    System.out.print(res[j] + " ");
                }
                System.out.println(res[res.length - 1]);
            }
        }
    }
}
