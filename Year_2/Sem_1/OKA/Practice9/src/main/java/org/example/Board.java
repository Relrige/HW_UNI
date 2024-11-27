package org.example;

import java.util.ArrayList;
import java.util.List;

public class Board implements Comparable<Board> {
    private static int size;
    private int[][] blocks;
    private int numberOfSteps;
    private int posOfBlank;

    // construct дошку у вигляді двовимірного масиву N на N
    public Board(int[][] blocks)  {
        this.blocks = blocks;
        size = blocks.length;
        numberOfSteps = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] == 0) posOfBlank = i*size + j;
            }
        }
    }
    // розмірність дошки N
    public int dimension(){
        return size;
    }

    public int[][] getBlocks() {
        return blocks;
    }

    public int hamming(){
        int hammingCounter = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(blocks[i][j] != i*dimension()+j){
                    hammingCounter++;
                }
            }
        }
        return hammingCounter;
    }
    // manheten sum
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j <dimension(); j++) {
                int value = blocks[i][j];
                if (value != 0) {
                    int row = (value - 1) / dimension();
                    int col = (value - 1) % dimension();
                    int x = Math.abs(i - row) + Math.abs(j - col);
                    count+=x;
                }
            }
        }
        return count;
    }

    // is goal
    public boolean isGoal() {
        return manhattan() == 0;
    }
    public  boolean equals(Board y){
        for(int i = 0; i< dimension(); i++){
            for(int j = 0; j< dimension(); j++){
                if(blocks[i][j]!=y.blocks[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    // string output
    public String toString(){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size; j++){
                res.append(blocks[i][j]).append(" ");
            }
            res.append('\n');
        }
        return res.toString();
    }

    @Override
    public int compareTo(Board o) {
        if (Integer.compare(this.manhattan(), o.manhattan()) != 0)
            return Integer.compare(this.manhattan(), o.manhattan());
        else
            return Integer.compare(this.hamming(), o.hamming());
    }

    public Iterable<Board> neighbors() {
        List<Board> neighborsArr = new ArrayList<>();
        int rowOfBlank=posOfBlank/dimension();
        int colOfBlank=posOfBlank%dimension();

        // all neighbors
        int[][] allDirections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : allDirections) {
            int row = rowOfBlank + dir[0];
            int col = colOfBlank + dir[1];
            if (row >= 0 && row < size && col>= 0 && col < size) {
                int[][] newBlocks = makeCopyBlocks();
                newBlocks[rowOfBlank][colOfBlank] = newBlocks[row][col];
                newBlocks[row][col] = 0;
                neighborsArr.add(new Board(newBlocks));
            }
        }
        return neighborsArr;
    }

    private int[][] makeCopyBlocks() {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(blocks[i], 0, copy[i], 0, size);
        }
        return copy;
    }
}
