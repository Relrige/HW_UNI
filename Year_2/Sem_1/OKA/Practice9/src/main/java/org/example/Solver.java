package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solver {
    private int moves;
    private Stack<Board> solution;
    private Board init;

    public Solver(Board init) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(init, 0, null));
        this.init=init;
        Node currNode = null;
        if(!isSolvable()) {
            while (!pq.isEmpty()) {
                currNode = pq.poll();
                if (currNode.board.isGoal()) break;

                for (Board neighbor : currNode.board.neighbors()) {
                    if (currNode.prev == null || !neighbor.equals(currNode.prev.board)) {
                        pq.add(new Node(neighbor, currNode.moves + 1, currNode));
                    }
                }
            }

            if (currNode != null && currNode.board.isGoal()) {
                moves = currNode.moves;
                solution = new Stack<>();
                while (currNode != null) {
                    solution.push(currNode.board);
                    currNode = currNode.prev;
                }
                return;
            }
            moves = -1;
            solution = null;
        }
        else{
            moves = -1;
        }
    }

    public boolean isSolvable() {
        Board initboard = new Board(init.getBlocks());
        int[][] a = new int[initboard.getBlocks().length][];
        for (int i = 0; i < initboard.getBlocks().length; i++) {
            a[i] = initboard.getBlocks()[i].clone();
        }
        int counter = 0;
        int n = 1;
        for(int i = 0; i< a.length; i++){
            for(int j = 0; j<a.length;j++){
                if(a[i][j]==n){
                    counter+=moveToGoal(a[i][j],a, i, j);
                    i=0;
                    j=-1;
                    n++;
                }
            }
        }

        return counter%2==0;
    }
    private int moveToGoal(int curr, int[][] arr, int i, int j) {
        int counter = 0;
        int rows = arr.length;
        int cols = arr[0].length;
        int[] flatArray = new int[rows * cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                flatArray[row * cols + col] = arr[row][col];
            }
        }
        int targetIndex = curr - 1;
        int currentIndex = i * cols + j;
        while (currentIndex != targetIndex) {
            if (currentIndex < targetIndex) {
                swap(flatArray, currentIndex, ++currentIndex);
                if(flatArray[currentIndex]!=0&&flatArray[currentIndex-1]!=0)
                    counter++;
            } else {
                swap(flatArray, currentIndex, --currentIndex);
                if(flatArray[currentIndex]!=0&&flatArray[currentIndex+1]!=0)
                    counter++;
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                arr[row][col]=flatArray[row * cols + col];
            }
        }
        return counter;
    }


    private void swap(int[] arr, int i1, int j1) {
        int temp = arr[i1];
        arr[i1]= arr[j1];
        arr[j1] = temp;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    public static void main(String[] args) {
        String pathToFile = "C:\\Users\\stasp\\Desktop\\PazlTestFiles\\puzzle4x4.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathToFile));

            int size = Integer.parseInt(lines.get(0).trim());
            int[][] blocks = new int[size][size];

            for (int i = 0; i < size; i++) {
                String[] row = lines.get(i + 1).trim().split("\\s+");
                for (int j = 0; j < size; j++) {
                    blocks[i][j] = Integer.parseInt(row[j]);
                }
            }

            Board initial = new Board(blocks);
            Solver solver = new Solver(initial);

            if (!solver.isSolvable()) {
                System.out.println("Дошка не має розв’язку");
            } else {
                System.out.println("Мінімальна кількість кроків = " + solver.moves());
                for (Board board : solver.solution()) {
                    System.out.println(board);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
    private class Node implements Comparable<Node> {
        private final Board board;
        private final int moves;
        private final Node prev;
        private final int prior;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.prior = board.manhattan() + moves;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.prior, other.prior);
        }
    }
}
