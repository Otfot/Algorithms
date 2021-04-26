package datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author otfot
 * @date 2021/04/26
 */
class AdelsonVelskyAndLandisTreeTest {

    @Test
    void insert() {

        Integer[] data = {40, 48, 42, 60, 50, 68, 20, 30, 18, 10};

        AdelsonVelskyAndLandisTree<Integer> tree = new AdelsonVelskyAndLandisTree<>(data);

        System.out.println("=========start==========");
        tree.printStructure();
        System.out.println("=========end============");
    }
}