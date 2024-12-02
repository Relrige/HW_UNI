package my;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LabirynthSearch {
    private static Graph graph;
    private static int startNode;
    private static int lampNode;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("C:\\Users\\stasp\\Desktop\\input.txt"));
        int V = in.nextInt();
        int G = in.nextInt();
        graph = new Graph(V);
        startNode = 0;
        lampNode = new Random().nextInt(V-1)+1;
        lampNode = 9;
        System.out.println("Lamp at:"+lampNode);

        while (in.hasNextInt()) {
            int v = in.nextInt();
            int w = in.nextInt();
            graph.addEdge(v, w);
        }
        in.close();

        System.out.println("Find lamp by DFS:");
        findPathDFS();

        System.out.println("\nFind lamp by BFS:");
        findPathBFS();
    }

    private static void findPathDFS() {
        boolean[] visitedNodesInGraph = new boolean[graph.V()];

        int[] pathFromParent = new int[graph.V()];
        Arrays.fill(pathFromParent, -1);

        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        int counterVisitedNodes = 0;
        boolean isFound = false;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visitedNodesInGraph[current]) {
                continue;
            }
            visitedNodesInGraph[current] = true;
            counterVisitedNodes++;
            if (current == lampNode) {
                isFound = true;
                break;
            }
            for (int neighbor : graph.adj(current)) {
                if (!visitedNodesInGraph[neighbor]) {
                    stack.push(neighbor);
                    if (pathFromParent[neighbor] == -1) {
                        pathFromParent[neighbor] = current;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        if (isFound) {
            int node = lampNode;
            while (node != -1) {
                path.add(0, node);
                node = pathFromParent[node];
            }
        }
        printResultOfPath(isFound, path, counterVisitedNodes);
    }

    private static void findPathBFS() {
        boolean[] visitedNodesInGraph = new boolean[graph.V()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);

        int[] pathFromParent = new int[graph.V()];
        Arrays.fill(pathFromParent, -1);

        int counterVisitedNodes = 0;
        boolean found = false;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (visitedNodesInGraph[current]) continue;

            visitedNodesInGraph[current] = true;
            counterVisitedNodes++;

            if (current == lampNode) {
                found = true;
                break;
            }

            for (int neighbor : graph.adj(current)) {
                if (!visitedNodesInGraph[neighbor]) {
                    queue.add(neighbor);
                    if (pathFromParent[neighbor] == -1) {
                        pathFromParent[neighbor] = current;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        if (found) {
            int node = lampNode;
            while (node != -1) {
                path.add(0, node);
                node = pathFromParent[node];
            }
        }
        printResultOfPath(found, path, counterVisitedNodes);
    }


    private static void printResultOfPath(boolean found, List<Integer> path, int visitedNodes) {
        if (found) {
            System.out.println("Path to lamp: " + path);
            System.out.println("Length: " + (path.size() - 1));
        } else {
            System.out.println("Lamp was not found.");
        }
        System.out.println("Amount of visited nodes: " + visitedNodes);
    }
}
