package algorithms.sort.comparison;

import algorithms.sort.Sortable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date
 */
class ShellSortTest {
    Integer[] arr = {3,5,8,6,3,2,1,4,9,0};
    Integer[] asc = {0,1,2,3,3,4,5,6,8,9};
    Integer[] desc = {9,8,6,5,4,3,3,2,1,0};

    @Test
    void sort() {
        Sortable<Integer> shell = new SelectionSort<>();

        shell.sort(arr, true);
        assertArrayEquals(asc, arr);

        shell.sort(arr, false);
        assertArrayEquals(desc, arr);
    }
}