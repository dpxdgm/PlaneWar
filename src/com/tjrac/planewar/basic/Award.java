package com.tjrac.planewar.basic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.tjrac.planewar.father.FlyObject;
import com.tjrac.planewar.frame.MyFrame;
import com.tjrac.planewar.pojo.Hero;

public class Award extends FlyObject{
	private int speed=1;
    private boolean isAlife=true;
    private Random random=new Random();
    private static BufferedImage[] awards=new BufferedImage[3];
    private int type=0;
    private double k;
    
    public Award() {
    	try {
			awards[0]=ImageIO.read(MyFrame.class.getResource("../resource/t1.bmp"));
			awards[1]=ImageIO.read(MyFrame.class.getResource("../resource/t2.bmp"));
			awards[2]=ImageIO.read(MyFrame.class.getResource("../resource/t3.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	this.type=random.nextInt(3);
    	this.image=awards[type];
		this.x=random.nextInt(300);
		this.y=-random.nextInt(150);
		this.width=20;
		this.height=20;
		this.k=(MyFrame.hero.y-this.y)/(MyFrame.hero.x-this.x);
	}
    public void draw(Graphics g) {
    	if (isAlife==false) {
			MyFrame.awardlist.remove(this);
			return;
		}
		g.drawImage(this.image, this.x, this.y,this.width,this.height, null);
		movetheobject();
    }
    
	private void movetheobject() {
		if (Double.isInfinite(k)) {
			y+=speed;
		}else if (k>0) {
			x+=speed;
			y+=k*speed;
		}else {
			x+=-speed;
			y+=-k*speed;
		}
		if(y>710) this.isAlife=false;
		if(x<-20) this.isAlife=false;
		if(x>420) this.isAlife=false;
		if(y<-180) this.isAlife=false;
	}
	
	public void awardHero(FlyObject plane) {
		Rectangle shape=plane.getRect();
		Hero myHero=(Hero)plane;
		switch (this.type) {
//			加血
		case 0:
			if (this.isAlife&&getRect().intersects(shape)) {
				myHero.addLife();
				this.isAlife=false;
			}
			break;
//			改变子弹外形     :待更新，先写写成加血
		case 1:
			if (this.isAlife&&getRect().intersects(shape)) {
				MyFrame.hero.setMybullettype(2);
				this.isAlife=false;
			}
			break;
//			改变火力		:待更新，先写写成加血
		case 2:
			if (this.isAlife&&getRect().intersects(shape)) {
				myHero.addLife();
				this.isAlife=false;
			}
			break;
		default:
			break;
		}
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
}
