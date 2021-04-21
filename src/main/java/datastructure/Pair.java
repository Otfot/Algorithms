package datastructure;

/**
 * 辅助工具类 Pair 包含成对元素
 *
 * @author otfot
 * @date 2021/04/21
 */
public class Pair<K, V extends Comparable<V>> implements Comparable<Pair<K, V>> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }


    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int compareTo(Pair<K, V> o) {
        return this.value.compareTo(o.getValue());
    }
}
