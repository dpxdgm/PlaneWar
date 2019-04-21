    
package com.tjrac.planewar.frame;

import javax.swing.*;

import com.tjrac.planewar.basic.CreateBulletThread;
import com.tjrac.planewar.basic.CreateEnemyThread;
import com.tjrac.planewar.pojo.Bullet;
import com.tjrac.planewar.pojo.EnemyPlane;
import com.tjrac.planewar.pojo.Explode;
import com.tjrac.planewar.pojo.Hero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyFrame extends JFrame{
	static MyFrame myFrame = new MyFrame();
	
	private static MyButton startbtn=null;
	private static MyButton exitbtn=null;
	private static MyButton helpbtn=null;
	private static MyButton returnbtn=null;
	private static JLabel helpJLabel=null;
	private static JLabel titleJLabel=null;
	
//	                      1开始时
	public static int gameState=0;
	private static MyPanel myPanel=new MyPanel();;
//	创建英雄
	public static Hero hero=new Hero(myFrame);
//	创建敌机
	public static List<EnemyPlane> enemylist=new LinkedList<>();
	private static EnemyPlane ePlane=new EnemyPlane(100,20,myFrame);;
	//////////////////////英雄的子弹袋
	public static List<Bullet> bulletlist=new LinkedList<>();
	
//	爆炸对象
	public static List<Explode> explodelist=new LinkedList<>();
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
		setTitle("PlaneWar");
		setSize(400, 700);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		Thread starThread=new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					System.out.println("进入线程");
					if (gameState==1) {
						myFrame.getContentPane().removeAll();
						myFrame.setContentPane(myPanel);
						myPanel.startEnemyThread();
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
		public void startEnemyThread(){
			new CreateEnemyThread(myFrame).start();
		};
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawbackImg(g);
			drawHeroLife(g);
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (hero.getLife()==0||hero.getLife()<0) {
				drawEnding(g);
			}else {
				hero.draw(g);
				if (enemylist!=null||enemylist.size()>0) {
					for (int i = 0; i < enemylist.size(); i++) {
						EnemyPlane ePlane=enemylist.get(i);
						ePlane.draw(g);
						try {
							List<Bullet> ebs = ePlane.getBulletlist();
							if (ebs!=null||ebs.size()>0) {
								for (Bullet bullet : ebs) {
									bullet.draw(g);
									bullet.hitplane(hero,1);
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				
				///////////在每次重画时添加子弹
				if (bulletlist!=null||bulletlist.size()>0) {
					for (int i = 0; i < bulletlist.size(); i++) {
						Bullet bullet=bulletlist.get(i);
						bullet.draw(g);
						bullet.hitplanes(enemylist);
					}
				}
				if (explodelist!=null||explodelist.size()>0) {
					for (int i = 0; i < explodelist.size(); i++) {
						Explode explode=explodelist.get(i);
						explode.draw(g);
					}
				}
			}
		}
		
		private void drawbackImg(Graphics g) {
			Graphics2D g2d=(Graphics2D)g.create();
			g2d.drawImage(image,0,0,400,700,this);
			g2d.dispose();
		}
		private void drawHeroLife(Graphics g) {
			Graphics2D g2d=(Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(Color.white);
			g2d.setFont(new Font(null, Font.BOLD, 12));
			g2d.drawString("生命值：", 5, 20);
			g2d.drawString("分数：", 15, 50);
			g2d.drawString(""+hero.myscore, 70, 50);
			for (int i = 0; i <hero.getLife(); i++) {
				g2d.drawRoundRect(60+i*25, 5, 20, 20, 5, 5);
			}
			g2d.dispose();
		}
		private void drawEnding(Graphics g) {
			Graphics2D g2d=(Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(Color.white);
			Font font = new Font(null, Font.BOLD, 35);
			g2d.setFont(font);
			FontMetrics fm=g2d.getFontMetrics(font);
			int stringwidth=fm.stringWidth("GAME OVER");
			int widthx=(400-stringwidth)/2;
			g2d.drawString("GAME OVER", widthx, 300);
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