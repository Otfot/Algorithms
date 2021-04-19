## 算法练习

本仓库主要为了练习基础算法与数据结构，加深学习印象。

### 排序

基于比较的排序

- [插入排序](src/main/java/algorithms/sort/comparison/InsertionSort.java) :star::star::star:
    - 优化版 [希尔排序](src/main/java/algorithms/sort/comparison/ShellSort.java) :star::star::star::star:
- [冒泡排序](src/main/java/algorithms/sort/comparison/BubbleSort.java)    :star::star:
- [选择排序](src/main/java/algorithms/sort/comparison/SelectionSort.java) :star:
    - 优化版 [堆排序](src/main/java/algorithms/sort/HeapSort.java) :star::star::star::star:
- [归并排序](src/main/java/algorithms/sort/comparison/MergeSort.java) :star::star::star::star:
- [快速排序](src/main/java/algorithms/sort/comparison/QuickSort.java) :star::star::star::star::star:
- [计数排序](src/main/java/algorithms/sort/CountingSort.java)
    - 通用版 [桶排序]()
- [基数排序]()


线性排序

### 小技巧

- 对于插入排序和冒泡排序来说，一般使用插入排序，其数据移动操作（一步）要少于冒泡的数据交换操作（三步）

### 应用场景

稳定性排序算法的适用场景：

- 下订单顺序排列

## 术语

顺序度：在一组元素中小的元素在大的元素前面的个数。

逆序度 = 满顺序度 - 初始顺序度，满顺序度 = (1 + n-1) * (n-1) / 2

## 说明

本仓库部分代码实现参考[《算法》](https://book.douban.com/subject/19952400/)
一书、极客时间[《数据结构与算法》](https://time.geekbang.org/column/intro/126) 专栏、极客时间[《常用算法25讲》](https://time.geekbang.org/opencourse/intro/100057601?tab=catalog)专栏。