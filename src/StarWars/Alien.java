package StarWars;

import javax.swing.ImageIcon;

public class Alien {
	
	public ImageIcon image;
	public boolean collision = false;
	public int pos_X;
	public int pos_Y;
	public int Width;
	public int Height;
	public int speed;
	public int blood;
	
	public Alien(){}
	
	public ImageIcon Image() {
		return image;
	}
	public int getX(){
		return pos_X;
	}
	public void setX(int myX){
		this.pos_X = myX;
	}
	public int getY(){
		return pos_Y;
	}
	public  void setY(int myY){
		this.pos_Y = myY;
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int height) {
		Height = height;
	}
	public void FlyUP() {
    	pos_Y -= speed;
    	if (pos_Y < -30)  pos_Y = -30;
    }
    
    public void FlyDOWN() {
    	pos_Y += speed;
    	if (pos_Y+Height > 820)  pos_Y = 820-Height;
    }
    
    public void FlyLEFT() {
    	pos_X -= speed;
    	if (pos_X < 0)  pos_X = 0;
    }
    public void FlyRIGHT() {
    	pos_X += speed;
    	if (pos_X+Width > 600)  pos_X = 600-Width;
    }
	
}
