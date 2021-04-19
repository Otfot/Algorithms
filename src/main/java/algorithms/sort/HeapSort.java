package algorithms.sort;

/**
 * 堆排序
 * 需要先将数组构建成一个大顶堆，再依次将堆顶元素与最后元素交换得到一个升序数组
 * 降序数组排序则相反
 * <p>
 * 特定：
 * 原地排序算法
 * 是完全二叉树， 父结点为 i 子结点为 2*i 和 2*i + 1
 * <p>
 * 适用场景：
 * 优先队列
 * 维护集合 Top-K
 * 对顶堆求中位数
 * <p>
 * 与快排相比缺点：
 * 堆排序对元素非顺序访问，对 CPU 缓存不友好，而快排是局部有序访问
 * 堆排序比快速排序的交换次数多
 * <p>
 * 执行效率：
 * N 数组长度
 * 都要先完成堆化操作，然后排序，算法性能比较稳定 O(nlogn)
 *
 * @author otfot
 * @date 2021/04/17
 */
public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> implements Sortable<T> {


    @Override
    public void sort(T[] arr, boolean order) {

        // 判空和优化
        if (arr == null || arr.length <= 1) {
            return;
        }

        sortByOrder(arr, order);


    }

    private void sortByOrder(T[] arr, boolean order) {
        heapify(arr, order);

        int end = arr.length - 1;

        while (end > 0) {
            swap(arr, 0, end);
            end--;
            topShift2bottom(arr, 0, end, order);
        }
    }

    /**
     * 从当前结点依次判断是否需要下移
     */
    private void topShift2bottom(T[] arr, int parent, int tail, boolean order) {

        int node = parent;

        while (leftChildIndex(node) <= tail) {

            int swap = node;
            int lChild = leftChildIndex(node);

            if (order) {

                // 如果左边孩子比父结点大，更新要交换结点
                if (more(arr[lChild], arr[swap])) {
                    swap = lChild;
                }
                // 如果有右边孩子，且右边孩子比当前父结点大，更新交换结点
                if (lChild + 1 <= tail && more(arr[lChild + 1], arr[swap])) {
                    swap = lChild + 1;
                }


            } else {
                // 如果左边孩子比父结点小，更新要交换结点
                if (less(arr[lChild], arr[swap])) {
                    swap = lChild;
                }
                // 如果有右边孩子，且右边孩子比当前父结点小，更新交换结点
                if (lChild + 1 <= tail && less(arr[lChild + 1], arr[swap])) {
                    swap = lChild + 1;
                }


            }
            // 需要交换
            if (swap != node) {
                swap(arr, node, swap);
                // 父结点交换到子结点后，更新 node 值，利用循环判断此时子结点（父结点）是否满足大顶堆要求
                node = swap;
            } else {
                return;
            }

        }

    }

    /**
     * 计算左子结点位置， 数组下标从 0 开始
     *
     * @param parent 父结点位置
     * @return 返回左孩子位置
     */
    private int leftChildIndex(int parent) {
        return 2 * parent + 1;
    }

    /**
     * 计算右子结点位置
     *
     * @param parent 父结点位置
     * @return 返回右孩子位置
     */
    private int rightChildIndex(int parent) {
        return 2 * parent + 2;
    }

    /**
     * 计算父结点位置
     */
    private int parentIndex(int child) {
        return (int) Math.floor((child - 1) / 2.0);
    }

    /**
     * 构建大顶堆或小顶堆
     *
     * @param arr 待堆化数组
     */
    private void heapify(T[] arr, boolean order) {
        int tail = arr.length - 1;

        int parent = parentIndex(tail);

        while (parent >= 0) {
            topShift2bottom(arr, parent, tail, order);
            parent--;
        }
    }

}

