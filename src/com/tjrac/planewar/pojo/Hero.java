package com.tjrac.planewar.pojo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.tjrac.planewar.basic.CreateBulletThread;
import com.tjrac.planewar.basic.Direction;
import com.tjrac.planewar.father.FlyObject;
import com.tjrac.planewar.frame.MyFrame;

public class Hero extends FlyObject{
	
	private int doubleFire;
	private int life;
	private BufferedImage[] images;
	private int index;
	
	public static int myscore=0;
	private Direction dir = Direction.STOP;
	private boolean bL,bU,bR,bD;
	private int speed=10;
	
	private CreateBulletThread cBulletThread=null;
	private MyFrame myFrame;
	////////
	public boolean isfire = false;
	public Hero(MyFrame myFrame){
		this.myFrame=myFrame;
		try {
			image=ImageIO.read(MyFrame.class.getResource("../resource/plane15.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width=50;
		height=50;
		doubleFire = 100;
		life = 3;
		index=0;
		this.setX(200-this.getWidth()/2);
        this.setY(700-this.getHeight()*2);
        cBulletThread=new CreateBulletThread(myFrame);
		cBulletThread.start();
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
    public void delLife(){
        life--;
    }
    public int getLife(){
        return life;
    }
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
    public void moveHeroPress(KeyEvent e) {
    	int key = e.getKeyCode();  
	    switch(key) {  
	    case KeyEvent.VK_LEFT :  
	        bL = true;  
	        break;  
	    case KeyEvent.VK_UP :  
	        bU = true;  
	        break;  
	    case KeyEvent.VK_RIGHT :  
	        bR = true;  
	        break;  
	    case KeyEvent.VK_DOWN :  
	        bD = true;  
	        break;  
	    } 
	    locateDirection();
	}
    void locateDirection() { 
	    if(bL && !bU && !bR && !bD) dir = Direction.L;  
	    else if(bL && bU && !bR && !bD) dir = Direction.LU;  
	    else if(!bL && bU && !bR && !bD) dir = Direction.U;  
	    else if(!bL && bU && bR && !bD) dir = Direction.RU;  
	    else if(!bL && !bU && bR && !bD) dir = Direction.R;  
	    else if(!bL && !bU && bR && bD) dir = Direction.RD;  
	    else if(!bL && !bU && !bR && bD) dir = Direction.D;  
	    else if(bL && !bU && !bR && bD) dir = Direction.LD;  
	    else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;  
	}

	public void moveHeroRelease(KeyEvent e) {
		int key = e.getKeyCode();  
	    switch(key) {  
	    case KeyEvent.VK_SPACE:  
	    case KeyEvent.VK_CONTROL:  
	    	fire();
	        break;  
	    case KeyEvent.VK_LEFT :  
	        bL = false;  
	        break;  
	    case KeyEvent.VK_UP :  
	        bU = false;  
	        break;  
	    case KeyEvent.VK_RIGHT :  
	        bR = false;  
	        break;  
	    case KeyEvent.VK_DOWN :  
	        bD = false;  
	        break;  
	    }
	    locateDirection();
	}
	public void draw(Graphics g){
		if (life==0||life<0) {
			myFrame.gameState=2;
			return;
		}
		g.drawImage(this.image, this.x, this.y,this.getWidth(),this.getHeight(), null);
		move(); 
	}
	
	private void move() {
		switch(dir) {  
	    case L:  
	        x -= speed;  
	        break;  
	    case LU:  
	        x -= speed;  
	        y -= speed;  
	        break;  
	    case U:  
	        y -= speed;  
	        break;  
	    case RU:  
	        x += speed;  
	        y -= speed;  
	        break;  
	    case R:  
	        x += speed;  
	        break;  
	    case RD:  
	        x += speed;  
	        y += speed;  
	        break;  
	    case D:  
	        y += speed;  
	        break;  
	    case LD:  
	        x -= speed;  
	        y += speed;  
	        break;  
	    case STOP:  
	        break;  
	    }
		if(x < 0) x = 0;  
    	if(y < 0) y = 0;  
    	if(x + width > 400) x = 400 - width;  
    	if(y + height > 700) y = 700 - height;
		
	}

	private void fire() {

		if(this.isfire==true)
		{
			this.isfire=false;
		}
		else
		{
			this.isfire=true;
		}
	}

}
