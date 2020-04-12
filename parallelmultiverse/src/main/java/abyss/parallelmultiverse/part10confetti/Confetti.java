package abyss.parallelmultiverse.part10confetti;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;

public class Confetti {
	public static int[] screendataToWork;
	private int[] colors;

	public static Bob[] bob;
	
	public static final int SHIFT2=5;
	
	public volatile int offsetX;
	public volatile int offsetY;

	public int deltaX;
	public int deltaY;
	public int deltaBob;
	public int deltaFrame;
	private int indexBob;
	private int indexFrame;
	public boolean enabled;
	public static boolean fadeout;

	public Confetti(int offsetX,int offsetY, int deltaX, int deltaY, int deltaBob, int deltaFrame, int startBob, int startFrame,int[] colors) {
		this.offsetX=offsetX<<SHIFT2;
		this.offsetY=offsetY<<SHIFT2;
		this.deltaX=deltaX;
		this.deltaY=deltaY;
		this.deltaBob=deltaBob;
		this.deltaFrame=deltaFrame;
		this.indexBob=startBob<<SHIFT2;
		this.indexFrame=startFrame<<SHIFT2;
		this.colors=colors;
		this.enabled=true;
	}
	
	public void vbiRain() {
		if(!enabled) {
			return;
		}
		if((offsetY+=deltaY)>(1200<<SHIFT2)) {
			if(fadeout) {
				enabled=false;
			}else {
				offsetY-=1200<<SHIFT2;
			}
		}
		indexBob+=deltaBob;
		if(indexBob>=PartConfetti.NUMBER_OF_BOBS<<SHIFT2) {
			indexBob-=PartConfetti.NUMBER_OF_BOBS<<SHIFT2;
		}
		indexFrame+=deltaFrame;
		if(indexFrame>=PartConfetti.NUMBER_OF_BOBFRAMES<<SHIFT2) {
			indexFrame-=PartConfetti.NUMBER_OF_BOBFRAMES<<SHIFT2;
		}

	}
	
	public void vbiCannons() {
		if(!enabled) {
			return;
		}
		if((offsetY+=deltaY)>(Screen.screenSizeY<<SHIFT2)) {
			enabled=false;
		}
		offsetX+=deltaX;
		
		if(deltaY<(3<<SHIFT2)) {
			deltaY+=2;
		}
		
		if(deltaX>0) {
			if(deltaX>(1<<(SHIFT2-1))) {
				deltaX-=2;
			}
		}else {
			if(deltaX<(-1<<(SHIFT2-1))) {
				deltaX+=2;
			}
		}
		indexBob+=deltaBob;
		if(indexBob>=PartConfetti.NUMBER_OF_BOBS<<SHIFT2) {
			indexBob-=PartConfetti.NUMBER_OF_BOBS<<SHIFT2;
		}
		indexFrame+=deltaFrame;
		if(indexFrame>=PartConfetti.NUMBER_OF_BOBFRAMES<<SHIFT2) {
			indexFrame-=PartConfetti.NUMBER_OF_BOBFRAMES<<SHIFT2;
		}

	}
	
	public void render() {
		if(!enabled) {
			return;
		}
		int i=indexBob>>SHIFT2;
		int render_posX=offsetX>>SHIFT2;
		int render_posY=offsetY>>SHIFT2;

		int clipX2=Screen.screenSizeX;
		int clipY2=Screen.screenSizeY;
		
		int startX=	render_posX<0?								-render_posX:							0;
		int endX=	render_posX+PartConfetti.bobSize>clipX2?	clipX2-render_posX:						PartConfetti.bobSize;
		int startY=	render_posY<0?								-render_posY:							0;
		int endY=	render_posY+PartConfetti.bobSize>clipY2?	clipY2-render_posY+render_posY:			PartConfetti.bobSize+render_posY;

		int render_offsetBob=startY*PartConfetti.bobSize;
		startY+=render_posY;
		int offset=startY*clipX2+render_posX;
		int pixel;
		for(int y=startY;y<endY;y++,render_offsetBob+=PartConfetti.bobSize,offset+=clipX2){
			for(int x=startX;x<endX;x++){
				pixel=bob[i].bobdata[indexFrame>>SHIFT2][render_offsetBob+x];
				if(pixel!=0){
					screendataToWork[offset+x]=colors[pixel];
				}
			}
		}
	}
	
}
