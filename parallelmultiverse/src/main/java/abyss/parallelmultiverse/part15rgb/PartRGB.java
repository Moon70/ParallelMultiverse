package abyss.parallelmultiverse.part15rgb;

import java.util.Arrays;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.Object2D;
import abyss.lunarengine.gfx.PolarBear;
import abyss.lunarengine.gfx.Tools2D;
import abyss.lunarengine.gfx.Triangle;
import abyss.lunarengine.sinatra.Sinatra;

public class PartRGB extends APart{
	public static final int RENDERMETHOD_NOTHING=-1;
	public static final int RENDERMETHOD_HEXAGON=0;
	public static final int RENDERMETHOD_SINGLEBLOCKS=1;
	public static final int RENDERMETHOD_BLOCKS=2;
	public static final int RENDERMETHOD_BOBS=3;
	public int bobOffset;
	
	public int renderMethod;
	public double hexagonMorphRadius;
	public double hexagonMorphCenterRadius;
	public double hexagonMorphAngle;

	private static final int SHIFT=12;
	private static final int BLOCKSIZE=32;
	private double radius;
	private double centerR;
	private double centerA;
	
	private static int box_x;
	private static int box_y;

	private static int box1_x;
	private static int box1_y;
	private static int box2_x;
	private static int box2_y;
	private static int box3_x;
	private static int box3_y;

	private static int blockCountX;
	private static int blockCountY;
	private static int[][] blocks;

	private static final int ANIM_SIZE=200;
	private int animIndex;
	
