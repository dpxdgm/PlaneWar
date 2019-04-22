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
				System.out.println("游戏运行时间:"+(System.currentTimeMillis()-myFrame.starttime)/1000);
				if ((System.currentTimeMillis()-myFrame.starttime)/1000>15&&(System.currentTimeMillis()-myFrame.starttime)/1000%10==0) {
					myFrame.awardlist.add(new Award());
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
