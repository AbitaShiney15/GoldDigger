package com.example.abitashiney.golddiggertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Nose {
    int xPosition;
    int yPosition;
    int direction = -1;
    Bitmap noseImage;

    private Rect hitBox;
    private Rect hitBox2;
    public Nose(Context context, int x, int y) {
        this.noseImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.nose01);
        this.xPosition = x;
        this.yPosition = y;
        this.hitBox =new Rect(this.xPosition +100,
                this.yPosition +350,
                this.xPosition+ (this.noseImage.getWidth()/2) - 35, this.yPosition +this.noseImage.getHeight());
        this.hitBox2 =new Rect(this.xPosition + (this.noseImage.getWidth()/2) + 30,
                this.yPosition +350,
                this.xPosition+ (this.noseImage.getWidth()) -80 , this.yPosition +this.noseImage.getHeight());
    }

    public void updateNosePosition() {
         //update the position of the hitbox
//        this.updateHitbox();
//        this.updateHitbox2();
    }
  public void updateHitbox() {
        // update the position of the hitbox
      this.hitBox.top = this.yPosition;
      this.hitBox.left = this.xPosition;
      this.hitBox.right = this.xPosition + this.noseImage.getWidth();
      this.hitBox.bottom = this.yPosition + this.noseImage.getHeight();
    }

    public void updateHitbox2() {
        // update the position of the hitbox
        this.hitBox2.top = this.yPosition;
        this.hitBox2.left = this.xPosition;
        this.hitBox2.right = this.xPosition + this.noseImage.getWidth();
        this.hitBox2.bottom = this.yPosition + this.noseImage.getHeight();
    }

    public Rect getHitBox() {
        return this.hitBox;

    }
    public Rect getHitbox2() {
        return this.hitBox2;

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

    /**
     * Sets the direction of the player
     * @param i     0 = down, 1 = up
     */
    public void setDirection(int i) {
        this.direction = i;
    }
    public Bitmap getBitmap() {
        return this.noseImage;
    }


}
