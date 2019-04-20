package com.tjrac.planewar.frame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
public class MyJPanel extends JPanel{
	public MyJPanel() {
		new PaintTread().start();
	}
	
	
	class PaintTread extends Thread{
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private Image offScreenImage = null;
	public void update(Graphics g){
		if(offScreenImage == null){
			offScreenImage = this.createImage(400,700);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
}
