package abyss.parallelmultiverse.common;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Tools2D;

public class Quad2 {
	public static Tools2D tools2D;
	public static int steps=50;
	private static final int SHIFT=12;

	public int[] p1;
	public int[] p2;
	public int[] p3;
	public int[] p4;
	private int color;
	
	public Quad2(int[] p1,int[] p2,int[] p3,int[] p4,int color) {
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
		this.p4=p4;
		this.color=color;
	}
	
	public void drawFilled() {
		tools2D.pixel=color;
		int x0=(p1[0]+Screen.screenCenterX) << SHIFT;
		int y0=(p1[1]+Screen.screenCenterY) << SHIFT;
		int stepx0=((p4[0]-p1[0]) << SHIFT)/steps;
		int stepy0=((p4[1]-p1[1]) << SHIFT)/steps;

		int x1=(p2[0]+Screen.screenCenterX) << SHIFT;
		int y1=(p2[1]+Screen.screenCenterY) << SHIFT;
		int stepx1=((p3[0]-p2[0]) << SHIFT)/steps;
		int stepy1=((p3[1]-p2[1]) << SHIFT)/steps;
		
		for(int i=0;i<steps;i++,x0+=stepx0,y0+=stepy0,x1+=stepx1,y1+=stepy1) {
			tools2D.line((x0 >> SHIFT),(y0 >> SHIFT),(x1 >> SHIFT),(y1 >> SHIFT));
		}
	}

}
