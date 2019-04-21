package com.tjrac.planewar.basic;

import java.util.Random;

import com.tjrac.planewar.frame.MyFrame;
import com.tjrac.planewar.pojo.Bullet;
import com.tjrac.planewar.pojo.EnemyPlane;
import com.tjrac.planewar.pojo.Hero;
/////
public class CreateBulletThread extends Thread{
	//////
	//private MyFrame myFrame;
	private MyFrame myFrame;
	private Random random=new Random();
	private int bulletx;
	private int bullety;
	public CreateBulletThread(MyFrame myFrame) {
		this.myFrame=myFrame;
	}
	@Override
	public void run() {
		while (true) {
			if (myFrame.gameState==1) {
				if (myFrame.hero.isfire==true) {
					if (myFrame.bulletlist.size()<10||myFrame.bulletlist==null) {
						Bullet bullet = new Bullet(myFrame.hero.x,myFrame.hero.y,false);
						myFrame.bulletlist.add(bullet);
					}
				}
				
				
			}
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
