package abyss.parallelmultiverse.part15rgb;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.BobFactory;
import abyss.lunarengine.gfx.LordFader;
import abyss.lunarengine.sinatra.Sinatra;
import abyss.parallelmultiverse.common.BobTheBuilder;

public class SinusBobs {
	public Sinatra sinatraBobsX1;
	public Sinatra sinatraBobsX2;
	public Sinatra sinatraBobsY1;
	public Sinatra sinatraBobsY2;
	public int deltaX1;
	public int deltaX2;
	public int deltaY1;
	public int deltaY2;
	private Bob bob;
	
	
	public SinusBobs(int color,int frameindex) {
		bob=BobFactory.createBob(PartRGB.class, "/data/ball32/");
		bob.enabled=true;
		bob.animdelay=0;
		bob.frameAsync=frameindex;
		bob.setAnimType(Bob.ANIMTYPE_PINGPONG);
		//BobTheBuilder.reColorFade(bob,color);
		
		int[][] bobdata=bob.bobdata;
		bob.bobdata=new int[128][bobdata[0].length];
		for(int z=0;z<bob.bobSizeX*bob.bobSizeY;z++) {
			for(int i=0;i<128;i++){
				bob.bobdata[i][z]=bobdata[0][z];
			}
		}
		LordFader fader=new LordFader(color,0x000000,256);
		for(int i=0;i<128;i++) {
			BobTheBuilder.reColor(bob, i,fader.colors[i]);
		}

	}

	public void vbi() {
		sinatraBobsX1.move(deltaX1);
		sinatraBobsX2.move(deltaX2);
		sinatraBobsY1.move(deltaY1);
		sinatraBobsY2.move(deltaY2);
		bob.vbi();
	}

	public void render() {
		sinatraBobsX1.beginRender();
		sinatraBobsX2.beginRender();
		sinatraBobsY1.beginRender();
		sinatraBobsY2.beginRender();
		sinatraBobsX1.getNextValue(16);
		sinatraBobsX2.getNextValue(12);
		sinatraBobsY1.getNextValue(14);
		sinatraBobsY2.getNextValue(10);
		
//		int x=0,y=0;
		for(int t=0;t<50;t++) {
//			x=sinatraBobsX1.getNextDeltaValue(14)+sinatraBobsX2.getNextDeltaValue(12)+Screen.screenCenterX;
//			y=sinatraBobsY1.getNextDeltaValue(12)+sinatraBobsY2.getNextDeltaValue(10)+Screen.screenCenterY;
//			x=sinatraBobsX1.getNextDeltaValue(22)+Screen.screenCenterX;
//			y=sinatraBobsY1.getNextDeltaValue(22)+Screen.screenCenterY;
//			bob.setPos(x, y);
			bob.render(
					sinatraBobsX1.getNextDeltaValue(14)+sinatraBobsX2.getNextDeltaValue(12)+Screen.screenCenterX,
					sinatraBobsY1.getNextDeltaValue(12)+sinatraBobsY2.getNextDeltaValue(10)+Screen.screenCenterY
				);
		}
	}
	
	
	
}
