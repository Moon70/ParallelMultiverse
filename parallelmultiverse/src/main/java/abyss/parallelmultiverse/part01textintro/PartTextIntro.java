package abyss.parallelmultiverse.part01textintro;

import abyss.lunarengine.APart;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;

public class PartTextIntro extends APart{
	private FontIntro font;
	private Textline[] textlines_page1;
	private Textline[] textlines_page2;

	public static void main(String[] args){
		try{
			LunarEngine.initializeEngine();
			APart partTextIntro=new PartTextIntro();
			partTextIntro.precalc();
			partTextIntro.initialize();
			LunarEngine.setActivePart(partTextIntro);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	@Override
	public void precalc(){
		font=new FontIntro("/data/font96_SansForgetica");
		//															y		x		speed		delay1	delay2
		textlines_page1=new Textline[7];
		textlines_page1[0]=new Textline(font,"ABYSS",				50,		-180,	8.0,		2*60,	12*60-40);
		textlines_page1[1]=new Textline(font,"PRESENTS",			150,	 120,	-4.7,		3*60,	12*60-40);
		textlines_page1[2]=new Textline(font,"PARALLEL MULTIVERSE",	250,	 -160,	4.5,		4*60,	13*60+30-40);

		textlines_page1[3]=new Textline(font,"OLD GROUP",			500,	-160,	4.0,		5*60,	7*60+30);
		textlines_page1[4]=new Textline(font,"OLD MEMBERS",			600,	  0,	-3.0,		5*60,	7*60+30);
		textlines_page1[5]=new Textline(font,"OLD SCHOOL",			700,	  -64,	3.0,		5*60,	7*60+30);
		textlines_page1[6]=new Textline(font,"OMG!",				800,	  0,	-3.0,		5*60,	12*60-40);


		textlines_page2=new Textline[3];
		textlines_page2[0]=new Textline(font,"NO DIRECT X",			500,	  100,	-15.0,		1*60,	4*60-40);
		textlines_page2[1]=new Textline(font,"NO OPEN GL",			600,	  -64,	 12.0,		1*60,	4*60-40);
		textlines_page2[2]=new Textline(font,"THIS IS PURE JAVA",	700,	  0,	-12.0,		1*60,	4*60-40);
	}

	public void switchToPage2() {
		for(int i=0;i<textlines_page2.length;i++) {
			textlines_page1[3+i]=textlines_page2[i];
		}
	}

	@Override
	public void vbi(){
		for(int i=0;i<textlines_page1.length;i++) {
			textlines_page1[i].vbi();
		}
	}

	@Override
	public void worker1(){
		for(int i=0;i<textlines_page1.length;i++) {
			textlines_page1[i].render();
		}
	}

	@Override
	public void worker2(){
		int pixel;
		for(int index=0;index<Screen.screenSizeX*Screen.screenSizeY;index++){
			pixel=LunarEngine.screendataToReset[index];
			pixel=pixel-((pixel & 0xf8f8f8)>>3);
			if((pixel & 0xff)!=0){
				LunarEngine.screendataToReset[index]=pixel-0x010101;
			}else {
				LunarEngine.screendataToReset[index]=pixel;
			}
		}
	}

}
