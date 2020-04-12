package abyss.parallelmultiverse.part17splinescroller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Font;
import abyss.lunarengine.gfx.LordFader;
import abyss.lunarengine.gfx.Point2D;
import abyss.lunarengine.sinatra.Sinatra;
import abyss.lunarengine.spliner.Spliner;

public class PartSplineScroller extends APart{
	public boolean startColorFadein;
	public boolean startColorFadeout;
	public boolean renderBuffer2;
	public boolean renderBuffer3;
	public boolean startBounce;
	public boolean stopBounceOnNextZero;

	private static final boolean SPLINE_LINES=false;

	private int pixel;

	private int[] screendataWorking;
	
	public int splinerIndex=0;
	private Spliner[] spliner1;
	private Spliner[] spliner2;

	private Font font;
	private volatile int[] dataScroller;
	private volatile int[] dataCopperScrollerWork;
	private volatile int[] dataCopperScrollerRender;
	private volatile int[] dataCopperScrollerSwap;

	private int scrollerX;
	private int scrollerY;

	private final int scrollerOffsetTop=25;
	private int copperScrollerX;
	private int copperScrollerY;

	private static final int SHIFT=12;
	
	private int scrollFieldX;
	private Sinatra sinatra;
	private Sinatra sinatraScrollBounce;
	
	private volatile int sinusOffset;
	
	private int[][] colorsLightGrey;
	private int[][] colorsDarkGrey;
	private final int colorFadeinSteps=240;
	private int colorFadeinIndex;
	
	private final int copperbarsCount=5;
	private boolean[] copperbarDirections=new boolean[5];
	private int[] copperbarPos=new int[5];

	private int colorIndex;
	private int[] colorsRed=new int[] {
			0xee7700,
			0xee7700,
			0xee7700,
			0xcc6600,
			0xcc6600,
			0xcc6600,
			0xaa5500,
			0xaa5500,
			0xaa5500,
			0x884400,
			0x884400,
			0x884400,
			0x663300,
			0x663300,
			0x663300,
			0x442200,
			0x442200,
			0x442200
	};
	
	private int[] colorsBlue=new int[] {
			0x002244,
			0x002244,
			0x002244,
			0x003366,
			0x003366,
			0x003366,
			0x004488,
			0x004488,
			0x004488,
			0x0055aa,
			0x0055aa,
			0x0055aa,
			0x0066cc,
			0x0066cc,
			0x0066cc,
			0x0077ee,
			0x0077ee,
			0x0077ee
	};

	private int scrollPrintSleep=119;
	private int scrollPrintSleepPoint=scrollPrintSleep;
	private final int scrollSpeed=9;
	
	private int scrollTextIndex=0;
	private final String scrolltext="REMEMBER WHEN COPPERBAR SCROLLER RULED THE WORLD...     "
			+ "GREETINGS TO NUANCE    PADUA    LFT    GENESIS PROJECT    LEMON.    TEK    INSANE    NINJAFORCE    DESIRE    PMC    ABYSS CONNECTION    OFFENCE    PHENOMENA    OXYRON    LOONIES"
			+ "                                                                                                   ";

