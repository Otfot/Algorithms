package algorithms.sort;

import org.junit.jupiter.api.Test;
import util.RandomData;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date 2021/04/17
 */
class HeapSortTest {

    @Test
    void sort() {

        Integer[] arr = RandomData.gInteger();
        Integer[] asc = Arrays.copyOf(arr, arr.length);
        Arrays.sort(asc);
        Integer[] desc = Arrays.copyOf(arr, arr.length);
        Arrays.sort(desc, Comparator.reverseOrder());

        Sortable<Integer> heap = new HeapSort<>();

        heap.sort(arr, true);
        assertArrayEquals(asc, arr);

        heap.sort(arr, false);
        assertArrayEquals(desc, arr);
    }


}