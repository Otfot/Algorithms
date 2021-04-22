package algorithms.search.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date
 */
class DepthFirstSearchTest {
    Character[] arr = {'A', 'B', 'C', 'D', 'E'};

    @Test
    void search() {

        DepthFirstSearch<Character> dfs = new DepthFirstSearch<>();

        dfs.search(arr, Character.class, 3);
    }

    @Test
    void search2() {
        DepthFirstSearch<Character> dfs = new DepthFirstSearch<>();

        dfs.search2(arr, Character.class, 3);
    }

    @Test
    void search3() {
        DepthFirstSearch<Character> dfs = new DepthFirstSearch<>();

        dfs.search3();
    }
}