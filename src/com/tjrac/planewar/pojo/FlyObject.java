package com.tjrac.planewar.pojo;

import java.awt.image.BufferedImage;

public abstract class FlyObject {
	public BufferedImage image;
//	飞行物大小、宽高
	public int width;
	public int height;
//	飞行物位置
	public int x;
	public int y;
	
//	移动对象
	public abstract void movetheobject();
	
//	判断是否超过游戏边界
	public abstract boolean moveOut();
	
//	判断是否被击中
//	public boolean shootByBullet() {
//		等子弹类写完后在编写
//	}
}
