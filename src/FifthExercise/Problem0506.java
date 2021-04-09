package FifthExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0506 {
    /*
    管道网络
    Description
    Every house in the colony has at most one pipe going into it and at most one pipe going out of it.
    Tanks and taps are to be installed in a manner such that every house with one outgoing pipe
    but no incoming pipe gets a tank installed on its roof and every house with only an incoming pipe and no outgoing pipe gets a tap.
    Find the efficient way for the construction of the network of pipes.

    Input
    The first line contains an integer T denoting the number of test cases.
    For each test case, the first line contains two integer n & p denoting the number of houses and number of pipes respectively.
    Next, p lines contain 3 integer inputs a, b & d, d denoting the diameter of the pipe from the house a to house b.
    Constraints:1<=T<=50，1<=n<=20，1<=p<=50，1<=a, b<=20，1<=d<=100

    Output
    For each test case, the output is the number of pairs of tanks and taps installed
    i.e n followed by n lines that contain three integers: house number of tank, house number of tap and the minimum diameter of pipe between them.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i= 0; i < t; i++) {
            int n = scanner.nextInt();
            int p = scanner.nextInt();
            int[] link = new int[n + 1]; //link[i]表示房屋i的管道所连接的房屋
            int[] val = new int[n + 1]; //val[i]表示以房屋i为起点的管道长度
            int[] degree = new int[n + 1]; //记录入度，目的是获得所有起点管道
            for (int j = 0; j < p; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int v = scanner.nextInt();
                link[x] = y;
                val[x] = v;
                degree[y]++;
            }
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if (degree[j] == 0) {
                    list.add(j);
                }
            }
            List<String> res = new ArrayList<>();
            for (Integer start: list) {
                int end = link[start];
                int minVal = val[start];
                while (link[end] != 0) {
                    minVal = Math.min(minVal, val[end]);
                    end = link[end];
                }
                res.add(start + " " + end + " " + minVal);
            }
            System.out.println(res.size());
            for (String s: res) {
                System.out.println(s);
            }
        }
    }
}
