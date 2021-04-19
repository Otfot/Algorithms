package algorithms.sort;

import java.util.Objects;

/**
 * 基数排序
 * 先判断总的排序次数，然后在每一次排序时使用桶排序或计数排序
 *
 * 执行效率：
 * 1.找到元素的最大值或最大长度，得出排序次数
 * 2.对每个元素的当前排序位使用计数排序 (一定要从后往前计算最后的顺序）
 *
 *
 * @author otfot
 * @date 2021/04/19
 */
public class RadixSort {

    public void sort(Integer[] arr) {
        if (Objects.isNull(arr) || arr.length < 2) {
            return;
        }

        // 找到最大值，判断需要排几次
        int max = arr[0];
        for (Integer i : arr) {
            if (max < i) {
                max = i;
            }
        }

        // 依次对每一位排序
        for (int exp = 1; max / exp > 0; exp = exp * 10) {
            countingSort(arr, exp);
        }

    }

    private void countingSort(Integer[] arr, int exp) {
        // 0 - 9 10 个数
        int[] c = new int[10];

        // 记录每一位整数的数量
        for (Integer i : arr) {
            c[(i / exp) % 10]++;
        }

        // 依次累加
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i - 1] + c[i];
        }

        // 按顺序复制到另一个数组中
        Integer[] t = new Integer[arr.length];
        int end = arr.length - 1;
        //必须倒序访问，保证稳定性，即在原数组后面的还是在后面
        for (int i = arr.length - 1; i >=0 ; i--) {
            t[c[(arr[i] / exp) % 10] - 1] = arr[i];
            c[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(t, 0, arr, 0, arr.length);


    }


}
