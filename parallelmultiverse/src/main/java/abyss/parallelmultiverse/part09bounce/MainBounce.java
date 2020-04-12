package abyss.parallelmultiverse.part09bounce;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part08ribbons.PartRibbons;

public class MainBounce{
	
	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartBounce partBounce=new PartBounce();
			partBounce.precalc();
			
			final PartRibbons partRibbons=new PartRibbons();
			partRibbons.precalc();

			addCommands(partBounce,partRibbons);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartBounce partBounce, final PartRibbons partRibbons) {
		int index=Commander.addCommanderAction(500-33,new ICommanderAction(){
			public void doAction(Object object){
				partBounce.initialize();
				LunarEngine.setPartVbi1(partBounce);
				LunarEngine.setPartWorker1(partBounce);
				LunarEngine.setPartWorker3(partBounce);
				
				if(partRibbons!=null) {
					partRibbons.initialize();
					LunarEngine.setPartVbi2(partRibbons);
					LunarEngine.setPartWorker2(partRibbons);
				}
			}
		});
		
		Commander.addCommanderAction(80,new ICommanderAction(){
			public void doAction(Object object){
				partBounce.ballDown2=true;
			}
		});
		
		Commander.addCommanderAction(50,new ICommanderAction(){
			public void doAction(Object object){
				partBounce.lightnig=true;
			}
		});
		
		Commander.addCommanderAction(150,new ICommanderAction(){
			public void doAction(Object object){
				partBounce.ballUp=true;
				partBounce.eyeUp=true;
			}
		});
		
		Commander.addCommanderAction(150,new ICommanderAction(){
			public void doAction(Object object){
				if(partRibbons!=null) {
					partRibbons.switchToDottyMode();
				}
			}
		});
		
		Commander.addCommanderAction(50,new ICommanderAction(){
			public void doAction(Object object){
				partBounce.ballDown2=true;
			}
		});
		
		Commander.addCommanderAction(300,new ICommanderAction(){
			public void doAction(Object object){
				partBounce.lightnig=true;
				partBounce.ballUp=true;
				partBounce.eyeUp=true;
			}
		});
		
		Commander.addCommanderAction(120,new ICommanderAction(){
			public void doAction(Object object){
				partBounce.sinusIndex=190;
				partBounce.bounceOut1=true;
			}
		});
		
		Commander.addCommanderAction(600-300-30,new ICommanderAction(){
			public void doAction(Object object){
				partBounce.eyeZoomOut=true;
			}
		});

		return index;
	}
			
}
