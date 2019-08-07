package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static String choice = "";
    static int[][] grid = Sudoku.generateRandom();
    static int[][] solved = Sudoku.solve(grid);
    static int correct = Sudoku.clues;
    static  View screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.board);

    }
    public static void selected(View view){
        Button button = (Button) view;
        choice = button.getText().toString();
        if(choice.equals("X")){
            choice = "";
        }
        choose(boardgen.selectedRow,boardgen.selectedColumn);
    }

    public static void choose(int y, int x){
        if(Sudoku.num[y][x] == 1){
            int add;
            if(grid[y][x] == solved[y][x]){
                add = -1;
            }else{
                add = 1;
            }

            if (choice.equals("")) {
                grid[y][x] = 0;
            }else {
                grid[y][x] = Integer.parseInt(choice);
            }

            if(add == -1){
                if(grid[y][x] == solved[y][x]){
                    add = 0;
                }else{
                    add = -1;
                }
            }else{
                if(grid[y][x] == solved[y][x]){
                    add = 1;
                }else{
                    add = 0;
                }
            }
            correct+=add;
        }
        screen.invalidate();
    }
}
