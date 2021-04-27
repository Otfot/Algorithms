package datastructure;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * 跳表, 存储不重复内容
 * <p>
 * 跳表的索引是由链表结点本身记录的，而不是通过另外的结构
 * 链表元素通过一个数组来记录它在各个索引层是否有索引，如果有索引则记录在该层索引的下一个索引位置
 *
 * @author otfot
 * @date 2021/04/27
 */
public class SkipList<T extends Comparable<T>> {

    /**
     * 定义创建索引的最大层数
     */
    private static final int MAX_LEVEL = 16;
    /**
     * 设置链表索引按照每 2 个结点创建还是每 3 个结点创建
     */
    private static final double SKIPLIST_P = .5;

    /**
     * 记录当前创建的索引层数
     */
    private int levelCount = 1;

    /**
     * 带头节点的链表
     */
    private SkipListNode<T> head;


    public SkipList() {
        head = new SkipListNode<>(0, MAX_LEVEL);
    }


    public SkipListNode<T> find(T data) {
        SkipListNode<T> prev = head;
        SkipListNode<T>[] forwards = prev.getForwards();

        for (int count = levelCount - 1; count >= 0; count--) {
            while (Objects.nonNull(forwards[count]) && forwards[count].getData().compareTo(data) < 0) {
                prev = forwards[count];
                forwards = prev.getForwards();
            }
        }
        // prev 是查找结点的前一个结点
        if (Objects.nonNull(forwards[0]) && forwards[0].getData() == data) {
            return forwards[0];
        }

        return null;
    }


    @SuppressWarnings("unchecked")
    public void insert(T data) {

        // 无结点时避免创建索引
        int level = head.getForwards()[0] == null ? 1 : randomLevel();

        // 规定索引高度每次最多增加一层
        if (level > levelCount) {
            level = levelCount + 1;
            // 确保 levelCount 始终是最新的索引层数
            levelCount = level;
        }


        SkipListNode<T> newNode = new SkipListNode<>(level, MAX_LEVEL, data);

        //记录在插入过程中的搜寻路径
        SkipListNode<T>[] searchPath = (SkipListNode<T>[]) Array.newInstance(SkipListNode.class, MAX_LEVEL);

        //初始化数组,
        Arrays.fill(searchPath, head);


        // 插入结点时从快表的最高层索引查询，而不从该结点能创建的最高层索引查询
        // 第 0 层索引指的就是全部的数据
        SkipListNode<T> prev = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            // 在当前索引已经查询到 prev 结点，从该结点开始依次遍历该层索引的后续结点
            // 找到小于新结点的最后一个, forwards 记录的都是当前索引层该结点的下一个索引
            SkipListNode<T>[] forwards = prev.getForwards();
            // forwards[i] 表示 该节点在第 i 层的索引的下一个结点
            while (Objects.nonNull(forwards[i]) && forwards[i].getData().compareTo(data) < 0) {
                // 更新索引值，为下一层索引查询做准备
                prev = forwards[i];
                forwards = prev.getForwards();
            }
            // prev 为小于该查找元素的第一个元素，levelCount 有可能大于 level
            if (level > i) {
                searchPath[i] = prev;
            }

        }

        // 依靠 searchPath 创建新对象的索引值，新对象在每层索引的前一个位置即为 searchPath 中对应索引层的值
        SkipListNode<T>[] newForwards = newNode.getForwards();
        for (int i = 0; i < level; i++) {
            SkipListNode<T>[] searchForwards = searchPath[i].getForwards();
            //新结点记录下一层索引位置
            newForwards[i] = searchForwards[i];
            // 将新结点索引前一个结点的下一个索引更新为新结点
            searchForwards[i] = newNode;

        }
    }

    @SuppressWarnings("unchecked")
    public void delete(T data) {

        //记录在删除过程中的搜寻路径
        SkipListNode<T>[] searchPath = (SkipListNode<T>[]) Array.newInstance(SkipListNode.class, levelCount);

        SkipListNode<T> prev = head;
        SkipListNode<T>[] forwards = prev.getForwards();
        // 查询结点
        for (int count = levelCount - 1; count >= 0; count--) {
            while (Objects.nonNull(forwards[count]) && forwards[count].getData().compareTo(data) < 0) {
                prev = forwards[count];
                forwards = prev.getForwards();
            }
            searchPath[count] = prev;
        }

        // 如果存在删除的结点则 prev 为删除结点的前一个
        if (Objects.nonNull(forwards[0]) && forwards[0].getData().compareTo(data) == 0) {

            SkipListNode<T> deleteNode = forwards[0];
            SkipListNode<T>[] deleteForwards = deleteNode.getForwards();
            // 删除该结点，并更新索引值
            for (int i = 0; i < deleteNode.getLevel(); i++) {
                SkipListNode<T>[] searchForwards = searchPath[i].getForwards();
                searchForwards[i] = deleteForwards[i];
                deleteForwards[i] = null;
            }
        }


    }

    /**
     * 根据每两个元素生成一个索引的规则， 一级索引占原始数据 50%， 二级索引占原始数据 25% ...
     * <p>
     * 方法依据概率为新插入元素返回需要创建几级索引
     * <p>
     * 感觉这个只是随机某些元素会产生索引，但不能每两个都会产生索引
     */
    private int randomLevel() {
        int level = 1;

        // 有一级索引的才能有二级索引
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
            level += 1;
        }

        return level;
    }


    public void printAll() {
        SkipListNode<T> prev = head;
        SkipListNode<T>[] forwards = prev.getForwards();
        SkipListNode<T>[] temp;
        SkipListNode<T>[] temp2;

        // 依次输出每一层索引
        for (int count = levelCount - 1; count >= 0; count--) {
            temp = forwards;
            temp2 = forwards;
            // 第 0 层包含所有元素，遍历第 0 层元素
            while (Objects.nonNull(temp[0])) {
                // 获取当前的元素
                T value = temp[0].getData();

                if (Objects.isNull(temp2[count])) {
                    System.out.print("=========");
                } else {
                    T indexValue = temp2[count].getData();
                    if (indexValue.equals(value)) {
                        System.out.printf("=%3s:%-3s=", indexValue, count);
                        temp2 = temp2[count].getForwards();
                    } else {
                        System.out.print("=========");
                    }
                }


                temp = temp[0].getForwards();
            }

            System.out.println();

        }


    }
}

class SkipListNode<T extends Comparable<T>> {
    @Getter
    private T data;
    @Getter
    private int level;

    @Setter
    @Getter
    private SkipListNode<T>[] forwards;

    public SkipListNode(int level, int maxLevel) {
        this(level, maxLevel, null);
    }

    @SuppressWarnings("unchecked")
    public SkipListNode(int level, int maxLevel, T data) {
        this.data = data;
        this.level = level;
        this.forwards = (SkipListNode<T>[]) Array.newInstance(SkipListNode.class, maxLevel);
    }


    @Override
    public String toString() {

        return "SkipListNode{" +
                " data: " + data + "; " +
                " levels: " + level + " }";

    }
}
