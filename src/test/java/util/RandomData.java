package util;

import java.util.Random;

/**
 * 随机生成测试数据
 * @author otfot
 * @date 2021/04/17
 */
public class RandomData {

    public static Integer[] gInteger() {

        Random rand = new Random();
        Integer[] a = new Integer[100];
        for (int i = 0; i < 100; i++) {
            a[i] = rand.nextInt(100);
        }
        return a;
    }
}
