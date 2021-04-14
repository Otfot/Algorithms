package algorithms.sort.comparison;

import algorithms.sort.AbstractSort;
import algorithms.sort.Sortable;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 希尔排序 插入排序的改进版
 * 先使用较大的步长移动数据，待最后数据变为大多数已经排好序的状态时再使用插入排序
 * <p>
 * 优化点：
 * 排序之初通过分组，使得每组数量比较少，排序后虽然每组元素逐渐增多但每组元素逐渐有序
 * 相比插入排序每次可以移动不止 1 位
 * <p>
 * 特定：
 * 原地排序算法
 * 不具有稳定性
 * <p>
 * 执行效率：
 * 希尔排序的效率与步长的选择有关
 * <p>
 * 一般步长选择 数组长度 / 2 并取半递减到 1
 * <p>
 * 使用 Seggewick 提出的（1，5，19，41..) 序列，从第 0 项开始
 * 偶数来自 9*4^i - 9*2^i + 1,奇数来自 2^(i+2) * (2^(i+2)-3) + 1
 * 可以使得最坏复杂度为 O(nlogn)，在小数组中可快于快速排序和堆排序
 * <p>
 * 步长选择参考 维基百科 https://www.wikiwand.com/en/Shellsort#/Gap%20sequences
 *
 * @author otfot
 * @date
 */
public class ShellSort<T extends Comparable<T>> extends AbstractSort<T> implements Sortable<T> {


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

    /**
     * 根据数组长度生成复杂度为 O(nlogn) 的步长序列
     *
     * @param length 数组长度
     * @return 返回步长数组
     */
    private List<Integer> computeSteps(int length) {
        List<Integer> steps = new ArrayList<>();
        int odd = 0;
        int even = 0;
        boolean flag = true;
        int step;

        do {
            if (flag) {
                step = 9 * (int) Math.pow(4, even) - 9 * (int) Math.pow(2, even) + 1;
                even++;
            } else {
                step = (int) Math.pow(2, odd + 2) * (int) (Math.pow(2, odd + 2) - 3) + 1;
                odd++;
            }

            flag = !flag;
            steps.add(step);

        } while (step < length);

        steps.sort(Comparator.reverseOrder());
        return steps;
    }

    private void sortByAsc(T[] arr) {
        List<Integer> steps = computeSteps(arr.length);
        // 遍历每一步步长
        for (Integer step : steps) {
            // 从后往前比较，第一组的位置即为 step
            for (int i = step; i < arr.length; i++) {
                // j 最小为 step 因为还需要比较 j - step , 比较两个数小于时就移前
                for (int j = i; j >= step && less(arr[j], arr[j - step]); j -= step) {
                    exchange(arr, j, j - step);
                }
            }
        }
    }

    private void sortByDesc(T[] arr) {

        List<Integer> steps = computeSteps(arr.length);

        for (Integer step : steps) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i; j >= step && more(arr[j], arr[j - step]); j -= step) {
                    exchange(arr, j, j - step);
                }
            }
        }

    }

}
