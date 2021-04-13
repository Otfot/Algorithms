package algorithms.sort;


/**
 * 排序抽象类
 * 提供排序需要的通用方法
 *
 * @author otfot
 * @date 2021/04/13
 */
public class AbstractSort<T extends Comparable<T>> {

    /**
     * 比较 a、b 的大小
     *
     * @param a 待比较元素
     * @param b 待比较元素
     * @return a < b 是返回 true，反之返回 false
     */
    public boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 交换两个索引的元素
     *
     * @param arr 要操作的数组
     * @param i   要交换的数组索引
     * @param j   要交换的数组索引
     */
    public void exchange(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
