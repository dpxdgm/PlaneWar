package com.tjrac.planewar.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{
	private MyButton startbtn=null;
	private MyButton exitbtn=null;
	private MyButton helpbtn=null;
	private MyButton returnbtn=null;
	private JLabel helpJLabel=null;
	private JLabel titleJLabel=null;

	private static Image image = Toolkit.getDefaultToolkit().getImage("resource/bg1.jpg");
	private static final JComponent canvas = new JComponent() {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics gg = g.create(); //创建画笔
			gg.drawImage(image, 0, 0, getWidth(), getHeight(), this); //画图
			gg.dispose(); 
		}
	};
	public MyFrame() {
		setTitle("PlaneWar");
		setSize(400, 700);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c=getContentPane();
		canvas.setBounds(0,0,400,700);
		initializationAll();
		
		c.add(returnbtn);
		c.add(helpJLabel);
		c.add(startbtn);
		c.add(exitbtn);
		c.add(helpbtn);
		c.add(titleJLabel);
		c.add(canvas);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MyFrame();
	}
	
	private void initializationAll() {
//		初始化按钮
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
		
		helpJLabel=new JLabel("<html><body>玩家通过键盘的上下左右键，控制<br>玩家飞机上下左右，按住空格<br>键进行发射子弹<body></html>",JLabel.CENTER);
		helpJLabel.setBounds(100, 280, 200, 50);
		helpJLabel.setOpaque(false);
		helpJLabel.setForeground(Color.white);
		helpJLabel.setVisible(false);
	}
}
