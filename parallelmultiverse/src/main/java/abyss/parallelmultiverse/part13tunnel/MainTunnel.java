package abyss.parallelmultiverse.part13tunnel;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part12eyewave.PartEyeWave;

public class MainTunnel{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartTunnel partTunnel=new PartTunnel();
			partTunnel.precalc();
			
			final PartEyeWave partEyeWave=new PartEyeWave();
			partEyeWave.precalc();

			addCommands(partTunnel,partEyeWave);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartTunnel partTunnel,final PartEyeWave partEyeWave) {
		int tunnel=Commander.addCommanderAction(6*60,new ICommanderAction(){
			public void doAction(Object object){
				partTunnel.initialize();
				LunarEngine.setActivePart(null);
				LunarEngine.setPartVbi2(partTunnel);
				LunarEngine.setPartWorker2(partTunnel);
				LunarEngine.setPartWorker3(partTunnel);
				
				if(partEyeWave!=null) {
					partEyeWave.initialize();
				}
			}
		});
		
		Commander.addCommanderAction(4*60,new ICommanderAction(){
			public void doAction(Object object){
				partTunnel.lightningLinecountDest=10;
				partTunnel.lightningLinecountDelta=0.02;
				partTunnel.lightningRndDest=40;
				partTunnel.lightningRndDelta=0.05;
			}
		});
		
		Commander.addCommanderAction(2*60,new ICommanderAction(){
			public void doAction(Object object){
				partTunnel.enableAngleDelta=true;
			}
		});
		
		Commander.addCommanderAction(2*60,new ICommanderAction(){
			public void doAction(Object object){
				if(partEyeWave!=null) {
					LunarEngine.setPartVbi1(partEyeWave);
					LunarEngine.setPartWorker1(partEyeWave);
				}
			}
		});
		
		Commander.addCommanderAction(9*60,new ICommanderAction(){
			public void doAction(Object object){
				partTunnel.enableRadiusDelta=true;
				partTunnel.lightningRndDest=20;
				partTunnel.lightningRndDelta=0.2;
			}
		});
		
		Commander.addCommanderAction(2+3,new ICommanderAction(){
			public void doAction(Object object){
				if(partEyeWave!=null) {
					partEyeWave.renderScreen=1;
				}
			}
		});
		
		Commander.addCommanderAction(1,new ICommanderAction(){
			public void doAction(Object object){
				LunarEngine.setPartVbi2(null);
				LunarEngine.setPartWorker2(null);
				LunarEngine.setPartWorker3(null);
			}
		});
		
		return tunnel;
	}
	
}
