package com.company;

public class BST <K extends Comparable<K>,V> {


    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = right = null;
        }
    }

    // BST root node
    private Node root;

    public BST() {
        root = null;
    }
    public BST(K key, V val){
        root=new Node(key,val);
    }

    // putting
    public void insert(K key, V val) {
        root = insert(root, key, val);
    }

    // insert function
    private Node insert(Node root, K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            return root;
        }
        if (key.compareTo(root.key) < 0)    
            root.left = insert(root.left, key, val);
        else if (0 < key.compareTo(root.key))    
            root.right = insert(root.right, key, val);
        return root;
    }

    public V get(K key){
        return get(root,key);

    }
    private V get(Node root, K key){
        if(root==null)
            return null;
        else if(root.key.compareTo(key)==0)
            return root.val;
        else if(root.key.compareTo(key)<0)
            return get(root.right,key);
        else
            return get(root.left,key);
    }

    private K minValue(Node root) {
        K minval = root.key;
        //find minval
        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    public void delete(K key) {
        delete(root, key);
    }

    // delete function
    private Node delete(Node root, K key) {
        //base case
        if (root == null) return null;

        if (key.compareTo(root.key) < 0)     
            root.left = delete(root.left, key);
        else if (0 < key.compareTo(root.key)) 
            root.right = delete(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = delete(root.right, root.key);
        }
        return root;
    }

    public Iterable<K> Iterator(){
        return (Iterable<K>)root.key;
    }


}
