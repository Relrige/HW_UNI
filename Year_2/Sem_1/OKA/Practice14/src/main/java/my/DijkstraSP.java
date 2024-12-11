package my;

import my.IndexMinPQ;
import my.DirectedEdge;
import my.EdgeWeightedDigraph;

import java.util.Stack;

public class DijkstraSP {
	
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	public DijkstraSP(EdgeWeightedDigraph G, int s){
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		pq.insert(s, 0.0);
		while (!pq.isEmpty()){
			int v = pq.delMin();
			for (DirectedEdge e : G.adj(v))
				relax(e);
		}
	}
	public Iterable<DirectedEdge> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	private void relax(DirectedEdge e){
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight())	{
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w)) 
				pq.decreaseKey(w, distTo[w]);
			else 
				pq.insert (w, distTo[w]);
		}
	}
}
