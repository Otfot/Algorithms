package datastructure;

import lombok.Getter;

import java.util.Objects;

/**
 * 二叉平衡树
 * <p>
 * 节点的高度：节点到叶子节点的边数（最长）
 * 节点深度：从根节点到该节点的边数
 * 节点层数： 深度 + 1
 * 树高：根节点高度
 *
 * @author otfot
 * @date 2021/04/25
 */
public class AdelsonVelskyAndLandisTree<T extends Comparable<T>> {

    private Node<T> root;

    public AdelsonVelskyAndLandisTree(T[] arr) {
        for (T t : arr) {
            insert(t);
        }
    }


    public void insert(T data) {
        if (Objects.isNull(root)) {
            this.root = new Node<>(data);
        } else {
            Node<T> cur = root;

            while (true) {
                Node<T> left = cur.getLeft();
                Node<T> right = cur.getRight();

                int state = cur.getData().compareTo(data);
                cur.setCount(cur.getCount() + 1);
                cur.setHigh(cur.getHigh() + 1);

                if (state < 0) {
                    if (Objects.isNull(right)) {
                        cur.setRight(new Node<>(data));
                        break;
                    } else {
                        cur = cur.getRight();
                    }

                } else {
                    if (Objects.isNull(left)) {
                        cur.setLeft(new Node<>(data));
                        break;
                    } else {
                        cur = cur.getLeft();
                    }

                }
            }
        }

        // 插入节点后需要维持树高
        maintain();
    }

    private void maintain() {

        int left = treeLeftOrRightHigh(root.getLeft());
        int right = treeLeftOrRightHigh(root.getRight());
        int state = Math.abs(left - right);
        // 左右子树高度相差不大于 1 时不调整
        if (state <= 1) {
            return;
        }

        // 右子树高
        if (left < right) {
            // 判断是右子树的 左子树高还是右子树高， 此时右子树一定存在
            int rLeft = treeLeftOrRightHigh(root.getRight().getLeft());
            int rRight = treeLeftOrRightHigh(root.getRight().getRight());

            // 只可能一边比一边大, 当左子树比右子树大时，需要先对当前节点执行一次 右旋， 然后再对根节点执行一次左旋
            if (rLeft > rRight) {
                root.setRight(rightRotate(root.getRight()));
            }

            // 右子树比左子树大时，只需对 根节点执行一次左旋
            root = leftRotate(root);

        }
        // 左子树高时则相反
        else {
            int lLeft = treeLeftOrRightHigh(root.getLeft().getLeft());
            int lRight = treeLeftOrRightHigh(root.getLeft().getRight());

            if (lRight > lLeft) {
                root.setLeft(leftRotate(root.getLeft()));
            }

            root = rightRotate(root);
        }
    }

    // 左旋时需要保证 是当前节点的最右侧 树高偏高
    // 右旋时保证，是当前节点的最左侧树高偏高


    private Node<T> leftRotate(Node<T> node) {
        // 左旋操作，右子节点肯定存在, 且右子节点的右子节点也一定存在
        Node<T> right = node.getRight();
        Node<T> rlNode = right.getLeft();

        node.setRight(rlNode);
        // 右子节点的左子节点可能为空
        int rlCount = rlNode != null ? rlNode.getCount() : 0;
        node.setCount(node.getCount()  - right.getCount() + rlCount);
        // 当前节点下降一层，高度减1
        node.setHigh(node.getHigh() - 1);

        right.setLeft(node);
        Node<T> rrNode = right.getRight();
        int rrCount = rrNode != null ? rrNode.getCount() : 0;
        right.setCount(rrCount + node.getCount() + 1);
        // 右节点上升一层，高度加1
        right.setHigh(right.getHigh() + 1);

        return right;
    }


    private Node<T> rightRotate(Node<T> node) {
        // 执行右旋操作，左子节点肯定存在
        Node<T> left = node.getLeft();
        Node<T> lrNode = left.getRight();

        node.setLeft(lrNode);
        int lrCount = lrNode != null ? lrNode.getCount() : 0;
        // 当前节点的节点数 = 当前节点的数量 - 左子树的节点数 + 左子树的右孩子节点数
        node.setCount(node.getCount() - left.getCount() + lrCount);
        node.setHigh(node.getHigh() - 1);

        left.setRight(node);
        // 根节点节点数为左右节点数之和
        Node<T> llNode = left.getLeft();
        int llCount = llNode != null ? llNode.getCount() : 0;
        left.setCount(node.getCount() + llCount + 1);
        left.setHigh(left.getHigh() + 1);

        return left;
    }


    private int treeLeftOrRightHigh(Node<T> node) {
        if (Objects.isNull(node)) {
            return 0;
        } else {
            return node.getHigh() + 1;
        }
    }

    public void printStructure() {
        if (Objects.isNull(root)) {
            System.out.println("The tree is empty!!");
            return ;
        }
        int depth = root.getHigh();
        // 每个元素间的字符间隔为 3
        // 最后一行宽度为 2^depth 乘 3 加 1
        // 作为整个二维数组宽度
        int arrHeight = depth * 2 - 1;
        int arrWidth = (2 << (depth - 1)) * 3 + 1;

        // 字符串数组存储显示的元素
        String[][] tree = new String[arrHeight][arrWidth];
        for (int i = 0; i < arrHeight; i++) {
            for (int j = 0; j < arrWidth; j++) {
                tree[i][j] = " ";
            }
        }

        writeArr(root, 0, arrWidth/2, tree, depth);

        for (String[] line : tree) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i < line.length -1) {
                    i += line[i].length() > 4 ? 2 : line[i].length();
                }
            }

            System.out.println(sb);
        }
    }
    private void writeArr(Node<T> node, int row, int col, String[][] tree, int depth) {
        if (Objects.isNull(node)) {
            return;
        }

        // 保存当前节点到数组
        tree[row][col] = String.valueOf(node.getData());

        int curLevel = (row + 1) /2 ;


        if (curLevel == depth) {
            return;
        }

        // 计算当前行到下一行，每个元素的间隔（下一行列索引与当前行列索引）
        int gap = depth - curLevel -1;

        // 记录左节点
        if (Objects.nonNull(node.getLeft())) {
            tree[row+1][col-gap] = "/";
            writeArr(node.getLeft(), row + 2, col -gap*2, tree, depth);
        }

        // 记录右节点
        if (Objects.nonNull(node.getRight())) {
            tree[row+1][col+gap] = "\\";
            writeArr(node.getRight(), row + 2, col + gap* 2, tree, depth);

        }



    }



}
