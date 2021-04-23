package algorithms.search.tree;

import datastructure.BinarySearchTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date
 */
class BinarySearchTest {

    @Test
    void search() {

        // the order is 10,18,20,30,40,42,48,50,60,68
        Integer[] data = {40, 48, 42, 60, 50, 68, 20, 30, 18, 10};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(data);


        Integer[] res = new Integer[10];
        Integer[] real = {68, 60, 50, 48, 42, 40, 30, 20, 18, 10};
        BinarySearch bs = new BinarySearch();

        for (int i = 0; i < 10; i++) {
            res[i] = bs.search(tree, i + 1);
        }

        assertArrayEquals(res, real);

        Integer[] delete = {42, 68, 20, 40, 48, 30, 10, 50, 18, 60};

        for (Integer integer : delete) {
            tree.delete(integer);
        }


    }
}