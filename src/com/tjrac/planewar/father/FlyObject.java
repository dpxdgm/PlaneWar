package com.tjrac.planewar.father;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class FlyObject extends JPanel{
	public BufferedImage image;
//	飞行物大小、宽高
	public int width;
	public int height;
//	飞行物位置
	public int x;
	public int y;
	
	
	public BufferedImage getImage() {
			return image;
		}
	
		public void setImage(BufferedImage image) {
			this.image = image;
		}
	
		public int getWidth() {
			return width;
		}
	
		public void setWidth(int width) {
			this.width = width;
		}
	
		public int getHeight() {
			return height;
		}
	
		public void setHeight(int height) {
			this.height = height;
		}
	
		public int getX() {
			return x;
		}
	
		public void setX(int x) {
			this.x = x;
		}
	
		public int getY() {
			return y;
		}
	
		public void setY(int y) {
			this.y = y;
		}

	//移动对象
	public abstract void movetheobject();
	
//	判断是否超过游戏边界
	public abstract boolean moveOut();
	
	
}
