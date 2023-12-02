package cz.cvut.fel.dsv.core;

public class DsvPair<K, V> {
    private final K key;
    private final V value;

    public DsvPair(K key, V value) {
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
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}