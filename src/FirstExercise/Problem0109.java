package FirstExercise;

import java.util.*;

public class Problem0109 {
    /*
    点的凸包
    Description
    Convex Hull of a set of points, in 2D plane,
    is a convex polygon with minimum area such that each point lies either on the boundary of polygon or inside it.
    Now given a set of points the task is to find the convex hull of points.

    Input
    The first line of input contains an integer T denoting the no of test cases.
    Then T test cases follow. Each test case contains an integer N denoting the no of points.
    Then in the next line are N*2 space separated values denoting the points ie x and y.
    Constraints:1<=T<=100,1<=N<=100,1<=x,y<=1000

    Output
    For each test case in a new line print the points x and y of the convex hull separated by a space in sorted order
    (increasing by x) where every pair is separated from the other by a ','. If no convex hull is possible print -1.
     */
    public static List<Point> getConvexHull(Point[] points) {
        List<Point> res = new ArrayList<>();

        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(scanner.nextLine());
            String[] points_str = scanner.nextLine().split(" ");
            Point[] points = new Point[N];
            for (int j = 0; j < N; j++) {
                points[j] = new Point(points_str[2 * j], points_str[2 * j + 1]);
            }
            Arrays.sort(points);
        }

    }
}
