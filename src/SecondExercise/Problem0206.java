package SecondExercise;

import java.util.*;

public class Problem0206 {
    /*
    按照另一个数组排序
    Description
    Given two array A1[] and A2[], sort A1 in such a way that the relative order among the elements will be same as those in A2.
    For the elements not present in A2. Append them at last in sorted order.
    It is also given that the number of elements in A2[] are smaller than or equal to number of elements in A1[] and A2[] has all distinct elements.
    Input:A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8} A2[] = {2, 1, 8, 3}Output: A1[] = {2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9}
    Since 2 is present first in A2[], all occurrences of 2s should appear first in A[],
    then all occurrences 1s as 1 comes after 2 in A[]. Next all occurrences of 8 and then all occurrences of 3.
    Finally we print all those elements of A1[] that are not present in A2[]
    Constraints:1 ≤ T ≤ 50 1 ≤ M ≤ 50 1 ≤ N ≤ 10 & N ≤ M 1 ≤ A1[i], A2[i] ≤ 1000

    Input
    The first line of input contains an integer T denoting the number of test cases.
    The first line of each test case is M and N. M is the number of elements in A1 and N is the number of elements in A2.
    The second line of each test case contains M elements. The third line of each test case contains N elements.

    Output
    Print the sorted array according order defined by another array.
     */

    public static int[] sortByAnother(int[] a1, int[] a2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a2) {
            map.put(i, 0);
        }
        List<Integer> notInAnother = new ArrayList<>();
        for (int i : a1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                notInAnother.add(i);
            }
        }
        int[] res = new int[a1.length];
        int index = 0;
        for (int i = 0; i < a2.length; i++) {
            int cnt = map.get(a2[i]);
            for (int j = 0; j < cnt; j++) {
                res[index++] = a2[i];
            }
        }
        Collections.sort(notInAnother);
        for (int i = 0; i < notInAnother.size(); i++) {
            res[index++] = notInAnother.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[] a1 = new int[m];
            int[] a2 = new int[n];
            for (int j = 0; j < m; j++) {
                a1[j] = scanner.nextInt();
            }
            for (int j = 0; j < n; j++) {
                a2[j] = scanner.nextInt();
            }
            int[] res = sortByAnother(a1, a2);
            for (int j = 0; j < res.length - 1; j++) {
                System.out.print(res[j] + " ");
            }
            System.out.println(res[res.length - 1]);
        }
    }
}
