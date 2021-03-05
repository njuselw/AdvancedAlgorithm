package SecondExercise;

import java.util.Scanner;

public class Problem0207 {
    /*
    Searching_3
    Description
    They declared Sonam as bewafa. Although she is not, believe me!
    She asked a number of queries to people regrading their position in a test.
    Now its your duty to remove her bewafa tag by answering simple queries.
    All the students who give test can score from 1 to 10^18. Lower the marks, better the rank.
    Now instead of directly telling the marks of student they have been assigned groups where marks are distributed in continuous intervals,
    you have been given l(i) lowest mark of interval i and r(i) highest marks in interval i.
    So marks distribution in that interval is given as l(i), l(i)+1, l(i)+2 . . . r(i)
    Now Sonam ask queries in which she gives rank of the student (x) and you have to tell marks obtained by that student
    Note: rank1 is better than rank2 and rank2 is better than rank3 and so on and the first interval starts from 1.
    Constraints:1<=T<=50,1<=N<=10^5,1<=Q<=10^5,1<= l(i) < r(i) <=10^18,1<=x<=10^18

    Input
    The first line of input contains an integer T, denoting the no of test cases.
    Then T test cases follow. Each test case contains two space separated values N and Q denoting the no of groups and number of queries asked respectively.
    The next line contains N group of two integers separated by space which shows lowest marks in group i ie l(i) and highest marks in group i ie r(i) such that if i < j then r(i) < l(j).
    The next lines contain Q space separated integers x, denoting rank of student.

    Output
    For each query output marks obtain by student whose rank is x(1<=x<=10^18).
     */

    public static long getMark(long[] low, long[] len_arr, long x) {
        int left = 0, right = low.length - 1;
        //二分方式寻找x所处的区间
        while (left < right) {
            int mid = (left + right) / 2;
            if (len_arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //x所处区间前的所有区间长度和
        long preLen = left == 0 ? 0 : len_arr[left - 1];
        return low[left] + x - preLen - 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int Q = scanner.nextInt();
            long[] low = new long[N]; //记录各区间的左端点
            long[] high = new long[N]; //记录各区间的右端点
            long[] len_arr = new long[N]; //记录各区间的长度
            long[] query = new long[Q];
            long len = 0;
            for (int j = 0; j < N; j++) {
                low[j] = scanner.nextLong();
                high[j] = scanner.nextLong();
                len += high[j] - low[j] + 1;
                len_arr[j] = len;
            }
            for (int j = 0; j < Q; j++) {
                query[j] = scanner.nextLong();
            }
            for (int j = 0; j < Q - 1; j++) {
                System.out.print(getMark(low, len_arr, query[j]) + " ");
            }
            System.out.println(getMark(low, len_arr, query[Q - 1]));
        }
    }
}
