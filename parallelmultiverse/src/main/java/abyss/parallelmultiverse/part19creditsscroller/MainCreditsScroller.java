package abyss.parallelmultiverse.part19creditsscroller;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.parallelmultiverse.part18creditsbobs.PartCreditsBobs;

public class MainCreditsScroller{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartCreditsScroller partCreditsScroller=new PartCreditsScroller();
			partCreditsScroller.precalc();

			final PartCreditsBobs partCreditsBobs=new PartCreditsBobs();
			partCreditsBobs.precalc();

			addCommands(partCreditsScroller,partCreditsBobs);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartCreditsScroller partCreditsScroller,final PartCreditsBobs partCreditsBobs) {
		int index=Commander.addCommanderAction(32*60+3*60+30,new ICommanderAction(){
			public void doAction(Object object){
				partCreditsScroller.initialize();
				LunarEngine.setPartVbi2(partCreditsScroller);
				LunarEngine.setPartWorker2(partCreditsScroller);
				LunarEngine.setPartWorker3(partCreditsScroller);

				if(partCreditsBobs!=null) {
					partCreditsBobs.initialize();
					LunarEngine.setPartVbi1(partCreditsBobs);
					LunarEngine.setPartWorker1(partCreditsBobs);
				}
			}
		});

		Commander.addCommanderAction(60+60,new ICommanderAction(){
			public void doAction(Object object){
				partCreditsScroller.fadeDirection=-1;
				partCreditsScroller.borderYDest=Screen.screenSizeY;
				if(partCreditsBobs!=null) {
					partCreditsBobs.fadeOutLaser=true;
					partCreditsBobs.fadeDirection=-1;
				}
			}
		});

		Commander.addCommanderAction(270-90,new ICommanderAction(){
			public void doAction(Object object){
				partCreditsBobs.fadeOutColors=true;
			}
		});

		return index;
	}

}
