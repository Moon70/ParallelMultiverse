package abyss.parallelmultiverse.common;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.Point3D;
import abyss.lunarengine.tools.Random;

public class PointBounce extends Point3D{
	public int deltaX;
	public int deltaY;
	public static int bobSize;
	
	public PointBounce(int x, int y, int z, int data) {
		super(x, y, z, data);
		shiftLeft();
		dx=sx>>Bob.SHIFT;
		dy=sy>>Bob.SHIFT;
		dz=sz>>Bob.SHIFT;
		deltaX=getRandomDelta();
		deltaY=getRandomDelta();
	}

	@Override
	public void move() {
		sy+=deltaY;
		dy=sy>>Bob.SHIFT;
		sx+=deltaX;
		dx=sx>>Bob.SHIFT;
		if(sx<0 || sx>(Screen.screenSizeX-32)<<Bob.SHIFT) {
			if(deltaY>0 && deltaY<180) {
				enabled=false;
			}else {
				sx-=deltaX;
				deltaX=(-deltaX)>>1;
			}
		}
		
		if(sy<0 && deltaY<0) {
			sy-=deltaY;
			deltaY=-deltaY;
		}else if(sy>(Screen.screenSizeY-bobSize)<<Bob.SHIFT) {
			sy-=deltaY;
			deltaY=(-deltaY)>>1;
		}	
		
		deltaY+=1<<(Bob.SHIFT-4);
	}
	
	private int getRandomDelta() {
		int r;
		while(true) {
			r=(int)(Random.random()*26-13)<<Bob.SHIFT;
			if(r<-5 || r>5) {
				break;
			}
		}
		return r;
	}
	
}
