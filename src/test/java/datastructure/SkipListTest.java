package datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date
 */
class SkipListTest {

    SkipList<Integer> skip = new SkipList<>();



    @Test
    void find() {
    }

    @Test
    void insert() {
        skip.insert(5);
        skip.insert(10);
        skip.insert(15);
        skip.insert(25);
        skip.insert(35);
        skip.insert(75);
        skip.insert(45);
        skip.insert(55);
        skip.insert(65);
        skip.insert(58);
        skip.printAll();

    }

    @Test
    void delete() {
    }
}