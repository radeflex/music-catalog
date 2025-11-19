package by.radeflex.musiccatalog.lib;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.val;
    }

    public MyLinkedList<T> filter(Predicate<T> predicate) {
        var list = new MyLinkedList<T>();
        for (var val : this) {
            if (predicate.test(val)) {
                list.add(val);
            }
        }
        return list;
    }

    public <V> MyLinkedList<V> map(Function<T, V> mapper) {
        var list = new MyLinkedList<V>();
        for (var val : this) {
            list.add(mapper.apply(val));
        }
        return list;
    }

    public void put(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.val = value;
    }

    public void remove(T val) {
        if (head == null) {
            return;
        }
        size--;
        if (head.val == val) {
            head = head.next;
            return;
        }
        for (var node = head; node.next != null; node = node.next) {
            if (node.val.equals(val)) {
                node.next = node.next.next;
            }
        }
    }

    public MyLinkedList<T> sorted(Comparator<? super T> c) {
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) new Object[size];
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.val;
            current = current.next;
        }
        MergeSort.sort(arr, 0, arr.length - 1, c);
        var sorted = new MyLinkedList<T>();
        for (int i = 0; i < size; i++) {
            sorted.add(arr[i]);
        }
        return sorted;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                var val = current.val;
                current = current.next;
                return val;
            }
        };
    }

    private static class Node<T> {
        T val;
        Node<T> next;

        Node(T val) {this.val = val;}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T val : this) {
            sb.append(val).append(", ");
        }
        return sb.toString();
    }
}
