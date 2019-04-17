package com.tjrac.planewar.pojo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import com.tjrac.planewar.father.FlyObject;
import com.tjrac.planewar.frame.MyFrame;

public class Hero extends FlyObject{
	
	private int doubleFire; //火力值
	private int life; //命
	private BufferedImage[] images; //可切换的图片数组
	private int index; //协助图片切换
	
	
	//构造方法
	public Hero(){
		
		try {
			image=ImageIO.read(MyFrame.class.getResource("../resource/plane15.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width=50;
		height=50;
		doubleFire = 100; //默认为0(单倍火力)
		life = 3; //默认3条命
		index=0; //协助图片切换
		this.setX(200-this.getWidth()/2);
        this.setY(700-this.getHeight()*2);
	}
	
    public int getFire(){
        return doubleFire;
    }

    public void setFire(int fire) {
        this.doubleFire = fire;
    }

    public void addFire(){
    	doubleFire+=100;
    }

    public void addLife(){
        life++;
    }
    public void subLise(){
        life--;
    }
    public int getLife(){
        return life;
    }
    //发射子弹
    public Bullet[] shoot(){
        int xStep=getWidth()/4;
        int yStep=20;
       if(doubleFire==200){
            Bullet[] bullets=new Bullet[2];
            bullets[0]=new Bullet(getX()+xStep,getY()-yStep);
            bullets[1]=new Bullet(getX()+3*xStep,getY()-yStep);
            return bullets;
        }else if(doubleFire>=300){
            Bullet[] bullets=new Bullet[3];
            bullets[0]=new Bullet(getX()+xStep,getY()-yStep);
            bullets[1]=new Bullet(getX()+3*xStep,getY()-yStep);
            bullets[2]=new Bullet(getX()+2*xStep,getY()-yStep);
            return bullets;
        }else {
            Bullet[] bullets=new Bullet[1];
            bullets[0]=new Bullet(getX()+xStep,getY()-yStep);
            return bullets;
        }
    }
   //当前物体移动一下，相对距离，x,y鼠标位置
//    public void  moveTo(int x,int y){
//        this.setX(x-Hero.Hero1.getWidth()/2);
//        this.setY(y-Hero.Hero1.getHeight()/2);
//    }
    //飞机移动
	@Override
	public void movetheobject() {
		if(images.length>0){
            this.setImage(images[index++/10%images.length]);
        }
	}
	//检查是否出界
	@Override
	public boolean moveOut() {
		return false;
	}
	//碰撞算法
    public boolean hit(FlyObject object){
        int x1=object.getX()-this.getWidth()/2;
        int x2=object.getX()+this.getWidth()/2+object.getWidth();
        int y1=object.getY()-this.getHeight()/2;
        int y2=object.getY()+this.getHeight()/2+object.getHeight();
        int herox=this.getX()+this.getWidth()/2;
        int heroy=this.getY()+this.getHeight()/2;
        return herox>x1&&herox<x2&&heroy>y1&&heroy<y2;
    }

	@Override
	public String toString() {
		return "Hero [doubleFire=" + doubleFire + ", life=" + life + ", images=" + Arrays.toString(images) + ", index="
				+ index + "]";
	}

}
