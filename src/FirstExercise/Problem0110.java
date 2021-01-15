package FirstExercise;

import java.util.Scanner;

public class Problem0110 {
    /*
    书本分发
    Description
    You are given N number of books. Every ith book has Pi number of pages. You have to allocate books to M number of students.
    There can be many ways or permutations to do so.
    In each permutation one of the M students will be allocated the maximum number of pages. Out of all these permutations,
    the task is to find that particular permutation in which the maximum number of pages allocated to a student is minimum of those in all the other permutations,
    and print this minimum value. Each book will be allocated to exactly one student.
    Each student has to be allocated at least one book.
    每一个学生只能分配连续出现的书本。

    Input
    The first line contains 'T' denoting the number of testcases.
    Then follows description of T testcases:
    Each case begins with a single positive integer N denoting the number of books.
    The second line contains N space separated positive integers denoting the pages of each book.
    And the third line contains another integer M, denoting the number of students
    Constraints:1<= T <=70，1<= N <=50，1<= A [ i ] <=250，1<= M <=50，
    Note: Return -1 if a valid assignment is not possible, and allotment should be in contiguous order (see explanation for better understanding)

    Output
    For each test case, output a single line containing minimum number of pages each student has to read for corresponding test case.
     */

    /**
     * 本质上还是1-5这道题，但加了限制条件，m个学生每人至少分到一本书
     * 即将n个元素的数组nums划分为m个连续子数组，且这m个子数组都至少包含一个元素，要求求出所有划分方法中，m个子数组和最大值最小的情况
     * 可以理解为将数组中的连续元素分到m个桶中，要求桶的容量最小
     * 参考博客： https://www.cnblogs.com/ygh1229/p/10637504.html
     * @param nums 需要进行划分的数组
     * @param n 数组元素个数
     * @param m 划分的份数
     * @return 返回m个子数组和最大值最小的情况
     */
    public static int getRes(int[] nums, int n, int m) {
        if (n < m) return -1; //无法为每位同学分发一本书时，返回-1
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }
        //显然，极端情况下，桶的容量最小为数组中最大元素值，最大为数组所有元素之和
        int left = max, right = sum;
        //二分查找满足条件的最小桶容量
        while (left < right) {
            int mid = (left + right) / 2;
            if (getBucketsNum(nums, mid) > m) { //桶的容量太小了，扩大容量
                left = mid + 1;
            } else { //桶的容量大了，缩小
                right = mid;
            }
        }
        return left;
    }

    /**
     *
     * @param nums 需要划分的数组
     * @param capacity 桶的容量
     * @return 返回需要的桶数量
     */
    public static int getBucketsNum(int[] nums, int capacity) {
        int tem = 0;
        int buckets = 1;
        for (int i = 0; i < nums.length; i++) {
            tem += nums[i];
            if (tem > capacity) { //超出桶容量，换个新桶
                tem = nums[i];
                buckets++;
            }
        }
        return buckets;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int[] pages = new int[N];
            for (int j = 0; j < N; j++) {
                pages[j] = scanner.nextInt();
            }
            int M = scanner.nextInt();
            System.out.println(getRes(pages, N, M));
        }
    }
}
