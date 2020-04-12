package abyss.parallelmultiverse.part07turnbars;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.sinatra.Sinatra;

public class TurnbarY extends Turnbar{

	public TurnbarY(Bob bob,Sinatra sinatraMove1,Sinatra sinatraMove2,int positionOffset,boolean fade) {
		this.bob=bob;
		this.sinatraMove1=sinatraMove1;
		this.sinatraMove2=sinatraMove2;
		this.positionOffset=positionOffset;
		if(fade) {
			barStart=-2;
			barEnd=-2;
		}else {
			barStart=Screen.screenSizeY;
			barEnd=Screen.screenSizeY;
		}
	}
	
	public void vbi() {
		if(animPos<bob.bobSizeY-1) {
			animPos++;
		}else {
			animPos=0;
		}
		if(fadeIn) {
			if(barStart>0) {
				barStart-=4;
			}
			if(barEnd<Screen.screenSizeY) {
				barEnd+=4;
			}
		}else if(fadeOut){
			if(barEnd>0) {
				barEnd-=4;
			}
			if(barStart<Screen.screenSizeY) {
				barStart+=4;
			}
		}
	}
	
	public void render() {
		int b=sinatraMove1.getNextValue(2)+positionOffset+sinatraMove2.getNextValue(1);
		int pixel;
		final int m=bob.bobSizeY*10;
		for(int y=0;y<Screen.screenSizeY;y++) {
			if(y>=barStart && y<=barEnd) {
				for(int x=0;x<bob.bobSizeX;x++) {
					pixel=bob.bobdata[0][x+
					                     ((y/3-animPos+m) % bob.bobSizeY)
					                     *bob.bobSizeX];
					if((pixel & 0xffffff)!=0) {
						render_screendataWorking[b+x+y*Screen.screenSizeX]=pixel;
					}
				}
			}
			b=sinatraMove1.getNextDeltaValue(1)+positionOffset+sinatraMove2.getNextDeltaValue(2);
		}
	}
	
}
