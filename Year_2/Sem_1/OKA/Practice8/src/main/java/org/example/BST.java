package org.example;

import java.util.PriorityQueue;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value>{

    private Node root;
    private class Node<Key,Value>{
        Key key;
        Value val;
        Node<Key,Value> left;
        Node<Key,Value> right;
        private int counter=1;
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val){
        root = put(root,key,val);
    }
    public Node put(Node<Key, Value> node, Key key, Value val){
        if(node == null){
            return new Node(key,val);
        }
        if(key.compareTo(node.key) < 0){
            node.left = put(node.left,key,val);
        }
        else if(key.compareTo(node.key) > 0){
            node.right = put(node.right,key,val);
        }
        else if(key.compareTo(node.key) == 0){
                node.val = val;
        }
        node.counter = 1+size(node.left)+size(node.right);
        return node;
    }


    public Value get(Key key){
        Node<Key,Value> node = root;
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else if (cmp == 0) return node.val;
        }
        return null;
    }

    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete(Node<Key, Value> node, Key key) {
        if (node == null) return null;
        if(key.compareTo(node.key) < 0){
            node.left = delete(node.left, key);
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key);
        }
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.counter = 1+size(node.left) + size(node.right);
        return node;
    }
    //deleting mim node
    public void deleteMin(){
        if(root==null) return;
        root = deleteMin(root);
    }
    //deleting min node and renewing counter
    private Node deleteMin(Node node){
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.counter = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Node<Key, Value> min(Node<Key, Value> node2){
        Node<Key,Value> node = node2;
        while(node.left!=null&&node.left.left != null){
            node = node.left;
        }
        return node;
    }
    public Key min(){
        Node<Key,Value> node = root;
        if(node==null) return null;
        while(node.left!=null && node.left != null){
            node = node.left;
        }
        return node.key;
    } //min ключ в таблиці.

    public Key max(){
        Node<Key,Value> node = root;
        if(node==null) return null;
        while(node.right!=null&&node.right.right != null){
            node = node.right;
        }
        return node.key;
    }//max ключ в таблиці.

    public Key floor(Key key){
        Node node = floor(root, key);
        if (node == null) return null;
        return (Key) node.key;
    }
    private Node floor(Node node, Key key){
        if (node == null) return null;
        int cmpKeys = key.compareTo((Key) node.key);
        if (cmpKeys == 0)
            return node;
        if (cmpKeys < 0)
            return floor(node.left, key);
        Node temp = floor(node.right, key);
        if (temp != null)
            return temp;
        else return node;
    }

    public Key ceiling(Key key){
        Node node = ceiling(root, key);
        if (node == null) return null;
        return (Key) node.key;
    }
    private Node ceiling(Node node, Key key){
        if (node == null) return null;
        int cmpKeys = key.compareTo((Key) node.key);
        if (cmpKeys == 0)
            return node;
        if (cmpKeys > 0)
            return ceiling(node.right, key);
        Node temp = ceiling(node.left, key);
        if (temp != null)
            return temp;
        else return node;
    }

    public int rank(Key key){
        return rank(key, root);
    }
    private int rank(Key key, Node<Key, Value> x){
        if (x == null) return 0;
        int cmpKeys = key.compareTo(x.key);
        if (cmpKeys < 0)
            return rank(key, x.left);
        else if (cmpKeys > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    private int size(Node node){
        if (node == null) return 0;
        return node.counter;
    }
    public int size(){
        return size(root);
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new PriorityQueue<>();
        inOrder(root, queue);
        return queue;
    }

    private void inOrder(Node<Key, Value> x, Queue<Key> q){
        if (x == null) return;
        inOrder(x.left, q);
        q.add(x.key);
        inOrder(x.right, q);
    }
    private void preOrder(Node<Key, Value> x, Queue<Key> q){
        if (x == null) return;
        preOrder(x.left, q);
        preOrder(x.right, q);
        q.add(x.key);
    }
    private void postOrder(Node<Key, Value> x, Queue<Key> q){
        if (x == null) return;
        postOrder(x.left, q);
        postOrder(x.right, q);
        q.add(x.key);

    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();

        bst.put("T", 1);
        bst.put("E", 2);
        bst.put("A", 3);
        bst.put("R", 4);
        bst.put("C", 5);
        bst.put("H", 6);
        bst.put("M", 7);
        bst.put("V", 8);

        System.out.println("Value for key 'V': " + bst.get("V"));
        System.out.println("Value for key 'R': " + bst.get("R"));
        System.out.println("Value for non-existent key 'Z': " + bst.get("Z"));

        System.out.println("Maximum key: " + bst.max());
        System.out.println("Minimum key: " + bst.min());

        System.out.println("Floor of 'G': " + bst.floor("G"));
        System.out.println("Ceiling of 'G': " + bst.ceiling("G"));
        System.out.println("Floor of 'A': " + bst.floor("A"));
        System.out.println("Ceiling of 'Z': " + bst.ceiling("Z"));

        System.out.println("Rank of 'E': " + bst.rank("E"));
        System.out.println("Rank of 'M': " + bst.rank("M"));
        for (String key : bst.keys()) {
            System.out.print(key + " ");
        }

        System.out.println();
        System.out.println("Size of the BST: " + bst.size());

        System.out.println("Delete min key...");
        bst.deleteMin();
        System.out.println("New min key: " + bst.min());

        System.out.println("Deleting key 'M'...");
        bst.delete("M");
        System.out.println("Does the tree contain 'M'?: " + (bst.get("M") != null));

        System.out.println("In-order output:");
        for (String key : bst.keys()) {
            System.out.print(key + " ");
        }
        System.out.println();

        System.out.println("Final size of the BST: " + bst.size());
    }
}