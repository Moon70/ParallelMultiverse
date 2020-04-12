package abyss.parallelmultiverse.part06lightningobject;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.parallelmultiverse.part03mosaicbackground.PartMosaicBackground;
import abyss.parallelmultiverse.part05bobbackground.PartBobBackground;

public class MainLightningObject{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartLightningObject partLightningObject=new PartLightningObject();
			partLightningObject.precalc();

			addCommands(partLightningObject,null,null);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartLightningObject partLightningObject,final PartMosaicBackground partMosaicBackground,final PartBobBackground partBobBackground) {
		int index=Commander.addCommanderAction(60*3,new ICommanderAction(){
			public void doAction(Object object){
				partLightningObject.initialize();
				partLightningObject.xDestination=Screen.screenCenterX;
				LunarEngine.setPartVbi2(partLightningObject);
				LunarEngine.setPartWorker2(partLightningObject);
				LunarEngine.setPartWorker3(partLightningObject);

				if(partMosaicBackground!=null) {
					partMosaicBackground.startFastBetaGammaTurn();
				}
				
				if(partBobBackground!=null) {
					partBobBackground.initialize();
				}
			}
		});

		Commander.addCommanderAction(60*5,new ICommanderAction(){
			public void doAction(Object object){
				partLightningObject.lightningLinePartsDelta=0.02;
				partLightningObject.rndDelta=0.01;
			}
		});

		Commander.addCommanderAction(60*4,new ICommanderAction(){
			public void doAction(Object object){
				partLightningObject.lightningLinePartsDelta=0.02;
				partLightningObject.rndDelta=0.03;
			}
		});

		Commander.addCommanderAction(60*4,new ICommanderAction(){
			public void doAction(Object object){
				partLightningObject.rndDelta=0.05;
			}
		});

		Commander.addCommanderAction(60*4,new ICommanderAction(){
			public void doAction(Object object){
				partLightningObject.lightningLinePartsDelta=0;
				partLightningObject.rndDelta=0;
			}
		});

		Commander.addCommanderAction(60*2,new ICommanderAction(){
			public void doAction(Object object){
				partLightningObject.rndDelta=1;
			}
		});

		Commander.addCommanderAction(60*1+30,new ICommanderAction(){
			public void doAction(Object object){
				partLightningObject.rndDelta=-1.5;
				partLightningObject.fadeOut=true;
			}
		});

		Commander.addCommanderAction(1,new ICommanderAction(){
			public void doAction(Object object){
				partLightningObject.rndDelta=0;
			}
		});

		Commander.addCommanderAction(1+120+30,new ICommanderAction(){
			public void doAction(Object object){
				if(partBobBackground!=null) {
					LunarEngine.setPartVbi2(partBobBackground);
					LunarEngine.setPartWorker2(partBobBackground);
					LunarEngine.setPartWorker3(partBobBackground);
				}
				if(partMosaicBackground!=null) {
					partMosaicBackground.mosaicFade=2;
				}
			}
		});

		Commander.addCommanderAction(1+120,new ICommanderAction(){
			public void doAction(Object object){
				LunarEngine.setPartVbi1(null);
				LunarEngine.setPartWorker1(null);
				if(partBobBackground!=null) {
					partBobBackground.enableBackgroundFader=true;
				}
			}
		});

		return index;
	}

}
