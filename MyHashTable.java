package com.company;

import java.util.Objects;

public class MyHashTable<K, V> {
    private static class HashNode<K, V>{
        private int hashCode = 0;
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value,int hashcode){
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 8;
    private int size;

    public MyHashTable() {
        this(8);
    }

    public MyHashTable(int M){
        this.M = M;
        this.chainArray = new HashNode[M];
        this.size = 0;
    }

    private int hash(K key){
        return Objects.hashCode(key);
    }

    public void put(K key, V value){
        int index = hash(key);
        int hash = hash(key);
        HashNode<K, V> head = chainArray[index];
        while(head != null) {
            if(head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = chainArray[index];
        HashNode<K, V> node = new HashNode<K, V>(key, value,hash);
        node.next = head;
        chainArray[index] = node;
    }

    public int size(){
        return size;
    }

    public V get(K key){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        int hash = hash(key);
        while(head != null){
            if(head.key.equals(key) && head.hashCode == hash){
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> previous = null;
        int hash = hash(key);
        while(head != null){
            if(head.key.equals(key) && hash==head.hashCode){
                size--;
                if(previous != null){
                    previous.next = head.next;
                }
                else{
                    chainArray[index] = head.next;
                }
                return null;
            }
            previous = head;
            head = head.next;
        }
        return null;
    }
    public boolean contains(V value){
        for (HashNode<K, V> kvHashNode : chainArray) {
            HashNode<K, V> head = kvHashNode;
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public K getKey(V value){
        for (HashNode<K, V> kvHashNode : chainArray) {
            HashNode<K, V> head = kvHashNode;
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }
}
