package FirstExercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Point {
    private double x;
    private double y;

    public Point() {

    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return this.x + " " + this.y;
    }
}

public class Problem0124 {
    /*
    分治法解最近对问题
    Description
    最近对问题：使用分治算法解决最近对问题。

    Input
    第一行为测试用例个数。后面每一行表示一个用例，一个用例为一些平面上点的集合，点与点之间用逗号隔开，一个点的两个坐标用空格隔开。坐标值都是正数。

    Output
    对每一个用例输出两个距离最近的点（坐标使用空格隔开），用逗号隔开，先按照第一个坐标大小排列，再按照第二个坐标大小排列。
    如果有多个解，则按照每个解的第一个点的坐标排序，连续输出多个解，用逗号隔开。
     */

    //获取两个点之间的欧氏距离
    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY()) - p2.getY());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            String[] points_str = scanner.nextLine().split(",");
            Point[] points = new Point[points_str.length];
            for (int j = 0; j < points.length; j++) {
                String[] point_str = points_str[j].split(" ");
                points[j] = new Point(Double.parseDouble(point_str[0]), Double.parseDouble(point_str[1]));
            }
            Arrays.sort(points, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return (int) (o1.getX() - o2.getX());  //按x轴坐标进行排序，方便后续进行分治处理
                }
            });
            System.out.println(Arrays.toString(points));
        }
    }
}
