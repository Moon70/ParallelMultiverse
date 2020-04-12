package abyss.parallelmultiverse.part20lasercutter;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.BobFactory;
import abyss.lunarengine.gfx.LordFader;
import abyss.lunarengine.gfx.Point3D;
import abyss.lunarengine.gfx.Tools2D;
import abyss.parallelmultiverse.common.BobTheBuilder;
import abyss.parallelmultiverse.common.Bobs3D;
import abyss.parallelmultiverse.common.BobsSpread;
import abyss.parallelmultiverse.common.PointSpread;
import abyss.parallelmultiverse.part19creditsscroller.PartCreditsScroller;

public class PartLaserCutter extends APart{
	public static final int BACKGROUNDCOLOR = 0x030404;
	private Bob[] bobs;
	private Bobs3D bobs3dLogo;
	private Bobs3D bobs3dLaser;
	private Bobs3D laserPoint1;
	private Tools2D tools2d;
	private BobsSpread bobSpread;
	private int laserX;
	private int laserY;
	
	private static final int BOBSIZE=8;
	private BobLogo bobLogo;
	private boolean laserPower;
	private LordFader faderBackground;
	
 	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			APart partLaserCutter=new PartLaserCutter();
			partLaserCutter.precalc();
			partLaserCutter.initialize();
			LunarEngine.setActivePart(partLaserCutter);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	@Override
	public void precalc(){
		faderBackground=new LordFader(PartCreditsScroller.BACKGROUNDCOLOR,BACKGROUNDCOLOR,120);
		tools2d=new Tools2D();
		bobs=new Bob[7];
		bobs[0]=BobFactory.createBob(PartLaserCutter.class, "/data/ball08/");
		for(int i=1;i<bobs.length;i++) {
			bobs[i]=bobs[0].clone();
			bobs[i].enabled=true;
			bobs[i].animdelay=0;
			switch(i) {
			case 1:
				BobTheBuilder.reColorFade(bobs[i],0x9BACC3);
				break;
			case 2:
				BobTheBuilder.reColorFade(bobs[i],0x748BAC);
				break;
			case 3:
				BobTheBuilder.reColorFade(bobs[i],0x42628E);
				break;
			case 4:
				BobTheBuilder.reColorFade(bobs[i],0x203B5F);
				break;
			case 5:
				BobTheBuilder.reColorFade(bobs[i],0xaaaaaa);
				break;
			case 6:
				BobTheBuilder.reColorFade(bobs[i],0x666666);
				break;
			}
		}
		
		int gammaS=0;
		int betaS=0;
		int focus1=900;
		int focus2=900;
		double turnAlpha=0;
		double turnBeta=1.5;
		double turnGamma=0;

		bobs3dLogo=BobTheBuilder.createCuboid(BOBSIZE,110-6,4,55-12);
		bobs3dLogo.beta=betaS;
		bobs3dLogo.gamma=gammaS;
		bobs3dLogo.turnAlpha=turnAlpha;
		bobs3dLogo.turnBeta=turnBeta;
		bobs3dLogo.turnGamma=turnGamma;
		bobs3dLogo.focus1=focus1;
		bobs3dLogo.focus2=focus2;
		
		bobs3dLaser=BobTheBuilder.createLaser(BOBSIZE);
		bobs3dLaser.beta=betaS;
		bobs3dLaser.gamma=gammaS;
		bobs3dLaser.turnAlpha=turnAlpha;
		bobs3dLaser.turnBeta=turnBeta;
		bobs3dLaser.turnGamma=turnGamma;
		bobs3dLaser.focus1=focus1;
		bobs3dLaser.focus2=focus2;
		
		laserPoint1=new Bobs3D();
		laserPoint1.addBob(new Point3D(0, 	5+BobTheBuilder.PERSP_DELTA_Y, 0,1));
		laserPoint1.addBob(new Point3D(0, -50+BobTheBuilder.PERSP_DELTA_Y, 0,1));
		laserPoint1.createArrays();
		laserPoint1.beta=betaS;
		laserPoint1.gamma=gammaS;
		laserPoint1.turnAlpha=turnAlpha;
		laserPoint1.turnBeta=turnBeta;
		laserPoint1.turnGamma=turnGamma;
		laserPoint1.focus1=focus1;
		laserPoint1.focus2=focus2;
		laserPoint1.rotateCALC();		
		
		bobSpread=new BobsSpread();
		bobLogo=new BobLogo();
	}
	
	@Override
	public void initialize() {
		tools2d.lightningLineParts=8;
		tools2d.rnd=16;
		PointSpread.bobsize=BOBSIZE;
	}
	
