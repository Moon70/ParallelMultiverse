package abyss.parallelmultiverse.part03mosaicbackground;

import abyss.lunarengine.gfx.Object2D;

public class Mosaic extends Object2D{
	public int posX;
	public int posY;

	public void drawB() {
		for(int i=0;i<4;i++) {
			tools2D.lineB(to[lo[i][0]][0]+posX,to[lo[i][0]][1]+posY,to[lo[i][1]][0]+posX,to[lo[i][1]][1]+posY);
		}
	}

}
