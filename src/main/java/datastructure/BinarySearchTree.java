package datastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 二叉搜索树
 *
 * @author otfot
 * @date 2021/04/23
 */
public class BinarySearchTree<T extends Comparable<T>> {

    @Getter
    private Node<T> root;

    public BinarySearchTree(T[] arr) {
        for (T t : arr) {
            insert(t);
        }
    }

    public void insert(T data) {
        if (Objects.isNull(root)) {
            root = new Node<>(data);
        } else {
            Node<T> cur = root;

            while (true) {

                Node<T> left = cur.getLeft();
                Node<T> right = cur.getRight();

                if (cur.getData().compareTo(data) > 0) {
                    if (Objects.isNull(left)) {
                        Node<T> node = new Node<>(data);
                        cur.setLeft(node);
                        cur.setCount(cur.getCount() + 1);
                        break;
                    } else {
                        cur.setCount(cur.getCount() + 1);
                        cur = left;
                    }
                } else {
                    if (Objects.isNull(right)) {
                        Node<T> node = new Node<>(data);
                        cur.setRight(node);
                        cur.setCount(cur.getCount() + 1);
                        break;
                    } else {
                        cur.setCount(cur.getCount() + 1);
                        cur = right;
                    }
                }

            }

        }
    }

    public void delete(T data) {
        Node<T> cur = root;
        Node<T> prev;

        while (Objects.nonNull(cur)) {
            int state = cur.getData().compareTo(data);
            if (state == 0) {
                break;
            }
            if (state > 0) {
                cur = cur.getLeft();
            } else {
                cur = cur.getRight();
            }
        }

        // 该树没有需要删除的值
        if (Objects.isNull(cur)) {
            return;
        }

        // 一定有删除值，重新执行查找过程 更新每个结点的子结点数
        cur = root;
        prev = null;

        while (Objects.nonNull(cur)) {
            int state = cur.getData().compareTo(data);
            if (state == 0) {
                break;
            }

            prev = cur;
            prev.setCount(prev.getCount() - 1);
            if (state > 0) {
                cur = cur.getLeft();
            } else {
                cur = cur.getRight();
            }

        }

        // 删除结点有两个子结点
        if (Objects.nonNull(cur.getRight()) && Objects.nonNull(cur.getLeft())) {
            // 找到当前结点的中序后继结点
            Node<T> rMin = cur.getRight();
            Node<T> rMinPrev = cur;
            // 找后继时依次减 1
            rMinPrev.setCount(rMinPrev.getCount() - 1);
            while (Objects.nonNull(rMin.getLeft())) {
                rMinPrev = rMin;
                rMin = rMin.getLeft();
                // 在最后一次时可能多减，下面补上
                rMinPrev.setCount(rMinPrev.getCount() - 1);

            }

            // 替换数据
            cur.setData(rMin.getData());
            // 替换方便后续统一删除 ， 此时当前节点变为了只有一个或一个都没有的结点
            cur = rMin;
            prev = rMinPrev;


        }

        // 删除节点 走到当前步骤时 当前结点只可能是 叶子结点或者 存在一个子结点
        Node<T> child;
        if (Objects.nonNull(cur.getLeft())) {
            child = cur.getLeft();
        } else if (Objects.nonNull(cur.getRight())) {
            child = cur.getRight();
        } else {
            child = null;
        }

        // 删除的是根结点
        if (Objects.isNull(prev)) {
            root = child;
        } else {
            if (prev.getLeft() == cur) {
                prev.setLeft(child);

            } else {
                prev.setRight(child);

            }

        }

    }

}




