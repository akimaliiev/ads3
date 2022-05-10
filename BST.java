package com.company;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BST <K extends Comparable<K>,V> {
    

    //node class that defines BST node
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

    // Constructor for BST =>initial empty tree
    public BST() {
        root = null;
    }

    //delete a node from BST
    public void deleteKey(K key) {
        delete(root, key);
    }

    //recursive delete function
    private Node delete(Node root, K key) {
        //base case
        if (root == null) return root;

        if (key.compareTo(root.key) < 0)     //traverse left subtree
            root.left = delete(root.left, key);
        else if (0 < key.compareTo(root.key))  //traverse right subtree
            root.right = delete(root.right, key);
        else {
            // node contains only one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = delete(root.right, root.key);
        }
        return root;
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

    // insert a node in BST 
    public void insert(K key, V val) {
        root = insert(root, key, val);
    }

    //recursive insert function
    private Node insert(Node root, K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            return root;
        }
        if (key.compareTo(root.key) < 0)     //insert in the left subtree
            root.left = insert(root.left, key, val);
        else if (0 < key.compareTo(root.key))    //insert in the right subtree
            root.right = insert(root.right, key, val);
        // return pointer
        return root;
    }

    // method for inorder traversal of BST
    public void inorder() {
        inorder(root);
    }

    // recursively traverse the BST  
    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + "=>" + root.val);
            inorder(root.right);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node root) {
        if (root != null) {
            postOrder(root.right);
            System.out.println(root.key + "=>" + root.val);
            postOrder(root.left);
        }
    }

    public boolean search(K key) {
        Node node = root;
        return search(node, key);
    }

    private boolean search(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (node.key == key) {
            return true;
        } else {
            if (node.key.compareTo(key) > 0) {
                return search(node.right, key);
            } else {
                return search(node.left, key);
            }
        }
    }
}