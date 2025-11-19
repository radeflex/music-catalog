package by.radeflex.musiccatalog.lib;

public class MyMap<K, V> {
    private final MyLinkedList<Entry<K,V>>[] values;
    private int capacity = 1500;
    private int size;

    public MyMap() {
        values = new MyLinkedList[capacity];
        size = 0;
    }

    public MyMap(int capacity) {
        this.capacity = capacity;
        values = new MyLinkedList[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    private int genHash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K k, V v) {
        int hash = genHash(k);

        if (values[hash] == null) {
            values[hash] = new MyLinkedList<>();
        }
        for (var entry : values[hash]) {
            if (entry.getKey().equals(k)) {
                entry.setValue(v);
                return;
            }
        }
        values[hash].add(new Entry<>(k, v));
        size++;
    }

    public V get(K k) {
        int hash = genHash(k);

        var list = values[hash];
        if (list == null) {
            return null;
        }
        for (Entry<K,V> entry : list) {
            if (entry.getKey().equals(k)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V remove(K k) {
        int hash = genHash(k);
        var list = values[hash];
        if (list == null) {
            return null;
        }
        for (var entry : list) {
            if (entry.getKey().equals(k)) {
                list.remove(entry);
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(i).append(". ").append(values[i]).append("\n");
        }
        return sb.toString();
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
