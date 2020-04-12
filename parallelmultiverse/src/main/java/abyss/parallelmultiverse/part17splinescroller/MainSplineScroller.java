package abyss.parallelmultiverse.part17splinescroller;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part15rgb.PartRGB;

public class MainSplineScroller{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartSplineScroller partSplineScroller=new PartSplineScroller();
			partSplineScroller.precalc();
		
			final PartRGB partRGB=new PartRGB();
			partRGB.precalc();
			partRGB.initialize();
			
			addCommands(partSplineScroller,partRGB);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartSplineScroller partSplineScroller,final PartRGB partRGB) {
		int index=Commander.addCommanderAction(180,new ICommanderAction(){
			public void doAction(Object object){
				partSplineScroller.initialize();
				LunarEngine.setPartVbi2(partSplineScroller);
				LunarEngine.setPartWorker2(partSplineScroller);
				LunarEngine.setPartWorker3(partSplineScroller);
				partSplineScroller.renderBuffer3=true;
				if(partRGB!=null) {
					LunarEngine.setPartVbi1(partRGB);
					LunarEngine.setPartWorker1(partRGB);
					partRGB.colors=partRGB.colorsDark;
					partRGB.renderMethod=PartRGB.RENDERMETHOD_HEXAGON;
					partRGB.hexagonMorphRadius=200;
				}
			}
		});
		
		Commander.addCommanderAction(240-180,new ICommanderAction(){
			public void doAction(Object object){
				if(partRGB!=null) {
					partRGB.hexagonMorphCenterRadius=100;
				}
			}
		});
		
		Commander.addCommanderAction(60,new ICommanderAction(){
			public void doAction(Object object){
				partSplineScroller.startColorFadein=true;
			}
		});
		
		Commander.addCommanderAction(120,new ICommanderAction(){
			public void doAction(Object object){
				if(partRGB!=null) {
					partRGB.hexagonMorphCenterRadius=50;
					partRGB.hexagonMorphAngle=140;
				}
			}
		});
		
		Commander.addCommanderAction(120,new ICommanderAction(){
			public void doAction(Object object){
				if(partRGB!=null) {
					partRGB.hexagonMorphRadius=350;
					partRGB.hexagonMorphCenterRadius=280;
					partRGB.hexagonMorphAngle=45;
				}
			}
		});
		
		Commander.addCommanderAction(90,new ICommanderAction(){
			public void doAction(Object object){
				partSplineScroller.startBounce=true;
			}
		});

		Commander.addCommanderAction(300-30-90+180,new ICommanderAction(){
			public void doAction(Object object){
				if(partRGB!=null) {
					partRGB.renderMethod=PartRGB.RENDERMETHOD_BLOCKS;
				}
			}
		});

		Commander.addCommanderAction(300,new ICommanderAction(){
			public void doAction(Object object){
				partSplineScroller.splinerIndex=1;
				partSplineScroller.stopBounceOnNextZero=true;
			}
		});
		
		Commander.addCommanderAction(300,new ICommanderAction(){
			public void doAction(Object object){
				if(partRGB!=null) {
					partRGB.renderMethod=PartRGB.RENDERMETHOD_BOBS;
					partRGB.bobOffset=3;
				}
			}
		});
		
		Commander.addCommanderAction(300,new ICommanderAction(){
			public void doAction(Object object){
				partSplineScroller.splinerIndex=2;
				if(partRGB!=null) {
					partRGB.bobOffset=2;
				}
			}
		});
		
		Commander.addCommanderAction(300,new ICommanderAction(){
			public void doAction(Object object){
				if(partRGB!=null) {
					partRGB.bobOffset=1;
				}
			}
		});
		
		Commander.addCommanderAction(600,new ICommanderAction(){
			public void doAction(Object object){
				partSplineScroller.splinerIndex=3;
				if(partRGB!=null) {
					partRGB.renderMethod=PartRGB.RENDERMETHOD_SINGLEBLOCKS;
				}
			}
		});
		
		Commander.addCommanderAction(5,new ICommanderAction(){
			public void doAction(Object object){
				partSplineScroller.startColorFadein=false;
				partSplineScroller.renderBuffer2=true;
			}
		});
		
		Commander.addCommanderAction(120+60,new ICommanderAction(){
			public void doAction(Object object){
				partSplineScroller.renderBuffer2=false;
				partSplineScroller.renderBuffer3=false;
				if(partRGB!=null) {
					partRGB.renderMethod=PartRGB.RENDERMETHOD_NOTHING;
				}
			}
		});
		
		return index;
	}

}
