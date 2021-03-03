package SecondExercise;

import java.util.*;

public class Problem0201 {
    /*
    拓扑排序解的个数
    Description
    给定有向无环图中所有边，计算图的拓扑排序解的个数。

    Input
    输入第一行为用例个数，后面每一行表示一个图中的所有边，边的起点和终点用空格隔开，边之间使用逗号隔开。

    Output
    输出拓扑排序解的个数。
     */

    /**
     *
     * @param graph 邻接矩阵
     * @param indegree 入度数组
     * @param zeros 入度为0的节点列表
     * @param isVisited 标志节点是否已访问
     * @param list 记录当前拓扑
     * @param res 所有拓扑的列表
     */
    public static void topo(int[][] graph, int[] indegree, List<Integer> zeros, boolean[] isVisited, ArrayList<Integer> list, List<List<Integer>> res) {
        if (list.size() >= graph.length) { //所有点都已访问
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < zeros.size(); i++) {
            int index = zeros.get(i);
            if (!isVisited[index]) {
                list.add(index); //将顶点插入拓扑排序中
                isVisited[index] = true;
                List<Integer> deleteEdge = new ArrayList<>(); //记录删去的边的终点
                int cnt = 0; //记录新的入度为0的顶点个数
                for (int j = 0; j < graph.length; j++) { //将index顶点出发的边删除
                    if (graph[index][j] == 1) {
                        deleteEdge.add(j);
                        indegree[j]--;
                        graph[index][j] = 0;
                        if (indegree[j] == 0) {
                            zeros.add(j);
                            cnt++;
                        }
                    }
                }
                topo(graph, indegree, zeros, isVisited, list, res);
                //将删除的边恢复，将点的访问状态重置
                isVisited[index] = false;
                list.remove(list.size() - 1);
                for (int j = 0; j < cnt; j++) {
                    zeros.remove(zeros.size() - 1);
                }
                for (Integer endIndex : deleteEdge) {
                    graph[index][endIndex] = 1;
                    indegree[endIndex]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            String[] edges = scanner.nextLine().split(",");
            Set<String> vertex = new HashSet<>();
            for (String edge: edges) {
                String[] v = edge.split(" ");
                vertex.add(v[0]);
                vertex.add(v[1]);
            }
            int n = vertex.size();
            int[][] graph = new int[n][n]; //邻接矩阵
            int[] indegree = new int[n]; //记录所有顶点的入度
            for (String edge: edges) {
                String[] v = edge.split(" ");
                int x = v[0].charAt(0) - 'a';
                int y = v[1].charAt(0) - 'a';
                graph[x][y] = 1;
                indegree[y]++;
            }
            List<Integer> zeros = new ArrayList<>(); //记录入度为0的点
            for (int j = 0; j < n; j++) {
                if (indegree[j] == 0) {
                    zeros.add(j);
                }
            }
            boolean[] isVisited = new boolean[n]; //标志节点是否访问，即是否已加入拓扑队列中
            List<List<Integer>> res = new ArrayList<>();//存放所有拓扑排序结果
            topo(graph, indegree, zeros, isVisited, new ArrayList<Integer>(), res);
            System.out.println(res.size());
        }
    }
}
