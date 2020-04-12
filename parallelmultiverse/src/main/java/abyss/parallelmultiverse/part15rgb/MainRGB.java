package abyss.parallelmultiverse.part15rgb;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.parallelmultiverse.part16dottyobject.PartDottyObject;

public class MainRGB{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartRGB partRGB=new PartRGB();
			partRGB.precalc();
			partRGB.initialize();

			final PartDottyObject partDottyObject=new PartDottyObject();
			partDottyObject.precalc();

			addCommands(partRGB,partDottyObject);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartRGB partRGB,final PartDottyObject partDottyObject) {
		int index=Commander.addCommanderAction(4*60,new ICommanderAction(){
			public void doAction(Object object){
				LunarEngine.setActivePart(partRGB);
				partRGB.renderMethod=PartRGB.RENDERMETHOD_BOBS;
				if(partDottyObject!=null) {
					partDottyObject.initialize();
				}
			}
		});

		Commander.addCommanderAction(4*60+420+120-5+120+60+60,new ICommanderAction(){
			public void doAction(Object object){
				partRGB.renderMethod=PartRGB.RENDERMETHOD_SINGLEBLOCKS;
				partRGB.colors=partRGB.colorsDark;
				if(partDottyObject!=null) {
					LunarEngine.setPartVbi2(partDottyObject);
					LunarEngine.setPartWorker3(partDottyObject);
					partDottyObject.xDestination=Screen.screenCenterX;
				}
			}
		});

		Commander.addCommanderAction(5,new ICommanderAction(){
			public void doAction(Object object){
				if(partDottyObject!=null) {
					partDottyObject.renderBuffer=2;
				}
			}
		});

		Commander.addCommanderAction(2*60,new ICommanderAction(){
			public void doAction(Object object){
				partRGB.renderMethod=PartRGB.RENDERMETHOD_BLOCKS;
				LunarEngine.setPartVbi2(null);
				LunarEngine.setPartWorker3(null);
			}
		});

		return index;
	}
	
}
