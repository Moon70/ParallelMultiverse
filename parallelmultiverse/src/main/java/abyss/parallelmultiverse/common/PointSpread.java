package abyss.parallelmultiverse.common;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.Point3D;
import abyss.lunarengine.tools.Random;

public class PointSpread extends Point3D{
	public int deltaX;
	public int deltaY;
	public static int bobsize;
	
	public PointSpread(int x, int y, int z, int data) {
		super(x, y, z, data);
		shiftLeft();
		dx=sx>>Bob.SHIFT;
		dy=sy>>Bob.SHIFT;
		dz=sz>>Bob.SHIFT;
		deltaX=getRandomDelta();
		if(deltaX<(-2<<Bob.SHIFT) || deltaX>(2<<Bob.SHIFT)) {
			deltaY=getRandomDelta();
		}else {
			while(true) {
				deltaY=getRandomDelta();
				if(deltaY<(-2<<Bob.SHIFT) || deltaY>(2<<Bob.SHIFT)) {
					break;
				}
			}
		}
	}

	public void move() {
		sx+=deltaX;
		sy+=deltaY;
		dx=sx>>Bob.SHIFT;
		dy=sy>>Bob.SHIFT;
		if(sx<0 || sx>(Screen.screenSizeX-32)<<Bob.SHIFT) {
			enabled=false;
			if(deltaY>0 && deltaY<180) {
				enabled=false;
			}else {
				sx-=deltaX;
				deltaX=(-deltaX)>>1;
			}
		}
		
		if(sy<0 && deltaY<0) {
			enabled=false;
			sy-=deltaY;
			deltaY=-deltaY;
		}else if(sy>(Screen.screenSizeY-bobsize)<<Bob.SHIFT) {
			enabled=false;
			sy-=deltaY;
			deltaY=(-deltaY)>>1;
		}	
	}
	
	private int getRandomDelta() {
		return (int)(Random.random()*(26<<Bob.SHIFT)-(13<<Bob.SHIFT));
	}
	
}
