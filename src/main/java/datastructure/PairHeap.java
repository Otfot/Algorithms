package datastructure;

import java.util.LinkedList;

/**
 * 元素类型为 Pair 的堆
 *
 * @author otfot
 * @date 2021/04/21
 */
public class PairHeap<K, V extends Comparable<V>> extends AbstractHeap<Pair<K, V>> {

    @SuppressWarnings("unchecked")
    public PairHeap(int capacity, boolean flag) throws ClassNotFoundException {
        super(capacity, flag, (Class<Pair<K, V>>) Class.forName("datastructure.Pair"));
    }

    @Override
    public void update(Pair<K, V> node) {

        // 简单实现
//        for (int i = 0; i < len; i++) {
//            if (heap[i].getKey().equals(node.getKey())) {
//                heap[i] = node;
//                break;
//            }
//        }

        // 采用深度优先搜索查找更新
        dfsFind(node, 0);

    }

    public void dfsFind(Pair<K, V> node, int cur) {

        if (cur >= len) {
            return;
        }

        dfsFind(node, leftChildIndex(cur));

        if (heap[cur].getKey().equals(node.getKey())) {
            heap[cur] = node;
            return;
        }

        dfsFind(node, rightChildIndex(cur));
    }


}
