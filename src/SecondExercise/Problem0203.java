package SecondExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0203 {
    /*
    深度优先遍历
    Description
    按照给定的起始顶点深度优先遍历给定的无向图，尝试所有可能的遍历方式，打印遍历过程中出现的最大深度。

    Input
    输入第一行是用例个数，后面每个用例使用多行表示，用例的第一行是图中节点的个数n和起始点，用空格隔开，后面n+1行为图的邻接矩阵，其中第一行为节点名称。
    值之间使用空格隔开。

    Output
    输出深度优先遍历中遇到的最大深度。
     */
    public static int dfs(int startIndex, int[][] graph, boolean[] isVisited, List<Integer> dfsList) {
        if (dfsList.size() >= graph.length || isVisited[startIndex]) { //遍历完毕的情况 或 遇到的点已遍历 则返回
            return dfsList.size();
        }
        isVisited[startIndex] = true;
        dfsList.add(startIndex);
        int maxSize = dfsList.size();
        for (int i = 0; i < graph[startIndex].length; i++) {
            if (graph[startIndex][i] == 1) {
                maxSize = Math.max(maxSize, dfs(i, graph, isVisited, dfsList));
            }
        }
        isVisited[startIndex] = false;
        dfsList.remove(dfsList.size() - 1);
        return maxSize;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            String s = scanner.nextLine();
            int n = Integer.parseInt(s.split(" ")[0]);
            String startVertex = s.split(" ")[1];
            String[] vertex_str = scanner.nextLine().split(" ");
            List<String> vertexes = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                vertexes.add(vertex_str[j]);
            }
            int[][] graph = new int[n][n]; //邻接矩阵
            for (int j = 0; j < n; j++) {
                String[] strs = scanner.nextLine().split(" ");
                int index = vertexes.indexOf(strs[0]);
                for (int k = 0; k < n; k++) {
                    graph[index][k] = Integer.parseInt(strs[k + 1]);
                }
            }
            List<Integer> dfsList = new ArrayList<>(); //记录深度优先遍历结果
            boolean[] isVisited  = new boolean[n]; //记录节点访问情况
            int startIndex = vertexes.indexOf(startVertex);
            System.out.println(dfs(startIndex, graph, isVisited, dfsList));
        }
    }

}
