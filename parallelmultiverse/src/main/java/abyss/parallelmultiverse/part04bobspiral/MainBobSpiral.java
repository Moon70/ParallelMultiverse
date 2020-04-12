package abyss.parallelmultiverse.part04bobspiral;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part03mosaicbackground.PartMosaicBackground;

public class MainBobSpiral{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartBobSpiral partBobSpiral=new PartBobSpiral();
			partBobSpiral.precalc();

			addCommands(partBobSpiral, null);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartBobSpiral partBobSpiral,final PartMosaicBackground partMosaicBack) {
		int index=Commander.addCommanderAction(60*11,new ICommanderAction(){
			public void doAction(Object object){
				partBobSpiral.initialize();
				LunarEngine.setPartVbi2(partBobSpiral);
				LunarEngine.setPartWorker2(partBobSpiral);
				LunarEngine.setPartWorker3(partBobSpiral);
				
				if(partMosaicBack!=null) {
					partMosaicBack.startSlowBetaGammaTurn();
				}
			}
		});

		Commander.addCommanderAction(60*4+30,new ICommanderAction(){
			public void doAction(Object object){
				partBobSpiral.fadeIn=false;
			}
		});

		return index;
	}

}
