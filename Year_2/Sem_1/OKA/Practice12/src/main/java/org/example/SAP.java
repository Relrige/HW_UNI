package org.example;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SAP {
    Digraph digraph;
    public SAP(Digraph G){
        digraph = G;
    }
    //lenght shortest path to same parent v та w
    //-1 if not found
    public int length(int v, int w){
        int ans = ancestor(v,w);
        BreadthFirstPaths breadthFirstPaths1 = new BreadthFirstPaths(digraph,v, ans);
        BreadthFirstPaths breadthFirstPaths2= new BreadthFirstPaths(digraph,w, ans);
        AtomicInteger i1 = new AtomicInteger(0);
        AtomicInteger i2 = new AtomicInteger(0);
        breadthFirstPaths1.pathTo(ans).forEach(x-> i1.getAndIncrement());
        breadthFirstPaths2.pathTo(ans).forEach(x-> i2.getAndIncrement());
        return i1.get()+i2.get();
    }
    // same parent v та w, from shortest path
    public int ancestor(int v, int w){
        BFS2 bfs2 = new BFS2(digraph, v, w);
        return bfs2.ans;
    }
    // length of shortest path з v та з w;
    public int length(Iterable<Integer> v, Iterable<Integer> w){
        ArrayList<Integer> integers = new ArrayList<>();
        v.forEach(x->{
            w.forEach(y->{
                integers.add(length(x,y));
            });
        });
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        integers.forEach(x->{
            if(min.get() >x && x!=-1) min.set(x);
        });
        return integers.get(0);
    }


    // same parent of shortest path any v w
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int minLength = Integer.MAX_VALUE;
        int ancestorX = -1, ancestorY = -1;

        for (int x : v) {
            for (int y : w) {
                int length = length(x, y);
                if (length != -1 && length < minLength) {
                    minLength = length;
                    ancestorX = x;
                    ancestorY = y;
                }
            }
        }
        return ancestor(ancestorX, ancestorY);
    }
}