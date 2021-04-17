package algorithms.sort.comparison;

import algorithms.sort.AbstractSort;
import algorithms.sort.Sortable;

/**
 * 冒泡排序
 * 从头到尾依次对两个相邻元素比较，符合条件就交换，每次冒泡至少有一个元素移动到最终的位置
 * <p>
 * 特点：
 * 原地排序算法
 * 具有稳定性
 * 逆序度即为需要移动的次数
 * <p>
 * 适用场景：
 * 非随机数组 小规模数据
 * 相同小数据量下一般使用插入排序
 * <p>
 * 执行效率：
 * N 数组长度
 * 最好情况 数据已经有序
 * 只遍历一次外循环，内循环比较 N-1 次，0 次交换 O(N-1) = O(n)
 * <p>
 * 平均情况 数据部分有序
 * 最少逆序度为 0，最多为 n*(n-1) / 2，平均为 n*(n-1)/4，比较比交换操作多，所以最多 O(n^2)
 * <p>
 * 最坏情况 数据完全逆序
 * 每次内循环都要遍历完，比较 i 次，交换 i 次, 总共比较和交换 (1 + (n-1))*(n-1) 次，O(n^2)
 *
 * @author otfot
 * @date 2021/04/14
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSort<T> implements Sortable<T> {

    @Override
    public void sort(T[] arr, boolean order) {
        // 判空和优化
        if (arr == null || arr.length <= 1) {
            return;
        }

        if (order) {
            sortByAsc(arr);
        } else {
            sortByDesc(arr);
        }
    }

    private void sortByAsc(T[] arr) {
        // 外循环遍历数组长度
        for (int i = arr.length - 1; i >= 0; i--) {
            // 优化，没有改动时停止
            boolean flag = false;

            //内循环每次遍历长度少 1
            for (int j = 0; j < i; j++) {
                if (more(arr[j], arr[j + 1])) {
                    flag = true;swap(arr, j, j + 1);
                }

            }
            if (!flag) {
                break;
            }
        }
    }

    private void sortByDesc(T[] arr) {

        // 正常的外层 for 循环
        for (int i = 0; i < arr.length; i++) {

            boolean flag = false;

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (less(arr[j], arr[j + 1])) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
    }


}
