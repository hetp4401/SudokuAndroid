package com.example.sudoku;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Sudoku{

    static int[][] num = new int[9][9];
    static int clues = 0;

    static boolean[][] box;
    static boolean[][] row;
    static boolean[][] col;



    public static int[][] solve(int[][] b){
        box = new boolean[9][9];
        row = new boolean[9][9];
        col = new boolean[9][9];

        solver(0, b);

        return b;
    }

    public static int[][] generateRandom() {
        box = new boolean[9][9];
        row = new boolean[9][9];
        col = new boolean[9][9];

        int[][] rand = new int[9][9];
        solver(0, rand);

        return rand;
    }

    public static boolean solver(int p, int[][] b ){
        for (; p < 81; p++) {
            int y = p / 9;
            int x = p % 9;
            if (b[p / 9][p % 9] != 0)
                continue;

            ArrayList<Integer> li = new ArrayList<Integer>();
            for(int i = 1; i <= 9; i++) li.add(i);
            Collections.shuffle(li);

            for (int i : li) {
                if (row[y][i-1] || col[x][i-1] || box[3 * ((y) / 3) + ((x) / 3)][i-1])
                    continue;

                b[p / 9][p % 9] = i;
                row[y][i-1] = true;
                col[x][i-1] = true;
                box[3 * ((y) / 3) + ((x) / 3)][i-1] = true;

                if (solver(p + 1, b))
                    return true;

                b[p / 9][p % 9] = 0;
                row[y][i-1] = true;
                col[x][i-1] = true;
                box[3 * ((y) / 3) + ((x) / 3)][i-1] = false;
            }
            return false;
        }
        return true;
    }
}