	public final int[] colorsNormal=new int[] {0x0f0f0f,0x0f0f0f,0x2f0f0f,0x3f0f0f,0x5f0f0f,0x7f0f0f,0x5f0f0f,0x2f0f0f,0x0f0f0f,0x0f0f0f,0x1f1f2f,0x3f3f3f,0x4f4f5f,0x6f6f7f,0x4f4f5f,0x2f2f2f,0x0f0f0f,0x2f1f0f,0x4f2f0f,0x6f3f0f,0x8f4f0f,0x5f3f0f,0x3f1f0f,0x0f0f0f,0x0f0f0f,0x0f1f1f,0x0f2f1f,0x0f2f2f,0x0f3f2f,0x0f4f3f,0x0f3f2f,0x0f2f1f,0x0f1f1f,0x0f0f0f,0x1f0f1f,0x2f1f2f,0x4f2f3f,0x5f2f4f,0x4f2f3f,0x3f1f2f,0x1f0f1f,0x0f0f0f,0x1f1f0f,0x2f2f1f,0x3f3f1f,0x4f4f2f,0x3f3f1f,0x1f1f1f,0x0f0f0f,0x0f1f1f,0x1f2f2f,0x1f3f3f,0x2f4f4f,0x1f3f3f,0x1f1f1f,0x0f0f0f,0x1f0f1f,0x2f0f2f,0x3f0f2f,0x4f0f2f,0x5f0f2f,0x5f0f2f,0x6f0f2f,0x7f0f1f,0x8f0f0f,0x9f1f0f,0xAf2f0f,0xBf4f0f,0xBf6f0f,0xCf7f0f,0xDfAf0f,0xEfCf0f,0xFfFf0f,0xEfDf0f,0xDfCf0f,0xCfBf0f,0xBfAf0f,0xBf9f0f,0xAf8f0f,0x9f7f0f,0x8f6f0f,0x7f5f0f,0x6f4f0f,0x5f3f0f,0x5f3f0f,0x4f2f0f,0x3f1f0f,0x2f1f0f,0x1f0f1f,0x3f0f3f,0x4f1f4f,0x6f2f6f,0x8f4f8f,0xAf6fAf,0xBf9fBf,0xDfBfDf,0xFfFfFf,0xDfCfCf,0xCfAf9f,0xAf8f7f,0x9f6f5f,0x8f4f3f,0x6f3f1f,0x5f2f1f,0x3f1f0f,0x4f2f0f,0x6f3f1f,0x7f5f2f,0x9f7f3f,0xAf9f4f,0xCfBf6f,0xDfDf8f,0xFfFfAf,0xDfCf8f,0xBf9f7f,0x9f6f6f,0x8f4f5f,0x6f3f4f,0x4f2f3f,0x2f1f2f,0x2f0f1f,0x3f1f2f,0x4f1f2f,0x5f1f2f,0x6f1f2f,0x7f1f2f,0x8f2f3f,0x9f3f4f,0xAf4f5f,0xBf5f5f,0xCf6f5f,0xDf7f6f,0xEf8f7f,0xFfAf9f,0xFfCfBf,0xFfEfDf,0xFfEfDf,0xFfEfCf,0xFfEfCf,0xEfDfAf,0xDfDf9f,0xBfCf7f,0xAfBf6f,0x8fAf5f,0x6f9f4f,0x4f8f3f,0x3f7f2f,0x2f6f2f,0x1f5f2f,0x0f3f2f,0x0f2f2f,0x0f1f1f,0x0f1f1f,0x0f2f2f,0x0f3f3f,0x1f3f4f,0x1f4f5f,0x1f5f5f,0x2f5f6f,0x3f6f7f,0x3f7f8f,0x4f8f9f,0x5f8fAf,0x6f9fBf,0x7fAfBf,0x9fBfCf,0xAfCfDf,0xBfDfEf,0xDfEfFf,0xBfCfEf,0xAfAfDf,0x9f9fDf,0x8f7fCf,0x8f6fBf,0x8f5fAf,0x8f4f9f,0x8f3f9f,0x8f3f8f,0x7f2f6f,0x6f1f5f,0x5f1f3f,0x5f1f2f,0x4f0f1f,0x3f0f0f};
	public int[] colorsDark;
	public int[] colors=new int[] {0x0f0f0f,0x0f0f0f,0x2f0f0f,0x3f0f0f,0x5f0f0f,0x7f0f0f,0x5f0f0f,0x2f0f0f,0x0f0f0f,0x0f0f0f,0x1f1f2f,0x3f3f3f,0x4f4f5f,0x6f6f7f,0x4f4f5f,0x2f2f2f,0x0f0f0f,0x2f1f0f,0x4f2f0f,0x6f3f0f,0x8f4f0f,0x5f3f0f,0x3f1f0f,0x0f0f0f,0x0f0f0f,0x0f1f1f,0x0f2f1f,0x0f2f2f,0x0f3f2f,0x0f4f3f,0x0f3f2f,0x0f2f1f,0x0f1f1f,0x0f0f0f,0x1f0f1f,0x2f1f2f,0x4f2f3f,0x5f2f4f,0x4f2f3f,0x3f1f2f,0x1f0f1f,0x0f0f0f,0x1f1f0f,0x2f2f1f,0x3f3f1f,0x4f4f2f,0x3f3f1f,0x1f1f1f,0x0f0f0f,0x0f1f1f,0x1f2f2f,0x1f3f3f,0x2f4f4f,0x1f3f3f,0x1f1f1f,0x0f0f0f,0x1f0f1f,0x2f0f2f,0x3f0f2f,0x4f0f2f,0x5f0f2f,0x5f0f2f,0x6f0f2f,0x7f0f1f,0x8f0f0f,0x9f1f0f,0xAf2f0f,0xBf4f0f,0xBf6f0f,0xCf7f0f,0xDfAf0f,0xEfCf0f,0xFfFf0f,0xEfDf0f,0xDfCf0f,0xCfBf0f,0xBfAf0f,0xBf9f0f,0xAf8f0f,0x9f7f0f,0x8f6f0f,0x7f5f0f,0x6f4f0f,0x5f3f0f,0x5f3f0f,0x4f2f0f,0x3f1f0f,0x2f1f0f,0x1f0f1f,0x3f0f3f,0x4f1f4f,0x6f2f6f,0x8f4f8f,0xAf6fAf,0xBf9fBf,0xDfBfDf,0xFfFfFf,0xDfCfCf,0xCfAf9f,0xAf8f7f,0x9f6f5f,0x8f4f3f,0x6f3f1f,0x5f2f1f,0x3f1f0f,0x4f2f0f,0x6f3f1f,0x7f5f2f,0x9f7f3f,0xAf9f4f,0xCfBf6f,0xDfDf8f,0xFfFfAf,0xDfCf8f,0xBf9f7f,0x9f6f6f,0x8f4f5f,0x6f3f4f,0x4f2f3f,0x2f1f2f,0x2f0f1f,0x3f1f2f,0x4f1f2f,0x5f1f2f,0x6f1f2f,0x7f1f2f,0x8f2f3f,0x9f3f4f,0xAf4f5f,0xBf5f5f,0xCf6f5f,0xDf7f6f,0xEf8f7f,0xFfAf9f,0xFfCfBf,0xFfEfDf,0xFfEfDf,0xFfEfCf,0xFfEfCf,0xEfDfAf,0xDfDf9f,0xBfCf7f,0xAfBf6f,0x8fAf5f,0x6f9f4f,0x4f8f3f,0x3f7f2f,0x2f6f2f,0x1f5f2f,0x0f3f2f,0x0f2f2f,0x0f1f1f,0x0f1f1f,0x0f2f2f,0x0f3f3f,0x1f3f4f,0x1f4f5f,0x1f5f5f,0x2f5f6f,0x3f6f7f,0x3f7f8f,0x4f8f9f,0x5f8fAf,0x6f9fBf,0x7fAfBf,0x9fBfCf,0xAfCfDf,0xBfDfEf,0xDfEfFf,0xBfCfEf,0xAfAfDf,0x9f9fDf,0x8f7fCf,0x8f6fBf,0x8f5fAf,0x8f4f9f,0x8f3f9f,0x8f3f8f,0x7f2f6f,0x6f1f5f,0x5f1f3f,0x5f1f2f,0x4f0f1f,0x3f0f0f};

