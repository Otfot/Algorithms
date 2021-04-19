package algorithms.sort;

import java.util.Objects;

/**
 * 计数排序
 * 建立一个数据范围数组，统计每一个数据值出现的次数，得到按数据值排序的结果
 * 特点：
 * 具有稳定性
 * <p>
 * 适用范围：
 * 数据范围小，但数据量比较大，排序 key 为整型的数据
 * <p>
 * 执行效率：
 * 1.需要遍历一遍数组得到数据范围 min、max  O(2n)
 * 2.需要一个数据范围的数组 Space O(max-min+1)
 * 3.需要一个与数组等大的临时数组 Space O(n)
 * 4.需要再遍历一次数组 O(n)
 * 5.拷贝一次数组
 * 总的时间效率 O(3n) = O(n)
 *
 * @author otfot
 * @date 2021/04/19
 */
public class CountingSort {

    public void sort(Integer[] arr) {

        if (Objects.isNull(arr) || arr.length <= 1) {
            return;
        }

        int min = arr[0];
        int max = arr[0];

        // 获取数组范围
        for (int i : arr) {
            if (min > i) {
                min = i;
            }
            if (max < i) {
                max = i;
            }
        }

        // 数组值相等，无需排序
        if (max == min) {
            return;
        }

        int cap = max - min + 1;
        // 计数数组
        int[] c = new int[cap];

        // 计算每个元素的个数
        for (int i : arr) {
            // 无论数据值范围是负还是正得到的都是正确的位置
            c[i - min]++;
        }

        // 依次累加
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i - 1] + c[i];
        }

        Integer[] t = new Integer[arr.length];
        // 根据排名排序
        for (int i = arr.length - 1; i >= 0; i--) {
            // 取出排名, 减 1 是因为排名从 1 开始
            int index = c[arr[i] - min] - 1;
            t[index] = arr[i];
            c[arr[i] - min]--;
        }

        // 将结果拷贝回原数组
        System.arraycopy(t, 0, arr, 0, arr.length);
    }
}
