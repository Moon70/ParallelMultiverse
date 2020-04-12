package abyss.parallelmultiverse.part19creditsscroller;

import java.util.Vector;

import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Font;
import abyss.parallelmultiverse.common.PointSpread;

public class TextlineHotizontal {
	private Font font;
	private int textlineXPos;
	
	private Vector<LetterBounce> letters=new Vector<LetterBounce>();
	private int lineposY;
	
	public TextlineHotizontal(Font font,int textlineXPos) {
		this.font=font;
		this.textlineXPos=textlineXPos;
		lineposY=Screen.screenSizeY+30;
	}
	
	public void addTextline(char[] text) {
		addTextline(text,0,0);
	}
	
	public void addTextline(char[] text,int stopAtLine, int sleepFrameCount) {
		int totalsize=0;
		for(int i=0;i<text.length;i++) {
			totalsize+=5+font.getCharWidth(text[i]);
		}
		int offset=(710-totalsize)>>1;
		int posX=textlineXPos+offset;
		for(int i=0;i<text.length;i++) {
			if(text[i]!=' ') {
				letters.add(new LetterBounce(posX,lineposY,0,text[i],i==0?stopAtLine:0,sleepFrameCount));
			}
			posX+=5+font.getCharWidth(text[i]);
		}
		lineposY+=70;
	}
	
	public void vbi(int border) {
		for(int i=0;i<letters.size();i++) {
			if(letters.get(i).move(border)) {
				break;
			}
		}
	}
	
	private PointSpread render_pointSpread;
	public void render() {
		for(int i=0;i<letters.size();i++) {
			render_pointSpread=letters.get(i);
			if(render_pointSpread.enabled) {
				font.render((char)render_pointSpread.data,render_pointSpread.dx,render_pointSpread.dy,LunarEngine.screendataToWork);
			}
		}
	}
	
}
