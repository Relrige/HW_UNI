package org.example;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>{

    protected Item[] s;
    protected int n =0;

    public Bag(){
        s = (Item[])new Object[1];
    }

    public void add(Item item) {
        if (item==null)throw new NullPointerException();
        if (n==s.length) resize(2*s.length);
        s[n++] = item;
    }
    public boolean isEmpty() {
        return n==0;
    }


    public int size() {
        return n;
    }

    private void resize(int capacity){
        Item[] copy = (Item[])new Object[capacity];
        for (int i=0;i<n;i++)
            copy[i]=s[i];
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i=n;
        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return s[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
