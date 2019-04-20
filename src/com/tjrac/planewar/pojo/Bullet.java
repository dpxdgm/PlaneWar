package com.tjrac.planewar.pojo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.tjrac.planewar.father.FlyObject;
import com.tjrac.planewar.frame.MyFrame;

public class Bullet extends FlyObject{
	
    private int speed=5;

    private static MyFrame myFrame;
    private boolean isAlife=true;
	public Bullet(int x,int y){
		////
	    //System.out.println("ok");
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
	public void movetheobject() {
		y-=speed; 
	    //if(y < 0) y = 0;
	    if(y<-this.height) isAlife=false;
	}
//	@Override
//	public void movetheobject() {
//		this.setY(getY()-speed);	
//	}

	public void hitplanes(List<EnemyPlane> enemylist) {
		for (EnemyPlane enemyPlane : enemylist) {
			hitplane(enemyPlane,2);
		}
	}
	@Override
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
//									type 1的时候表示hero 2的时候表示敌机
	public void hitplane(FlyObject plane, int type) {
		Rectangle shape=plane.getRect();
		switch (type) {
		case 1:
			Hero hero=(Hero)plane;
			if (this.isAlife&&getRect().intersects(shape)) {
				hero.delLife();
			}
			break;
		case 2:
			EnemyPlane ePlane=(EnemyPlane)plane;
			if (this.isAlife&&getRect().intersects(shape)) {
				MyFrame.hero.myscore+=ePlane.getScore();
				ePlane.setAlife(false);
				this.isAlife=false;
			}
			break;
		}
	}
	

}
