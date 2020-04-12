package abyss.parallelmultiverse.part07turnbars;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part05bobbackground.PartBobBackground;

public class MainTurnBars{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartTurnbars partTurnbars=new PartTurnbars();
			partTurnbars.precalc();
			final PartBobBackground partBobBackground=new PartBobBackground();
			partBobBackground.precalc();
			partBobBackground.initialize();
			
			addCommands(partTurnbars,partBobBackground);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartTurnbars partTurnbars,final PartBobBackground partBobBackground) {
		int delayIn=180-100;
		int index=Commander.addCommanderAction(delayIn,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.initialize();
				LunarEngine.setPartVbi1(partTurnbars);
				LunarEngine.setPartWorker1(partTurnbars);
				partTurnbars.turnbars[0].fadeIn=true;
				
				if(partBobBackground!=null) {
					LunarEngine.setPartVbi2(partBobBackground);
					LunarEngine.setPartWorker2(partBobBackground);
					LunarEngine.setPartWorker3(partBobBackground);
				}
			}
		});

		Commander.addCommanderAction(delayIn,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[1].fadeIn=true;
			}
		});

		Commander.addCommanderAction(delayIn,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[2].fadeIn=true;
			}
		});

		Commander.addCommanderAction(delayIn,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[3].fadeIn=true;
			}
		});

		Commander.addCommanderAction(delayIn,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[4].fadeIn=true;
			}
		});

		Commander.addCommanderAction(delayIn,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[5].fadeIn=true;
			}
		});

		Commander.addCommanderAction(delayIn,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[6].fadeIn=true;
			}
		});

		Commander.addCommanderAction(delayIn,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[7].fadeIn=true;
			}
		});

		Commander.addCommanderAction(delayIn+300,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[8].fadeIn=true;
			}
		});

		Commander.addCommanderAction(200,new ICommanderAction(){
			public void doAction(Object object){
				if(partBobBackground!=null) {
					partBobBackground.fadeOut=true;
				}
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[0].fadeIn=false;
				partTurnbars.turnbars[0].fadeOut=true;
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[1].fadeIn=false;
				partTurnbars.turnbars[1].fadeOut=true;
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[2].fadeIn=false;
				partTurnbars.turnbars[2].fadeOut=true;
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[3].fadeIn=false;
				partTurnbars.turnbars[3].fadeOut=true;
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[4].fadeIn=false;
				partTurnbars.turnbars[4].fadeOut=true;
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[5].fadeIn=false;
				partTurnbars.turnbars[5].fadeOut=true;
				partTurnbars.turnbars[6].fadeIn=false;
				partTurnbars.turnbars[6].fadeOut=true;
			}
		});

		Commander.addCommanderAction(300,new ICommanderAction(){
			public void doAction(Object object){
				partTurnbars.turnbars[7].fadeIn=false;
				partTurnbars.turnbars[7].fadeOut=true;
				partTurnbars.turnbars[8].fadeIn=false;
				partTurnbars.turnbars[8].fadeOut=true;
			}
		});

		return index;
	}
	
}
