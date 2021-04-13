package algorithms.sort;


/**
 * 排序接口，提供一个 sort 方法，实现该接口即拥有排序能力
 *
 * @author otfot
 * @date 2021/04/13
 */
public interface Sortable<T extends Comparable<T>> {

    /**
     * 执行具体的排序算法
     *
     * @param arr 待排序数组
     * @param order 排序方式
     */
    void sort(T[] arr, boolean order);
}
