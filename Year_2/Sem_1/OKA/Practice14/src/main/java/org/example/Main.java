package org.example;

import my.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        try {
            File input = new File("C:\\Users\\stasp\\Desktop\\photos\\pic.jpg");
            BufferedImage image = ImageIO.read(input);

            int width = image.getWidth();
            int height = image.getHeight();

            int[][][] rgbMatrix = new int[height][width][3];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    rgbMatrix[y][x] = new int[]{red, green, blue};
                }
            }

            int amountForHorizontal = 150;
            int amountForVertical = 150;
            boolean removeHorizontal = false;
            boolean removeVertical = false;
            boolean addVertical = !removeVertical;
            boolean addHorizontal = !removeHorizontal;

            if (removeHorizontal) {
                for (int i = 0; i < amountForHorizontal; i++) {
                    int[][] energyMatrix = computeEnergyMatrix(rgbMatrix);
                    int[] seamPath = findHorizontalSeam(energyMatrix);
                    rgbMatrix = removeHorizontalSeam(rgbMatrix, seamPath);
                }
                height -= amountForHorizontal;
            }
            if (removeVertical) {
                for (int i = 0; i < amountForVertical; i++) {
                    int[][] energyMatrix = computeEnergyMatrix(rgbMatrix);
                    int[] seamPath = findVerticalSeam(energyMatrix);
                    rgbMatrix = removeVertSeam(rgbMatrix, seamPath);
                }
                width -= amountForVertical;
            }
            if (addVertical) {
                boolean[][] mask = new boolean[rgbMatrix.length][rgbMatrix[0].length];
                for (int y = 0; y < mask.length; y++) {
                    Arrays.fill(mask[y], true);
                }

                for (int i = 0; i < amountForVertical; i++) {
                    int[][] energyMatrix = computeEnergyMatrix(rgbMatrix);

                    applyMaskToEnergyMatrix(energyMatrix, mask);

                    int[] seamPath = findVerticalSeam(energyMatrix);
                    rgbMatrix = addVertSeamWithMask(rgbMatrix, seamPath, mask);
                }
                width += amountForVertical;
            }
            if (addHorizontal) {
                boolean[][] mask = new boolean[rgbMatrix.length][rgbMatrix[0].length];
                for (int y = 0; y < mask.length; y++) {
                    Arrays.fill(mask[y], true);
                }

                for (int i = 0; i < amountForHorizontal; i++) {
                    int[][] energyMatrix = computeEnergyMatrix(rgbMatrix);

                    applyMaskToEnergyMatrix(energyMatrix, mask);

                    int[] seamPath = findHorizontalSeam(energyMatrix);
                    rgbMatrix = addHorizontalSeamWithMask(rgbMatrix, seamPath, mask);
                    mask = resizeMask(mask, seamPath, true);
                }
                height += amountForHorizontal;
            }

            BufferedImage carvedImage = createImageFromMatrix(rgbMatrix, width, height);
            File output = new File("C:\\Users\\stasp\\Desktop\\image.jpg");
            ImageIO.write(carvedImage, "jpg", output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static BufferedImage createImageFromMatrix(int[][][] rgbMatrix, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] rgb = rgbMatrix[y][x];
                int color = (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
                image.setRGB(x, y, color);
            }
        }
        return image;
    }

    private static int[][] computeEnergyMatrix(int[][][] rgbMatrix) {
        int height = rgbMatrix.length;
        int width = rgbMatrix[0].length;
        int[][] energyMatrix = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] left = (x > 0) ? rgbMatrix[y][x - 1] : rgbMatrix[y][x];
                int[] right = (x < width - 1) ? rgbMatrix[y][x + 1] : rgbMatrix[y][x];
                int[] top = (y > 0) ? rgbMatrix[y - 1][x] : rgbMatrix[y][x];
                int[] bottom = (y < height - 1) ? rgbMatrix[y + 1][x] : rgbMatrix[y][x];

                int[] topLeft = (y > 0 && x > 0) ? rgbMatrix[y - 1][x - 1] : rgbMatrix[y][x];
                int[] topRight = (y > 0 && x < width - 1) ? rgbMatrix[y - 1][x + 1] : rgbMatrix[y][x];
                int[] bottomLeft = (y < height - 1 && x > 0) ? rgbMatrix[y + 1][x - 1] : rgbMatrix[y][x];
                int[] bottomRight = (y < height - 1 && x < width - 1) ? rgbMatrix[y + 1][x + 1] : rgbMatrix[y][x];

                int dx = computeSquareDifference(left, right) +
                        computeSquareDifference(topLeft, bottomRight) +
                        computeSquareDifference(bottomLeft, topRight);

                int dy = computeSquareDifference(top, bottom) +
                        computeSquareDifference(topLeft, bottomRight) +
                        computeSquareDifference(topRight, bottomLeft);

                energyMatrix[y][x] = dx + dy;
            }
        }

        return energyMatrix;
    }
    private static int computeSquareDifference(int[] pixel1, int[] pixel2) {
        int dr = pixel1[0] - pixel2[0];
        int dg = pixel1[1] - pixel2[1];
        int db = pixel1[2] - pixel2[2];
        return dr * dr + dg * dg + db * db;
    }

    public static int[] findVerticalSeam(int[][] energy) {
        int rows = energy.length;
        int cols = energy[0].length;

        int amountOfNodes = rows * cols;
        int[] distTo = new int[amountOfNodes];
        int[] edgeTo = new int[amountOfNodes];

        Arrays.fill(distTo, Integer.MAX_VALUE);
        Arrays.fill(edgeTo, -1);

        IndexMinPQ<Integer> pq = new IndexMinPQ<>(amountOfNodes);
        for (int col = 0; col < cols; col++) {
            int startNode = col;
            distTo[startNode] = energy[0][col];
            pq.insert(startNode, distTo[startNode]);
        }
        while (!pq.isEmpty()) {
            int current = pq.delMin();
            int currRow = current / cols;
            int currCol = current % cols;
            if (currRow < rows - 1) {
                for (int nextPixel = -1; nextPixel <= 1; nextPixel++) {
                    int neighborCol = currCol + nextPixel;
                    if (neighborCol >= 0 && neighborCol < cols) {
                        int neighbor = (currRow + 1) * cols + neighborCol;
                        int newDist = distTo[current] + energy[currRow + 1][neighborCol];
                        if (newDist < distTo[neighbor]) {
                            distTo[neighbor] = newDist;
                            edgeTo[neighbor] = current;
                            if (pq.contains(neighbor)) {
                                pq.changeKey(neighbor, newDist);
                            } else {
                                pq.insert(neighbor, newDist);
                            }
                        }
                    }
                }
            }
        }

        int minD = Integer.MAX_VALUE;
        int minI = -1;
        for (int i = 0; i < cols; i++) {
            int checkNode = (rows - 1) * cols + i;
            if (distTo[checkNode] < minD) {
                minD = distTo[checkNode];
                minI = checkNode;
            }
        }

        int[] path = new int[rows];
        int curr = minI;
        for (int i = rows - 1; i >= 0; i--) {
            path[i] = curr % cols;
            curr = edgeTo[curr];
        }
        return path;
    }

    public static int[] findHorizontalSeam(int[][] energy) {
       energy=transposeMatrix(energy);
       int[] path = findVerticalSeam(energy);
       return path;
    }

    private static int[][] transposeMatrix(int[][] energy) {
        int[][] tmp = new int[energy[0].length][energy.length];
        for (int i = 0; i < energy.length; i++)
            for (int j = 0; j < energy[0].length; j++)
                tmp[j][i] = energy[i][j];
        return tmp;
    }

    private static int[][][] removeVertSeam(int[][][] rgbMatrix, int[] seamPath) {
        int height = rgbMatrix.length;
        int width = rgbMatrix[0].length - 1;
        int[][][] newRGBMatrix = new int[height][width][3];

        for (int y = 0; y < height; y++) {
            int index = 0;
            for (int x = 0; x < rgbMatrix[0].length; x++) {
                if (x != seamPath[y]) {
                    newRGBMatrix[y][index++] = rgbMatrix[y][x];
                }
            }
        }
        return newRGBMatrix;
    }
    private static int[][][] removeHorizontalSeam(int[][][] rgbMatrix, int[] seamPath) {
        int height = rgbMatrix.length - 1;
        int width = rgbMatrix[0].length;
        int[][][] newRGBMatrix = new int[height][width][3];

        for (int x = 0; x < width; x++) {
            int index = 0;
            for (int y = 0; y < rgbMatrix.length; y++) {
                if (y != seamPath[x]) {
                    newRGBMatrix[index++][x] = rgbMatrix[y][x];
                }
            }
        }
        return newRGBMatrix;
    }

    public static int[][][] addHorizontalSeamWithMask(int[][][] rgbMatrix, int[] seamPath, boolean[][] mask) {
        int height = rgbMatrix.length;
        int width = rgbMatrix[0].length;

        int[][][] newRGBMatrix = new int[height + 1][width][3];
        boolean[][] newMask = new boolean[height + 1][width];

        for (int x = 0; x < width; x++) {
            int seamRow = seamPath[x];
            int newRow = 0;

            for (int y = 0; y < height; y++) {
                if (y < seamRow) {
                    newRGBMatrix[newRow][x] = rgbMatrix[y][x];
                    newMask[newRow++][x] = mask[y][x];
                } else if (y == seamRow) {
                    newRGBMatrix[newRow][x] = rgbMatrix[y][x];
                    newMask[newRow++][x] = false;

                    int[] previousPixel = (y > 0) ? rgbMatrix[y - 1][x] : rgbMatrix[y][x];
                    int[] nextPixel = (y < height - 1) ? rgbMatrix[y + 1][x] : rgbMatrix[y][x];
                    int[] averagedPixel = calculateAverageColor(previousPixel, nextPixel);

                    newRGBMatrix[newRow][x] = averagedPixel;
                    newMask[newRow++][x] = false;
                } else {
                    newRGBMatrix[newRow][x] = rgbMatrix[y][x];
                    newMask[newRow++][x] = mask[y][x];
                }
            }
        }

        for (int i = 0; i < height; i++) {
            mask[i] = newMask[i];
        }
        return newRGBMatrix;
    }
    public static int[][][] addVertSeamWithMask(int[][][] rgbMatrix, int[] seamPath, boolean[][] mask) {
        int height = rgbMatrix.length;
        int width = rgbMatrix[0].length;

        int[][][] newRGBMatrix = new int[height][width + 1][3];
        boolean[][] newMask = new boolean[height][width + 1];

        for (int y = 0; y < height; y++) {
            int seamCol = seamPath[y];
            int newCol = 0;

            for (int x = 0; x < width; x++) {
                if (x < seamCol) {
                    newRGBMatrix[y][newCol] = rgbMatrix[y][x];
                    newMask[y][newCol++] = mask[y][x];
                } else if (x == seamCol) {
                    newRGBMatrix[y][newCol] = rgbMatrix[y][x];
                    newMask[y][newCol++] = false;

                    int[] previousPixel = (x > 0) ? rgbMatrix[y][x - 1] : rgbMatrix[y][x];
                    int[] nextPixel = (x < width - 1) ? rgbMatrix[y][x + 1] : rgbMatrix[y][x];
                    int[] averagedPixel = calculateAverageColor(previousPixel, nextPixel);

                    newRGBMatrix[y][newCol] = averagedPixel;
                    newMask[y][newCol++] = false;
                } else {
                    newRGBMatrix[y][newCol] = rgbMatrix[y][x];
                    newMask[y][newCol++] = mask[y][x];
                }
            }
        }

        for (int i = 0; i < height; i++) {
            mask[i] = newMask[i];
        }

        return newRGBMatrix;
    }

    private static void applyMaskToEnergyMatrix(int[][] energyMatrix, boolean[][] mask) {
        for (int y = 0; y < energyMatrix.length; y++) {
            for (int x = 0; x < energyMatrix[0].length; x++) {
                if (!mask[y][x]) {
                    energyMatrix[y][x] += 2345;
                }
            }
        }
    }
    private static int[] calculateAverageColor(int[] pixel1, int[] pixel2) {
        return new int[] {
                (pixel1[0] + pixel2[0]) / 2,
                (pixel1[1] + pixel2[1]) / 2,
                (pixel1[2] + pixel2[2]) / 2
        };
    }
    public static boolean[][] resizeMask(boolean[][] mask, int[] seamPath, boolean isHorizontal) {
        int height = mask.length;
        int width = mask[0].length;
        if (isHorizontal) {
            boolean[][] newMask = new boolean[height + 1][width];
            for (int x = 0; x < width; x++) {
                int seamRow = seamPath[x];
                int newRow = 0;
                for (int y = 0; y < height; y++) {
                    if (y <= seamRow) {
                        newMask[newRow++][x] = mask[y][x];
                    }
                }
                newMask[newRow++][x] = false;
                for (int y = seamRow + 1; y < height; y++) {
                    newMask[newRow++][x] = mask[y][x];
                }
            }
            return newMask;
        } else {
            boolean[][] newMask = new boolean[height][width + 1];
            for (int y = 0; y < height; y++) {
                int seamCol = seamPath[y];
                int newCol = 0;
                for (int x = 0; x < width; x++) {
                    if (x <= seamCol) {
                        newMask[y][newCol++] = mask[y][x];
                    }
                }
                newMask[y][newCol++] = false;
                for (int x = seamCol + 1; x < width; x++) {
                    newMask[y][newCol++] = mask[y][x];
                }
            }
            return newMask;
        }
    }

    public static int[] findSeamDijkstra2(int[][] grayMatrix) {
    int rows = grayMatrix.length;
    int cols = grayMatrix[0].length;

    int totalV = rows * cols + 2;
    int source = totalV-2;
    int sink = totalV-1;

    EdgeWeightedDigraph G = new EdgeWeightedDigraph(totalV);

    for (int col = 0; col < cols; col++) {
        G.addEdge(new DirectedEdge(source, col, grayMatrix[0][col]));
    }

    for (int row = 0; row < rows - 1; row++) {
        for (int col = 0; col < cols; col++) {
            int curr = row * cols + col;

            int cValue = grayMatrix[row + 1][col];
            G.addEdge(new DirectedEdge(curr, curr + cols, cValue));

            if (col > 0) {
                int lValue = grayMatrix[row + 1][col - 1];
                G.addEdge(new DirectedEdge(curr, curr + cols - 1, lValue));
            }

            if (col < cols - 1) {
                int rValue = grayMatrix[row + 1][col + 1];
                G.addEdge(new DirectedEdge(curr, curr + cols + 1, rValue));
            }
        }
    }

    for (int col = 0; col < cols; col++) {
        int lastRowPixel = (rows - 1) * cols + col;
        G.addEdge(new DirectedEdge(lastRowPixel, sink, 0));
    }

    DijkstraSP sp = new DijkstraSP(G, source);

    int[] path = new int[rows];
    int index = 0;

    for (DirectedEdge e : sp.pathTo(sink)) {
        int from = e.from();
        int to = e.to();

        if (from == source) continue;
        if (to == sink) break;

        path[index++] = from % cols;
    }

    return path;
}
    private static int[] findSeamGreedy(int[][] grayMatrix) {
        int height = grayMatrix.length;
        int width = grayMatrix[0].length;

        int minIndex = 0;
        int[] npath = new int[height];

        int minValue = Integer.MAX_VALUE;
        for (int x = 0; x < width; x++) {
            if (grayMatrix[0][x] < minValue) {
                minValue = grayMatrix[0][x];
                minIndex = x;
            }
        }
        npath[0] = minIndex;

        for (int y = 1; y < height; y++) {
            int previousMinIndex = minIndex;
            minValue = Integer.MAX_VALUE;

            for (int x = Math.max(0, previousMinIndex - 1); x <= Math.min(width - 1, previousMinIndex + 1); x++) {
                if (grayMatrix[y][x] < minValue) {
                    minValue = grayMatrix[y][x];
                    minIndex = x;
                }
            }
            npath[y] = minIndex;
        }

        return npath;
    }
    private static int[] findSeamV2(int[][] grayMatrix) {
        int rows = grayMatrix.length;
        int cols = grayMatrix[0].length;

        int[][] minImageGrad = new int[rows][cols];

        for (int x = 0; x < cols; x++) {
            minImageGrad[0][x] = grayMatrix[0][x];
        }

        for (int y = 0; y < rows-1; y++) {
            for (int x = 0; x < cols; x++) {
                int left = (x > 0) ? minImageGrad[y + 1][x - 1] : Integer.MAX_VALUE;
                int mid = minImageGrad[y + 1][x];
                int right = (x < cols - 1) ? minImageGrad[y + 1][x + 1] : Integer.MAX_VALUE;

                minImageGrad[y][x] = grayMatrix[y][x] + Math.min(mid, Math.min(left, right));
            }
        }

        int minIndex = Integer.MAX_VALUE;
        int[] path = new int[rows];

        int minValue = Integer.MAX_VALUE;
        for (int x = 0; x < cols; x++) {
            if (grayMatrix[rows - 1][x] < minValue) {
                minValue = grayMatrix[rows - 1][x];
                minIndex = x;
            }
        }
        path[0] = minIndex;

        for (int y = rows-1; y>0; y--) {
            int previousMinIndex = minIndex;
            minValue = Integer.MAX_VALUE;

            for (int x = Math.max(0, previousMinIndex - 1); x <= Math.min(cols - 1, previousMinIndex + 1); x++) {
                if (grayMatrix[y][x] < minValue) {
                    minValue = grayMatrix[y][x];
                    minIndex = x;
                }
            }
            path[y] = minIndex;
        }
        return path;
    }
}
