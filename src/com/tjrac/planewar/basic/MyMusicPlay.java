package com.tjrac.planewar.basic;

import java.io.File;

import com.tjrac.planewar.frame.MyFrame;

import javafx.scene.media.AudioClip;

public class MyMusicPlay {
	public static void playMusic() {
		AudioClip ac;
		ac =new AudioClip(new File("src\\com\\tjrac\\planewar\\resource\\revolution.mp3").toURI().toString());
		ac.play();
	}
}
