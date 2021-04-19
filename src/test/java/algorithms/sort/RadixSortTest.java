package algorithms.sort;

import org.junit.jupiter.api.Test;
import util.RandomData;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date 2021/04/19
 */
class RadixSortTest {

    @Test
    void sort() {
        Integer[] arr = RandomData.gInteger();
        Integer[] asc = Arrays.copyOf(arr, arr.length);
        Arrays.sort(asc);

        RadixSort sort = new RadixSort();

        sort.sort(arr);

        assertArrayEquals(asc, arr);
    }

}