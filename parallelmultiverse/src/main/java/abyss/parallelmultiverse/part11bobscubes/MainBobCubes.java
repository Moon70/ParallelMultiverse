package abyss.parallelmultiverse.part11bobscubes;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part08ribbons.PartRibbons;
import abyss.parallelmultiverse.part10confetti.PartConfetti;

public class MainBobCubes{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartBobCubes partBobCubes=new PartBobCubes();
			partBobCubes.precalc();
			
			final PartRibbons partRibbons=new PartRibbons();
			partRibbons.precalc();
			partRibbons.initialize();
			
			final PartConfetti partConfetti=new PartConfetti();
			partConfetti.precalc();

			addCommands(partBobCubes,partRibbons,partConfetti);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartBobCubes partBobCubes, final PartRibbons partRibbons, final PartConfetti partConfetti) {
		int index=Commander.addCommanderAction(120-30,new ICommanderAction(){
			public void doAction(Object object){
				partBobCubes.initialize();
				LunarEngine.setActivePart(partBobCubes);
				
				if(partRibbons!=null) {
					LunarEngine.setPartVbi2(partRibbons);
					LunarEngine.setPartWorker2(partRibbons);
				}

				if(partConfetti!=null) {
					partConfetti.initialize();
				}
			}
		});
		
		
		Commander.addCommanderAction(120+30,new ICommanderAction(){
			public void doAction(Object object){
				if(partRibbons!=null) {
					partRibbons.beginFadeOut();
				}
			}
		});
		
		Commander.addCommanderAction(60*28+30-120-120,new ICommanderAction(){
			public void doAction(Object object){
				if(partConfetti!=null) {
					partConfetti.mode=PartConfetti.MODE_RAIN;
					LunarEngine.setPartVbi2(partConfetti);
					LunarEngine.setPartWorker2(partConfetti);
				}
			}
		});
		
		Commander.addCommanderAction(60*2,new ICommanderAction(){
			public void doAction(Object object){
				partBobCubes.fadeOut=true;
				if(partConfetti!=null) {
					partConfetti.beginFadeOut();
				}
			}
		});
		
		return index;
	}

}
