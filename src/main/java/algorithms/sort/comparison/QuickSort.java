package algorithms.sort.comparison;

import algorithms.sort.AbstractSort;
import algorithms.sort.Sortable;

/**
 * 快速排序
 * 从要排序的数组先选择一个分割点 P，将大于 P 的元素放到 P 的后面，小于 P 的元素放到 P 的前面，P 放到中间，
 * 然后基于分治思想，再排序 P 前面的数据和 P 后面的数据，直到分割的数据为 1
 * <p>
 * 特点：
 * 原地排序算法
 * 不具有稳定性
 * <p>
 * 优化：
 * 减少调用函数次数
 * 基准值选取
 * partition 双指针
 * <p>
 * 适用场景：
 * 查找第 K 大元素，每次分割会筛选掉一般元素
 * <p>
 * 执行效率：
 * N 数组长度
 * <p>
 * 最好情况，每次选择的分割点都能够平均的分割 O(nlogn)
 * <p>
 * 平均情况，分割有好有坏 O(nlogn)
 * <p>
 * 最坏情况，每次选择的分割点都是最大的或最小的 O(n^2)，极少出现
 *
 * @author otfot
 * @date 2021/04/16
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> implements Sortable<T> {


    @Override
    public void sort(T[] arr, boolean order) {

        // 判空和优化
        if (arr == null || arr.length <= 1) {
            return;
        }

        if (order) {
            sortByAsc(arr, 0, arr.length - 1);
        } else {
            sortByDesc(arr, 0, arr.length - 1);
        }
    }


    private void sortByAsc(T[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int middle = partitionByAsc(arr, start, end);

        sortByAsc(arr, start, middle - 1);
        sortByAsc(arr, middle + 1, end);
    }

    private int partitionByAsc(T[] arr, int start, int end) {

        // 选择最后一个为分割点
        T pivot = arr[end];


        int i = start;

        // 从前向后依次遍历，将小于 pivot 的移动到前面区，则后面的就是大于 pivot 的
        for (int j = start; j <= end - 1; j++) {
            if (less(arr[j], pivot)) {
                swap(arr, j, i);
                i++;
            }
        }
        swap(arr, i, end);

        return i;

    }

    private void sortByDesc(T[] arr, int start, int end) {


        while (start < end) {

            int middle = partitionByDesc(arr, start, end);

            sortByDesc(arr, middle + 1, end);

            // 减少了 sort 的函数调用，但实际工作的 partition 函数调用次数并没有减少。
            end = middle - 1;
        }

    }

    private void optimizationPivot(T[] arr, int start, int end) {
        // 基准值优化策略，三点取中法
        int mid = start + (end - start) / 2;

        if (more(arr[start], arr[mid])) {
            swap(arr, start, mid);
        }
        if (more(arr[start], arr[end])) {
            swap(arr, start, end);
        }
        if (more(arr[mid], arr[end])) {
            swap(arr, mid, end);
        }
        swap(arr, start, mid);
    }

    private int partitionByDesc(T[] arr, int start, int end) {

        optimizationPivot(arr, start, end);

        T pivot = arr[start];

        // 使用双指针遍历，优化 partition 操作
        int i = start;
        int j = end + 1;

        while (true) {

            // 循环过后找到 小于或等于 pivot 位置
            while (more(arr[++i], pivot)) {
                if (i == end) {
                    break;
                }
            }

            // 循环过后，找到 大于或等于 pivot 的位置
            while (more(pivot, arr[--j])) {
                if (j == start) {
                    break;
                }
            }

            // 如果 i = j 则找到了一个与 pivot 相等的位置
            // 如果 i > j 则此时 j 的位置后面都小于 pivot，前面都大于 pivot
            if (i >= j) {
                break;
            }

            // 正常情况下交换两个的位置
            swap(arr, i, j);
        }
        // 当 i >= j 时交换 pivot 与找到的中间位
        swap(arr, start, j);

        return j;
    }


}
