package com.tjrac.planewar.pojo;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tjrac.planewar.father.FlyObject;
import com.tjrac.planewar.frame.MyFrame;

public class Bullet extends FlyObject{
	public static BufferedImage bullet1;
	public static BufferedImage bullet2;
	static{
	try {
		bullet1=ImageIO.read(MyFrame.class.getResource("../resource/bullet1.png"));
		bullet2=ImageIO.read(MyFrame.class.getResource("../resource/bullet2.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	//子弹的速度
    private int speed=3;
    private BufferedImage[] images; //可切换的图片数组
    //子弹的构造方法，初始化子弹的位置
	public Bullet(Hero hero){
	    this.setImage(Bullet.bullet1);
	    this.setWidth(this.getImage().getWidth());
	    this.setHeight(this.getImage().getHeight());
	    this.setX(400-hero.getWidth()*3-hero.getWidth()/2-3);
	    this.setY(700-hero.getHeight()*2-10);
	    images= new BufferedImage[]{Bullet.bullet1,Bullet.bullet2};  
	}
	public Bullet(int x,int y){
	    this.setX(x);
	    this.setY(y);
	    this.setImage(Bullet.bullet1);
	}

	@Override
	public void movetheobject() {
		this.setY(getY()-speed);	
	}

	@Override
	public boolean moveOut() {
		
		 return getY()<getHeight();
	}
	

}
