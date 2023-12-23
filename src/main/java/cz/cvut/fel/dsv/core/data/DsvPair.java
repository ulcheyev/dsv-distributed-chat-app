package cz.cvut.fel.dsv.core.data;

public class DsvPair<K, V> {
    private final K key;
    private final V value;

    private DsvPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> DsvPair<K, V> of(K key, V value) {
        return new DsvPair<>(key, value);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    //todo method of
}
