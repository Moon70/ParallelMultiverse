package abyss.parallelmultiverse.part01textintro;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Font;

public class FontIntro extends Font{
	public boolean useColor;
	public int color;
	private static final int SPACE_SIZE_X = 70;
	private int writeposX;
	private int writeposY;
	private int pixel;

	public FontIntro(String fontName) {
		super(fontName);
	}

	public int render(char c,int x,int y,int[] screendataWorking) {
		writeposX=x;
		writeposY=y;
		return render(c,screendataWorking);
	}

	private int render(char c,int[] screendataWorking) {
		int pixelPosX;
		int i=characterset.indexOf(c);
		if(i!=-1) {
			for(int render_y=0;render_y<height;render_y++) {
				for(int render_x=0;render_x<index[i][2];render_x++) {
					pixel=fontdata[index[i][0]+render_x+(index[i][1]+render_y)*fontWidth];
					pixelPosX=writeposX+render_x;
					if((pixel & 0xffffff) !=0) {
						if(useColor) {
							pixel=color;
						}
						if(pixelPosX>=0 && pixelPosX<Screen.screenSizeX) {
							screendataWorking[pixelPosX+(writeposY+render_y)*Screen.screenSizeX]=pixel;
						}
					}
				}
			}
			writeposX+=index[i][2]+10;
			return index[i][2]+10;
		}else {
			writeposX+=SPACE_SIZE_X;
			return SPACE_SIZE_X;
		}
	}

}
