package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private BSTNode<K, V> left;
        private BSTNode<K, V> right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode<K, V> root;
    private int size;

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
        Set<K> key_set = new TreeSet<K>();
        addToKeySet(key_set, root);
        return key_set;
    }

    private void addToKeySet(Set<K> key_set, BSTNode<K, V> node) {
        if (node == null) {
            return;
        }
        addToKeySet(key_set, node.left);
        key_set.add(node.key);
        addToKeySet(key_set, node.right);
    }

    @Override
    public V remove(K key) {
        V removed_value = null;
        remove(root, key, removed_value);
        return removed_value;
    }

    @Override
    public V remove(K key, V value) {
        remove(root, key, value);
        return value;
    }

    private BSTNode<K, V> remove(BSTNode<K, V> node, K key, V removed_value) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            if (removed_value == null || node.value == removed_value) {
                removed_value = node.value;
                node = remove(node);
            } else {
                removed_value = null;
            }
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key, removed_value);
        } else {
            node.left = remove(node.left, key, removed_value);
        }
        return node;
    }

    private BSTNode<K, V> remove(BSTNode<K, V> node) {
        if (node.left == null && node.right == null) {
            return null;
        } else if (node.left != null && node.right != null) {
            BSTNode<K, V> right_most = node.left;
            while (right_most.right != null) {
                right_most = right_most.right;
            }
            node.key = right_most.key;
            node.value = right_most.value;
            remove(node.left, right_most.key, null);
            return node;
        } else if (node.left != null) {
           return node.left; 
        } else {
            return node.right;
        }
    }

    private class KeyIterator implements Iterator<K> {
        private Iterator<K> key_iter;
        private KeyIterator() {
            key_iter = keySet().iterator();
        }

        public boolean hasNext() {
            return key_iter.hasNext();
        }

        public K next() {
            return key_iter.next();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    public void printInOrder() {
        printTree(root);
    }

    private void printTree(BSTNode<K, V> node) {
        if (node == null) {
            return;
        }
        printTree(node.left);
        System.out.println(node.key + ": "+ node.value);
        printTree(node.right);
    }
}
