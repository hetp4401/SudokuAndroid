package com.example.sudoku;


import java.util.Random;

public class Sudoku{

    static int[][][] squares = {
            {{1,2,3,10,11,12,19,20,21}, {4,5,6,13,14,15,22,23,24}, {7,8,9,16,17,18,25,26,27}},
            {{28,29,30,37,38,39,46,47,48}, {31,32,33,40,41,42,49,50,51}, {34,35,36,43,44,45,52,53,54}},
            {{55,56,57,64,65,66,73,74,75}, {58,59,60,67,68,69,76,77,78}, {61,62,63,70,71,72,79,80,81}}};

    static int[][] num = new int[9][9];
    static int clues = 0;


    public static int[][] solve(int[][] s){
        int[][] backTrack = new int[9][9];
        int[][] sudoku = new int[9][9];

        for(int i = 0; i < 81; i++) {
            sudoku[i/9][i%9] = s[i/9][i%9];
            if(s[i/9][i%9] == 0) {
                backTrack[i/9][i%9] = 1;
                num[i/9][i%9] = 1;
            }else {
                backTrack[i/9][i%9] = 0;
                num[i/9][i%9] = 0;
                clues++;
            }
        }


        int index = 0; int direction = 1; boolean bool = false;
        while(index < 81) {
            if(backTrack[index/9][index%9] != 0) {
                bool = false;
                for(int i = backTrack[index/9][index%9]; i <= 9; i++) {
                    if(checkAll(index,i,sudoku)) {
                        backTrack[index/9][index%9] = i;
                        bool = true;
                        sudoku[index/9][index%9] = i;
                        direction = 1;
                        break;
                    }
                }
                if(bool == false) {
                    sudoku[index/9][index%9] = 0;
                    backTrack[index/9][index%9] = 1;
                    direction = -1;
                }
            }
            index+= direction;
        }
        return sudoku;
    }

    public static int[][] generateRandom() {
        Random rand = new Random();
        int[][] nums = new int[9][9];

        int index = 0;
        while(index<81) {
            int num = rand.nextInt(8)+1;
            while(checkAll(index,num,nums) == false) {
                num = rand.nextInt(8)+1;
            }
            nums[index/9][index%9] = num;
            index += rand.nextInt(4)+4;
        }
        return nums;
    }
    public static boolean checkAll(int cell, int testNum,int[][] sudoku) {
        if(checkRow(cell,testNum,sudoku) &&
                checkColumn(cell,testNum,sudoku) &&
                checkSquare(cell,testNum,sudoku)) {
            return true;
        }
        return false;
    }

    public static boolean checkRow(int cell, int testNum,int[][] sudoku) {
        for(int i = 0; i < 9; i++) {
            if(sudoku[cell/9][i] == testNum) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkColumn(int cell,int testNum,int[][] sudoku) {
        for(int i = 0; i < 9; i++) {
            if(sudoku[i][cell%9] == testNum) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSquare(int cell,int testNum,int[][] sudoku) {
        int x = cell/9; x /= 3;
        int y = cell%9; y /= 3;

        for(int i = 0; i < 9; i++) {
            if(sudoku[(squares[x][y][i]-1)/9][(squares[x][y][i]-1)%9] == testNum) {
                return false;
            }
        }
        return true;

    }
}
