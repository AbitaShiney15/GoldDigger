package com.example.abitashiney.golddiggertest;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Rect;

import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="Gold Digger";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;


    Nose nose;
    Finger finger;
    int loss=0;
    int nosemissed = 0;
    int nosepicked = 0;

    public GameEngine(Context context, int w, int h) {
        super(context);


        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;


        this.printScreenInfo();

        // @TODO: Add your sprites
        this.spawnNose();
        this.spawnFinger();
        // @TODO: Any other game setup

    }


    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnNose() {
        //@TODO: Start the player at the left side of screen
        nose = new Nose(this.getContext(), 500, 50);

    }
    private void spawnFinger() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location
        finger = new Finger(this.getContext(), 200, 600);
    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void updatePositions() {
        nose.updateNosePosition();
        finger.updateFingerPosition();

        // @TODO: Collision detection between two
        if (finger.getXPosition() >= this.screenWidth) {
            finger.setXPosition(0);
        }
        if (nose.getHitBox().intersect(finger.getHitbox())) {
            // reduce lives
            nosepicked++;
            // reset player to original position
            finger.setXPosition(0);


        }else{
            nosemissed++;
        }

//        if(finger.getHitbox().intersect(this.g)){
//            loss++;
//            canvas.drawText("You lost: " , 100, 800, paintbrush);
//        }

    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);


            //@TODO: Draw the nose
            canvas.drawBitmap(this.nose.getBitmap(), this.nose.getXPosition(), this.nose.getYPosition(), paintbrush);

            //@TODO: Draw the finger
            canvas.drawBitmap(this.finger.getBitmap(), this.finger.getXPosition(), this.finger.getYPosition(), paintbrush);

            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);

            Rect fingerHitbox = finger.getHitbox();
            canvas.drawRect( fingerHitbox.left,
                    fingerHitbox.top,
                    fingerHitbox.right,
                    fingerHitbox.bottom,
                    paintbrush);

            paintbrush.setColor(Color.RED);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);

            Rect noseHitbox = nose.getHitBox();
            canvas.drawRect( noseHitbox.left,
                    noseHitbox.top,
                    noseHitbox.right,
                    noseHitbox.bottom,
                    paintbrush);

            Rect noseHitbox2 = nose.getHitbox2();
            canvas.drawRect( noseHitbox2.left,
                    noseHitbox2.top,
                    noseHitbox2.right,
                    noseHitbox2.bottom,
                    paintbrush);

            paintbrush.setTextSize(60);
            paintbrush.setColor(Color.BLACK);
            canvas.drawText("Nose Picked"+ nosepicked , 100, 200, paintbrush);
            canvas.drawText("Nose Missed"+ nosemissed , 100, 300, paintbrush);

            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {
            // move player up
            this.finger.setDirection(1);
           // Log.d("PUSH", "PERSON CLICKED AT: (" + event.getX() + "," + event.getY() + ")");

        }
        else  if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d("Loss", "finger tapped the screen");
        }
        return true;
    }
}
