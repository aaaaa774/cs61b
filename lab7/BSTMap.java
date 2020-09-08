import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author q
 * @create 2020-08-31 21:40
 */
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    public K key;
    public V value;
    public BSTMap left;
    public BSTMap right;
    public int size = 0;

//    public BSTMap(K key, V value, BSTMap left, BSTMap right){
//        this.key = key;
//        this.value = value;
//        this.right = right;
//        this.left = left;
//    }

    public BSTMap(K key, V value){
        this.key = key;
        this.value = value;

    }

    public BSTMap(){
        key = null;
        value = null;
        right = null;
        left = null;
    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        this.key = null;
        this.value = null;
        this.left = null;
        this.right = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        if (find(this, key) == null) return false;
        return true;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return find(this, key);
    }

    private V find (BSTMap T, K key){
        if (T == null || T.key == null) return null;
        int cmp = key.compareTo((K) T.key);
        if (cmp == 0) {
            return (V) T.value;
        }
        if (cmp < 0){
            return find(T.left, key);
        }else if(cmp > 0){
            return find(T.right, key);
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        if (size == 0){
            this.key = key;
            this.value = value;
            size++;
            return;
        }
        size++;
        insert(this, key, value);
    }

    private BSTMap insert (BSTMap T, K key, V value){
        if (T == null) return new BSTMap(key, value);
        int cmp = key.compareTo((K) T.key);
        if (cmp == 0) {
            T.value = value;
            return T;
        }
        if (cmp < 0){
            T.left = insert(T.left, key, value);
        }else if (cmp > 0){
            T.right = insert(T.right, key, value);
        }
        return T;
    }


    private BSTMap findParent (BSTMap T, K key){
        if (key.compareTo((K) T.key) == 0) return T;
        if (T.left != null && key.compareTo((K) T.left.key) == 0) return T;
        if (T.right != null && key.compareTo((K) T.right.key) == 0) return T;
        int cmp = key.compareTo((K) T.key);
        if (cmp > 0){
            return findParent(T.right,key);
        } else if (cmp < 0){
            return findParent(T.left,key);
        }
        return null;
    }

    private void removeTwo(BSTMap R) {
        if (R.left != null) {
            BSTMap temp = R.left;
            BSTMap tempParent = R.left;
            while (temp.right != null) {
                tempParent = temp;
                temp = temp.right;
            }
            if (temp.left == null && temp != tempParent) {
                R.key = temp.key;
                R.value = temp.value;
                tempParent.right = null;
            } else if (temp.left != null && temp == tempParent) {
                R.key = temp.key;
                R.value = temp.value;
                R.left = temp.left;
            } else if (temp.left == null && temp == tempParent) {
                R.key = temp.key;
                R.value = temp.value;
                R.left = null;
            } else if (temp.left != null && temp != tempParent) {
                R.key = temp.key;
                R.value = temp.value;
                tempParent.right = temp.left;
            }
        } else {
            BSTMap temp = R.right;
            BSTMap tempParent = R.right;
            while (temp.left != null) {
                tempParent = temp;
                temp = temp.left;
            }
            if (temp.right == null && temp != tempParent) {
                R.key = temp.key;
                R.value = temp.value;
                tempParent.left = null;
            } else if (temp.right != null && temp == tempParent) {
                R.key = temp.key;
                R.value = temp.value;
                R.right = temp.right;
            } else if (temp.right == null && temp == tempParent) {
                R.key = temp.key;
                R.value = temp.value;
                R.right = null;
            } else if (temp.right != null && temp != tempParent) {
                R.key = temp.key;
                R.value = temp.value;
                tempParent.left = temp.right;
            }

        }
    }

    public V remove(K key){
        if (!containsKey(key)) throw new IllegalArgumentException("not found");
        if (key.compareTo(this.key) == 0 && size() == 1){
            V v = this.value;
            clear();
            return v;
        } else if (key.compareTo(this.key) == 0 && size() != 1){
            V v = this.value;
            removeTwo(this);
            size--;
            return v;
        }
        BSTMap temp = findParent(this, key);
        if (temp.left != null && key.compareTo((K) temp.left.key) == 0){
            BSTMap tempLeft = temp.left;
            if (tempLeft.left == null && tempLeft.right == null){
                temp.left = null;
                size--;
                return (V) tempLeft.value;
            } else if (tempLeft.left != null && tempLeft.right != null){
                V v = (V) tempLeft.value;
                removeTwo(tempLeft);
                size--;
                return v;
            } else if (tempLeft.left == null) {
                temp.left = tempLeft.right;
                size--;
                return (V) tempLeft.value;
            } else {
                temp.left = tempLeft.left;
                size--;
                return (V) tempLeft.value;
            }
        }else {
            BSTMap tempRight = temp.right;
            if (tempRight.left == null && tempRight.right == null) {
                temp.right = null;
                size--;
                return (V) tempRight.value;
            } else if (tempRight.left != null && tempRight.right != null) {
                V v = (V) tempRight.value;
                removeTwo(tempRight);
                size--;
                return v;
            } else if (tempRight.left == null) {
                temp.right = tempRight.right;
                size--;
                return (V) tempRight.value;
            } else {
                temp.right = tempRight.left;
                size--;
                return (V) tempRight.value;
            }
        }
    }

    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    public Set<K> keySet(){
        return getKeySet(this);
    }

    private Set<K> getKeySet(BSTMap<K, V> T){
        Set<K> temp0 = new HashSet<>();
        if (T.left == null && T.right == null){
            Set<K> temp = new HashSet<>();
            temp.add(T.key);
            return temp;
        } else if (T.left != null) {
            temp0.add(T.key);
            temp0.addAll(getKeySet(T.left));
        }
        if (T.right != null) {
            temp0.add(T.key);
            temp0.addAll(getKeySet(T.right));
        }
        return temp0;
    }

    public Iterator<K> iterator(){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        Set<String> set0 = new HashSet<>();
        set1.add("a");
        set2.add("b");
        set2.add("a");
        set1.addAll(set2);
        System.out.println(set1.toString());
    }


}


/*
    private class BST<K extends Comparable<K>,V> {
        K k;
        V values;
        BST right;
        BST left;

        public BST(K key, V values){
            this.k = key;
            this.values = values;
        }

        BST find(BST T, K key){
            if (T == null) return null;
            if (T.k == key) return T;
            int cmp = key.compareTo((K) T.k);


            if (key.compareTo(T.key)) {
                return find(T.right, key);
            } else{
                return find(T.left, key);
            }
        }

    }
    */
