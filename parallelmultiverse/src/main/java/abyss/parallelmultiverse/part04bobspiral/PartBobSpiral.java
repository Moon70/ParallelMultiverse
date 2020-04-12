package abyss.parallelmultiverse.part04bobspiral;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.BobFactory;
import abyss.lunarengine.sinatra.Sinatra;
import abyss.parallelmultiverse.part02abysslogo.PartAbyssLogo;

public class PartBobSpiral extends APart{
	private Sinatra sinatraX;
	private Sinatra sinatraY;
	private int[][] tunnelMove;
	private int[][] tunnelRender;
	private int[][] tunnelWork;
	private static final int SPIRAL_LENGTH=160+100;
	private int tunnelIndex;
	
	private Bob bob;
	private int sinTab[];
	private int cosTab[];
	public boolean fadeIn=true;
	private static final int SHIFT=12;
	private int fadePos;
	private volatile int deltaAngle;

	public static void main(String[] args){
		try{
			//new SinatraController().openGUI();

			LunarEngine.initializeEngine();
			APart partBobSpiral=new PartBobSpiral();
			partBobSpiral.precalc();
			LunarEngine.setActivePart(partBobSpiral);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public PartBobSpiral() {
		sinTab=new int[360];
		cosTab=new int[360];
		tunnelMove=new int[SPIRAL_LENGTH][3];
		tunnelRender=new int[SPIRAL_LENGTH][3];
		tunnelWork=new int[SPIRAL_LENGTH][3];
	}
	
	@Override
	public void precalc(){
		for(int i=0;i<360;i++) {
			sinTab[i]=(int)(Math.sin(i*Math.PI/180)*(1<<SHIFT)+0.5);
			cosTab[i]=(int)(Math.cos(i*Math.PI/180)*(1<<SHIFT)+0.5);
		}
		
		bob=BobFactory.createBob(PartBobSpiral.class, "/data/spiralbob/");
		bob.enabled=true;
		
		sinatraX=Sinatra.getInstance(new double[]{2048.0,3.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,200.0,0.3515625,0.0,360.0,0.0,0.0,0.0});
		sinatraY=Sinatra.getInstance(new double[]{2048.0,2.0,1.0,0.703125,0.0,360.0,160.0,0.17578125,0.0,360.0,180.0,0.3515625,0.0,360.0,0.0,0.0,0.0});

		for(int i=0;i<SPIRAL_LENGTH;i++) {
			vbi();
		}
	}

	@Override
	public void initialize() {
		fadePos=SPIRAL_LENGTH;
	}

	@Override
	public void vbi(){
		if(fadeIn) {
			if(fadePos>0) {
				fadePos--;
			}
		}else {
			if(fadePos<SPIRAL_LENGTH) {
				fadePos++;
			}
		}
		
		for(int i=0;i<SPIRAL_LENGTH;i++) {
			tunnelMove[i][2]+=1;
		}
		
		if(tunnelIndex==SPIRAL_LENGTH-1) {
			tunnelIndex=0;
		}else {
			tunnelIndex++;
		}
			
		tunnelMove[tunnelIndex][0]=sinatraX.getNextValue(4);
		tunnelMove[tunnelIndex][1]=sinatraY.getNextValue(4);
		tunnelMove[tunnelIndex][2]=40;
		
		int index=0;
		for(int i=tunnelIndex+1;i<SPIRAL_LENGTH;i++) {
			tunnelWork[index++]=tunnelMove[i];
		}
		for(int i=0;i<=tunnelIndex;i++) {
			tunnelWork[index++]=tunnelMove[i];
		}
		tunnelRender=tunnelWork;
		
		deltaAngle+=7;
		bob.vbi();
	}

	@Override
	public void worker2(){
		Bob.screendataToWork=LunarEngine.screendataToWork2;
		int radius;
		int deltaLoop=deltaAngle;
		for(int t=0;t<SPIRAL_LENGTH;t++) {
			if(t>=fadePos) {
				radius=tunnelRender[t][2];
				for(int i=0;i<180;i+=10) {
					bob.render(
							calcXint(radius, i+ ++deltaLoop)+Screen.screenCenterX+tunnelRender[t][0],
							calcYint(radius, i+ deltaLoop)+Screen.screenCenterY+tunnelRender[t][1]
							);
				}
			}else {
				deltaLoop+=18;
			}
		}
	}
	
	private int calcXint(int r, int a) {
		return (cosTab[a%360]*r)>>SHIFT;
	}
	
	private int calcYint(int r, int a) {
		return (sinTab[a%360]*r)>>SHIFT;
	}

	@Override
	public void worker3(){
		Screen.screenClear(LunarEngine.screendataToReset,PartAbyssLogo.BACKGROUNDCOLOR);
	}

	@Override
	public void rotateBuffers() {
		rotateBuffers4();
	}

}
