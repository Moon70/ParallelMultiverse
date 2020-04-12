package abyss.parallelmultiverse.part20lasercutter;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part10confetti.PartConfetti;

public class MainLaserCutter{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartLaserCutter partLaserCutter=new PartLaserCutter();
			partLaserCutter.precalc();
			
			final PartConfetti partConfetti=new PartConfetti();
			partConfetti.precalc();
			
			addCommands(partLaserCutter,partConfetti);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartLaserCutter partLaserCutter,final PartConfetti partConfetti) {
		int index=Commander.addCommanderAction(60*10,new ICommanderAction(){
			public void doAction(Object object){
				partLaserCutter.initialize();
				LunarEngine.setActivePart(partLaserCutter);
				
				if(partConfetti!=null) {
					partConfetti.mode=PartConfetti.MODE_CANNON;
					partConfetti.initialize();
				}
			}
		});

		Commander.addCommanderAction(20,new ICommanderAction(){
			public void doAction(Object object){
				partLaserCutter.moveToFront();
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partLaserCutter.explosionLaser();
			}
		});

		Commander.addCommanderAction(60*6-15-20-30+140,new ICommanderAction(){
			public void doAction(Object object){
				if(partConfetti!=null) {
					LunarEngine.setPartVbi2(partConfetti);
					LunarEngine.setPartWorker2(partConfetti);
				}
			}
		});

		Commander.addCommanderAction(60*8-10,new ICommanderAction(){
			public void doAction(Object object){
				partLaserCutter.explosionLogo();
				if(partConfetti!=null) {
					partConfetti.disableConfettiCanons=true;
				}
			}
		});

		Commander.addCommanderAction(1,new ICommanderAction(){
			public void doAction(Object object){
				LunarEngine.shutdown();
			}
		});

		return index;
	}
	
}
