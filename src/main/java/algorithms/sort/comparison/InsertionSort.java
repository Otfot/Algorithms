package algorithms.sort.comparison;


import algorithms.sort.AbstractSort;
import algorithms.sort.Sortable;


/**
 * 插入排序
 * 将剩余元素中的一个插入到前面已有序元素中的合适位置
 * 特点：
 * 原地排序算法
 * 具有稳定性
 * 逆序度即为需要移动的次数
 * <p>
 * 适用场景：
 * 非随机数组小规模数据
 * <p>
 * 执行效率：
 * N 数组长度
 * <p>
 * 最好情况 数据已经有序
 * 只遍历外循环，需要 N - 1 次比较，0 次交换， O((N-1)*1) = O(n)
 * <p>
 * 平均情况 数据部分有序
 * 内循环平均每次需要比较 i/2 次，交换 i/2 次，大概 (n^2)/4 次比较和交换， O(n^2 / 2)
 * <p>
 * 最坏情况 数据完全逆序
 * 每次内循环都要遍历完，比较 i 次，交换 i 次，总共比较和交换 (1 + (n-1))*(n-1) 次，O(n^2)
 *
 * @author otfot
 * @date 2021/04/13
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> implements Sortable<T> {

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
        // 外循环需要循环 N 次，N 为数组长度
        for (int i = 1; i < arr.length; i++) {

            // 内循环 less 每次最多需要比较 i 次，最少需要比较 1 次
            for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {

                // 交换次数最多 i 次，最少交换 0 次
                swap(arr, j, j - 1);

            }
        }
    }

    private void sortByDesc(T[] arr) {

        for (int i = 1; i < arr.length; i++) {

            // 缓存当前值，替换 exchange 方法
            T val = arr[i];
            int j = i;

            for (; j > 0 && more(val, arr[j - 1]); j--) {
                // 推荐使用这种数据移动方式，减少时间
                arr[j] = arr[j - 1];
            }
            arr[j] = val;
        }
    }


}

