package ThirdExercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem0308 {
    /*
    距离问题
    Description
    In a given cartesian plane, there are N points. We need to find the Number of Pairs of points(A,B) such that
    Point A and Point B do not coincide.
    Manhattan Distance and the Euclidean Distance between the points should be equal.
    Note : Pair of 2 points(A,B) is considered same as Pair of 2 points(B,A).
    Manhattan Distance = |x2-x1|+|y2-y1|
    Euclidean Distance = ((x2-x1)^2 + (y2-y1)^2)^0.5 where points are (x1,y1) and (x2,y2).
    Constraints:1<=T <= 50, 1<=N <= 2*10 ^ 5, 0<=(|Xi|, |Yi|) <= 10^9

    Input
    First Line Consist of T - number of test cases. For each Test case:First Line consist of N , Number of points.
    Next line contains N pairs contains two integers Xi and Yi，i.e, X coordinate and the Y coordinate of a Point

    Output
    Print the number of pairs as asked above.
     */

    //欧式距离 == 曼哈顿距离 即 A, B两点的横坐标相等或纵坐标相等
    //又要求A, B两点不在同一位置，所以还需减去横纵坐标均相等的点
    public static int getNumOfPoints(int[][] points) {
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();
        Map<String, Integer> pointMap = new HashMap<>();
        for (int[] point : points) {
            xMap.put(point[0], xMap.getOrDefault(point[0], 0) + 1);
            yMap.put(point[1], yMap.getOrDefault(point[1], 0) + 1);
            pointMap.put(Arrays.toString(point), pointMap.getOrDefault(Arrays.toString(point), 0) + 1);
        }

        int xCnt = 0, yCnt = 0, pCnt = 0;
        for (Map.Entry<Integer, Integer> entry: xMap.entrySet()) {
            int cnt = entry.getValue();
            xCnt += cnt * (cnt - 1) / 2;
        }
        for (Map.Entry<Integer, Integer> entry: yMap.entrySet()) {
            int cnt = entry.getValue();
            yCnt += cnt * (cnt - 1) / 2;
        }
        for (Map.Entry<String, Integer> entry: pointMap.entrySet()) {
            int cnt = entry.getValue();
            pCnt += cnt * (cnt - 1) / 2;
        }
        //重合点对在横轴纵轴均算了一遍
        return xCnt + yCnt - pCnt * 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(scanner.nextLine());
            int[][] points = new int[n][2];
            for (int j = 0; j < n; j++) {
                String[] point_str = scanner.nextLine().split(" ");
                points[j][0] = Integer.parseInt(point_str[0]);
                points[j][1] = Integer.parseInt(point_str[1]);
            }
            System.out.println(getNumOfPoints(points));
        }
    }
}
