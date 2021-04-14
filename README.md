## 算法练习

本仓库是为了练习算法的实现，在代码实现上分析它的复杂度与适用场景，加深学习印象。

### 排序

基于比较的排序

- [插入排序](src/main/java/algorithms/sort/comparison/InsertionSort.java) :star::star::star:
- [冒泡排序](src/main/java/algorithms/sort/comparison/BubbleSort.java)    :star::star:
- [选择排序](src/main/java/algorithms/sort/comparison/SelectionSort.java) :star:


### 小技巧

- 对于插入排序和冒泡排序来说，一般使用插入排序，其数据移动操作（一步）要少于冒泡的数据交换操作（三步）

### 应用场景

稳定性排序算法的适用场景：

- 下订单顺序排列

## 术语

顺序度：在一组元素中小的元素在大的元素前面的个数。

逆序度 = 满顺序度 - 初始顺序度，满顺序度 = (1 + n-1) * (n-1) / 2

## 说明

本仓库部分代码实现参考[《算法》](https://book.douban.com/subject/19952400/) 一书、极客时间[《数据结构与算法》](https://time.geekbang.org/column/intro/126) 专栏。