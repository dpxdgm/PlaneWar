package com.tjrac.planewar.frame;

import javax.swing.*;

import com.tjrac.planewar.pojo.Hero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class MyFrame extends JFrame{
	static MyFrame myFrame = new MyFrame();
	
	private static MyButton startbtn=null;
	private static MyButton exitbtn=null;
	private static MyButton helpbtn=null;
	private static MyButton returnbtn=null;
	private static JLabel helpJLabel=null;
	private static JLabel titleJLabel=null;
	
//	                      1开始时
	private static int gameState=0;
	private static MyPanel myPanel=new MyPanel();;
//	创建英雄
	private static Hero hero=new Hero(myFrame);
//   加载图片
	private static Image image = Toolkit.getDefaultToolkit().getImage("resource/bg1.jpg");
	private static final JComponent canvas = new JComponent() {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics gg = g.create();
			gg.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			gg.dispose(); 
		}
	};
	public MyFrame() {
		
	}
	public static void main(String[] args) {
		myFrame.setTitle("PlaneWar");
		myFrame.setSize(400, 700);
		myFrame.setResizable(false);
		myFrame.setLayout(null);
		myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Thread starThread=new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					System.out.println("进入线程");
					if (gameState==1) {
						myFrame.setContentPane(myPanel);
						System.out.println("开始游戏");
						break;
					}
				}
			}
		});
		starThread.start();
		
		Container c=myFrame.getContentPane();
		canvas.setBounds(0,0,400,700);
		initializationAll(myFrame);
		c.add(returnbtn);
		c.add(helpJLabel);
		c.add(startbtn);
		c.add(exitbtn);
		c.add(helpbtn);
		c.add(titleJLabel);
		c.add(canvas);
		myFrame.setVisible(true);
	}
	
	private static void initializationAll(MyFrame frame) {
//		初始化
		startbtn=new MyButton("开始游戏");
		startbtn.setBounds(120, 300, 160, 40);
		startbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startbtn.setVisible(false);
				exitbtn.setVisible(false);
				helpbtn.setVisible(false);
				titleJLabel.setVisible(false);
				image=Toolkit.getDefaultToolkit().getImage("resource/bg.jpg");
				gameState=1;
				System.out.println("gameState状态:"+gameState);
			}
		});
		exitbtn=new MyButton("退出游戏");
		exitbtn.setBounds(120, 350, 160, 40);
		exitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		helpbtn=new MyButton("帮助");
		helpbtn.setBounds(120, 400, 160, 40);
		helpbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startbtn.setVisible(false);
				exitbtn.setVisible(false);
				helpbtn.setVisible(false);
				titleJLabel.setVisible(false);
				helpJLabel.setVisible(true);
				returnbtn.setVisible(true);
			}
		});
		returnbtn=new MyButton("返回");
		returnbtn.setBounds(160, 340, 80, 30);
		returnbtn.setVisible(false);
		returnbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startbtn.setVisible(true);
				exitbtn.setVisible(true);
				helpbtn.setVisible(true);
				titleJLabel.setVisible(true);
				helpJLabel.setVisible(false);
				returnbtn.setVisible(false);
			}
		});
		titleJLabel=new JLabel("Plane War",JLabel.CENTER);
		titleJLabel.setBounds(100, 250, 200, 40);
		titleJLabel.setFont(new Font("华文行楷",Font.BOLD,20));
		titleJLabel.setOpaque(false);
		titleJLabel.setForeground(Color.white);
		
		helpJLabel=new JLabel("<html><body>玩家通过键盘的上下左右键，控制<br>玩家飞机上下左右，按住空格<br>键进行发射子弹body></html>",JLabel.CENTER);
		helpJLabel.setBounds(100, 280, 200, 50);
		helpJLabel.setOpaque(false);
		helpJLabel.setForeground(Color.white);
		helpJLabel.setVisible(false);
	}

	public static class MyPanel extends MyJPanel{
		public MyPanel() {
			addKeyListener(myFrame.new KeyMonitor());
			setFocusable(true);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawbackImg(g);
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			hero.draw(g);
			System.out.println(hero.y);
		}
		private void drawbackImg(Graphics g) {
			Graphics2D g2d=(Graphics2D)g.create();
			g2d.drawImage(image,0,0,400,700,this);
			g2d.dispose();
		}
	}
	
	private class KeyMonitor extends KeyAdapter{  
	    @Override  
	    public void keyPressed(KeyEvent e) {  
	        hero.moveHeroPress(e);
	    }
	    @Override  
	    public void keyReleased(KeyEvent e) {  
	        hero.moveHeroRelease(e);
	    }  
	      
	}
}
