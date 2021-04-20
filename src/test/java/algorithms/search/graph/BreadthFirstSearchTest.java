package algorithms.search.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date 2021/04/20
 */
class BreadthFirstSearchTest {

    @Test
    void search() {
        // 如果没有找到出口路线，重新执行测试
        int len = 20;
        int[][] maze = new int[len][len];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if ((i == 0 && j == 0) || (i == len-1 && j == len-1)) {
                    continue;
                }
                if (Math.random() >= 0.7) {
                    maze[i][j] = -1;
                }

            }
        }


        BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.search(maze, len, len);

    }
}