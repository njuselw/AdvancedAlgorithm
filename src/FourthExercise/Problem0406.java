package FourthExercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem0406 {
    /*
    按照要求保留数组元素使得和最大
    Description
    Given an array of N numbers, we need to maximize the sum of selected numbers.
    At each step, you need to select a number Ai, delete one occurrence of Ai-1 (if exists) and Ai each from the array.
    Repeat these steps until the array gets empty. The problem is to maximize the sum of selected numbers.

    Input
    The first line of the input contains T denoting the number of the test cases.
    For each test case, the first line contains an integer n denoting the size of the array.
    Next line contains n space separated integers denoting the elements of the array.
    Constraints:1<=T<=100，1<=n<=50，1<=A[i]<=20
    Note: Numbers need to be selected from maximum to minimum.

    Output
    For each test case, the output is an integer displaying the maximum sum of selected numbers.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(scanner.nextInt());
            }
            System.out.println(getMaxSumOfSelected(list));
        }
    }

    //迷惑，说了从最大删到最小，那还有什么可操作的点，直接遍历不就完了
    public static int getMaxSumOfSelected(List<Integer> list) {
        Collections.sort(list);
        int index = list.size() - 1;
        int sum = 0;
        while (list.size() > 0) {
            int num = list.get(index);
            list.remove(index);
            sum += num;
            int lowIndex = list.indexOf(num - 1);
            if (lowIndex != -1) {
                list.remove(lowIndex);
                index--;
            }
            index--;
        }
        return sum;
    }
}
