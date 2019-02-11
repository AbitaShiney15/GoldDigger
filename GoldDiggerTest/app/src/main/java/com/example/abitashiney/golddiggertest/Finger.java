package com.example.abitashiney.golddiggertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Finger {
    int xPosition;
    int yPosition;
    int direction = 0;
    Bitmap image;

    private Rect hitBox;

    public Finger(Context context, int x, int y) {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.finger01);
        this.xPosition = x;
        this.yPosition = y;

        this.hitBox = new Rect(this.xPosition, this.yPosition, this.xPosition + this.image.getWidth(), this.yPosition + this.image.getHeight());
    }

    public void updateFingerPosition() {
        // update the position of the hitbox
        this.hitBox.left = this.xPosition;
        this.hitBox.right = this.xPosition + this.image.getWidth();
        this.hitBox.top = this.yPosition;
        this.hitBox.bottom = this.yPosition + this.image.getHeight();
       this.updateHitbox();

        if (this.direction == 1) {
            // move up
            this.yPosition = this.yPosition - 15;
        }else{
            this.xPosition = this.xPosition + 25;
        }

    }

    public void updateHitbox() {
        // update the position of the hitbox

        this.hitBox.top = this.yPosition;
        this.hitBox.left = this.xPosition;
        this.hitBox.right = this.xPosition + this.image.getWidth();
        this.hitBox.bottom = this.yPosition + this.image.getHeight();
    }

    public Rect getHitbox() {
        return this.hitBox;
    }


    public void setXPosition(int x) {
        this.xPosition = x;
       this.updateHitbox();
    }
    public void setYPosition(int y) {
        this.yPosition = y;
       this.updateHitbox();
    }
    public int getXPosition() {
        return this.xPosition;
    }
    public int getYPosition() {
        return this.yPosition;
    }
    public void setDirection(int i) {
        this.direction = i;
    }
    public Bitmap getBitmap() {
        return this.image;
    }

}
