package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    @ParameterizedTest
    @MethodSource("boards")
    public void test3x3(Board board, int expectedNumberOfSteps) {
        Solver solver = new Solver(board);
        assertEquals(expectedNumberOfSteps, solver.moves());
    }

    private static Stream<Arguments> boards() {
        return boards3x3();
    }

    private static Stream<Arguments> boards3x3() {
        return Stream.of(
            Arguments.of(new Board(new int[][]{{0, 6, 4}, {8, 5, 7}, {1, 3, 2}}), 26),
            Arguments.of(new Board(new int[][]{{5, 1, 2}, {3, 6, 0}, {4, 8, 7}}), 21),
            Arguments.of(new Board(new int[][]{{6, 8, 3}, {7, 1, 2}, {5, 4, 0}}), 26),
            Arguments.of(new Board(new int[][]{{6, 2, 7}, {1, 0, 8}, {3, 5, 4}}), 24),
            Arguments.of(new Board(new int[][]{{8, 4, 6}, {7, 0, 1}, {2, 3, 5}}), 24),
            Arguments.of(new Board(new int[][]{{5, 8, 2}, {1, 3, 0}, {7, 6, 4}}), 17),
            Arguments.of(new Board(new int[][]{{2, 3, 5}, {8, 4, 7}, {0, 6, 1}}), 22),
            Arguments.of(new Board(new int[][]{{2, 8, 6}, {4, 0, 5}, {3, 1, 7}}), 22),
            Arguments.of(new Board(new int[][]{{2, 6, 5}, {0, 3, 1}, {7, 4, 8}}), 23),
            Arguments.of(new Board(new int[][]{{5, 2, 8}, {0, 4, 3}, {1, 7, 6}}), 17),
            Arguments.of(new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}), 0),
            Arguments.of(new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}}), 1),
            Arguments.of(new Board(new int[][]{{8, 3, 7}, {2, 6, 4}, {1, 5, 0}}), -1),
            Arguments.of(new Board(new int[][]{{0, 1, 5}, {3, 8, 7}, {4, 6, 2}}), -1),
            Arguments.of(new Board(new int[][]{{5, 4, 1}, {7, 8, 0}, {3, 6, 2}}), -1),
            Arguments.of(new Board(new int[][]{{5, 7, 3}, {8, 1, 6}, {2, 4, 0}}), -1),
            Arguments.of(new Board(new int[][]{{0, 3, 5}, {8, 6, 4}, {7, 1, 2}}), -1),
            Arguments.of(new Board(new int[][]{{4, 0, 1}, {3, 8, 2}, {7, 6, 5}}), -1),
            Arguments.of(new Board(new int[][]{{1, 7, 6}, {8, 5, 2}, {4, 0, 3}}), -1),
            Arguments.of(new Board(new int[][]{{0, 6, 4}, {2, 5, 1}, {7, 8, 3}}), -1),
            Arguments.of(new Board(new int[][]{{0, 3, 7}, {5, 6, 1}, {2, 4, 8}}), -1),
            Arguments.of(new Board(new int[][]{{7, 3, 4}, {5, 6, 0}, {1, 8, 2}}), -1)
        );
    }

    private static Stream<Arguments> boards4x4() {
        return Stream.of(
                Arguments.of(new Board(new int[][]{{2, 3, 4, 1}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}}), -1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 0, 12}, {13, 14, 11, 15}}), 2),
//                Arguments.of(new Board(new int[][]{{1, 15, 9, 12}, {5, 3, 10, 4}, {11, 14, 7, 6}, {13, 0, 8, 2}}), 48),
//                Arguments.of(new Board(new int[][]{{1, 2, 7, 5}, {8, 3, 13, 9}, {15, 11, 10, 0}, {4, 6, 14, 12}}), 55),
//                Arguments.of(new Board(new int[][]{{15, 6, 10, 11}, {13, 7, 1, 14}, {8, 2, 5, 9}, {12, 3, 4, 0}}), 62),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {0, 9, 10, 11}, {13, 14, 15, 12}}), 4),
                Arguments.of(new Board(new int[][]{{1, 0, 3, 4}, {5, 2, 6, 8}, {9, 10, 11, 12}, {13, 14, 15, 7}}), 13),
                Arguments.of(new Board(new int[][]{{1, 3, 4, 2}, {5, 6, 7, 8}, {9, 10, 11, 0}, {13, 14, 15, 12}}), 17),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 0}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 4}}), 19),
                Arguments.of(new Board(new int[][]{{1, 2, 0, 4}, {5, 3, 6, 8}, {9, 10, 11, 12}, {13, 14, 7, 15}}), 22),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}}), 0),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 0}, {13, 14, 15, 12}}), 1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {6, 5, 7, 8}, {9, 0, 10, 12}, {13, 14, 11, 15}}), -1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 0, 12}, {13, 14, 15, 11}}), -1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 8, 7}, {9, 10, 11, 12}, {0, 13, 14, 15}}), -1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 0}, {5, 6, 4, 8}, {9, 10, 11, 12}, {13, 14, 15, 7}}), -1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {5, 0, 6, 8}, {9, 7, 11, 12}, {13, 14, 15, 10}}), -1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 0}, {9, 10, 11, 12}, {13, 14, 15, 8}}), -1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {0, 5, 6, 8}, {9, 10, 7, 12}, {13, 14, 15, 11}}), -1),
                Arguments.of(new Board(new int[][]{{0, 2, 3, 4}, {5, 1, 6, 8}, {9, 10, 11, 12}, {13, 14, 15, 7}}), -1),
                Arguments.of(new Board(new int[][]{{1, 2, 3, 4}, {9, 5, 6, 8}, {10, 11, 7, 12}, {13, 14, 15, 0}}), -1)
        );
    }
}
