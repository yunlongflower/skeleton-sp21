package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode<K extends Comparable<K>, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    BSTNode<K, V> root;
    int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return exist(root, key);
    }

    private boolean exist(BSTNode<K, V> node, K key) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) == 0) {
            return true;
        } else if (node.key.compareTo(key) < 0) {
            return exist(node.right, key);
        } else {
            return exist(node.left, key);
        }
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

   private V get(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node.value;
        } else if (node.key.compareTo(key) < 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
   }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            return;
        }
        root = put(root, key, value);
        size++;
    }

    private BSTNode<K, V> put(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            return new BSTNode<K, V>(key, value);
        }
        if (node.key.compareTo(key) < 0) {
            node.right = put(node.right, key, value);
        } else if (node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
