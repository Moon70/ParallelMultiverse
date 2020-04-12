package abyss.parallelmultiverse.part10confetti;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob2;
import abyss.lunarengine.gfx.LordFader;
import abyss.lunarengine.gfx.PolarBear;
import abyss.lunarengine.tools.Random;

public class PartConfetti extends APart{
	public int mode;
	public boolean disableConfettiCanons;
	public static final int MODE_RAIN=1;
	public static final int MODE_CANNON=2;
	public static final int NUMBER_OF_BOBS=30;
	public static final int NUMBER_OF_BOBFRAMES=60;
	
	private Confetti[] confettis;
	private final int CONFETTI_COUNT=2000;
	
	public static final int confettiSize=16;
	public static final int bobSize=confettiSize+confettiSize+1;
	
	private LordFader fader;
	private LordFader faderConfetti;
	private Bob2[] bob;
	private int[][] colors;
	private int nextConfettiIndex;
	private int firstShotLeft=200;
	private int firstShotRight=200;
	
	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			PartConfetti partConfetti=new PartConfetti();
			partConfetti.precalc();
			partConfetti.initialize();
			partConfetti.mode=PartConfetti.MODE_RAIN;
			LunarEngine.setActivePart(partConfetti);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public PartConfetti() {
		bob=new Bob2[NUMBER_OF_BOBS];
		confettis=new Confetti[CONFETTI_COUNT];
	}
	
	@Override
	public void precalc(){
		fader=new LordFader(0x0000ff,0x000000,31);
		
		int[] cols=new int[] {
				0x330000,
				0x003300,
				0x000033,
				0x333300,
				0x330033,
				0x003333,
				0x333333
		};
		
		int[] cols2=new int[] {
				0xff9999,
				0x99ff99,
				0x9999ff,
				0xffff99,
				0xff99ff,
				0x99ffff,
				0xffffff
		};
		
		colors=new int[cols.length][];
		int step1=230;
		int step2=256-step1;
		for(int i=0;i<cols.length;i++) {
			colors[i]=new int[256];
			faderConfetti=new LordFader(0x000000,cols[i],step1);
			for(int j=0;j<step1;j++) {
				colors[i][j]=faderConfetti.colors[j];
			}
			faderConfetti=new LordFader(cols[i],cols2[i],step2);
			for(int j=0;j<step2;j++) {
				colors[i][step1+j]=faderConfetti.colors[j];
			}
		}
		
		bob[0]=new Bob2(NUMBER_OF_BOBFRAMES,bobSize,bobSize);
		
		for(int i=0;i<NUMBER_OF_BOBFRAMES;i++) {
			int r2a=i+i+i;
			int radius2=PolarBear.calcXint(confettiSize,r2a);
			int colorindex=i>=NUMBER_OF_BOBFRAMES/2?NUMBER_OF_BOBFRAMES-i:i;
			for(int a=90;a<180+90;a++) {
				int x1=PolarBear.calcXint(radius2, a);
				int y1=PolarBear.calcYint(confettiSize, a);

				double r1=PolarBear.calcRadius(x1, y1);
				double a1=PolarBear.calcAlpha(x1, y1);
				x1=PolarBear.calcXint(r1, a1);
				y1=PolarBear.calcYint(r1, a1);

				for(int x=-x1;x<=x1;x++) {
					int xx=x+confettiSize;
					int yy=(y1+confettiSize)*bobSize;
					bob[0].bobdata[i][xx+yy]=fader.colors[colorindex];
				}
			}
		}
		
		double angle=0;
		for(int b=1;b<NUMBER_OF_BOBS;b++) {
			bob[b]=new Bob2(NUMBER_OF_BOBFRAMES,bobSize,bobSize);
			angle+=(180/NUMBER_OF_BOBS);
			for(int f=0;f<NUMBER_OF_BOBFRAMES;f++) {
				for(double y=-confettiSize;y<=confettiSize;y+=0.7) {
					for(double x=-confettiSize;x<=confettiSize;x+=0.7) {
						double r=PolarBear.calcRadius(x, y);
						double a=PolarBear.calcAlpha(x, y)+angle;
						if(x<0) {
							a+=180;
						}
						int x1=confettiSize+PolarBear.calcXint(r, a);
						int y1=confettiSize+PolarBear.calcYint(r, a);
						int x2=(int)(x+0.5)+confettiSize;
						int y2=(int)(y+0.5)+confettiSize;
						if(x1>=0 && x1<bobSize && y1>=0 && y1<bobSize) {
							bob[b].bobdata[f][x1+y1*bobSize]=bob[0].bobdata[f][x2+y2*bobSize];
						}
					}
				}
			}
		}
		
		for(int i=0;i<bob.length;i++) {
			bob[i].enabled=true;
		}
		
		final int[] deltaY= {48,52,56,60,64,68,72,76,80,84,88,92,96,100,104,108,112,116,120,124,128};
		final int[] deltaBob= {5,7,13,17,19};
		final int[] deltaFrame= {17,8,19,5,7,13};
		final int[] startBob= {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28};
		final int[] startFrame= {3,6,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57};
		for(int i=0;i<CONFETTI_COUNT;i++) {
			confettis[i]=new Confetti(
					(int)(Random.random()*1900),			//offsetX
					(int)(Random.random()*1000)-1100,		//offsetY
					0,
					deltaY[i%deltaY.length],
					deltaBob[i%deltaBob.length],
					deltaFrame[i%deltaFrame.length],
					startBob[i%startBob.length],
					startFrame[i%startFrame.length],
					colors[i%colors.length]
						);
		}
		Confetti.bob=bob;
	}

