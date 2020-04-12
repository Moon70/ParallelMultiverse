package abyss.parallelmultiverse.part14lightningfields;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.parallelmultiverse.part15rgb.PartRGB;

public class MainLightningFields{

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			final PartLightningFields partLightningFields=new PartLightningFields();
			partLightningFields.precalc();
			
			final PartRGB partRGB=new PartRGB();
			partRGB.precalc();
			partRGB.initialize();
			
			addCommands(partLightningFields,partRGB);
			
			Commander.start(0);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	public static int addCommands(final PartLightningFields partLightningFields,final PartRGB partRGB) {
		int index=Commander.addCommanderAction(2*60,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.initialize();
				LunarEngine.setPartVbi2(partLightningFields);
				LunarEngine.setPartWorker2(partLightningFields);
				LunarEngine.setPartWorker3(partLightningFields);
				partLightningFields.objQuad[0].enabled=true;
				if(partRGB!=null) {
					partRGB.initialize();
					LunarEngine.setPartVbi1(partRGB);
					LunarEngine.setPartWorker1(partRGB);
					partRGB.renderMethod=PartRGB.RENDERMETHOD_NOTHING;
				}
			}
		});
		
		Commander.addCommanderAction(60,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[0].drawToRgbBuffer=true;
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[1].enabled=true;
				partLightningFields.objQuad[2].enabled=true;
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[0].enabled=false;
			}
		});

		Commander.addCommanderAction(60,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[1].drawToRgbBuffer=true;
			}
		});

		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[2].drawToRgbBuffer=true;
				partLightningFields.objQuad[3].enabled=true;
				partLightningFields.objQuad[5].enabled=true;
			}
		});
		
		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[1].enabled=false;
				partLightningFields.objQuad[4].enabled=true;
				partLightningFields.objQuad[6].enabled=true;
			}
		});
		
		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[2].enabled=false;
			}
		});

		Commander.addCommanderAction(60,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[3].drawToRgbBuffer=true;
				partLightningFields.objQuad[5].drawToRgbBuffer=true;
			}
		});
		
		Commander.addCommanderAction(60+60,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[4].drawToRgbBuffer=true;
				partLightningFields.objQuad[6].drawToRgbBuffer=true;
			}
		});
		
		Commander.addCommanderAction(60,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[3].enabled=false;
				partLightningFields.objQuad[6].enabled=false;
			}
		});
		
		Commander.addCommanderAction(30,new ICommanderAction(){
			public void doAction(Object object){
				partLightningFields.objQuad[4].enabled=false;
				partLightningFields.objQuad[5].enabled=false;
				partLightningFields.objQuad[7].drawToRgbBuffer=true;
			}
		});
		
		return index;
	}

}
