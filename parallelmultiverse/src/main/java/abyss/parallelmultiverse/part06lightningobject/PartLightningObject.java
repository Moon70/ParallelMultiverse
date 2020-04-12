package abyss.parallelmultiverse.part06lightningobject;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.LordFader;
import abyss.lunarengine.gfx.PolarBear;
import abyss.lunarengine.gfx.Tools2D;
import abyss.parallelmultiverse.part02abysslogo.PartAbyssLogo;

public class PartLightningObject extends APart{
	public double lightningLinePartsDelta;
	public double rndDelta;
	public boolean fadeOut;
	private double xOffset;
	public double xDestination=xOffset;

	private Tools2D tools2D;
	Lineobject3D lineobject3D=new Lineobject3D();
	
	private double lightningLineParts;
	
	private double rnd;
	private int angleStep;
	private int pointsPerCircle;
	private int circles;
	private int[] colors=new int[] {
			0xB7700D,
			0xCf7f0f,
			0xB7700D,
			
			0x84A258,
			0x8fAf5f,
			0x84A258,

			0x656574,
			0x6f6f7f,
			0x656574,

			0x6C3C0B,
			0x8f4f0f,
			0x6C3C0B,

			0xC8BA80,
			0xDfCf8f,
			0xC8BA80,
			};
	private int[][] fadedeColors;
	
	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			APart partLightningObjects=new PartLightningObject();
			partLightningObjects.precalc();
			partLightningObjects.initialize();
			LunarEngine.setActivePart(partLightningObjects);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}
	
	public PartLightningObject() {
		fadedeColors=new int[colors.length][256];
	}
	
	@Override
	public void precalc() {
		angleStep=5;
		pointsPerCircle=360/angleStep;
		circles=15;
		tools2D=new Tools2D();
		addCircle(50,	-240);
		addCircle(80,	-190);
		addCircle(150,	-170);
		addCircle(180,	-150);
		addCircle(180,	-125);
		addCircle(150,	-100);
		addCircle(300,	-10);
		addCircle(300,	0);
		addCircle(150,	-50);
		addCircle(150,	20);
		addCircle(100,	60);
		addCircle(100,	90);
		addCircle(70,	90);
		addCircle(70,	140);
		addCircle(120,	200);
		
		LordFader fader;
		for(int i=0;i<colors.length;i++) {
			int color=colors[i];
			fader=new LordFader(0x000000,color,128+30);
			for(int j=0;j<128;j++) {
				fadedeColors[i][j]=fader.colors[j+30];
			}
			fader=new LordFader(color,0xffffff,240);
			for(int j=0;j<128;j++) {
				fadedeColors[i][j+128]=fader.colors[j];
			}
		}
				
		int colorIndex=0;
		int i;
		int index=0;
		for(int j=0;j<circles;j++) {
			for(i=index;i<index+pointsPerCircle-1;i++) {
				lineobject3D.addLine(new Line(i,i+1,fadedeColors[colorIndex]));
			}
			lineobject3D.addLine(new Line(i,index,fadedeColors[colorIndex]));
			index+=pointsPerCircle;
			colorIndex++;
			colorIndex=colorIndex%colors.length;
		}

		colorIndex=0;
		index=0;
		for(int j=0;j<circles-1;j++) {
			for(i=index;i<index+pointsPerCircle;i++) {
				lineobject3D.addLine(new Line(i,i+pointsPerCircle,fadedeColors[colorIndex]));
			}
			index+=pointsPerCircle;
			colorIndex++;
			colorIndex=colorIndex%colors.length;
		}
		
		lineobject3D.createArrays();
		lineobject3D.turnAlpha=0.4;
		lineobject3D.turnBeta=0.5;
		lineobject3D.turnGamma=0.3;
		lineobject3D.focus1=800;
		lineobject3D.focus2=1000;
	}

	private void addCircle(double radius, int z) {
		z=z*3;
		radius=radius*1.5;
		for(double a=0;a<360;a+=angleStep) {
			int x=(int)(radius*Math.cos(a*PolarBear.DEG2RAD));
			int y=(int)(radius*Math.sin(a*PolarBear.DEG2RAD));
			lineobject3D.addPoint(new LinePoint3D(x,	y,	z));
		}
	}
	
	@Override
	public void initialize() {
		lightningLineParts=1;
		xOffset=-400;
	}
	
	@Override
	public void vbi(){
		lineobject3D.rotateVBI();
		if(xOffset<xDestination) {
			xOffset+=2;
		}
		lightningLineParts+=lightningLinePartsDelta;
		tools2D.lightningLineParts=(int)lightningLineParts;
	
		rnd+=rndDelta;
		if(rnd<0) {
			rnd=0;
		}
		tools2D.rnd=(int)rnd;
		
		if(fadeOut && lineobject3D.focus1>10) {
			lineobject3D.focus1-=8;
		}
	}
	
	@Override
	public void worker2(){
		lineobject3D.rotateCALC();
		tools2D.screendataWorking=LunarEngine.screendataToWork2;
		lineobject3D.render(tools2D,(int)xOffset);
	}
	
	@Override
	public void worker3(){
		Screen.screenClear(LunarEngine.screendataToReset,PartAbyssLogo.BACKGROUNDCOLOR);
	}
	
	@Override
	public void rotateBuffers() {
		rotateBuffers5();
	}

}
