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
    private boolean isEnemy=false;
	public Bullet(int x,int y,boolean isEnemy){
		if (!isEnemy) {
			try {
				this.image=ImageIO.read(MyFrame.class.getResource("../resource/m8.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setWidth(20);
		    this.setHeight(20);
		    this.setX(x+15);
		} else {
			try {
				this.image=ImageIO.read(MyFrame.class.getResource("../resource/em7.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    this.setWidth(10);
		    this.setHeight(10);
		    this.setX(x+20);
		}
		this.isEnemy=isEnemy;
	    this.setY(y);
	}
	
//	改变外形构造方法
	public Bullet(int x, int y, boolean isEnemy, int i) {
		if (!isEnemy) {
			try {
				this.image=ImageIO.read(MyFrame.class.getResource("../resource/m11.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setWidth(20);
		    this.setHeight(20);
		    this.setX(x+15);
		} else {
			try {
				this.image=ImageIO.read(MyFrame.class.getResource("../resource/em7.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    this.setWidth(10);
		    this.setHeight(10);
		    this.setX(x+20);
		}
		this.isEnemy=isEnemy;
	    this.setY(y);
	}
	////////////
	public void draw(Graphics g){
		if (isAlife) {
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
		if (!isEnemy) {
			y-=speed; 
		} else {
			y+=speed; 
		}
	    if(y<-this.height) isAlife=false;
	    if(y>700+this.height) isAlife=false;
	}

	public void hitplanes(List<EnemyPlane> enemylist) {
		for (int i = 0; i < enemylist.size(); i++) {
			EnemyPlane enemyPlane=enemylist.get(i);
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
				this.isAlife=false;
			}
			break;
		case 2:
			EnemyPlane ePlane=(EnemyPlane)plane;
			if (this.isAlife&&getRect().intersects(shape)) {
				MyFrame.hero.myscore+=ePlane.getScore();
				MyFrame.explodelist.add(new Explode(ePlane.x, ePlane.y));
				ePlane.setAlife(false);
				this.isAlife=false;
			}
			break;
		}
	}
	

}
