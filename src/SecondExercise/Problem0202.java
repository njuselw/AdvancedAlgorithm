package SecondExercise;

import java.util.*;

public class Problem0202 {
    /*
    广度优先遍历图
    Description
    按照给定的起始顶点广度优先遍历图，每一次通过字母顺序选择顶点查找下一层邻接点，打印遍历顺序。

    Input
    输入第一行为测试用例个数，后面每一个用例用多行表示，用例第一行是节点个数n和开始顶点，用空格隔开，后面n+1行为图的邻接矩阵，其中第一行为节点名称。
    值之间使用空格隔开。

    Output
    输出遍历顺序，用空格隔开
     */

    /**
     *
     * @param startIndex 起始点
     * @param graph 邻接矩阵
     * @param isVisited 节点访问标记
     * @param bfsList 遍历结果
     */
    public static void bfs(int startIndex, int[][] graph, boolean[] isVisited, List<Integer> bfsList) {
        isVisited[startIndex] = true;
        bfsList.add(startIndex);
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(startIndex);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int i = 0; i < graph[index].length; i++) {
                if (!isVisited[i] && graph[index][i] == 1) {
                    isVisited[i] = true;
                    queue.offer(i);
                    bfsList.add(i);
                }
            }
        }
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
            Collections.sort(vertexes);
            int[][] graph = new int[n][n]; //邻接矩阵
            for (int j = 0; j < n; j++) {
                String[] strs = scanner.nextLine().split(" ");
                int index = vertexes.indexOf(strs[0]);
                for (int k = 0; k < n; k++) {
                    graph[index][k] = Integer.parseInt(strs[k + 1]);
                }
            }
            List<Integer> bfsList = new ArrayList<>(); //记录广度优先遍历结果
            boolean[] isVisited  = new boolean[n]; //记录节点访问情况
            int startIndex = vertexes.indexOf(startVertex);
            bfs(startIndex, graph, isVisited, bfsList);
            for (int j = 0; j < n - 1; j++) {
                System.out.print(vertexes.get(bfsList.get(j)) + " ");
            }
            System.out.println(vertexes.get(bfsList.get(n - 1)));
        }
    }
}