	private int colorIndex;
	private int colorIndex2;
	private int colorIndex3;
	private int colorIndex4;
	private int color1;
	private int color2;
	private int color3;
	private int color4;
	private int colorRotateSleep;

	private Tools2D tools2D;

	private Sinatra sinatraBlocksX1;
	private Sinatra sinatraBlocksX2;
	private Sinatra sinatraBlocksY1;
	private Sinatra sinatraBlocksY2;

	private Sinatra sinatraBlock1X1;
	private Sinatra sinatraBlock1X2;
	private Sinatra sinatraBlock1Y1;
	private Sinatra sinatraBlock1Y2;

	private Sinatra sinatraBlock2X1;
	private Sinatra sinatraBlock2X2;
	private Sinatra sinatraBlock2Y1;
	private Sinatra sinatraBlock2Y2;

	private Sinatra sinatraBlock3X1;
	private Sinatra sinatraBlock3X2;
	private Sinatra sinatraBlock3Y1;
	private Sinatra sinatraBlock3Y2;

	private Sinatra sinatraTurn;
	
	private Triangle[] hexagon;
	private SinusBobs[] sinusBobs;
	
	public static void main(String[] args){
		try{
			//new SinatraController().openGUI();

			LunarEngine.initializeEngine();
			PartRGB partRGB=new PartRGB();
			partRGB.precalc();
			partRGB.initialize();
			LunarEngine.setActivePart(partRGB);
			partRGB.renderMethod=RENDERMETHOD_BOBS;
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public PartRGB() {
		blockCountX=Screen.screenSizeX/BLOCKSIZE;
		blockCountY=Screen.screenSizeY/BLOCKSIZE;
		blocks=new int[blockCountX*blockCountY*2][ANIM_SIZE];

	}
	
	@Override
	public void precalc(){
		tools2D=new Tools2D();
		colorsDark=new int[colorsNormal.length];
		int color=0;
		for(int i=0;i<colorsNormal.length;i++) {
			color=colorsNormal[i];
			color=(color & 0xfefefe)>>1;
			colorsDark[i]=color;
		}
		sinatraBlocksX1=Sinatra.getInstance(new double[]{1800.0,3.0,175.0,0.2,0.0,360.0,200.0,1.0,0.0,360.0,190.0,1.0,0.0,180.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlocksX2=Sinatra.getInstance(new double[]{720.0,2.0,100.0,0.5,0.0,360.0,27.0,2.0,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlocksY1=Sinatra.getInstance(new double[]{1800.0,2.0,100.0,0.2,0.0,360.0,100.0,1.0,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlocksY2=Sinatra.getInstance(new double[]{720.0,2.0,100.0,0.5,0.0,360.0,100.0,2.0,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		
		sinatraBlock1X1=Sinatra.getInstance(new double[]{3600.0,2.0,200.0,0.5,0.0,360.0,100.0,0.4,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock1X2=Sinatra.getInstance(new double[]{1800.0,2.0,150.0,0.6,0.0,360.0,50.0,0.4,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock1Y1=Sinatra.getInstance(new double[]{1800.0,2.0,150.0,0.4,0.0,360.0,80.0,0.2,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock1Y2=Sinatra.getInstance(new double[]{720.0,2.0,50.0,1.0,0.0,360.0,50.0,0.5,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		
		sinatraBlock2X1=Sinatra.getInstance(new double[]{1200.0,2.0,200.0,0.6,0.0,360.0,100.0,0.3,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock2X2=Sinatra.getInstance(new double[]{3600.0,2.0,120.0,0.3,0.0,360.0,106.0,0.5,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock2Y1=Sinatra.getInstance(new double[]{3600.0,2.0,150.0,0.3,0.0,360.0,116.0,0.5,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock2Y2=Sinatra.getInstance(new double[]{1800.0,3.0,60.0,0.8,0.0,360.0,60.0,0.4,0.0,360.0,62.0,0.6,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		
		sinatraBlock3X1=Sinatra.getInstance(new double[]{3600.0,3.0,50.0,0.4,0.0,360.0,100.0,0.3,0.0,360.0,50.0,1.0,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock3X2=Sinatra.getInstance(new double[]{1800.0,2.0,100.0,0.2,0.0,360.0,100.0,0.4,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock3Y1=Sinatra.getInstance(new double[]{1800.0,2.0,100.0,0.4,0.0,360.0,50.0,1.0,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinatraBlock3Y2=Sinatra.getInstance(new double[]{900.0,2.0,50.0,0.8,0.0,360.0,30.0,0.4,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		
		sinatraTurn=Sinatra.getInstance(new double[]{3600.0,3.0,50.0,1.0,0.0,360.0,25.0,0.5,0.0,360.0,25.0,0.6,0.0,360.0});//generated by LunarTools Sinus Editor 1.0

		int xm,ym;
		double a,r;
		for(int i=0;i<ANIM_SIZE;i++) {
			for(int y=0;y<blockCountY;y++) {
				for(int x=0;x<blockCountX;x++) {
					xm=x*BLOCKSIZE;
					ym=y*BLOCKSIZE;
					blocks[(x+y*blockCountX)*2][i]=xm+ym*Screen.screenSizeX;
					xm-=Screen.screenCenterX;
					ym-=Screen.screenCenterY;
					a=PolarBear.calcAlpha(xm, ym);
					if(xm<0) {
						a-=180;
					}
//					a+=0.2*i-20;
					a+=0.15*i-20;
					r=PolarBear.calcRadius(xm, ym);
					r=r*96.5/100.0;
					xm=PolarBear.calcXint(r,a);
					ym=PolarBear.calcYint(r,a);
					xm+=Screen.screenCenterX;
					ym+=Screen.screenCenterY;
					if(xm<0 || xm>(Screen.screenSizeX-BLOCKSIZE) || ym<0 || ym>(Screen.screenSizeY-BLOCKSIZE)){
						blocks[(x+y*blockCountX)*2][i]=-1;
					}else {
						blocks[(x+y*blockCountX)*2+1][i]=xm+ym*Screen.screenSizeX;
					}
				}
			}
		}

		hexagon=createHexagon();
		
		sinusBobs=new SinusBobs[6];
		sinusBobs[0]=new SinusBobs(0xCf7f0f,0);
		sinusBobs[0].sinatraBobsX1=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[0].sinatraBobsX2=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[0].sinatraBobsY1=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[0].sinatraBobsY2=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[0].deltaX1=12;
		sinusBobs[0].deltaX2=15;
		sinusBobs[0].deltaY1=10;
		sinusBobs[0].deltaY2=14;
		
		sinusBobs[1]=new SinusBobs(0x8fAf5f,42);
		sinusBobs[1].sinatraBobsX1=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[1].sinatraBobsX2=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[1].sinatraBobsY1=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[1].sinatraBobsY2=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[1].deltaX1=10;
		sinusBobs[1].deltaX2=13;
		sinusBobs[1].deltaY1=12;
		sinusBobs[1].deltaY2=16;

		sinusBobs[2]=new SinusBobs(0x6f6f7f,84);
		sinusBobs[2].sinatraBobsX1=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[2].sinatraBobsX2=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[2].sinatraBobsY1=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[2].sinatraBobsY2=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[2].deltaX1=14;
		sinusBobs[2].deltaX2=15;
		sinusBobs[2].deltaY1=8;
		sinusBobs[2].deltaY2=12;

		sinusBobs[3]=new SinusBobs(0x1f3f4f,0);
		sinusBobs[3].sinatraBobsX1=Sinatra.getInstance(new double[]{2048.0,3.0,90.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[3].sinatraBobsX2=Sinatra.getInstance(new double[]{2048.0,3.0,90.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[3].sinatraBobsY1=Sinatra.getInstance(new double[]{2048.0,3.0,123.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[3].sinatraBobsY2=Sinatra.getInstance(new double[]{2048.0,3.0,123.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[3].deltaX1=12;
		sinusBobs[3].deltaX2=15;
		sinusBobs[3].deltaY1=10;
		sinusBobs[3].deltaY2=14;
		
		sinusBobs[4]=new SinusBobs(0x5f1f2f,0);
		sinusBobs[4].sinatraBobsX1=Sinatra.getInstance(new double[]{2048.0,3.0,90.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,180.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[4].sinatraBobsX2=Sinatra.getInstance(new double[]{2048.0,3.0,90.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,180.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[4].sinatraBobsY1=Sinatra.getInstance(new double[]{3600.0,3.0,127.0,0.5,0.0,360.0,199.0,0.5,180.0,360.0,199.0,0.3,0.0,180.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[4].sinatraBobsY2=Sinatra.getInstance(new double[]{3600.0,3.0,127.0,0.5,0.0,360.0,199.0,0.5,180.0,360.0,199.0,0.3,0.0,180.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[4].deltaX1=10;
		sinusBobs[4].deltaX2=13;
		sinusBobs[4].deltaY1=12;
		sinusBobs[4].deltaY2=16;

		sinusBobs[5]=new SinusBobs(0x555555,0);
		sinusBobs[5].sinatraBobsX1=Sinatra.getInstance(new double[]{3600.0,3.0,199.0,0.2,0.0,360.0,128.0,0.3,0.0,360.0,22.0,2.0,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[5].sinatraBobsX2=Sinatra.getInstance(new double[]{3600.0,3.0,199.0,0.2,0.0,360.0,128.0,0.3,0.0,360.0,22.0,2.0,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[5].sinatraBobsY1=Sinatra.getInstance(new double[]{3600.0,3.0,61.0,0.5,0.0,360.0,112.0,0.5,180.0,360.0,199.0,0.3,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[5].sinatraBobsY2=Sinatra.getInstance(new double[]{3600.0,3.0,61.0,0.5,0.0,360.0,112.0,0.5,180.0,360.0,199.0,0.3,0.0,360.0});//generated by LunarTools Sinus Editor 1.0
		sinusBobs[5].deltaX1=14;
		sinusBobs[5].deltaX2=15;
		sinusBobs[5].deltaY1=8;
		sinusBobs[5].deltaY2=12;
	}
	
	@Override
	public void initialize() {
		colorIndex=0;
		colorIndex2=10;
		colorIndex3=20;
		colorIndex4=30;
		animIndex=0;
		Triangle.tools2D=tools2D;
		hexagonMorphRadius=350;
		hexagonMorphCenterRadius=280;
		hexagonMorphAngle=45;
		sinatraBlocksX1.setIndex(0);
		sinatraBlocksX2.setIndex(0);
		sinatraBlocksY1.setIndex(0);
		sinatraBlocksY2.setIndex(0);
		
		sinatraBlock1X1.setIndex(0);
		sinatraBlock1X2.setIndex(0);
		sinatraBlock1Y1.setIndex(0);
		sinatraBlock1Y2.setIndex(0);
		
		sinatraBlock2X1.setIndex(0);
		sinatraBlock2X2.setIndex(0);
		sinatraBlock2Y1.setIndex(0);
		sinatraBlock2Y2.setIndex(0);
		
		sinatraBlock3X1.setIndex(0);
		sinatraBlock3X2.setIndex(0);
		sinatraBlock3Y1.setIndex(0);
		sinatraBlock3Y2.setIndex(0);
		
		sinatraTurn.setIndex(0);
		bobOffset=0;
	}

	@Override
	public void vbi(){
		box_x=sinatraBlocksX1.getNextValue(1)+sinatraBlocksX2.getNextValue(1)+Screen.screenCenterX;
		box_y=sinatraBlocksY1.getNextValue(1)+sinatraBlocksY2.getNextValue(1)+Screen.screenCenterY;

		box1_x=sinatraBlock1X1.getNextValue(1)+sinatraBlock1X2.getNextValue(1)+Screen.screenCenterX-100;
		box1_y=sinatraBlock1Y1.getNextValue(1)+sinatraBlock1Y2.getNextValue(1)+Screen.screenCenterY-50;
		box2_x=sinatraBlock2X1.getNextValue(1)+sinatraBlock2X2.getNextValue(1)+Screen.screenCenterX+100;
		box2_y=sinatraBlock2Y1.getNextValue(1)+sinatraBlock2Y2.getNextValue(1)+Screen.screenCenterY+30;
		box3_x=sinatraBlock3X1.getNextValue(1)+sinatraBlock3X2.getNextValue(1)+Screen.screenCenterX-200;
		box3_y=sinatraBlock3Y1.getNextValue(1)+sinatraBlock3Y2.getNextValue(1)+Screen.screenCenterY-200;

		animIndex=sinatraTurn.getNextValue(1)+100;

		switch(renderMethod) {
		case RENDERMETHOD_HEXAGON:
			morphHexagon();
			for(int i=0;i<hexagon.length;i++) {
				hexagon[i].rotateVBI();
			}
			break;
		case RENDERMETHOD_BOBS:
			for(int i=bobOffset;i<bobOffset+3;i++) {
				sinusBobs[i].vbi();
			}
			break;
		}
		
		if(--colorRotateSleep<0) {
			colorRotateSleep=3;
			color1=colors[colorIndex];
			if(++colorIndex==colors.length) {
				colorIndex=0;
			}
			color2=colors[colorIndex2];
			if(++colorIndex2==colors.length) {
				colorIndex2=0;
			}
			color3=colors[colorIndex3];
			if(++colorIndex3==colors.length) {
				colorIndex3=0;
			}
			color4=colors[colorIndex4];
			if(++colorIndex4==colors.length) {
				colorIndex4=0;
			}
		}

//		if(SinatraController.devSinarta1!=null) {
//			if(SinatraController.devSinarta1[0]!=null) {
//				sinatraBlocksX1=SinatraController.devSinarta1[0];
//			}
//			if(SinatraController.devSinarta1[1]!=null) {
//				sinatraBlocksX2=SinatraController.devSinarta1[1];
//			}
//			if(SinatraController.devSinarta1[2]!=null) {
//				sinatraBlocksY1=SinatraController.devSinarta1[2];
//			}
//			if(SinatraController.devSinarta1[3]!=null) {
//				sinatraBlocksY2=SinatraController.devSinarta1[3];
//			}

			
//			if(SinatraController.devSinarta1[0]!=null) {
//				sinatraBlock3X1=SinatraController.devSinarta1[0];
//			}
//			if(SinatraController.devSinarta1[1]!=null) {
//				sinatraBlock3X2=SinatraController.devSinarta1[1];
//			}
//			if(SinatraController.devSinarta1[2]!=null) {
//				sinatraBlock3Y1=SinatraController.devSinarta1[2];
//			}
//			if(SinatraController.devSinarta1[3]!=null) {
//				sinatraBlock3Y2=SinatraController.devSinarta1[3];
//			}

			
//			if(SinatraController.devSinarta1[0]!=null) {
//				sinatraTurn=SinatraController.devSinarta1[0];
//			}
//		}
	}

	@Override
	public void worker1(){
		final int[] screendataRender=LunarEngine.screendataToWork2;
		final int[] screendataWorking=LunarEngine.screendataToWork;
		tools2D.screendataWorking=screendataWorking;
		int indexY;
		int index;
		int pixel;
		int pixelOld;
		int pixelIndex;
		for(int i=0;i<blocks.length;i++) {
			if((pixelIndex=blocks[i++][animIndex])>=0) {
				for(indexY=pixelIndex;indexY<Screen.screenSizeX*BLOCKSIZE+pixelIndex;indexY+=Screen.screenSizeX) {
					for(index=indexY;index<indexY+BLOCKSIZE;index++){
						pixel=screendataRender[index];
						pixelOld=screendataRender[blocks[i][animIndex]+index-pixelIndex];
						if(pixelOld!=pixel) {
							screendataWorking[index]=((pixelOld & 0xfefefe)+(pixel & 0xfefefe))>>1;
						}else {
							pixelOld=pixelOld & 0xfcfcfC;
							screendataWorking[index]=(pixelOld+pixelOld+pixelOld)>>2;
						}
					}
				}
			}
		}

		switch(renderMethod) {
		case RENDERMETHOD_HEXAGON:
			drawHexagon(screendataWorking);
			break;
		case RENDERMETHOD_SINGLEBLOCKS:
			drawBlocksSingle(screendataWorking);
			break;
		case RENDERMETHOD_BLOCKS:
			drawBlocks(screendataWorking);
			break;
		case RENDERMETHOD_BOBS:
			drawBobs(screendataWorking);
			break;
		}
	}

	private void drawHexagon(int[] screendataWorking) {
		Object2D.tools2D.screendataWorking=screendataWorking;
		for(int i=0;i<hexagon.length;i++) {
			hexagon[i].rotateCALC();
			switch(i) {
			case 0:
			case 1:
				hexagon[i].drawFilled(Screen.screenCenterX,Screen.screenCenterY,color1);
				break;
			case 2:
			case 3:
				hexagon[i].drawFilled(Screen.screenCenterX,Screen.screenCenterY,color2);
				break;
			case 4:
			case 5:
				hexagon[i].drawFilled(Screen.screenCenterX,Screen.screenCenterY,color3);
				break;
			}
		}
	}
	
	private void drawBlocksSingle(int[] screendataWorking) {
		for(int y=box1_y*Screen.screenSizeX;y<(box1_y+100)*Screen.screenSizeX;y+=Screen.screenSizeX){
			Arrays.fill(screendataWorking, box1_x+y, box1_x+y+100, color1);
		}
		for(int y=box2_y*Screen.screenSizeX;y<(box2_y+100)*Screen.screenSizeX;y+=Screen.screenSizeX){
			Arrays.fill(screendataWorking, box2_x+y, box2_x+y+100, color2);
		}
		for(int y=box3_y*Screen.screenSizeX;y<(box3_y+100)*Screen.screenSizeX;y+=Screen.screenSizeX){
			Arrays.fill(screendataWorking, box3_x+y, box3_x+y+100, color3);
		}
	}

	private void drawBlocks(int[] screendataWorking) {
		int offset;
		for(int y=box_y;y<box_y+100;y++){
			offset=y*Screen.screenSizeX+box_x;
			Arrays.fill(screendataWorking, offset, offset+100, color1);

			offset=y*Screen.screenSizeX+Screen.screenSizeX-(box_x);
			Arrays.fill(screendataWorking, offset, offset+100, color2);
			
			offset=(Screen.screenSizeY-1-y)*Screen.screenSizeX+Screen.screenSizeX-(box_x);
			Arrays.fill(screendataWorking, offset, offset+100, color3);
			
			offset=(Screen.screenSizeY-1-y)*Screen.screenSizeX+(box_x);
			Arrays.fill(screendataWorking, offset, offset+100, color4);
		}
	}
	
	private void morphHexagon() {
		double angle1=0;
		double angle2=60;
		double centerAtemp=centerA;
		
		if(radius<hexagonMorphRadius) {
			radius+=1;
		}else if(radius>hexagonMorphRadius) {
			radius-=1;
		}
		
		if(centerR<hexagonMorphCenterRadius) {
			centerR+=1.0;
		}else if(centerR>hexagonMorphCenterRadius) {
			centerR-=1.0;
		}
		
		if(centerA<hexagonMorphAngle) {
			centerA+=1.0;
		}else if(centerA>hexagonMorphAngle) {
			centerA-=1.0;
		}
		
		int centerX=0;
		int centerY=0;
		for(int i=0;i<6;i++) {
			if((i&1)==0) {
				centerX=(int)PolarBear.calcXint(centerR, centerAtemp);
				centerY=(int)PolarBear.calcYint(centerR, centerAtemp);
				centerAtemp+=120;
			}
			hexagon[i].vo[0][0]=centerX << SHIFT;
			hexagon[i].vo[0][1]=centerY << SHIFT;
			hexagon[i].vo[0][2]=0;
			hexagon[i].vo[1][0]=(int)PolarBear.calcXint(radius, angle1) << SHIFT;
			hexagon[i].vo[1][1]=(int)PolarBear.calcYint(radius, angle1) << SHIFT;
			hexagon[i].vo[1][2]=0;
			hexagon[i].vo[2][0]=(int)PolarBear.calcXint(radius, angle2) << SHIFT;
			hexagon[i].vo[2][1]=(int)PolarBear.calcYint(radius, angle2) << SHIFT;
			hexagon[i].vo[2][2]=0;
			hexagon[i].turnAlpha=2;
			
			angle1+=60;
			angle2+=60;
		}
		return;
	}

	private void drawBobs(int[] screendataWorking) {
		Bob.screendataToWork=screendataWorking;
		for(int i=bobOffset;i<bobOffset+3;i++) {
			sinusBobs[i].render();
		}
	}
	
	private Triangle[] createHexagon() {
		Triangle[] triangles=new Triangle[6];
		for(int i=0;i<6;i++) {
			triangles[i]=new Triangle();
			triangles[i].vo=new int[3][3];
			triangles[i].to=new int[3][3];
			triangles[i].vo[0][0]=0;
			triangles[i].vo[0][1]=0;
			triangles[i].vo[0][2]=0;
			triangles[i].vo[1][0]=0;
			triangles[i].vo[1][1]=0;
			triangles[i].vo[1][2]=0;
			triangles[i].vo[2][0]=0;
			triangles[i].vo[2][1]=0;
			triangles[i].vo[2][2]=0;
			
			triangles[i].turnAlpha=1;
		}
		return triangles;
	}

	@Override
 	public void worker2(){
		Screen.screenClear(LunarEngine.screendataToReset,0);
	}

	@Override
	public void rotateBuffers() {
		rotateBuffers5();
	}
}
