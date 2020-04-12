package abyss.parallelmultiverse.part19creditsscroller;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Font;
import abyss.lunarengine.gfx.LordFader;
import abyss.lunarengine.gfx.Tools2D;
import abyss.parallelmultiverse.common.PointSpread;

public class PartCreditsScroller extends APart{
	public static final int BACKGROUNDCOLOR = 0x060300;
	private Font fontHorizontal;
	private TextlineHotizontal textlinesHorizontal;
	private Tools2D tools2d;
	private volatile int borderY=200-100;
	private int scrollOffset=900;
	private int scrollSize=710;
	
	public int borderXDest=1000;
	public int borderYDest=borderY;
	private LordFader fader;
	public int fadeDirection=1;
	private LordFader faderBackground;

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			APart partCreditsScroller=new PartCreditsScroller();
			partCreditsScroller.precalc();
			partCreditsScroller.initialize();
			LunarEngine.setActivePart(partCreditsScroller);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	@Override
	public void precalc(){
		faderBackground=new LordFader(0,BACKGROUNDCOLOR,180);
		tools2d=new Tools2D();
		fader=new LordFader(0,0x00ffcc,240);

		fontHorizontal=new Font("/data/font40_COOPBLA");
		fontHorizontal.space=10;
		scrollOffset=Screen.screenCenterX+((Screen.screenCenterX-scrollSize)>>1);
		textlinesHorizontal=new TextlineHotizontal(fontHorizontal, scrollOffset);
		
		insertEmptyLines(3);
		textlinesHorizontal.addTextline("PARALLEL MULTIVERSE".toCharArray());
		textlinesHorizontal.addTextline("by".toCharArray());
		textlinesHorizontal.addTextline("ABYSS".toCharArray());
		textlinesHorizontal.addTextline("the deepest experience".toCharArray());
		insertEmptyLines(2);
		textlinesHorizontal.addTextline("released at".toCharArray());
		textlinesHorizontal.addTextline("REVISION 2020".toCharArray());
		textlinesHorizontal.addTextline("Saarbrücken".toCharArray());
		textlinesHorizontal.addTextline("Germany".toCharArray());
		insertEmptyLines(3);
		textlinesHorizontal.addTextline("- credits -".toCharArray());
		insertEmptyLines(1);
		
		textlinesHorizontal.addTextline("music".toCharArray());
		textlinesHorizontal.addTextline("by".toCharArray());
		textlinesHorizontal.addTextline("NEURODANCER".toCharArray());
		textlinesHorizontal.addTextline("neurowerx.bandcamp.com".toCharArray(),Screen.screenCenterY-50,2*60);
		insertEmptyLines(8);
		
		textlinesHorizontal.addTextline("logo".toCharArray());
		textlinesHorizontal.addTextline("by".toCharArray());
		textlinesHorizontal.addTextline("CELTIC/AXIS".toCharArray());
		insertEmptyLines(5);
		
		textlinesHorizontal.addTextline("eyeball".toCharArray());
		textlinesHorizontal.addTextline("by".toCharArray());
		textlinesHorizontal.addTextline("TYSHDOMOS".toCharArray());
		insertEmptyLines(5);
		
		textlinesHorizontal.addTextline("java code".toCharArray());
		textlinesHorizontal.addTextline("by".toCharArray());
		textlinesHorizontal.addTextline("MOON".toCharArray());
		insertEmptyLines(1);
		textlinesHorizontal.addTextline("may the source be with you".toCharArray());
		textlinesHorizontal.addTextline("github.com/Moon70".toCharArray()		,Screen.screenCenterY-50,2*60);
		textlinesHorizontal.addTextline("/ParallelMultiverse".toCharArray());
		insertEmptyLines(7);

		textlinesHorizontal.addTextline("Ogg Vorbis player".toCharArray());
		textlinesHorizontal.addTextline("by".toCharArray());
		textlinesHorizontal.addTextline("JON KRISTENSEN".toCharArray());
		insertEmptyLines(4);

		textlinesHorizontal.addTextline("THANK YOU".toCharArray());
		textlinesHorizontal.addTextline("FOR WATCHING".toCharArray());
		insertEmptyLines(3);
		textlinesHorizontal.addTextline("and always".toCharArray());
		textlinesHorizontal.addTextline("REMEMBER".toCharArray());
		insertEmptyLines(2);
		textlinesHorizontal.addTextline("für eine".toCharArray());
		textlinesHorizontal.addTextline("Extraportion".toCharArray());
		textlinesHorizontal.addTextline("ABYSS".toCharArray());
		textlinesHorizontal.addTextline("ist man nie".toCharArray());
		textlinesHorizontal.addTextline("zu alt".toCharArray());
	}
	
	private void insertEmptyLines(int count) {
		for(int i=0;i<count;i++) {
			textlinesHorizontal.addTextline("".toCharArray());
		}
	}
	
	@Override
	public void initialize() {
		tools2d.lightningLineParts=50;
		tools2d.rnd=20;
		PointSpread.bobsize=32;
		fader.setIndex(0);
	}
	
	@Override
	public void vbi(){
		if(borderY<borderYDest) {
			borderY+=4;
		}else if(borderY>borderYDest) {
			borderY-=4;
		}
		
		textlinesHorizontal.vbi(borderY);
		
		fader.fade(fadeDirection);
		faderBackground.fade(1);
	}

	
	@Override
	public void worker2(){
		tools2d.screendataWorking=LunarEngine.screendataToWork;
		textlinesHorizontal.render();
		tools2d.pixel=fader.getCurrentColor();
		tools2d.lightningLine(Screen.screenCenterX, borderY, Screen.screenSizeX, borderY);
	}

	@Override
	public void worker3(){
		Screen.screenClear(LunarEngine.screendataToReset,faderBackground.getCurrentColor());
	}

}
