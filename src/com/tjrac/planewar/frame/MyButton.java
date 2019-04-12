package com.tjrac.planewar.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.Border;

import javafx.scene.layout.BorderPane;

public class MyButton extends JButton{
	public MyButton(String name) {
		setText(name);
		setPreferredSize(new Dimension(160,40));
		setBackground(Color.red);
		setOpaque(false);
		setFont(new Font("华文行楷",Font.BOLD,16));
		setFocusPainted(false);
		setForeground(Color.white);
		
		
	}
	
}
