package org.example;

import java.util.*;

public class DirectedAcyclicGraph {
    private final int vertices; // Кількість вершин
    private final List<List<Integer>> adjacencyList; // Список суміжності

    // Конструктор
    public DirectedAcyclicGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Додавання ребра
    public void addEdge(int from, int to) {
        adjacencyList.get(from).add(to);
    }

    // Перевірка на цикли за допомогою DFS
    public boolean hasCycle() {
        boolean[] visited = new boolean[vertices];
        boolean[] recStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (hasCycleUtil(i, visited, recStack)) {
                return true; // Цикл знайдено
            }
        }
        return false;
    }

    private boolean hasCycleUtil(int node, boolean[] visited, boolean[] recStack) {
        if (recStack[node]) return true; // Цикл знайдено
        if (visited[node]) return false;

        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : adjacencyList.get(node)) {
            if (hasCycleUtil(neighbor, visited, recStack)) {
                return true;
            }
        }

        recStack[node] = false;
        return false;
    }

    // Топологічне сортування
    public List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, result);
            }
        }

        Collections.reverse(result); // Порядок у топологічній черзі
        return result;
    }

    private void topologicalSortUtil(int node, boolean[] visited, List<Integer> result) {
        visited[node] = true;

        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, result);
            }
        }

        result.add(node);
    }

    // Тестування
    public static void main(String[] args) {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph(6);

        dag.addEdge(5, 2);
        dag.addEdge(5, 0);
        dag.addEdge(4, 0);
        dag.addEdge(4, 1);
        dag.addEdge(2, 3);
        dag.addEdge(3, 1);

        // Перевірка на цикли
        if (dag.hasCycle()) {
            System.out.println("Граф містить цикл. Це не DAG.");
        } else {
            System.out.println("Граф не містить циклів. Це DAG.");
            System.out.println("Топологічне сортування: " + dag.topologicalSort());
        }
    }
}
