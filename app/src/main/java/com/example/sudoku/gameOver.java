package com.example.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class gameOver extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        DisplayMetrics dp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dp);

        int height = dp.heightPixels;
        int width = dp.widthPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.2));

        Button b = (Button)findViewById(R.id.replay);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.choice = "";
                MainActivity.grid = Sudoku.generateRandom();
                MainActivity.solved = Sudoku.solve(MainActivity.grid);
                MainActivity.correct = Sudoku.clues;
                finish();
            }
        });
    }
}