	@Override
	public void vbi(){
		bobs3dLaser.rotateVBI();
		bobs3dLogo.rotateVBI();
		laserPoint1.rotateVBI();
		
		LaserPos laserPos=bobLogo.getNextLaserPos();
		if(laserPos!=null) {
			laserPower=laserPos.power;
			laserX=laserPos.x<<3;
			laserY=laserPos.y<<3;
		}else {
			laserPower=false;
		}
		if(toFront){
			if(bobs3dLogo.alpha>1) {
				bobs3dLogo.alpha-=1;
				bobs3dLaser.alpha-=1;
				laserPoint1.alpha-=1;
			}else if(bobs3dLogo.alpha<1){
				bobs3dLogo.alpha+=1;
				bobs3dLaser.alpha+=1;
				laserPoint1.alpha+=1;
			}
			
			if(bobs3dLogo.beta>2) {
				bobs3dLogo.beta-=1;
				bobs3dLaser.beta-=1;
				laserPoint1.beta-=1;
			}else if(bobs3dLogo.beta<1){
				bobs3dLogo.beta+=1;
				bobs3dLaser.beta+=1;
				laserPoint1.beta+=1;
			}
			
			if(bobs3dLogo.gamma>-75) {
				bobs3dLogo.gamma-=1;
				bobs3dLaser.gamma-=1;
				laserPoint1.gamma-=1;
			}else if(bobs3dLogo.gamma<-76){
				bobs3dLogo.gamma+=1;
				bobs3dLaser.gamma+=1;
				laserPoint1.gamma+=1;
			}
		}
			
		laserPoint1.deltaX=bobs3dLaser.deltaX=laserX;
		laserPoint1.deltaZ=bobs3dLaser.deltaZ=laserY;
		
		bobSpread.vbi();
	
		if(laserPos!=null && laserPos.power) {
			int laserZ=(laserPoint1.points[1].sz>>Bob.SHIFT)+laserPoint1.deltaZ;
			for(int i=0;i<bobs3dLogo.arrayIndex;i++) {
				if(bobs3dLogo.points[i].enabled) {
					if(
							Math.abs((bobs3dLogo.points[i].sx>>Bob.SHIFT)-laserX)<10 && 
							bobs3dLogo.points[i].sy<((BobTheBuilder.PERSP_DELTA_Y+24+8+50)<<Bob.SHIFT) && 
							Math.abs((bobs3dLogo.points[i].sz>>Bob.SHIFT)-laserZ)<10) {
						bobs3dLogo.points[i].enabled=false;
						bobSpread.addBob(bobs3dLogo.points[i]);
					}else if(toFront) {
						bobs3dLogo.points[i].sy-=5<<Bob.SHIFT;
					}
				}
			}
		}
		faderBackground.fade(1);
	}

	private boolean toFront;
	
	public void moveToFront() {
		toFront=true;
		bobs3dLogo.turnAlpha=0;
		bobs3dLogo.turnBeta=0;
		bobs3dLogo.turnGamma=0;
		bobs3dLaser.turnAlpha=0;
		bobs3dLaser.turnBeta=0;
		bobs3dLaser.turnGamma=0;
		laserPoint1.turnAlpha=0;
		laserPoint1.turnBeta=0;
		laserPoint1.turnGamma=0;
	}
	
	public void explosionLogo() {
		for(int i=0;i<bobs3dLogo.arrayIndex;i++) {
			if(bobs3dLogo.points[i].enabled) {
				bobs3dLogo.points[i].enabled=false;
				bobSpread.addBob(bobs3dLogo.points[i]);
			}
		}
	}

	public void explosionLaser() {
		for(int i=0;i<bobs3dLaser.arrayIndex;i++) {
			if(bobs3dLaser.points[i].enabled) {
				bobs3dLaser.points[i].enabled=false;
				bobSpread.addBob(bobs3dLaser.points[i]);
			}
		}
	}

	@Override
	public void worker1(){
		tools2d.screendataWorking=LunarEngine.screendataToWork2;
		Bob.screendataToWork=LunarEngine.screendataToWork2;
		bobs3dLaser.rotateCALC();
		bobs3dLogo.rotateCALC();
		laserPoint1.rotateCALCnosort();
		
		int z;
		Bob bob;
		for(int i=0;i<bobs3dLogo.arrayIndex;i++) {
			if(bobs3dLogo.points[i].enabled) {
				bob=bobs[bobs3dLogo.points[i].data];

				z=(475-bobs3dLogo.points[i].dz)>>2;
				if(z>255) {
					bob.frame=255;
				}else if(z<0) {
					bob.frame=0;
				}else {
					bob.frame=z;
				}
				bob.render(
						bobs3dLogo.points[i].dx+Screen.screenCenterX,
						bobs3dLogo.points[i].dy+Screen.screenCenterY
						);
			}
		}

		for(int i=0;i<bobs3dLaser.arrayIndex;i++) {
			bob=bobs[bobs3dLaser.points[i].data];

			z=(475-bobs3dLaser.points[i].dz)>>2;
			if(z>255) {
				bob.frame=255;
			}else if(z<0) {
				bob.frame=0;
			}else {
				bob.frame=z;
			}
			bob.render(
					bobs3dLaser.points[i].dx+Screen.screenCenterX,
					bobs3dLaser.points[i].dy+Screen.screenCenterY
					);
		}

		bobSpread.render(bobs);

		if(laserPower) {
			int laserXX1=laserPoint1.points[1].dx+Screen.screenCenterX+4;
			int laserYY1=laserPoint1.points[1].dy+Screen.screenCenterY+4;
			int laserXX2=laserPoint1.points[0].dx+Screen.screenCenterX+4;
			int laserYY2=laserPoint1.points[0].dy+Screen.screenCenterY+4;
			tools2d.pixel=0xff0000;
			tools2d.lightningLine(laserXX1, laserYY1, laserXX2, laserYY2);
			tools2d.lightningLine(laserXX1, laserYY1, laserXX2, laserYY2);
			tools2d.lightningLine(laserXX1, laserYY1, laserXX2, laserYY2);
		}
	}
	
	@Override
	public void worker3(){
		Screen.screenClear(LunarEngine.screendataToReset,faderBackground.getCurrentColor());
	}
	
	@Override
	public void rotateBuffers() {
		rotateBuffers5();
	}

}
