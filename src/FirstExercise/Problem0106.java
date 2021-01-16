package FirstExercise;

import java.util.Scanner;

public class Problem0106 {
    /*
    序号乘方
    Description
    There are Infinite People Standing in a row, indexed from 1.
    A person having index 'i' has strength of (i*i).You have Strength 'P'.
    You need to tell what is the maximum number of People You can Kill With your Strength P.
    You can only Kill a person with strength 'X' if P >= 'X' and after killing him, Your Strength decreases by 'X'.

    Input
    First line contains an integer 'T' - the number of testcases,
    Each of the next 'T' lines contains an integer 'P'- Your Power.
    Constraints:1<=T<=10000 1<=P<=1000000000000000

    Output
    For each testcase Output The maximum Number of People You can kill.
     */

    public static long getMaxPeople(long p) {
        long cnt = 0;
        while (p >= 0) {
            cnt++;
            p -= cnt * cnt;
        }
        return cnt - 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            long P = scanner.nextLong();
            System.out.println(getMaxPeople(P));
        }
    }
}
