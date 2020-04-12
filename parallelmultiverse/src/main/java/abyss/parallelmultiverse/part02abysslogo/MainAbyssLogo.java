package abyss.parallelmultiverse.part02abysslogo;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part03mosaicbackground.PartMosaicBackground;

public class MainAbyssLogo{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartAbyssLogo partAbyssLogo=new PartAbyssLogo();
			partAbyssLogo.precalc();

			final PartMosaicBackground partMosaicBackground=new PartMosaicBackground();
			partMosaicBackground.precalc();

			addCommands(partAbyssLogo,partMosaicBackground);

			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartAbyssLogo partAbyssLogo,final PartMosaicBackground partMosaicBackground) {
		int index=Commander.addCommanderAction(180  +10,new ICommanderAction(){
			public void doAction(Object object){
				LunarEngine.setActivePart(null);
				partAbyssLogo.initialize();
				LunarEngine.setPartVbi2(partAbyssLogo);
				LunarEngine.setPartWorker2(partAbyssLogo);
				LunarEngine.setPartWorker3(partAbyssLogo);

				if(partMosaicBackground!=null) {
					partMosaicBackground.initialize();
					LunarEngine.setPartWorker1(partMosaicBackground);
				}
			}
		});

		Commander.addCommanderAction(5*60,new ICommanderAction(){
			public void doAction(Object object){
				partAbyssLogo.enableSpliner=true;
			}
		});

		Commander.addCommanderAction(6*60+40,new ICommanderAction(){
			public void doAction(Object object){
				partAbyssLogo.logoFadeSleep=30;
			}
		});

		Commander.addCommanderAction(5*60,new ICommanderAction(){
			public void doAction(Object object){
				partAbyssLogo.logoFadeSleep=40;
				partAbyssLogo.logoFadeDirection=-1;
			}
		});

		Commander.addCommanderAction(2*60-50,new ICommanderAction(){
			public void doAction(Object object){
				partAbyssLogo.logoFadeDirection=0;
			}
		});

		Commander.addCommanderAction(3*60,new ICommanderAction(){
			public void doAction(Object object){
				partAbyssLogo.enableBackgroundFader=true;
			}
		});

		Commander.addCommanderAction(4*60,new ICommanderAction(){
			public void doAction(Object object){
				if(partMosaicBackground!=null) {
					LunarEngine.setPartVbi1(partMosaicBackground);
					partMosaicBackground.mosaicFade=1;
				}
			}
		});

		Commander.addCommanderAction(4*60-50,new ICommanderAction(){
			public void doAction(Object object){
				partAbyssLogo.quadLogo.turnAlpha=0.5;
				partAbyssLogo.quadLogo.turnBeta=0.5;
			}
		});

		Commander.addCommanderAction(2*60+10,new ICommanderAction(){
			public void doAction(Object object){
				partAbyssLogo.logoFadeout=true;
			}
		});

		Commander.addCommanderAction(3*60+30,new ICommanderAction(){
			public void doAction(Object object){
				partAbyssLogo.logoFadeDirection=-1;
			}
		});

		return index;
	}

}
