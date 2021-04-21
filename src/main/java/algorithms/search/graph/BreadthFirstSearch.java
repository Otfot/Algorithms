package algorithms.search.graph;

import datastructure.Pair;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 广度优先搜索算法
 * <p>
 * distance 距离数组，记录起点到当前的位置最短路线，初始时也可以当标记数组判断当前是否有访问到当前结点
 * prev 归程数组，记录到当前位置最短路线的前一个位置
 * queue 遍历队列，依次遍历可能性
 *
 * @author otfot
 * @date 2021/04/20
 */
public class BreadthFirstSearch {
    private int[][] distance;
    private Pair<Integer, Integer>[][] prev;


    /**
     * 基于二维数组的迷宫求解问题
     *
     * @param arr 迷宫数组
     * @param row 迷宫的行数
     * @param col 迷宫的列数
     */
    @SuppressWarnings("unchecked")
    public void search(int[][] arr, int row, int col) {
        int posRow, posCol;

        // 距离数组，记录从出发点到当前位置的最少步,
        distance = new int[row][col];
        // -1 代表没有走过
        for (int[] intArr : distance) {
            Arrays.fill(intArr, -1);
        }
        // 归程数组，记录当前位置是从哪个位置走来的
        prev = (Pair<Integer, Integer>[][]) Array.newInstance(Pair.class, row, col);


        Queue<Pair<Integer, Integer>> q = new LinkedList<>();

        // 起点入队
        q.offer(new Pair<>(0, 0));
        distance[0][0] = 0;

        while (!q.isEmpty()) {
            Pair<Integer, Integer> curPos = q.poll();
            posRow = curPos.getKey();
            posCol = curPos.getValue();

            // 向下没有超出边界且没有阻碍时，走一步
            if (posRow < row - 1 && arr[posRow + 1][posCol] != -1) {
                // 如果当前没有走过该点，或者当前到该点的步长小于原来的，则更新
                if (distance[posRow + 1][posCol] == -1 || distance[posRow][posCol] + 1 < distance[posRow + 1][posCol]) {
                    distance[posRow + 1][posCol] = distance[posRow][posCol] + 1;
                    prev[posRow + 1][posCol] = curPos;
                    q.offer(new Pair<>(posRow + 1, posCol));
                }
            }

            // 向上没有超出边界且没有阻碍时，走一步
            if (posRow > 0 && arr[posRow - 1][posCol] != -1) {
                if (distance[posRow - 1][posCol] == -1 || distance[posRow][posCol] + 1 < distance[posRow - 1][posCol]) {
                    distance[posRow - 1][posCol] = distance[posRow][posCol] + 1;
                    prev[posRow - 1][posCol] = curPos;
                    q.offer(new Pair<>(posRow - 1, posCol));
                }
            }

            // 向右没有超出边界且没有阻碍时，走一步
            if (posCol < col - 1 && arr[posRow][posCol + 1] != -1) {
                if (distance[posRow][posCol + 1] == -1 || distance[posRow][posCol] + 1 < distance[posRow][posCol + 1]) {
                    distance[posRow][posCol + 1] = distance[posRow][posCol] + 1;
                    prev[posRow][posCol + 1] = curPos;
                    q.offer(new Pair<>(posRow, posCol + 1));
                }
            }

            // 向左没有超出边界且没有阻碍时，走一步
            if (posCol > 0 && arr[posRow][posCol - 1] != -1) {
                if (distance[posRow][posCol - 1] == -1 || distance[posRow][posCol] + 1 < distance[posRow][posCol - 1]) {
                    distance[posRow][posCol - 1] = distance[posRow][posCol] + 1;
                    prev[posRow][posCol - 1] = curPos;
                    q.offer(new Pair<>(posRow, posCol - 1));
                }
            }

        }

        displayStep(arr, row, col);

    }

    /**
     * 可视化输出路线查找
     *
     * @param arr 原始数组
     * @param row 数组的行数
     * @param col 数组的列数
     */
    private void displayStep(int[][] arr, int row, int col) {
        System.out.println("The array source state: ");
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.printf("%+3d", anInt);
            }
            System.out.println();
        }

        Pair<Integer, Integer> end = prev[row - 1][col - 1];
        if (Objects.isNull(end)) {
            System.out.println("Don't find the exit route！！");
            return;
        }
        dfsOutput(arr, row - 1, col - 1);

        System.out.println("The array modified state: ");

        for (int[] ints : arr) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    System.out.printf("%3c", 'X');
                } else {
                    System.out.printf("%+3d", anInt);
                }

            }
            System.out.println();
        }

    }

    /**
     * 从出口位置依次访问前一个位置，得到路线
     *
     * @param arr 原始数组
     * @param x   访问的行
     * @param y   访问的列
     */
    private void dfsOutput(int[][] arr, int x, int y) {
        arr[x][y] = 1;
        if (x == 0 && y == 0) {
            return;
        }
        Pair<Integer, Integer> temp = prev[x][y];
        dfsOutput(arr, temp.getKey(), temp.getValue());
    }


}
