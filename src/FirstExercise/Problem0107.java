package FirstExercise;

import java.util.Arrays;
import java.util.Scanner;

public class Problem0107 {
    /*
    Description
    Given a Complete Binary tree, print the level order traversal in sorted order.

    Input
    The first line of the input contains integer T denoting the number of test cases. For each test case,
    the first line takes an integer n denoting the size of array i.e number of nodes followed by n-space separated integers denoting the nodes of the tree in level order fashion.(1<=T<=100；1<=n<=10^5）

    Output
    For each test case, the output is the level order sorted tree.
    ( Note: For every level, we only print distinct elements.)
     */

    //实现完全二叉树的层级遍历
    public static void levelOrder(int[] nums) {
        //对于完全二叉树，只有每层节点填满后才会填下一层节点
        //即除去最后一层外，每层节点个数均为2^h，其中根节点h=0，余下的节点全部填进最后一层
        int n = nums.length, nodeCnt = 1, index = 0;
        while (n - nodeCnt >= 0) {
            n -= nodeCnt;
            int[] nodes = new int[nodeCnt];
            for (int i = 0; i < nodeCnt; i++) {
                nodes[i] = nums[index++];
            }
            print(nodes);
            nodeCnt <<= 1;
        }
        if (n > 0) {
            int[] nodes = new int[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = nums[index++];
            }
            print(nodes);
        }
    }

    //对每层节点进行排序去重输出
    public static void print(int[] nodes) {
        Arrays.sort(nodes);
        int i = 0;
        String output = "";
        while (i < nodes.length) {
            output += nodes[i] + " ";
            int tem = nodes[i];
            while (i < nodes.length && nodes[i] == tem) i++; //去重
        }
        output = output.substring(0, output.length() - 1);
        System.out.println(output);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int T = scanner.nextInt();
            for (int i = 0; i < T; i++) {
                int n = scanner.nextInt();
                int[] nums = new int[n];
                for (int j = 0; j < n; j++) {
                    nums[j] = scanner.nextInt();
                }
                levelOrder(nums);
            }
        }
    }
}
