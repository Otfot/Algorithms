package algorithms.sort;

import org.junit.jupiter.api.Test;
import util.RandomData;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date 2021/04/19
 */
class CountingSortTest {

    @Test
    void sort() {

        Integer[] arr = RandomData.gInteger();
        Integer[] asc = Arrays.copyOf(arr, arr.length);
        Arrays.sort(asc);

        CountingSort sort = new CountingSort();
        sort.sort(arr);

        assertArrayEquals(asc, arr);
    }
}