package algorithms.sort;


import java.util.Objects;

/**
 * 桶排序
 * 将一组数据，平均划分到多个桶中，在每个桶中执行排序操作
 *
 * 适用场景：
 * 外部排序
 *
 * 执行效率
 * 1.遍历一次找到最大、最小值 O(2n)
 * 2.根据最大、最小值计算桶的个数 k 划分为多个桶 k  Space O(n)
 * 3.遍历一遍，将元素放入多个桶 O(n)
 * 4.记录放入每个桶的的数量 Space O(k)
 * 5.对每个桶执行快排操作 O(k * (n/k)log(n/k)) = O(nlog(n/k))
 * 6.依次遍历桶放入原数组 O(n)
 *
 * @author otfot
 * @date 2021/04/19
 */
public class BucketSort {

    public void sort(Integer[] arr, int bucketSize) {

        if (Objects.isNull(arr) || arr.length < 2) {
            return;
        }

        int min = arr[0];
        int max = arr[1];
        for (Integer i : arr) {
            if (min > i) {
                min = i;
            }
            // 减少判断次数
            else if (max < i) {
                max = i;
            }
        }

        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        // 每个桶存储的数量
        int[] indexArr = new int[bucketCount];

        // 将数组中的值分配到各个桶中
        for (Integer i : arr) {
            // 获取值对应的桶索引
            int bucketIndex = (i - min) / bucketCount;

            int index = indexArr[bucketIndex];
            // 如果当前桶容量已满，就扩容
            if (index == buckets[bucketIndex].length) {
                expansion(buckets, bucketIndex);
            }

            buckets[bucketIndex][index] = i;
            indexArr[bucketIndex]++;

        }

        int a = 0;
        // 对每个桶元素排序，并最后输出
        for (int i = 0; i < indexArr.length; i++) {
            if (indexArr[i] == 0) {
                continue;
            }
            quickSort(buckets[i], 0, indexArr[i] - 1);

            for (int j = 0; j < indexArr[i]; j++) {
                arr[a++] = buckets[i][j];
            }
        }

    }

    private void quickSort(int[] arr, int start, int end) {
        if (end < start) {
            return;
        }

        int middle = partition(arr, start, end);
        quickSort(arr, start, middle - 1);
        quickSort(arr, middle + 1, end);

    }

    private int partition(int[] arr, int start, int end) {
        int pivot = arr[end];

        int i = start;
        for (int j = start; j <= end-1; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, end);
        return i;

    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 执行扩容操作，扩容大小为原来大小的 1.5 倍
     *
     * @param buckets 包含待扩容的一维数组的二维数组
     * @param index   待扩容的一维数组的索引
     */
    private void expansion(int[][] buckets, int index) {
        int len = buckets[index].length;
        int[] e = new int[(int) (len * 1.5)];
        int j = 0;
        for (int i : buckets[index]) {
            e[j++] = i;
        }
        buckets[index] = e;
    }
}
