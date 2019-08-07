package com.example.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class boardgen extends View {

    private int size = 9;
    private float cellPerSize = 0F;
    private int selectedRow = -1;
    private int selectedColumn= -1;

    private Paint thickpaint = new Paint();
    private Paint thinpaint = new Paint();
    private Paint cellSelect = new Paint();



    boardgen(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizePixel = Math.min(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(sizePixel,sizePixel);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        cellPerSize = (getWidth()/size);
        fillCellS(canvas);
        drawLines(canvas);

    }

    public void fillCellS(Canvas canvas){
        if(selectedColumn == -1 || selectedColumn == -1){
        }else{
            for(int r = 0; r < size; r++){
                for(int c = 0; c < size; c++){
                    if(r == selectedRow && c == selectedColumn){
                        if(MainActivity.choose(r,c))fillCell(canvas,r,c,cellSelect);
                    }
                    if(MainActivity.grid[r][c] != 0){
                        putNum(canvas,r,c);
                    }
                }
            }
        }
    }

    public void fillCell(Canvas canvas, int r, int c, Paint paint){
        canvas.drawRoundRect(c*cellPerSize,r*cellPerSize,(c+1)*cellPerSize,(r+1)*cellPerSize,1000,1000,paint);
        canvas.drawText(MainActivity.choice,(c*cellPerSize)+cellPerSize/3,(r*cellPerSize)+cellPerSize*2/3,thickpaint);
    }
    public void putNum(Canvas canvas,int r, int c){
        canvas.drawText(MainActivity.grid[r][c]+"",(c*cellPerSize)+cellPerSize/3,(r*cellPerSize)+cellPerSize*2/3,thickpaint);
    }

    public void drawLines(Canvas canvas){
        thickpaint.setStyle(Paint.Style.STROKE);
        thickpaint.setColor(Color.parseColor("#3686ff"));
        thickpaint.setTextSize(65); thickpaint.setLinearText(true);
        thickpaint.setStrokeWidth(13F);
        thinpaint.setStyle(Paint.Style.STROKE);
        thinpaint.setColor(Color.GRAY);
        thinpaint.setStrokeWidth(5F);

        cellSelect.setStyle(Paint.Style.FILL_AND_STROKE);
        cellSelect.setColor(Color.LTGRAY);
        canvas.drawRect(0F,0F,getWidth(),getHeight(),thickpaint);

        Paint paint;
        for(int i = 1; i <= size; i++){
            if(i % 3 == 0){
                paint = thickpaint;
            }else{
                paint = thinpaint;
            }
            canvas.drawLine(i*cellPerSize,0F,i*cellPerSize,getHeight(),paint);
            canvas.drawLine(0F,i*cellPerSize,getWidth(),i*cellPerSize,paint);

        }
    }
    public void touch( float r, float c){
        selectedRow = (int)(c/cellPerSize);
        selectedColumn = (int)(r/cellPerSize);
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touch(event.getX(),event.getY());
            return true;
        }else{
            return false;
        }
    }
}
