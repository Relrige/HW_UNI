package org.example;

public class LinkedQueue<Item>{
	private Node first,last;
	private int count =0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void enqueue(Item item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		count++;
		if (isEmpty())
			first = last;
		else
			oldLast.next=last;
	}

	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		count--;
		if (isEmpty()) last =null;
		return item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return count;
	}

}