	public static void main(String[] args){
		try{
//			new SinatraController().openGUI();
//			new SplineController().openGUI();
			
			LunarEngine.initializeEngine();
			PartSplineScroller partSplineScroller=new PartSplineScroller();
			partSplineScroller.precalc();
			partSplineScroller.initialize();
			LunarEngine.setActivePart(partSplineScroller);
			partSplineScroller.renderBuffer3=true;
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}
	
	@Override
	public void precalc() {
		int[] colorTableLightGrey=new int[] {0x333333,0x555555,0x777777,0x999999,0xbbbbbb,0xbbbbbb,0x999999,0x777777,0x555555,0x333333};
		int[] colorTableDarkGrey=new int[] {0x111111,0x333333,0x555555,0x666666,0x888888,0x888888,0x666666,0x555555,0x333333,0x111111,};
		int colorvaluesCount=colorTableLightGrey.length;
		colorsLightGrey=new int[colorFadeinSteps][colorvaluesCount];
		colorsDarkGrey=new int[colorFadeinSteps][colorvaluesCount];
		LordFader fader;
		for(int i=0;i<colorvaluesCount;i++) {
			fader=new LordFader(colorTableLightGrey[i],0,colorFadeinSteps);
			for(int j=0;j<colorFadeinSteps;j++) {
				colorsLightGrey[j][i]=fader.colors[j];
			}
		}
		for(int i=0;i<colorvaluesCount;i++) {
			fader=new LordFader(colorTableDarkGrey[i],0,colorFadeinSteps);
			for(int j=0;j<colorFadeinSteps;j++) {
				colorsDarkGrey[j][i]=fader.colors[j];
			}
		}
		
		sinatraScrollBounce=Sinatra.getInstance(new double[]{90.0,1.0,550.0,1.0,270.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraScrollBounce.playmode=Sinatra.PLAYMODE_PINGPONG;
		
		spliner1=new Spliner[4];
		spliner2=new Spliner[4];
		
		spliner1[0]=Spliner.getInstance(new int[]{2,1,1600,0,400,850,400,1918,400});//generated by LunarTools Spline Editor 1.0
		spliner2[0]=Spliner.getInstance(new int[]{2,1,1600,0,550,850,550,1918,550});//generated by LunarTools Spline Editor 1.0
		
		spliner1[1]=Spliner.getInstance(new int[]{1,4,1600,0,400,850,400,1918,400,300,0,400,39,822,450,1078,300,850,400,650,400,450,350,300,1918,400,1918,400,1918,400});//generated by LunarTools Spline Editor 1.0
		spliner2[1]=Spliner.getInstance(new int[]{1,4,1600,0,550,850,550,1919,550,300,0,550,144,956,650,1078,300,850,550,750,550,650,500,300,1918,550,1918,550,1918,550});//generated by LunarTools Spline Editor 1.0
		
		spliner1[2]=Spliner.getInstance(new int[]{1,4,1600,450,1078,450,350,1918,400,300,450,1078,442,456,0,850,300,450,350,1752,-24,149,-34,300,1918,400,1044,355,1750,0});//generated by LunarTools Spline Editor 1.0
		spliner2[2]=Spliner.getInstance(new int[]{1,4,1600,650,1078,650,500,1918,550,300,650,1078,392,641,0,1000,300,650,500,-517,1011,1530,1228,300,1918,550,952,372,1918,0});//generated by LunarTools Spline Editor 1.0
		
		spliner1[3]=Spliner.getInstance(new int[]{1,4,1600,0,850,149,-34,1750,0,300,0,850,-35,224,321,0,300,149,-34,170,341,1127,543,300,1750,0,1719,119,1918,376});//generated by LunarTools Spline Editor 1.0
		spliner2[3]=Spliner.getInstance(new int[]{1,4,1600,0,1000,1530,1228,1918,0,300,0,1000,0,562,58,0,300,1530,1228,881,862,321,1075,300,1918,0,1127,511,1918,969});//generated by LunarTools Spline Editor 1.0
		
		font=new Font("/data/font144_COOPBLA");
		font.noclipping=true;
		
		scrollFieldX=Screen.screenSizeX-100;
		scrollerX=Screen.screenSizeX+200;
		scrollerY=136;
		BufferedImage bufferedImageScroller=new BufferedImage(scrollerX,scrollerY+1,BufferedImage.TYPE_INT_RGB);
		dataScroller=((DataBufferInt)bufferedImageScroller.getRaster().getDataBuffer()).getData();
		bufferedImageScroller=null;
		
		copperScrollerX=Screen.screenSizeX;
		copperScrollerY=scrollerY+scrollerOffsetTop+scrollerOffsetTop;
		BufferedImage bufferedImageCopperScroller=new BufferedImage(copperScrollerX,copperScrollerY,BufferedImage.TYPE_INT_RGB);
		dataCopperScrollerWork=((DataBufferInt)bufferedImageCopperScroller.getRaster().getDataBuffer()).getData();
		dataCopperScrollerRender=new int[dataCopperScrollerWork.length];
		bufferedImageCopperScroller=null;
		
		sinatra=Sinatra.getInstance(new double[]{180.0,1.0,88.0,2.0,90.0,450.0,0.0});//generated by LunarTools Sinus Editor 1.0
	}
	
	@Override
	public void initialize() {
		sinatraScrollBounce.setIndex(0);
		sinatra.setIndex(0);
		startColorFadein=false;
		startColorFadeout=false;
		renderBuffer2=false;
		renderBuffer3=false;
		startBounce=false;
		stopBounceOnNextZero=false;
		colorFadeinIndex=colorFadeinSteps-1;
	}
	
	@Override
	public void vbi(){
		System.arraycopy(dataScroller, scrollSpeed, dataScroller, 0, dataScroller.length-scrollerX);
		
		if(colorFadeinIndex>0 && startColorFadein) {
			colorFadeinIndex--;
		}else if(startColorFadeout && colorFadeinIndex<colorFadeinSteps-1) {
			colorFadeinIndex++;
		}
		
		if(startBounce) {
			sinusOffset=sinatraScrollBounce.getNextValue(1)+549;
			if(stopBounceOnNextZero && sinusOffset==0) {
				startBounce=false;
			}
		}
		
		if((scrollPrintSleepPoint-=scrollSpeed)<=0) {
			for(int offset=0;offset<scrollerY*scrollerX;offset+=scrollerX) {
				Arrays.fill(dataScroller, Screen.screenSizeX+offset, offset+scrollerX, 0);
			}
			scrollPrintSleepPoint+=font.render(scrolltext.charAt(scrollTextIndex),Screen.screenSizeX,0,dataScroller,scrollerX);
			if(++scrollTextIndex==scrolltext.length()) {
				scrollTextIndex=0;
			}
		}
		
		sinatra.getNextValue(1);
		Arrays.fill(dataCopperScrollerWork,0);
		if(startColorFadein || startColorFadeout) {
			for(int i=0;i<copperbarsCount;i++) {
				copperbarPos[i]=sinatra.getNextDeltaValue(7)+88;
				copperbarDirections[i]=sinatra.getDeltaIndex()>(sinatra.getWaveLen()>>1);
				if(!copperbarDirections[i]) {
					for(int y=0;y<10;y++) {
						for(int offset=(copperbarPos[i]+y)*scrollFieldX;offset<(copperbarPos[i]+y)*scrollFieldX+scrollFieldX;offset++) {
							dataCopperScrollerWork[offset]=colorsDarkGrey[colorFadeinIndex][y];
						}
					}
				}
			}
		}
		
		colorIndex=++colorIndex%colorsRed.length;
		int indexScroller=0;
		int color;
		for(int y=0;y<scrollerY;y++) {
			if(y<scrollerY>>1) {
				color=colorsRed[(colorIndex+y)%colorsRed.length];
			}else {
				color=colorsBlue[(colorIndex-y+scrollerY)%colorsBlue.length];
			}
			for(int x=(scrollerOffsetTop+y)*copperScrollerX;x<(scrollerOffsetTop+y)*copperScrollerX+copperScrollerX;x++) {
				if(dataScroller[indexScroller++]!=0) {
					dataCopperScrollerWork[x]=color;
				}
			}
			indexScroller+=scrollerX-copperScrollerX;
		}
		
		for(int i=0;i<copperbarsCount;i++) {
			if(copperbarDirections[i]) {
				for(int y=0;y<10;y++) {
					for(int x=(copperbarPos[i]+y)*scrollFieldX;x<(copperbarPos[i]+y)*scrollFieldX+scrollFieldX;x++) {
						dataCopperScrollerWork[x]=colorsLightGrey[colorFadeinIndex][y];
					}
				}
			}
		}
		
//		if(SplineController.devSpliner!=null) {
//			if(SplineController.devSpliner[0]!=null && SplineController.devSpliner[0].getWaveLen()==1600) {
//				spliner1[0]=SplineController.devSpliner[0];
//			}
//			if(SplineController.devSpliner[1]!=null && SplineController.devSpliner[1].getWaveLen()==1600) {
//				spliner2[0]=SplineController.devSpliner[1];
//			}
//		}

		spliner1[splinerIndex].nextWave();
		spliner2[splinerIndex].nextWave();
	}
	
	
	private Point2D point1;
	private Point2D point2;
	public void worker2(){
//		dataCopperScrollerSwap=dataCopperScrollerRender;
//		dataCopperScrollerRender=dataCopperScrollerWork;
//		dataCopperScrollerWork=dataCopperScrollerSwap;
		
		int drawX;
		int drawY;
		int deltaX;
		int deltaY;
		int diff;
		int index;
		int fontPeekYDelta;
		
		if(renderBuffer2) {
			screendataWorking=LunarEngine.screendataToWork2;
		}else if(renderBuffer3){
			screendataWorking=LunarEngine.screendataToWork3;
		}else {
			return;
		}

		spliner1[splinerIndex].beginRender();
		spliner1[splinerIndex].gotoFirstValue();
		spliner2[splinerIndex].beginRender();
		spliner2[splinerIndex].gotoFirstValue();
		for(int i=0;i<spliner1[splinerIndex].getWaveLen();i++) {
			point1=spliner1[splinerIndex].getNextValue(1);
			point2=spliner2[splinerIndex].getNextValue(1);

			if(SPLINE_LINES) {
				screendataWorking[point1.x+point1.y*Screen.screenSizeX]=0xffff00;
				screendataWorking[point2.x+point2.y*Screen.screenSizeX]=0x00ffff;
			}
			
			drawX=point1.x << SHIFT;
			drawY=(point1.y+sinusOffset) << SHIFT;
			
			deltaY=Math.abs(point2.y-point1.y);
			deltaX=Math.abs(point2.x-point1.x);
			diff=deltaX>deltaY?deltaX:deltaY;
			if(diff==0) {
				deltaX=((point2.x-point1.x) << SHIFT);
				deltaY=((point2.y-point1.y) << SHIFT);
				fontPeekYDelta=(copperScrollerY << SHIFT);
			}else {
				deltaX=((point2.x-point1.x) << SHIFT)/diff;
				deltaY=((point2.y-point1.y) << SHIFT)/diff;
				fontPeekYDelta=(copperScrollerY << SHIFT)/(diff);
				for(int fontPeekY=0;fontPeekY<diff*fontPeekYDelta;fontPeekY+=fontPeekYDelta) {
					pixel=dataCopperScrollerRender[i+(fontPeekY >> SHIFT)*copperScrollerX];
					index=(drawX >> SHIFT) + (drawY >> SHIFT)*Screen.screenSizeX;
					if((pixel & 0xffffff)!=0) {
						screendataWorking[index]=pixel;
						screendataWorking[++index]=pixel;
						screendataWorking[index+=Screen.screenSizeX]=pixel;
						screendataWorking[--index]=pixel;
					}
					drawX+=deltaX;
					drawY+=deltaY;
				}
			}
		}
	}

	@Override
	public void worker3(){
		Screen.screenClear(LunarEngine.screendataToReset,0x000000);
	}
	
	@Override
	public void rotateBuffers() {
		rotateBuffers5();
	}

	
	@Override
	public void rotatePartBuffers() {
		dataCopperScrollerSwap=dataCopperScrollerRender;
		dataCopperScrollerRender=dataCopperScrollerWork;
		dataCopperScrollerWork=dataCopperScrollerSwap;
	}
}