package abyss.parallelmultiverse.part03mosaicbackground;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.Object2D;
import abyss.lunarengine.gfx.Tools2D;
import abyss.lunarengine.tools.Random;
import abyss.parallelmultiverse.part02abysslogo.PartAbyssLogo;

public class PartMosaicBackground extends APart{
	public int mosaicFade;
	
	private Tools2D tools2D=new Tools2D();
	private Mosaic[] object2D;
	private int[] colors;
	private int focus2=4000;
	
	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			APart partMosaicBack=new PartMosaicBackground();
			partMosaicBack.precalc();
			partMosaicBack.initialize();
			LunarEngine.setActivePart(partMosaicBack);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}
	
	@Override
	public void precalc() {
		colors=new int[] {
				0x000B16,
				0x221107,
				0x192515,
				0x0F163D,
				0x2C1B31,
				0x380C13,
				0x191919,
				0x2B2B00,
				0,
				0
		};
		Object2D.tools2D=tools2D;
		object2D=new Mosaic[100];

		int[] objectData=new int[]{-21,-13,33,-13,29,17,-44,18,1190,646,-30,-14,27,-15,37,33,-41,25,1666,711,-44,-21,28,-25,37,12,-32,22,675,130,-37,-34,25,-23,17,30,-18,26,861,824,-24,-14,25,-40,26,23,-37,14,1806,810,-33,-29,36,-31,44,16,-19,32,1389,220,-14,-19,29,-22,42,30,-28,22,285,158,-27,-26,44,-40,22,34,-18,10,651,855,-34,-27,34,-33,26,31,-32,18,764,922,-28,-23,16,-39,16,17,-41,15,1224,493,-36,-12,35,-29,29,39,-14,22,1580,457,-37,-25,18,-24,16,26,-30,31,453,245,-40,-11,13,-40,43,33,-35,25,275,494,-17,-34,35,-27,21,20,-19,27,348,286,-40,-21,14,-31,18,23,-35,17,670,454,-20,-19,21,-13,43,30,-20,10,1793,712,-37,-19,42,-17,16,39,-37,28,1769,268,-18,-23,32,-38,37,27,-37,36,886,446,-12,-18,38,-12,32,15,-38,40,1139,244,-32,-12,27,-22,28,30,-40,20,1728,576,-21,-33,21,-27,42,12,-34,38,364,803,-33,-13,40,-31,32,16,-37,16,1636,282,-30,-26,11,-20,42,31,-14,34,774,579,-43,-15,12,-39,24,11,-27,13,475,796,-14,-16,40,-27,34,26,-21,31,1641,94,-21,-20,41,-29,36,29,-25,26,158,359,-24,-11,33,-16,34,17,-13,27,1533,200,-29,-18,40,-39,19,13,-25,36,1282,749,-20,-28,33,-22,17,29,-35,20,1355,393,-34,-12,19,-12,35,39,-11,28,536,597,-24,-38,30,-23,26,26,-43,38,1694,428,-13,-32,37,-37,43,26,-26,13,758,283,-20,-18,43,-19,42,23,-29,36,518,460,-14,-36,35,-40,34,40,-19,12,971,284,-41,-10,18,-24,41,10,-34,37,1074,712,-21,-12,30,-27,30,40,-27,19,924,700,-36,-36,41,-23,21,11,-33,40,1634,927,-33,-19,38,-37,18,39,-12,39,1120,104,-37,-37,33,-38,42,27,-44,18,1410,673,-14,-36,27,-15,17,22,-19,22,1077,950,-38,-24,37,-24,24,29,-40,15,323,695,-22,-12,33,-28,25,38,-12,23,852,189,-33,-39,41,-39,12,36,-14,20,1041,473,-12,-21,19,-23,33,17,-22,31,411,588,-20,-40,44,-31,18,29,-38,10,228,903,-42,-33,43,-21,36,30,-32,13,1261,141,-37,-22,44,-16,28,24,-28,11,1604,603,-29,-32,14,-38,19,13,-22,36,659,677,-16,-19,43,-19,34,31,-29,16,1202,902,-36,-21,42,-27,27,11,-19,15,444,115,-29,-23,36,-31,35,17,-24,32,998,151,-32,-14,27,-21,36,26,-38,11,1433,982,-25,-21,22,-32,16,21,-41,10,1229,341,-33,-39,41,-13,26,26,-38,40,1690,817,-41,-37,34,-31,38,26,-24,22,1300,640,-33,-38,28,-16,11,18,-25,25,556,163,-18,-30,22,-33,36,15,-37,15,970,916,-32,-29,36,-15,40,33,-43,38,1074,348,-22,-12,22,-40,27,25,-19,21,1518,797,-41,-24,18,-21,29,31,-22,39,364,379,-20,-32,40,-16,21,24,-27,31,601,352,-18,-24,32,-16,11,32,-41,39,508,903,-28,-11,27,-11,26,14,-32,18,344,985,-44,-22,13,-40,40,37,-29,33,171,733,-16,-18,24,-34,41,12,-41,28,1461,389,-13,-13,27,-12,33,38,-19,37,152,510,-13,-40,41,-22,42,36,-40,30,1329,486,-25,-39,24,-27,32,25,-14,21,1177,790,-36,-10,38,-15,16,31,-34,11,1344,852,-20,-25,25,-36,11,39,-27,10,1794,140,-11,-31,40,-16,30,33,-37,34,241,250,-44,-32,36,-16,36,22,-20,11,120,218,-37,-21,35,-30,43,13,-44,19,1468,552,-20,-15,18,-36,27,12,-30,20,1305,954,-35,-25,24,-20,32,12,-30,40,781,404,-40,-34,22,-20,29,30,-42,26,557,260,-14,-16,16,-37,16,27,-32,40,138,630,-37,-21,18,-16,27,22,-35,23,625,977,-26,-38,27,-10,19,18,-29,14,1019,590,-35,-13,21,-21,11,19,-28,11,1765,932,-38,-33,27,-24,32,10,-24,29,670,576,-24,-11,27,-27,41,29,-20,28,1265,238,-32,-28,40,-14,42,25,-32,24,117,840,-38,-34,40,-15,11,23,-25,22,429,694,-11,-27,38,-40,30,40,-30,25,1800,405,-16,-27,43,-16,17,15,-13,28,1466,889,-22,-29,41,-32,43,16,-16,36,1024,817,-25,-33,18,-32,18,29,-24,19,114,970,-32,-27,36,-12,17,24,-19,37,472,354,-25,-26,41,-27,16,19,-43,28,766,716,-16,-12,41,-23,34,19,-41,33,756,810,-33,-38,24,-32,21,24,-30,15,1404,118,-36,-16,22,-11,22,16,-12,11,883,587,-43,-28,30,-40,26,36,-35,28,867,982,-44,-14,42,-11,35,17,-19,36,149,95,-21,-15,13,-30,22,39,-40,15,393,477,-42,-10,43,-28,40,29,-33,34,1518,95,-30,-38,16,-10,42,20,-19,23,262,593,-18,-20,43,-32,14,20,-12,23,1497,293,-22,-17,18,-17,19,32,-14,39,867,94};
		int objectDataIndex=0;
		Object2D.tools2D=tools2D;
		for(int i=0;i<object2D.length;i++) {
			object2D[i]=new Mosaic();
			object2D[i].vo=new int[4][3];
			object2D[i].to=new int[4][3];
			object2D[i].lo=new int[4][2];
			object2D[i].vo[0][0]=objectData[objectDataIndex++] << Bob.SHIFT;
			object2D[i].vo[0][1]=objectData[objectDataIndex++] << Bob.SHIFT;
			object2D[i].vo[0][2]=0 << Bob.SHIFT;
			object2D[i].vo[1][0]=objectData[objectDataIndex++] << Bob.SHIFT;
			object2D[i].vo[1][1]=objectData[objectDataIndex++] << Bob.SHIFT;
			object2D[i].vo[1][2]=0 << Bob.SHIFT;
			object2D[i].vo[2][0]=objectData[objectDataIndex++] << Bob.SHIFT;
			object2D[i].vo[2][1]=objectData[objectDataIndex++] << Bob.SHIFT;
			object2D[i].vo[2][2]=0 << Bob.SHIFT;
			object2D[i].vo[3][0]=objectData[objectDataIndex++] << Bob.SHIFT;
			object2D[i].vo[3][1]=objectData[objectDataIndex++] << Bob.SHIFT;
			object2D[i].vo[3][2]=0 << Bob.SHIFT;
			object2D[i].lo[0][0]=0;
			object2D[i].lo[0][1]=1;
			object2D[i].lo[1][0]=1;
			object2D[i].lo[1][1]=2;
			object2D[i].lo[2][0]=2;
			object2D[i].lo[2][1]=3;
			object2D[i].lo[3][0]=3;
			object2D[i].lo[3][1]=0;
			
			while(Math.abs(object2D[i].turnAlpha)<0.2) {
				object2D[i].turnAlpha=Random.random()*1.0-0.5;
			}
			object2D[i].posX=objectData[objectDataIndex++];
			object2D[i].posY=objectData[objectDataIndex++];
		}
	}

	public void startSlowBetaGammaTurn() {
		for(int i=0;i<object2D.length;i+=2) {
			while(Math.abs(object2D[i].turnBeta)<0.2) {
				object2D[i].turnBeta=Random.random()*0.8-0.4;
			}
			while(Math.abs(object2D[i].turnGamma)<0.2) {
				object2D[i].turnGamma=Random.random()*0.8-0.4;
			}
		}
	}
	
	public void startFastBetaGammaTurn() {
		for(int i=1;i<object2D.length;i+=2) {
			while(Math.abs(object2D[i].turnBeta)<1) {
				object2D[i].turnBeta=Random.random()*4-2;
			}
			while(Math.abs(object2D[i].turnGamma)<1) {
				object2D[i].turnGamma=Random.random()*4-2;
			}
		}
	}
	
	@Override
	public void vbi(){
		for(int i=0;i<object2D.length;i++) {
			object2D[i].focus1=500;
			object2D[i].focus2=focus2;
			object2D[i].rotateVBI();
			object2D[i].rotateCALC();
		}
		if(mosaicFade==1) {
			if(focus2>1200) {
				focus2-=20;
			}else if(focus2>470) {
				focus2-=5;
			}
		}else if(mosaicFade==2){
			if(focus2<120) {
				focus2+=5;
			}else if(focus2<4000) {
				focus2+=20;
			}
		}
	}
	
	@Override
	public void worker1(){
		tools2D.screendataWorking=LunarEngine.screendataToWork;
		tools2D.background=colors[0];
		Object2D.tools2D=tools2D;
		for(int i=0;i<object2D.length;i++) {
			tools2D.pixel=colors[i % 8];
			object2D[i].drawB();
		}
		tools2D.fill();
	}

	@Override
	public void worker2(){
		Screen.screenClear(LunarEngine.screendataToReset,PartAbyssLogo.BACKGROUNDCOLOR);
	}
	
	@Override
	public void rotateBuffers() {
		rotateBuffers5();
	}

}
