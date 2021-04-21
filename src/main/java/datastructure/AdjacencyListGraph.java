package datastructure;

import java.util.*;

/**
 * 基于邻接表的有权图
 *
 * @author otfot
 * @date 2021/04/21
 */
public class AdjacencyListGraph<T> {
    private HashMap<T, ArrayList<Pair<T, Integer>>> adjacencyList;
    private int vertexCount;

    public AdjacencyListGraph(int capacity) {
        adjacencyList = new HashMap<>(capacity);
    }

    public HashMap<T, ArrayList<Pair<T, Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

    public void addEdge(T start, T end, int weight) {

        ArrayList<Pair<T, Integer>> relation = new ArrayList<>();
        Pair<T, Integer> temp = new Pair<>(end, weight);
        relation.add(temp);

        ArrayList<Pair<T, Integer>> result = adjacencyList.putIfAbsent(start, relation);

        if (Objects.nonNull(result)) {
            result.add(temp);
        } else {
            vertexCount++;
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public ArrayList<Pair<T, Integer>> getAdjacencyVertexs(T vertex) {
        return adjacencyList.get(vertex);
    }

}