	@Override
	public void initialize() {
		Confetti.fadeout=false;
		switch(mode) {
		case MODE_RAIN:
			break;
		case MODE_CANNON:
			for(int i=0;i<CONFETTI_COUNT;i++) {
				confettis[i].enabled=false;
			}
			break;
		}
	}
	
	public void beginFadeOut() {
		Confetti.fadeout=true;
		for(int i=0;i<CONFETTI_COUNT;i++) {
			if(confettis[i].offsetY<0) {
				confettis[i].enabled=false;
			}else {
				confettis[i].deltaY+=6<<5;
			}
		}
	}

	@Override
	public void vbi(){
		switch(mode) {
		case MODE_RAIN:
			for(int i=0;i<CONFETTI_COUNT;i++) {
				confettis[i].vbiRain();
			}
			break;
		case MODE_CANNON:
			for(int i=0;i<CONFETTI_COUNT;i++) {
				confettis[i].vbiCannons();
			}
			if(disableConfettiCanons) {
				return;
			}
			for(int i=0;nextConfettiIndex<CONFETTI_COUNT;nextConfettiIndex++,i++) {
				if(!confettis[nextConfettiIndex].enabled) {
					confettis[nextConfettiIndex].enabled=true;
					confettis[nextConfettiIndex].offsetX=0;
					confettis[nextConfettiIndex].offsetY=Screen.screenSizeY<<5;
					confettis[nextConfettiIndex].deltaX=((int)(Random.random()*(11<<5)))+(2<<5);
					confettis[nextConfettiIndex].deltaY=-((int)(Random.random()*(8<<5)))-(4<<5);
					if(firstShotLeft==0) {
						if(i>1) {
							break;
						}
					}else {
						firstShotLeft--;
					}
				}
			}
			if(nextConfettiIndex==CONFETTI_COUNT) {
				nextConfettiIndex=0;
			}

			for(int i=0;nextConfettiIndex<CONFETTI_COUNT;nextConfettiIndex++,i++) {
				if(!confettis[nextConfettiIndex].enabled) {
					confettis[nextConfettiIndex].enabled=true;
					confettis[nextConfettiIndex].offsetX=Screen.screenSizeX<<5;
					confettis[nextConfettiIndex].offsetY=Screen.screenSizeY<<5;
					confettis[nextConfettiIndex].deltaX=-((int)(Random.random()*(11<<5)))-(2<<5);
					confettis[nextConfettiIndex].deltaY=-((int)(Random.random()*(8<<5)))-(4<<5);
					if(firstShotRight==0) {
						if(i>1) {
							break;
						}
					}else {
						firstShotRight--;
					}
				}
			}
			if(nextConfettiIndex==CONFETTI_COUNT) {
				nextConfettiIndex=0;
			}
			break;
		}
	}

	@Override
	public void worker2(){
		Bob2.screendataToWork=LunarEngine.screendataToWork;
		Confetti.screendataToWork=LunarEngine.screendataToWork;
		
		for(int i=0;i<CONFETTI_COUNT;i++) {
			confettis[i].render();
		}
	}

	@Override
	public void worker3(){
		Screen.screenClear(LunarEngine.screendataToReset,0);
	}

}
