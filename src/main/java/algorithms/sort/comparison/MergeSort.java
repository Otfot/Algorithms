package algorithms.sort.comparison;

import algorithms.sort.AbstractSort;
import algorithms.sort.Sortable;


/**
 * 归并排序
 * 递归的将一个数组分成两半，分别排序后在将两个有序的数组归并成一个更大的有序数组。
 * <p>
 * 特点：
 * 非原地排序算法,需要额外的空间 N
 * 具有稳定性
 * 基于分治思想
 * 优化：
 * 可以在小数组中使用插入排序
 *
 * <p>
 * <p>
 * 执行效率：
 * N 数组长度
 * <p>
 * 最好情况，平均情况，最坏情况一致，都需要递归划分成最小数组，再排序归并, O(nlogn)
 * 归并树中（树高为 n），每层有 2^k 个子数组，数组长度为 2^n, 每次需要比较的次数 2^k * 2^(n-k) = 2^n, 一共需要 n2^n = NlogN
 * 根据不同的有序情况，比较次数不变，移动次数根据算法的实现（是否需要额外空间）有所不同
 *
 * @author otfot
 * @date 2021/04/15
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> implements Sortable<T> {

    /**
     * 小数组时，一次性分配数组可以减少创建数组的开销
     */
    private T[] aux;

    @Override
    @SuppressWarnings("unchecked")
    public void sort(T[] arr, boolean order) {

        // 判空和优化
        if (arr == null || arr.length <= 1) {
            return;
        }

        if (order) {
            // 是否应该是 Comparable 类型
            aux = (T[]) new Comparable[arr.length];
            sortByAsc(arr, 0, arr.length - 1);
        } else {
            sortByDesc(arr, 0, arr.length - 1);
        }

        // sortBottom2Top(arr, order);
    }


    private void sortByAsc(T[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sortByAsc(arr, lo, mid);
        sortByAsc(arr, mid + 1, hi);

        mergeByAsc(arr, lo, mid, hi);
    }

    private void mergeByAsc(T[] arr, int lo, int mid, int hi) {

        int i = lo;
        int j = mid + 1;

        // 复制数组元素到临时数组 不手动 for 循环复制数组
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

        // 需要比较的次数
        for (int k = lo; k <= hi; k++) {

            // 前半数组已经走完，直接放入后半数组元素
            if (i > mid) {
                arr[k] = aux[j++];
            }
            // 后半数组已经走完，直接放入前半数组元素
            else if (j > hi) {
                arr[k] = aux[i++];
            }
            // 前半数组元素小于等于后半数组,保证算法稳定性
            else if (more(aux[j], aux[i])) {
                arr[k] = aux[i++];
            }
            // 前半数组元素大于后半数组
            else {
                arr[k] = aux[j++];
            }

        }

    }

    private void sortByDesc(T[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sortByDesc(arr, lo, mid);
        sortByDesc(arr, mid + 1, hi);

        mergeByDesc(arr, lo, mid, hi);
    }

    @SuppressWarnings("unchecked")
    private void mergeByDesc(T[] arr, int lo, int mid, int hi) {
        // 创建临时数组
        int n = hi - lo + 1;
        T[] temp = (T[]) new Comparable[n];

        int i = lo;
        int j = mid + 1;
        int ind = 0;

        // 共同遍历，直到一方走完
        while (i <= mid && j <= hi) {
            if (more(arr[i], arr[j])) {
                temp[ind++] = arr[i++];
            } else {
                temp[ind++] = arr[j++];
            }
        }

        // 遍历剩下的一方
        while (i <= mid) {
            temp[ind++] = arr[i++];
        }

        while (j <= hi) {
            temp[ind++] = arr[j++];
        }

        // 将临时数组元素拷贝到原数组, 不手动拷贝数组
        System.arraycopy(temp, 0, arr, lo, temp.length);
    }

    /**
     * 自底向上的方式归并数组
     * @param arr 待归并数组
     * @param order 排序方式
     */
    @SuppressWarnings("unchecked")
    private void sortBottom2Top(T[] arr, boolean order) {
        // 顺序排列时采用的是一次性分配数组方式
        if (order) {
            aux = (T[]) new Comparable[arr.length];
        }
        // 归并数组的大小，从 1 到 整个数组长度，归并后下次待归并长度增加一倍
        for (int size = 1; size < arr.length; size += size) {

            // 从 0 开始，依次归并两个相同长度的子数组
            for (int lo = 0; lo < arr.length - size; lo += size + size) {

                // 防止最后一个子数组长度达不到，如果最后不满足两个数组则将最后剩余的元素归并
                int hi = Math.min(lo + size + size - 1, arr.length - 1);


                if (order) {
                    // 要归并的数组区间
                    mergeByAsc(arr, lo, lo + size - 1, hi);
                } else {
                    mergeByDesc(arr, lo, lo + size - 1, hi);
                }

            }
        }
    }



}
