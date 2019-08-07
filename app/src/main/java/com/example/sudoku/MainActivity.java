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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public static void selected(View view){
        Button button = (Button) view;
        choice = button.getText().toString();
        if(choice.equals("X")){
            choice = "";
        }

    }

    public static boolean choose(int y, int x){

        if(Sudoku.num[y][x] == 1) {
            if (choice.equals("")) {
                grid[y][x] = 0;
            } else {
                grid[y][x] = Integer.parseInt(choice);
            }
            return true;
        }
        return false;
    }
}
