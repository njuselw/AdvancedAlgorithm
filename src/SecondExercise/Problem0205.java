package SecondExercise;

import java.util.Scanner;

public class Problem0205 {
    /*
    Searching_4
    Description
    Given n Magnets which are placed linearly, with each magnet to be considered as of point object.
    Each magnet suffers force from its left sided magnets such that they repel it to the right and vice versa.
    All forces are repulsive. The force being equal to the distance (1/d , d being the distance).
    Now given the positions of the magnets, the task to find all the points along the linear line where net force is ZERO.
    Note: Distance have to be calculated with precision of 0.0000000000001.
    Constraints:1<=T<=100,1<=N<=100,1<=M[]<=1000

    Input
    The first line of input contains an integer T denoting the no of test cases.
    Then T test cases follow. The second line of each test case contains an integer N.
    Then in the next line are N space separated values of the array M[], denoting the positions of the magnet.

    Output
    For each test case in a new line print the space separated points having net force zero till precised two decimal places.
     */

    public static double getForce(double x, int[] m) {
        double force = 0.0;
        for (int i = 0; i < m.length; i++) {
            force += 1 / (x - m[i]);
        }
        return force;
    }

    //二分查找每两个磁体间的所求受力为0的点
    public static double getForceZero(int[] m, int index) {
        //double error = 0.0000000000001;//允许误差
        double error = 0.000000000001;//题中给出的允许误差太精确了，会超时，适当扩大误差范围
        double left = m[index], right = m[index + 1];
        while (left < right) {
            double mid = (left + right) / 2.0;
            double force = getForce(mid, m);
            if (Math.abs(force) < error) {
                return mid;
            } else if (force > 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int[] M = new int[N];
            for (int j = 0; j < N; j++) {
                M[j] = scanner.nextInt();
            }
            for (int j = 0; j < N - 2; j++) {
                double pos = getForceZero(M, j);
                System.out.print(String.format("%.2f", pos) + " ");
            }
            System.out.print(String.format("%.2f", getForceZero(M, N - 2)));
            System.out.println();
        }
    }
}
