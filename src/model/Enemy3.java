package model;

import java.lang.Math;

public class Enemy3 extends Hazard{
  public Enemy3(int xpos, int ypos, int xvel, int yvel, int spawntime){
  	super(xpos, ypos, xvel, yvel, spawntime);
  }
//  public Enemy3(){
//	  super(200,200,1,1);
//  }
  
  public void moveLeft(){
	  this.xpos = xpos-xvel;
	  	double y = Math.cos(xpos/7)*10;
	  	this.ypos = ((int)y)+ypos;
  }
}