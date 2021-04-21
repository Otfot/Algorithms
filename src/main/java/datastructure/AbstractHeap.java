package datastructure;

import java.lang.reflect.Array;

/**
 * 简单抽象堆
 *
 * @author otfot
 * @date 2021/04/21
 */
public abstract class AbstractHeap<T extends Comparable<T>> {
    protected int capacity;
    protected  T[] heap;
    /**
     * 记录当前数组的实际存储长度
     */
    protected int len;

    /**
     * flag == true 是大顶堆， false 是小顶堆
     */
    protected boolean flag;

    @SuppressWarnings("unchecked")
    public AbstractHeap(int capacity, boolean flag, Class<T> clazz) {
        this.capacity = capacity;
        this.flag = flag;
        heap = (T[]) Array.newInstance(clazz, capacity);
    }

    /**
     * 向堆内添加元素
     *
     * @param e 添加元素
     * @throws Exception fail-fast 机制
     */
    public void add(T e) throws Exception {
        if (len > heap.length) {
            throw new Exception("Out of heap capacity!");
        } else {
            heap[len++] = e;
            heapify();
        }
    }

    /**
     * 取出最值元素
     * @return 当前最值
     * @throws Exception fail-fast 机制
     */
    public T poll() throws Exception {
        if (len == 0) {
            throw new Exception("The heap is empty!");
        }
        // 取出最值返回
        T top = heap[0];

        // 执行恢复堆操作
        heap[0] = heap[len - 1];
        heap[len - 1] = null;
        len--;
        shiftTop2Down(0);

        return top;
    }

    public boolean isEmpty() {
        return len == 0;
    }

    /**
     * 根据 T 的类型实现不同的更新方法, 非 public 修饰
     * @param node 更新 node 结点的值
     */
    abstract void update(T node);




    protected int parentIndex(int child) {
        return (child - 1) / 2;
    }

    protected int leftChildIndex(int parent) {
        return (parent + 1) * 2 - 1;
    }

    protected int rightChildIndex(int parent) {
        return (parent + 1) * 2;
    }

    protected void heapify() {
        int parent = parentIndex(len - 1);
        while (parent >= 0) {
            shiftTop2Down(parent);
            parent--;
        }
    }

    protected void shiftTop2Down(int cur) {

        while (cur < len) {
            T curNode = heap[cur];
            int swap = cur;


            int leftC = leftChildIndex(cur);
            int rightC = rightChildIndex(cur);

            // 大顶堆
            if (flag) {
                if (leftC < len && heap[leftC].compareTo(heap[swap]) > 0) {
                    swap = leftC;
                }

                if (rightC < len && heap[rightC].compareTo(heap[swap]) > 0) {
                    swap = rightC;
                }
            }
            // 小顶堆
            else {
                if (leftC < len && heap[leftC].compareTo(heap[swap]) < 0) {
                    swap = leftC;
                }
                if (rightC < len && heap[rightC].compareTo(heap[swap]) < 0) {
                    swap = rightC;
                }
            }
            if (swap == cur) {
                break;
            } else {
                heap[cur] = heap[swap];
                heap[swap] = curNode;
                cur = swap;
            }

        }
    }

}
