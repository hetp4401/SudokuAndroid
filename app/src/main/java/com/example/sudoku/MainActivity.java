package com.example.sudoku;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity{

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


        String s= "";
        for(int i = 0; i < 81; i++){
            if(grid[i/9][i%9] == 0){
                s+=".";
            }else{
                s+=grid[i/9][i%9]+"";
            }
        }
        Log.i("sudoku",s);

    }

    public void winGame(){
       startActivity(new Intent(MainActivity.this,gameOver.class));
    }
    public void selected(View view){
        Button button = (Button) view;
        choice = button.getText().toString();
        if(choice.equals("X")){
            choice = "";
            startActivity(new Intent(MainActivity.this,gameOver.class));
        }
        choose(boardgen.selectedRow,boardgen.selectedColumn);
    }

    public  void choose(int y, int x){
        if(y > -1 && x > -1 && Sudoku.num[y][x] == 1){
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

            if(correct == 81) winGame();

        }
        screen.invalidate();
    }
}
