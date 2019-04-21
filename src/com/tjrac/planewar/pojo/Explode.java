package com.tjrac.planewar.pojo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tjrac.planewar.father.FlyObject;
import com.tjrac.planewar.frame.MyFrame;

public class Explode extends FlyObject{
	private boolean isAlife=true;
	private int index;
	private static BufferedImage[] explodeimg=new BufferedImage[6];

	public Explode(int x,int y) {
		try {
			explodeimg[0]=ImageIO.read(MyFrame.class.getResource("../resource/blast_0_1.png"));
			explodeimg[1]=ImageIO.read(MyFrame.class.getResource("../resource/blast_0_2.png"));
			explodeimg[2]=ImageIO.read(MyFrame.class.getResource("../resource/blast_0_3.png"));
			explodeimg[3]=ImageIO.read(MyFrame.class.getResource("../resource/blast_0_4.png"));
			explodeimg[4]=ImageIO.read(MyFrame.class.getResource("../resource/blast_0_5.png"));
			explodeimg[5]=ImageIO.read(MyFrame.class.getResource("../resource/blast_0_6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x=x;
		this.y=y;
		width=50;
		height=50;
		this.index=0;
	}
	public void draw(Graphics g){
		if (isAlife&&index<6) {
			g.drawImage(explodeimg[index], this.x, this.y,this.width,this.height, null);
			index++;
			if (index==5) {
				this.isAlife=false;
				MyFrame.explodelist.remove(this);
			}
		}else {
			return;
		}
		
	}
	
}
