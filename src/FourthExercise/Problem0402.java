package FourthExercise;

import java.util.Scanner;

public class Problem0402 {
    /*
    矩阵计算
    Description
    Let's define a Series Whose recurrence formula is as follows :
    C(n)= 3C(i-1) + 4C(i-2) + 5C(i-3) + 6C(i-4)
    C(0)= 2
    C(1)= 0
    C(2)= 1
    C(3)= 7
    Now based on this Series a Matrix Mi,j of size nn is to be formed.
    The top left cell is(1,1) and the bottom right corner is (n,n).
    Each cell (i,j) of the Matrix contains either 1 or 0. If C( (i*j)^3 ) is odd, Mi,j is 1,
    otherwise, it's 0.Count the total number of ones in the Matrix.

    Input
    First Line Of the input will contain an integer 'T'- the number of test cases .
    Each of the next 'T' lines consists of an integer 'n'.-denoting the size of the matrix.
    Constraints :1 ≤ T ≤ 1000; 1 ≤ n ≤ 1000

    Output
    For each test case, output a single Integer -the taste value fo the dish of size-n*n.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            System.out.println(getOddCnt(n));
        }
    }

    //找规律题
    //C(n) = 3C(n-1)+4C(n-2)+5C(n-3)+6C(n-4)
    //显然C(n)的奇偶性取决于C(n-1)和C(n-3)的奇偶性，只要C(n-1)和C(n-3)一奇数一偶数，则C(n)为奇数，否则为偶数
    //经过计算可以发现，C(n)的奇偶性每7为一个循环
    public static int getOddCnt(int n) {
        int cnt = 0;
        int[] arr = {0, 0, 1, 1, 1, 0, 1};
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int index = (int) (Math.pow((i % 7) * (j % 7), 3) % 7);
                if (arr[index] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
