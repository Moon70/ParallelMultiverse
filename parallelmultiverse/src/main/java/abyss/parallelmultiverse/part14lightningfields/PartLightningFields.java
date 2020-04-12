package abyss.parallelmultiverse.part14lightningfields;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;

public class PartLightningFields extends APart{
	public LightningQuad[] objQuad;

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			APart partLightningFields=new PartLightningFields();
			partLightningFields.precalc();
			partLightningFields.initialize();
			LunarEngine.setActivePart(partLightningFields);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}
	
	@Override
	public void precalc() {
		objQuad=new LightningQuad[8];
		//								posX	posY	dX	dY	size	alpha	beta	border		fill1		fill2
		objQuad[0]=createLightningQuad(	-1300,	-100,	7,	0,	280,	1,		0,		0x777777,	0x555555,	0x494949);//grey
		objQuad[1]=createLightningQuad(	0,		800,	0,	-6,	180,	-1,		-0.03,	0x197D98,	0x125B6F,	0x125B6F);//lightblue
		objQuad[2]=createLightningQuad(	1300,	-800,	-6,	4,	220,	0.4,	-0.1,	0x6D2B00,	0x522000,	0x522000);//brown
		objQuad[3]=createLightningQuad(	1300,	0,		-4,	0,	250,	-0.4,	0.03,	0x848484,	0x626262,	0x464646);//grey
		objQuad[4]=createLightningQuad(	-1300,	300,	6,	0,	250,	-0.5,	-0.1,	0xC58100,	0x8E5E00,	0x563900);//orange
		objQuad[5]=createLightningQuad(	1000,	800,	-5,	-4,	220,	-0.8,	0.04,	0x7F0604,	0x580403,	0x2E021F);//red
		objQuad[6]=createLightningQuad(	-1300,	-800,	6,	4,	180,	0.75,	0.05,	0x431D04,	0x431D04,	0x431D04);//blue
		objQuad[7]=createLightningQuad(	300,	-900,	-3,	5,	200,	0.6,	-0.2,	0x22A5C9,	0x197D98,	0x14647A);//cyan

	}

	private LightningQuad createLightningQuad(int posX,int posY,int deltaX,int deltaY, int objSize, double alpha,double beta,int border, int fill1, int fill2) {
		LightningQuad lightningQuad=new LightningQuad(posX,posY,deltaX,deltaY);
		lightningQuad.vo=new int[4][3];
		lightningQuad.to=new int[4][3];
		lightningQuad.vo[0][0]=-objSize << Bob.SHIFT;
		lightningQuad.vo[0][1]=-objSize << Bob.SHIFT;
		lightningQuad.vo[0][2]= 000 << Bob.SHIFT;
		lightningQuad.vo[1][0]= objSize << Bob.SHIFT;
		lightningQuad.vo[1][1]=-objSize << Bob.SHIFT;
		lightningQuad.vo[1][2]= 000 << Bob.SHIFT;
		lightningQuad.vo[2][0]= objSize << Bob.SHIFT;
		lightningQuad.vo[2][1]= objSize << Bob.SHIFT;
		lightningQuad.vo[3][2]= 000 << Bob.SHIFT;
		lightningQuad.vo[3][0]=-objSize << Bob.SHIFT;
		lightningQuad.vo[3][1]= objSize << Bob.SHIFT;
		lightningQuad.vo[3][2]= 000 << Bob.SHIFT;

		lightningQuad.turnAlpha=alpha;
		lightningQuad.turnBeta=beta;
		lightningQuad.colorBorder=border;
		lightningQuad.colorFill1=fill1;
		lightningQuad.colorFill2=fill2;
		
		return lightningQuad;
	}
	
	@Override
	public void vbi(){
		for(int i=0;i<objQuad.length;i++) {
			objQuad[i].vbi();
		}
	}
	
	@Override
	public void worker2(){
		for(int i=0;i<objQuad.length;i++) {
			if(objQuad[i].enabled) {
				if(objQuad[i].drawToRgbBuffer) {
					objQuad[i].tools2D.screendataWorking=LunarEngine.screendataToWork2;
				}else {
					objQuad[i].tools2D.screendataWorking=LunarEngine.screendataToWork3;
				}
				objQuad[i].rotateCALC();
				//objQuad[i].draw();
				objQuad[i].drawFilled();
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
	
}
