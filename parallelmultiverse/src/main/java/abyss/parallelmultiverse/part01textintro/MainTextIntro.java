package abyss.parallelmultiverse.part01textintro;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;

public class MainTextIntro{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartTextIntro partTextIntro=new PartTextIntro();
			partTextIntro.precalc();
			
			addCommands(partTextIntro);

			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartTextIntro partTextIntro) {
		int index=Commander.addCommanderAction(8*60,new ICommanderAction(){
			public void doAction(Object object){
				partTextIntro.initialize();
				LunarEngine.setActivePart(partTextIntro);
			}
		});

		Commander.addCommanderAction(7*60-40,new ICommanderAction(){
			public void doAction(Object object){
				partTextIntro.switchToPage2();
			}
		});

		Commander.addCommanderAction(1,new ICommanderAction(){
			public void doAction(Object object){
				LunarEngine.setActivePart(null);
				Screen.screenClear(LunarEngine.screendataToReset,0x0);
				Screen.screenClear(LunarEngine.screendataToWork,0x0);
			}
		});
		
		return index;
	}

}
