package abyss.parallelmultiverse.part08ribbons;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.LordFader;
import abyss.lunarengine.gfx.Tools2D;
import abyss.lunarengine.tools.Random;

public class PartRibbons extends APart{
	private Tools2D tools2d;
	
	private Ribbon[] ribbon;
	private static final int RIBBONS_COUNT=90;

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			APart partRibbons=new PartRibbons();
			partRibbons.precalc();
			partRibbons.initialize();
			LunarEngine.setActivePart(partRibbons);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	@Override
	public void precalc(){
		tools2d=new Tools2D();
		ribbon=new Ribbon[RIBBONS_COUNT];

		LordFader[] faderA=new LordFader[9];
		LordFader[] faderB=new LordFader[9];
		faderA[0]=new LordFader(0x222222,LordFader.colorHalfBright(0xDfBfDf),256);
		faderB[0]=new LordFader(0x222222,LordFader.colorHalfBright(0xAf8f7f),256);
		faderA[1]=new LordFader(0x222222,LordFader.colorHalfBright(0x9fBfCf),256);
		faderB[1]=new LordFader(0x222222,LordFader.colorHalfBright(0x8f7fCf),256);
		faderA[2]=new LordFader(0x222222,LordFader.colorHalfBright(0x5f0f0f),256);
		faderB[2]=new LordFader(0x222222,LordFader.colorHalfBright(0x8f7fCf),256);
		faderA[3]=new LordFader(0x222222,LordFader.colorHalfBright(0x8fAf5f),256);
		faderB[3]=new LordFader(0x222222,LordFader.colorHalfBright(0x0f3f2f),256);
		faderA[4]=new LordFader(0x222222,LordFader.colorHalfBright(0xCf7f0f),256);
		faderB[4]=new LordFader(0x222222,LordFader.colorHalfBright(0xEfDf0f),256);
		faderA[5]=new LordFader(0x222222,LordFader.colorHalfBright(0xDfCf8f),256);
		faderB[5]=new LordFader(0x222222,LordFader.colorHalfBright(0xFfEfDf),256);
		faderA[6]=new LordFader(0x222222,LordFader.colorHalfBright(0x5f2f4f),256);
		faderB[6]=new LordFader(0x222222,LordFader.colorHalfBright(0x4f4f2f),256);
		faderA[7]=new LordFader(0x222222,LordFader.colorHalfBright(0x8f4f0f),256);
		faderB[7]=new LordFader(0x222222,LordFader.colorHalfBright(0x0f4f3f),256);
		faderA[8]=new LordFader(0x222222,LordFader.colorHalfBright(0x2f4f4f),256);
		faderB[8]=new LordFader(0x222222,LordFader.colorHalfBright(0x5f0f2f),256);

		final double[] deltaAngle={0.5,-0.5,0.3,-0.3,0.7,-0.7,0.4,-0.4};
		final int[] deltaSinus= {2,3,4,5,6,7};
		final int[] deltaY= {2,3,4};
		for(int i=0;i<RIBBONS_COUNT;i++) {
			ribbon[i]=new Ribbon(
					(int)(Random.random()*1900),
					(int)(Random.random()*2000)-1000-1100,
					(int)(Random.random()*360),
					faderA[i%9],
					faderB[i%9],
					deltaAngle[i%deltaAngle.length],
					deltaSinus[i%deltaSinus.length],
					deltaY[i%deltaY.length]
					);
		}
		Ribbon.tools2d=tools2d;
	}

	public void switchToDottyMode() {
		Ribbon.switchToDottyMode=true;
	}
	
	public void beginFadeOut() {
		Ribbon.fadeout=true;
		for(int i=0;i<RIBBONS_COUNT;i++) {
			if(ribbon[i].offsetY<0) {
				ribbon[i].enabled=false;
			}else {
				ribbon[i].deltaY+=6;
			}
		}
	}

	@Override
	public void vbi(){
		for(int i=0;i<RIBBONS_COUNT;i++) {
			ribbon[i].vbi();
		}
	}

	@Override
	public void worker2(){
		tools2d.screendataWorking=LunarEngine.screendataToWork;
		for(int i=0;i<RIBBONS_COUNT;i++) {
			ribbon[i].render();
		}
//		if(clearScreenInWorker2) {
//			Screen.screenClear(LunarEngine.screendataToReset,PartBounce.faderBackground.getCurrentColor());
//		}
	}

	@Override
	public void worker3(){
		Screen.screenClear(LunarEngine.screendataToReset, 0);
	}

}
