package com.tjrac.planewar.pojo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.tjrac.planewar.basic.Score;
import com.tjrac.planewar.father.Enemy;
import com.tjrac.planewar.frame.MyFrame;

public class EnemyPlane extends Enemy implements Score{
	
	public static BufferedImage Enemy1;
	private int speed=2; //定义飞机速度
	static{
		try {
			Enemy1=ImageIO.read(MyFrame.class.getResource("../resource/emeny1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//构造方法
	public EnemyPlane(){
		this.setImage(EnemyPlane.Enemy1);
		this.setWidth(this.getImage().getWidth());
		this.setHeight(this.getImage().getHeight());
		Random rand = new Random(); //随机数对象
		this.setX(rand.nextInt(400-this.width));
		this.setY(-this.height);
	}
	//击败一架敌机得分5
	public int getScore() {
		
		return 5;
	}

}
