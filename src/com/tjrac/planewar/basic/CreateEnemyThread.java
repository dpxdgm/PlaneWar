package com.tjrac.planewar.basic;

import java.util.Random;

import com.tjrac.planewar.frame.MyFrame;
import com.tjrac.planewar.pojo.EnemyPlane;

public class CreateEnemyThread extends Thread{
	private MyFrame myFrame;
	private Random random=new Random();
	public CreateEnemyThread(MyFrame myFrame) {
		this.myFrame=myFrame;
	}
	@Override
	public void run() {
		while (true) {
			if (myFrame.gameState==1) {
				if (myFrame.enemylist.size()<50||myFrame.enemylist==null) {
					EnemyPlane enemyPlane = new EnemyPlane(random.nextInt(300),-random.nextInt(150),myFrame);
					myFrame.enemylist.add(enemyPlane);
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
