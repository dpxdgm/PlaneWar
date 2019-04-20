package com.tjrac.planewar.pojo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tjrac.planewar.father.FlyObject;
import com.tjrac.planewar.frame.MyFrame;

public class Bullet extends FlyObject{
	
    private int speed=3;

    private static MyFrame myFrame;
    private boolean isAlife=true;
	public Bullet(int x,int y){
		
	    System.out.println("ok");
	    try {
			this.image=ImageIO.read(MyFrame.class.getResource("../resource/m8.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    this.setWidth(20);
	    this.setHeight(20);
	    this.setX(x+15);
	    this.setY(y);
	}
	////////////
	public void draw(Graphics g){
		if (isAlife==true) {
			g.drawImage(this.image, this.x, this.y,this.width,this.height, null);
			movetheobject(); 
			return;
		}
		else {
			myFrame.bulletlist.remove(this);
			return ;
		}
	}
	//////
	public void draw(Graphics g,int herox,int heroy){
		if (isAlife==true) {
			g.drawImage(this.image, herox, heroy,this.width,this.height, null);
			movetheobject(); 
			return;
		}
		else {
			myFrame.bulletlist.remove(this);
			return ;
		}
	}
	public void movetheobject() {
		y-=speed; 
	    //if(y < 0) y = 0;
	    if(y<-this.height) isAlife=false;
	}
//	@Override
//	public void movetheobject() {
//		this.setY(getY()-speed);	
//	}

	@Override
	public boolean moveOut() {
		
		 return getY()<getHeight();
	}
	

}
