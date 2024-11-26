package org.example;

import java.lang.reflect.Array;
import java.util.Iterator;


class Node<Key,Value>{
	Key key;
	Value val;
	Node<Key, Value> next;
	public Node(Key key, Value val) {
		this.key = key;
		this.val = val;
	}
}

public class ST<Key extends Comparable<Key>,Value> {

	private Node<Key,Value>[] map;
	private int n;

	public ST() {
		map = (Node<Key, Value>[]) Array.newInstance(Node.class, 1);
		n = 0;
	}
	
	public void put(Key key, Value val){
		if (key == null) return;
		int i = rank(key);
		if (isEmpty()){
			Node temp = new Node(key,val);
			map[n++]= temp;
			return;
		}
		if (i<n && map[i].key.compareTo(key)==0)
			map[i].val=val;
		else{
			if (n==map.length) resize(2*map.length);
			for (int j=n;j>i;j--){
				map[j]=map[j-1];
			}
			map[i]=new Node(key,val);
			n++;
		}
	}
	
	public Value get(Key key){
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < n && map[i].key.compareTo(key) == 0) return map[i].val;
		else return null;
	}

	Key min(){
		return map[0].key;
	}

	Key max(){

		return map[n-1].key;
	}
	Key floor(Key key) {
		if (isEmpty()) return null;
		int i = rank(key);

		if (i < n && map[i].key.compareTo(key) == 0) {
			return map[i].key;
		} else if (i == 0) {
			return null;
		} else {
			return map[i - 1].key;
		}
	}

	Key ceiling(Key key) {
		if (isEmpty()) return null;
		int i = rank(key);

		if (i < n) {
			return map[i].key;
		} else {
			return null;
		}
	}
	Key select(int k){
		if (k>n-1) return null;
		return map[k].key;
	}
	void deleteMin() {
		if (isEmpty()) return;
		for (int i = 0; i < n - 1; i++) {
			map[i] = map[i + 1];
		}
		map[--n] = null;
		if (n > 0 && n == map.length / 4) resize(map.length / 2);
	}

	void deleteMax() {
		if (isEmpty()) return;
		map[--n] = null;
		if (n > 0 && n == map.length / 4) resize(map.length / 2);
	}
	void delete(Key key) {
		if (key == null) return;
		int elToDelete = rank(key);
		if (elToDelete < n && map[elToDelete].key.compareTo(key) == 0) {
			for (int j = elToDelete; j < n - 1; j++) {
				map[j] = map[j + 1];
			}
			map[--n] = null;
			if (n > 0 && n == map.length / 4) resize(map.length / 2);
		}
	}

	public int size(Key lo, Key hi) {
		if (isEmpty()) return 0;
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (map[i].key.compareTo(lo) >= 0 && map[i].key.compareTo(hi) <= 0) {
				count++;
			}
		}
		return count;
	}
	int size(){
		return n;
	}
	public boolean isEmpty() {
		return n==0;
	}
	
	public boolean contains(Key key){
		return get(key)!=null;
	}
	
	public Iterable<Key> keys(){
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<Key>, Iterable<Key>{
		private int i=0;
		@Override
		public boolean hasNext() {
			return i<n;
		}

		@Override
		public Key next() {
			return map[i++].key;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public Iterator<Key> iterator() {
			return this;
		}
	}
	
	private int rank(Key key){
		int lo = 0, hi = n-1;
		while (lo <= hi){
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(map[mid].key);
			if (cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else if (cmp == 0) return mid;
		}
		return lo;
	}
	
	private void resize(int capacity){
		Node<Key,Value>[] copy = (Node[])Array.newInstance(Node.class, capacity);
		for (int i=0;i<n;i++)
			copy[i]=map[i];
		map = copy;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		ST<String, Integer> st = new ST<>();

		st.put("S", 1);
		st.put("E", 2);
		st.put("A", 3);
		st.put("R", 4);
		st.put("C", 5);
		st.put("H", 6);
		st.put("X", 7);
		for (String key : st.keys()) {
			System.out.print(key + " ");
		}
		System.out.println();
		System.out.println("Value for key 'X': " + st.get("X"));
		System.out.println("Value for key 'A': " + st.get("A"));
		System.out.println("Value for non-existent key 'Z': " + st.get("Z"));

		System.out.println("Minimum key: " + st.min());
		System.out.println("Maximum key: " + st.max());

		System.out.println("Floor of 'G': " + st.floor("G"));
		System.out.println("Ceiling of 'G': " + st.ceiling("G"));
		System.out.println("Floor of 'A': " + st.floor("A"));
		System.out.println("Ceiling of 'S': " + st.ceiling("S"));
		System.out.println("Floor of 'Z': " + st.floor("Z"));
		System.out.println("Ceiling of 'B': " + st.ceiling("B"));

		System.out.println("Key at rank 0: " + st.select(0));
		System.out.println("Key at rank 3: " + st.select(3));

		System.out.println();
		System.out.println("Deleting minimum key...");
		st.deleteMin();
		System.out.println("New minimum key: " + st.min());
		System.out.println("Deleting E.");
		st.delete("E");

		System.out.println("Deleting maximum key...");
		st.deleteMax();
		System.out.println("New maximum key: " + st.max());

		System.out.println("Keys in the symbol table:");
		for (String key : st.keys()) {
			System.out.print(key + " ");
		}
		System.out.println();

		st.put("L", 8);
		st.put("M", 9);
		st.put("P", 10);
		st.put("W", 11);
		System.out.println("After adding keys L, M, P:");

		for (String key : st.keys()) {
			System.out.print(key + " ");
		}
		System.out.println();
		System.out.println(st.size());
		System.out.println("Number of keys from D to N: ");//3
		System.out.println(st.size("A","D"));



	}

}
