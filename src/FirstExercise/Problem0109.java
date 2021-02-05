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
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            indexes.add(i);
        }
        List<Integer> upper = upperHull(points, indexes);
        List<Integer> lower = lowerHull(points, indexes);
        //合并结果
        List<Integer> resIndex = new ArrayList<>();
        resIndex.add(indexes.get(0));
        resIndex.add(indexes.get(indexes.size() - 1));
        resIndex.addAll(upper);
        resIndex.addAll(lower);
        Collections.sort(resIndex);
        for (int i = 0; i< resIndex.size(); i++) {
            res.add(points[resIndex.get(i)]);
        }
        return res;
    }

    //上包计算
    public static List<Integer> upperHull(Point[] points, List<Integer> indexes) {
        int maxDis = 0;
        int maxIndex = -1;
        int size = indexes.size();
        Point p1 = points[indexes.get(0)];
        Point p2 = points[indexes.get(size - 1)];
        //记录属于上包的点的索引
        List<Integer> upperIndexes = new ArrayList<>();
        for (int i = 1; i < size - 1; i++) { //两个顶点不需要计算
            int dis = getDistance(p1, p2, points[indexes.get(i)]);
            if (dis > 0) {
                upperIndexes.add(i);
                if (dis > maxDis) {
                    maxDis = dis;
                    maxIndex = indexes.get(i);
                }
            }
        }
        if (maxIndex == -1) { //不存在上包点，返回空
            return new ArrayList<>();
        }

        //对上包再进行划分，分成p1->pmax, pmax->p2两部分
        List<Integer> divide1 = new ArrayList<>();
        List<Integer> divide2 = new ArrayList<>();
        divide1.add(indexes.get(0));
        divide2.add(maxIndex);
        for (int i = 0; i < upperIndexes.size(); i++) {
            divide1.add(upperIndexes.get(i));
            divide2.add(upperIndexes.get(i));
        }
        divide1.add(maxIndex);
        divide2.add(indexes.get(size - 1));

        List<Integer> res1 = upperHull(points, divide1);
        List<Integer> res2 = upperHull(points, divide2);
        res1.add(maxIndex);
        res1.addAll(res2);
        return res1;
    }

    //下包计算
    public static List<Integer> lowerHull(Point[] points, List<Integer> indexes) {
        int minDis = 0;
        int minIndex = -1;
        int size = indexes.size();
        Point p1 = points[indexes.get(0)];
        Point p2 = points[indexes.get(size - 1)];
        //记录属于下包的点的索引
        List<Integer> lowerIndexes = new ArrayList<>();
        for (int i = 1; i < size - 1; i++) { //两个顶点不需要计算
            int dis = getDistance(p1, p2, points[indexes.get(i)]);
            if (dis < 0) {
                lowerIndexes.add(i);
                if (dis < minDis) {
                    minDis = dis;
                    minIndex = indexes.get(i);
                }
            }
        }
        if (minIndex == -1) { //不存在上包点，返回空
            return new ArrayList<>();
        }

        //对上包再进行划分，分成p1->pmin, pmin->p2两部分
        List<Integer> divide1 = new ArrayList<>();
        List<Integer> divide2 = new ArrayList<>();
        divide1.add(indexes.get(0));
        divide2.add(minIndex);
        for (int i = 0; i < lowerIndexes.size(); i++) {
            divide1.add(lowerIndexes.get(i));
            divide2.add(lowerIndexes.get(i));
        }
        divide1.add(minIndex);
        divide2.add(indexes.get(size - 1));

        List<Integer> res1 = lowerHull(points, divide1);
        List<Integer> res2 = lowerHull(points, divide2);
        res1.add(minIndex);
        res1.addAll(res2);
        return res1;
    }


    //计算行列式
    //  |x1 y1 1|                                             >0 p3在p1p2左侧
    //  |x2 y2 1| = x1y2 + x3y1 + x2y3 - x3y2 - x2y1 - x1y3   =0 p3在p1p2上
    //  |x3 y3 1|                                             <0 p3在p1p2右侧
    //同时该行列式绝对值的1/2等于三角形p1p2p3的面积
    public static int getDistance(Point p1, Point p2, Point p3) {
        return p1.getX() * p2.getY() + p3.getX() * p1.getY() + p2.getX() * p3.getY() - p3.getX() * p2.getY() - p2.getX() * p1.getY() - p1.getX() * p3.getY();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(scanner.nextLine());
            String[] points_str = scanner.nextLine().split(" ");
            if (N == 0) {
                System.out.println(-1);
                continue;
            }
            Point[] points = new Point[N];
            for (int j = 0; j < N; j++) {
                points[j] = new Point(points_str[2 * j], points_str[2 * j + 1]);
            }
            Arrays.sort(points);
            List<Point> convexHull = getConvexHull(points);
            if (convexHull.size() <= 2) {
                System.out.println(-1);
            } else {
                System.out.println(convexHull);
            }
        }

    }
}
