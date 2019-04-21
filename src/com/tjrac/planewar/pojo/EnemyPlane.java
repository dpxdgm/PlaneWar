package com.tjrac.planewar.pojo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.tjrac.planewar.basic.Score;
import com.tjrac.planewar.father.Enemy;
import com.tjrac.planewar.frame.MyFrame;

public class EnemyPlane extends Enemy implements Score{
	private static MyFrame myFrame;
	public static BufferedImage Enemys[]=new BufferedImage[8];
	private int speed=2; //定义飞机速度
	private boolean isAlife=true;

	private List<Bullet> bulletlist=new LinkedList<>();
	private CreateEBThread eThred=null;
	//构造方法
	public EnemyPlane(int x,int y,MyFrame myFrame){
		try {
			Enemys[0]=ImageIO.read(MyFrame.class.getResource("../resource/enemy7.png"));
			Enemys[1]=ImageIO.read(MyFrame.class.getResource("../resource/enemy3.png"));
			Enemys[2]=ImageIO.read(MyFrame.class.getResource("../resource/enemy4.png"));
			Enemys[3]=ImageIO.read(MyFrame.class.getResource("../resource/enemy2.png"));
			Enemys[4]=ImageIO.read(MyFrame.class.getResource("../resource/enemy20.png"));
			Enemys[5]=ImageIO.read(MyFrame.class.getResource("../resource/enemy21.png"));
			Enemys[6]=ImageIO.read(MyFrame.class.getResource("../resource/enemy22.png"));
			Enemys[7]=ImageIO.read(MyFrame.class.getResource("../resource/enemy23.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.myFrame=myFrame;
		Random rand = new Random(); //随机数对象
		this.image=Enemys[rand.nextInt(8)];
		this.width=50;
		this.height=50;
		this.x=x;
		this.y=y;
		eThred=new CreateEBThread(this);
		eThred.start();
	}
	
	//击败一架敌机得分5
	public int getScore() {
		return 5;
	}

	public boolean isAlife() {
		return isAlife;
	}
	public void setAlife(boolean isAlife) {
		this.isAlife = isAlife;
	}
	public void draw(Graphics g){
		if (isAlife==false) {
			myFrame.enemylist.remove(this);
			return;
		}
		g.drawImage(this.image, this.x, this.y,this.width,this.height, null);
		movetheobject(); 
	}
	@Override
	public void movetheobject() {
		y+=speed;
		if(x>400+this.width) x=400-this.width; 
    	if(y < 0) y = 0;
    	if(y>700+this.height) isAlife=false;
	}
	@Override
	public String toString() {
		return "EnemyPlane [speed=" + speed + ", isAlife=" + isAlife + "]";
	}
	
	public List<Bullet> getBulletlist() {
		return bulletlist;
	}

	public void setBulletlist(List<Bullet> bulletlist) {
		this.bulletlist = bulletlist;
	}

	public class CreateEBThread extends Thread{
		private EnemyPlane ePlane;
		private Random random=new Random();
		public CreateEBThread(EnemyPlane ePlane) {
			this.ePlane=ePlane;
		}
		@Override
		public void run() {
			while (true) {
				if (MyFrame.gameState==1) {
					if (ePlane.isAlife) {
						if (ePlane.getBulletlist().size()<10||ePlane.getBulletlist()==null) {
							Bullet bullet = new Bullet(ePlane.x,ePlane.y,true);
							ePlane.getBulletlist().add(bullet);
						}
					}else {
						break;
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
