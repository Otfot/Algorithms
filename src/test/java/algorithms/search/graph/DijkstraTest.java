package algorithms.search.graph;

import datastructure.AdjacencyListGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date
 */
class DijkstraTest {

    @Test
    void search() throws Exception {

        AdjacencyListGraph<Character> graph = new AdjacencyListGraph<>(6);

        graph.addEdge('A', 'B', 7);
        graph.addEdge('A', 'C', 9);
        graph.addEdge('A', 'F', 14);

        graph.addEdge('B', 'A', 7);
        graph.addEdge('B', 'C', 10);
        graph.addEdge('B', 'D', 15);

        graph.addEdge('C', 'A', 9);
        graph.addEdge('C', 'B', 10);
        graph.addEdge('C', 'D', 11);
        graph.addEdge('C', 'F', 2);

        graph.addEdge('D', 'B', 15);
        graph.addEdge('D', 'C', 11);
        graph.addEdge('D', 'E', 6);

        graph.addEdge('E', 'D', 6);
        graph.addEdge('E', 'F', 9);

        graph.addEdge('F', 'A', 14);
        graph.addEdge('F', 'C', 2);
        graph.addEdge('F', 'E', 9);

        Dijkstra dijkstra = new Dijkstra();

        String route = dijkstra.search(graph, 'A', 'E');
        String real = "A-->C-->F-->E";
        assertEquals(real, route);


    }
}