package algorithms.sort.comparison;

import algorithms.sort.AbstractSort;
import algorithms.sort.Sortable;

/**
 * 选择排序
 * 从剩余元素中找到最小或最大的一个放入到已排序元素之后
 *
 * 特点：
 * 原地排序算法
 * 不具有稳定性
 *
 * 适用场景：
 * 理论上，相同小数据量下一般使用插入排序
 *
 * 执行效率：
 * N 数组长度
 *
 * 最好情况，平均情况，最坏情况一致，都需要完全遍历剩下的元素， O(n^2)
 * 移动次数为 N-1 次，比较次数为 （1+(n-1))*(n-1)/2
 *
 * @author otfot
 * @date 2021/04/14
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> implements Sortable<T> {


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

        for (int i = 0; i < arr.length; i++) {

            T val = arr[i];
            T min = val;
            int index = i;

            for (int j = i; j < arr.length; j++) {
                if (more(min, arr[j])) {
                    min = arr[j];
                    index = j;
                }
            }

            arr[i] = min;
            arr[index] = val;
        }
    }

    private void sortByDesc(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 减少多余变量 index
            int max = i;

            for (int j = i; j < arr.length; j++) {
                if (less(arr[max], arr[j])) {
                    max = j;
                }
            }

            T val = arr[i];
            arr[i] = arr[max];
            arr[max] = val;
        }
    }
}
