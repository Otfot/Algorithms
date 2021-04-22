package algorithms.search.graph;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * 深度优先搜索
 *
 * @author otfot
 * @date 2021/04/22
 */
public class DepthFirstSearch<T extends Comparable<T>> {

    /**
     * 对输入数组，求取出 count 个元素的全排列
     *
     * @param arr   待排列数组
     * @param count 要排列元素的个数
     */
    @SuppressWarnings("unchecked")
    public void search(T[] arr, Class<T> clazz, int count) {

        int len = arr.length;

        T[] dis = (T[]) Array.newInstance(clazz, count);

        boolean[] flag = new boolean[len];

        dfs(dis, arr, flag, 0, count - 1);
    }

    private void dfs(T[] dis, T[] arr, boolean[] flag, int depth, int count) {
        // 求出一个解
        if (depth > count) {
            // 输出解
            for (T di : dis) {
                System.out.print(di);
            }
            System.out.println();
            return;
        }


        // 依次遍历所有的可选元素
        for (int i = 0; i < arr.length; i++) {
            // 没有选择过
            if (!flag[i]) {

                flag[i] = true;
                dis[depth] = arr[i];
                dfs(dis, arr, flag, depth + 1, count);
                // 重置状态
                flag[i] = false;
            }
        }
    }

    /**
     * 对输入数组，求取出 count 个元素的组合排列
     *
     * @param arr   待排列数组
     * @param clazz 对应泛型 class 对象
     * @param count 取出的排列个数
     */
    @SuppressWarnings("unchecked")
    public void search2(T[] arr, Class<T> clazz, int count) {
        int len = arr.length;

        T[] dis = (T[]) Array.newInstance(clazz, count);

        boolean[] flag = new boolean[len];

        dfs2(dis, arr, flag, 0, count - 1);
    }

    private void dfs2(T[] dis, T[] arr, boolean[] flag, int depth, int count) {
        // 求出一个解
        if (depth > count) {
            // 输出解
            for (T di : dis) {
                System.out.print(di);
            }
            System.out.println();
            return;
        }


        T prev = null;
        if (depth > 0) {
            prev = dis[depth - 1];
        }
        for (int i = 0; i < arr.length; i++) {
            // 没有选择过
            if (!flag[i]) {
                if (depth == 0) {
                    flag[i] = true;

                    dis[depth] = arr[i];
                    dfs2(dis, arr, flag, depth + 1, count);
                    // 重置状态
                    flag[i] = false;
                } else if (Objects.nonNull(prev) && prev.compareTo(arr[i]) < 0) {
                    flag[i] = true;

                    dis[depth] = arr[i];
                    dfs2(dis, arr, flag, depth + 1, count);
                    // 重置状态
                    flag[i] = false;
                }
            }
        }
    }

    /**
     * 八皇后问题
     */
    public void search3() {

        boolean[] col = new boolean[8];
        int[] row = new int[8];
        boolean[] leftDiag = new boolean[15];
        boolean[] rightDiag = new boolean[15];
        dfs3(0, row, col, leftDiag, rightDiag);
    }

    /**
     * 根据八皇后限制条件 在搜索时剪枝
     *
     * @param depth     放置第几个皇后
     * @param col       判断该列是否能够放置
     * @param leftDiag  左对角线上是否有皇后, 规律是 横纵坐标和相同
     * @param rightDiag 右对角线上是否有皇后， 规律是 横纵坐标差相同
     */
    private void dfs3(int depth, int[] row, boolean[] col, boolean[] leftDiag, boolean[] rightDiag) {

        if (depth > 7) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (j == row[i]) {
                        System.out.print("* ");
                    } else {
                        System.out.print("0 ");
                    }
                }
                System.out.println();
            }
            System.out.println("=====================");
        }


        // 判断一行上面那些列可以放置
        for (int i = 0; i < 8; i++) {

            // 可以放置
            if (!col[i] && !leftDiag[depth + i] && !rightDiag[depth - i + 7]) {
                col[i] = leftDiag[depth + i] = rightDiag[depth - i + 7] = true;
                row[depth] = i;
                dfs3(depth + 1, row, col, leftDiag, rightDiag);
                row[depth] = -1;
                col[i] = col[i] = leftDiag[depth + i] = rightDiag[depth - i + 7] = false;
            }
        }

    }

}
