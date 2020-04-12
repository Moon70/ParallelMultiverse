package abyss.parallelmultiverse.part01textintro;

import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;

public class Textline {
	private FontIntro font;
	private char[] text;
	private int center;
	private int textlineYPos;

	private int[] colorTable;
	private volatile int colorIndex;
	private int colorIndexMove;

	private volatile double xFactor;
	private double xFactorMax;
	private double xFactorDelta=0;
	private volatile double movePos;
	private double moveSpeed;
	private int delay1;
	private int delay2;

	public Textline(FontIntro font,String text,int textlineYPos, int textlineXPos,double moveSpeed,int delay1,int delay2) {
		this.font=font;
		this.font.useColor=true;
		this.textlineYPos=textlineYPos;
		this.xFactorMax=1;
		this.moveSpeed=moveSpeed;
		movePos=textlineXPos;
		if(movePos>=0) {
			movePos=textlineXPos+Screen.screenSizeX;
		}
		this.delay1=delay1;
		this.delay2=delay2;

		if((text.length()&1)==1) {
			this.text=(text+" ").toCharArray();
		}else {
			this.text=text.toCharArray();
		}
		center=this.text.length>>1;
		this.colorIndexMove=1;

		colorTable=new int[256];
		for(int i=0;i<256;i++) {
			colorTable[i]=i+(i<<8)+(i<<16);
		}
	}

	public void vbi() {
		colorIndex+=colorIndexMove;
		if(colorIndex==0 || colorIndex==254) {
			colorIndexMove=0;
		}
		movePos+=moveSpeed;
		if(--delay1==0) {
			xFactorDelta=0.015;
			moveSpeed=moveSpeed/20;
		}
		if(xFactor<xFactorMax) {
			xFactor+=xFactorDelta;
		}

		if(--delay2==0) {
			colorIndexMove=-2;
			xFactorMax=8;
			moveSpeed=moveSpeed*40;
		}
	}

	public void render() {
		int pos1=0;
		int pos2=0;
		for(int i=0;i<center;i++) {
			pos2+=(int)(xFactor*(font.getCharWidth(text[center-1-i])+10));
			font.color=colorTable[colorIndex];
			font.render(text[center+i],pos1+(int)movePos,textlineYPos,LunarEngine.screendataToWork);
			font.render(text[center-1-i],-pos2+(int)movePos,textlineYPos,LunarEngine.screendataToWork);
			pos1+=(int)(xFactor*(font.getCharWidth(text[center+i])+10));
		}
	}

}
