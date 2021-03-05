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
        List<Integer> upper = hull(points, indexes, true);
        List<Integer> lower = hull(points, indexes, false);
        //合并结果
        //若所有点处于同一条线上，则上下包返回的均为所有点索引列表
        if (upper.size() == lower.size() && upper.containsAll(lower)) {
            return res;
        }
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

    /**
     *
     * @param points 所有点的数组
     * @param indexes 当前需要处理的点的索引列表
     * @param dir 上包还是下包
     * @return 返回对应的顶点列表
     */
    public static List<Integer> hull(Point[] points, List<Integer> indexes, boolean dir) {
        int pDis = 0; //上包记录最大值，下包记录最小值
        int pIndex = -1;
        int size = indexes.size();
        Point p1 = points[indexes.get(0)];
        Point p2 = points[indexes.get(size - 1)];
        List<Integer> upOrLowList = new ArrayList<>();//记录上包或下包的点索引
        List<Integer> onList = new ArrayList<>();//记录再p1p2线上的点
        for (int i = 1; i < size - 1; i++) {
            int dis = getDistance(p1, p2, points[indexes.get(i)]);
            if (dis == 0) {
                onList.add(indexes.get(i));
            }
            if (dir) { //上包
                if (dis > 0) {
                    upOrLowList.add(indexes.get(i));
                    if (dis > pDis) {
                        pDis = dis;
                        pIndex = indexes.get(i);
                    }
                }
            } else {
                if (dis < 0) {
                    upOrLowList.add(indexes.get(i));
                    if (dis < pDis) {
                        pDis = dis;
                        pIndex = indexes.get(i);
                    }
                }
            }
        }
        if (pIndex == -1) {// 未找到上包或下包点，返回线上的点集
            int start = indexes.get(0);
            int end = indexes.get(size - 1);
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < onList.size(); i++) {
                int index = onList.get(i);
                if (index != start && index != end) {
                    res.add(index);
                }
            }
            return res;
        }

        //对上包或下包进行划分，分成p1->pmax, pmax->p2两部分
        List<Integer> divide1 = new ArrayList<>();
        List<Integer> divide2 = new ArrayList<>();
        divide1.add(indexes.get(0));
        divide2.add(pIndex);
        divide1.addAll(upOrLowList);
        divide2.addAll(upOrLowList);
        divide1.add(pIndex);
        divide2.add(indexes.get(size - 1));

        //合并结果
        List<Integer> res1 = hull(points, divide1, dir);
        List<Integer> res2 = hull(points, divide2, dir);
        res1.add(pIndex);
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
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            Point[] points = new Point[N];
            for (int j = 0; j < N; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points[j] = new Point(String.valueOf(x), String.valueOf(y));
            }
            if (N == 0) {
                System.out.println(-1);
                continue;
            }
//            String[] points_str = scanner.nextLine().split(" ");
//            if (N == 0) {
//                System.out.println(-1);
//                continue;
//            }
//            Point[] points = new Point[N];
//            for (int j = 0; j < N; j++) {
//                points[j] = new Point(points_str[2 * j], points_str[2 * j + 1]);
//            }
            Arrays.sort(points);
            List<Point> convexHull = getConvexHull(points);
            if (convexHull.size() <= 2) {
                System.out.println(-1);
            } else {
                for (int j = 0; j < convexHull.size() - 1; j++) {
                    System.out.print(convexHull.get(j) + ", ");
                }
                System.out.println(convexHull.get(convexHull.size() - 1));
            }
        }


    }
}
