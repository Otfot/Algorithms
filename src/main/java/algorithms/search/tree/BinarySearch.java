package algorithms.search.tree;

import datastructure.BinarySearchTree;
import datastructure.Node;

import java.util.Objects;

/**
 * 二分搜索
 *
 * @author otfot
 * @date 2021/04/23
 */
public class BinarySearch {


    /**
     * 通过二叉搜索树 查找第 K 大元素
     *
     * @param tree 待搜索二叉查找树
     * @param k    第 K 大元素
     * @param <T>  实现了 Comparable 接口的泛型参数
     * @return 返回元素
     */
    public <T extends Comparable<T>> T search(BinarySearchTree<T> tree, int k) {

        if (Objects.isNull(tree)) {
            return null;
        }

        Node<T> node = tree.getRoot();

        return find(node, k);

    }

    private <T extends Comparable<T>> T find(Node<T> node, int k) {
        int count = 0;
        if (Objects.nonNull(node.getRight())) {
            count = node.getRight().getCount();
        }
        if (count + 1 == k) {
            return node.getData();
        } else if (count + 1 < k) {
            return find(node.getLeft(), k - count - 1);
        } else {
            return find(node.getRight(), k);
        }


    }
}
