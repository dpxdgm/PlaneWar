package com.tjrac.planewar.basic;

public interface Award {
	public int Double_Fire=0; //火力值
	
	public int Life=1; //生命
	
	//获取奖励类型(上面的0或1) 
	public int getType();
}
