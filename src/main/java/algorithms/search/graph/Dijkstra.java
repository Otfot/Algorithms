package algorithms.search.graph;

import datastructure.AdjacencyListGraph;
import datastructure.Pair;
import datastructure.PairHeap;

import java.util.HashMap;
import java.util.Objects;

/**
 * 迪杰斯特拉算法
 * 单源最短路径算法，无法处理带有负权边的图
 * <p>
 * 执行效率：
 * 进入队列的顶点个数是总的顶点个数 V
 * 遍历每个顶点邻接的顶点，次数最多 V - 1
 * 总的次数为 V * (V - 1) > E (边的全部个数), 应该直接用全部边的个数 E 来统计比较准确
 * <p>
 * 每次会执行一次堆顶点的删除，会堆化一次
 * 接着要么执行入堆操作，会堆化一次，要么执行更新堆操作 深度优先搜索更新结点 V
 *
 * @author otfot
 * @date 2021/04/21
 */
public class Dijkstra {

    /**
     * 泛型方法，搜索从 start 顶点到 end 顶点的最短路径
     *
     * @param start 开始顶点
     * @param end   结束顶点
     * @param <T>   泛型参数
     * @param graph 包含顶点关系的图
     */
    public <T> String search(AdjacencyListGraph<T> graph, T start, T end) throws Exception {

        // 事先知道顶点个数，指定具体容量，防止扩容
        int capacity = (int) (graph.getVertexCount() / 0.75 + 1);

        // 记录从起始顶点到每个顶点的距离
        HashMap<T, Integer> vertexes = new HashMap<>(capacity);

        // 初始化到每个顶点的距离为无穷大
        for (T t : graph.getAdjacencyList().keySet()) {
            vertexes.put(t, Integer.MAX_VALUE);
        }

        // 小顶堆的优先队列
        PairHeap<T, Integer> queue = new PairHeap<>(vertexes.size(), false);

        // 标记是否进入过队列
        HashMap<T, Boolean> inQueue = new HashMap<>(capacity);

        // 记录从起点到终点的最短路线
        HashMap<T, T> prev = new HashMap<>(capacity);

        // 起始顶点距离为 0
        vertexes.put(start, 0);
        inQueue.put(start, true);
        queue.add(new Pair<>(start, 0));

        while (!queue.isEmpty()) {
            // 获取当前到起始顶点距离最小的点
            Pair<T, Integer> min = queue.poll();
            // 如果当前顶点为寻找的终点则结束
            if (min.getKey().equals(end)) {
                break;
            }

            // 依次遍历该顶点邻接的所有顶点
            for (Pair<T, Integer> adjVertex : graph.getAdjacencyVertexs(min.getKey())) {

                // 起始顶点到当前顶点的最短距离 + 当前顶点到与它连接的顶点 B 的距离小于起点顶点到连接顶点 B 的距离时更新
                int distance = vertexes.get(min.getKey()) + adjVertex.getValue();
                if (distance < vertexes.get(adjVertex.getKey())) {
                    T vertex = adjVertex.getKey();

                    // 更新距离记录
                    vertexes.put(vertex, distance);
                    // 记录路径
                    prev.put(vertex, min.getKey());

                    Boolean exist = inQueue.putIfAbsent(vertex, true);

                    // 根据是否进入队列来判断是否需要更新
                    if (Objects.isNull(exist)) {
                        queue.add(new Pair<>(vertex, distance));
                    } else {
                        queue.update(new Pair<>(vertex, distance));
                    }
                }

            }

        }

        System.out.println("The shortest distance from " + start + " to " + end + " is " + vertexes.get(end));
        System.out.println("The route is :");
        System.out.print(start);
        StringBuilder route = new StringBuilder();
        route.append(start);
        reversePrint(prev, start, end, route);
        System.out.println();
        return route.toString();

    }


    private <T> void reversePrint(HashMap<T, T> prev, T s, T e, StringBuilder sb) {
        if (s.equals(e)) {
            return;
        }

        reversePrint(prev, s, prev.get(e), sb);
        System.out.print("-->" + e);
        // 尽量减少 + 号 因为会产生潜在的 StringBuilder
        sb.append("-->").append(e);

    }
}
