package SecondExercise;

import java.util.*;

public class Problem0204 {
    /*
    分配问题
    Description
    对给定的n个任务与n个人之间的成本矩阵完成成本最低的任务分配策略。

    Input
    输入：第一行为用例个数，之后为每一个用例；用例的第一行为任务个数，即n；
    用例的第二行为使用逗号隔开的人员完成任务的成本；每一个成本描述包括人员序号、任务序号和成本，使用空格隔开。
    人员序号和任务序号都是从1到n的整数，序号出现的次序没有固定规则。

    Output
    输出：每一个用例输出一行，从序号为1的人员开始，给出其分配的任务序号，使用空格隔开；使用逗号将多个解隔开。
    结果按照人员分配的任务序号大小排，第一个人员的任务序号大的放在前面，如果相同则看第二个人员的任务，以此类推。
     */

    /**
     *
     * @param list 当前排列列表
     * @param all 所有排列的集合
     * @param isVisited 记录各任务是否被访问
     * @param n 任务数量
     */
    public static void perm(List<Integer> list, List<List<Integer>> all, boolean[] isVisited, int n) {
        if (list.size() >= n) {
            all.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                list.add(i + 1);
                isVisited[i] = true;
                perm(list, all, isVisited, n);
                list.remove(list.size() - 1);
                isVisited[i] = false;
            }
        }
    }

    public static void printList(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.print(list.get(list.size() - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(scanner.nextLine());
            int[][] matrix = new int[n][n];
            String[] costs = scanner.nextLine().split(",");
            for (String cost_str: costs) {
                String[] str = cost_str.split(" ");
                int person = Integer.parseInt(str[0]);
                int task = Integer.parseInt(str[1]);
                int cost = Integer.parseInt(str[2]);
                matrix[person - 1][task - 1] = cost;
            }
            List<List<Integer>> all = new ArrayList<>();
            boolean[] isVisited = new boolean[n];
            perm(new ArrayList<>(), all, isVisited, n);
            int min = Integer.MAX_VALUE;
            List<List<Integer>> minList = new ArrayList<>();
            for (List<Integer> list : all) {
                int total = 0;
                for (int k = 0; k < n; k++) {
                    total += matrix[k][list.get(k) - 1];
                }
                if (total < min) {
                    min = total;
                    minList = new ArrayList<>();
                    minList.add(list);
                } else if (total == min) {
                    minList.add(list);
                }
            }
            minList.sort(new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    for (int j = 0; j < o1.size(); j++) {
                        if (o1.get(j) > o2.get(j)) {
                            return -1;
                        } else if (o1.get(j) < o2.get(j)) {
                            return 1;
                        }
                    }
                    return 0;
                }
            });
            for (int j = 0; j < minList.size() - 1; j++) {
                printList(minList.get(j));
                System.out.print(",");
            }
            printList(minList.get(minList.size() - 1));
            System.out.println();
        }
    }
}
