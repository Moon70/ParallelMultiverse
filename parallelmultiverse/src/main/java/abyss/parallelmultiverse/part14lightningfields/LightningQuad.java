package abyss.parallelmultiverse.part14lightningfields;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Object2D;
import abyss.lunarengine.gfx.Tools2D;
import abyss.lunarengine.tools.Random;

public class LightningQuad extends Object2D{
	public Tools2D tools2D=new Tools2D();
	public int colorBorder;
	public int colorFill1;
	public int colorFill2;
	public boolean enabled;
	public boolean drawToRgbBuffer;
	
	private int colorFill;

	private int offsetX;
	private int offsetY;
	private int deltaX;
	private int deltaY;
	
	
	public LightningQuad() {}
	
	public LightningQuad(int posX,int posY,int deltaX,int deltaY) {
		this.offsetX=posX;
		this.offsetY=posY;
		this.deltaX=deltaX;
		this.deltaY=deltaY;
	}
	
	public void vbi() {
		if(!enabled) {
			return;
		}
		super.rotateVBI();
		offsetX+=deltaX;
		offsetY+=deltaY;
	}
	
	public void draw() {
		if(!enabled) {
			return;
		}
		tools2D.pixel=colorBorder;
		lightningLine(to[0][0]+Screen.screenCenterX,to[0][1]+Screen.screenCenterY,to[1][0]+Screen.screenCenterX,to[1][1]+Screen.screenCenterY);
		lightningLine(to[1][0]+Screen.screenCenterX,to[1][1]+Screen.screenCenterY,to[2][0]+Screen.screenCenterX,to[2][1]+Screen.screenCenterY);
		lightningLine(to[2][0]+Screen.screenCenterX,to[2][1]+Screen.screenCenterY,to[3][0]+Screen.screenCenterX,to[3][1]+Screen.screenCenterY);
		lightningLine(to[3][0]+Screen.screenCenterX,to[3][1]+Screen.screenCenterY,to[0][0]+Screen.screenCenterX,to[0][1]+Screen.screenCenterY);
	}
	
	public void drawFilled() {
		if(!enabled) {
			return;
		}
		colorFill=colorFill1;
		lightningArea(
				to[0][0]+Screen.screenCenterX+offsetX,
				to[0][1]+Screen.screenCenterY+offsetY,
				to[1][0]+Screen.screenCenterX+offsetX,
				to[1][1]+Screen.screenCenterY+offsetY,
				to[2][0]+Screen.screenCenterX+offsetX,
				to[2][1]+Screen.screenCenterY+offsetY,
				to[3][0]+Screen.screenCenterX+offsetX,
				to[3][1]+Screen.screenCenterY+offsetY);
		colorFill=colorFill2;
		lightningArea(
				to[1][0]+Screen.screenCenterX+offsetX,
				to[1][1]+Screen.screenCenterY+offsetY,
				to[2][0]+Screen.screenCenterX+offsetX,
				to[2][1]+Screen.screenCenterY+offsetY,
				to[3][0]+Screen.screenCenterX+offsetX,
				to[3][1]+Screen.screenCenterY+offsetY,
				to[0][0]+Screen.screenCenterX+offsetX,
				to[0][1]+Screen.screenCenterY+offsetY);
	}
	
	
	private static final int NUMBER_OF_LIGHTNING_SECTIONS=16;
	private void lightningLine(int x1,int y1, int x2, int y2) {
		int lightning_deltaX=x2-x1;
		int lightning_deltaY=y2-y1;
		int lightning_stepX=lightning_deltaX/NUMBER_OF_LIGHTNING_SECTIONS;
		int lightning_stepY=lightning_deltaY/NUMBER_OF_LIGHTNING_SECTIONS;
		
		int lightning_xPos1=x1;
		int lightning_yPos1=y1;
		int lightning_xPos2;
		int lightning_yPos2;
		for(int i=1;i<NUMBER_OF_LIGHTNING_SECTIONS;i++) {
			x1+=lightning_stepX;
			y1+=lightning_stepY;
			
			lightning_xPos2=x1+getRND();
			lightning_yPos2=y1+getRND();
			
			tools2D.lineClip(lightning_xPos1,lightning_yPos1,lightning_xPos2,lightning_yPos2);
			
			lightning_xPos1=lightning_xPos2;
			lightning_yPos1=lightning_yPos2;

		}
		tools2D.lineClip(lightning_xPos1,lightning_yPos1,x2,y2);
	}

	private void lightningArea(int ax1,int ay1, int ax2, int ay2,int bx2,int by2, int bx1, int by1) {
		int lightning_adeltaX=ax2-ax1;
		int lightning_adeltaY=ay2-ay1;
		int lightning_astepX=lightning_adeltaX/NUMBER_OF_LIGHTNING_SECTIONS;
		int lightning_astepY=lightning_adeltaY/NUMBER_OF_LIGHTNING_SECTIONS;
		int lightning_axPos1=ax1;
		int lightning_ayPos1=ay1;
		int lightning_axPos2;
		int lightning_ayPos2;
		
		int lightning_bdeltaX=bx2-bx1;
		int lightning_bdeltaY=by2-by1;
		int lightning_bstepX=lightning_bdeltaX/NUMBER_OF_LIGHTNING_SECTIONS;
		int lightning_bstepY=lightning_bdeltaY/NUMBER_OF_LIGHTNING_SECTIONS;
		int lightning_bxPos1=bx1;
		int lightning_byPos1=by1;
		int lightning_bxPos2;
		int lightning_byPos2;
		
		for(int i=1;i<NUMBER_OF_LIGHTNING_SECTIONS;i++) {
			ax1+=lightning_astepX;
			ay1+=lightning_astepY;
			lightning_axPos2=ax1+getRND();
			lightning_ayPos2=ay1+getRND();
			
			bx1+=lightning_bstepX;
			by1+=lightning_bstepY;
			lightning_bxPos2=bx1+getRND();
			lightning_byPos2=by1+getRND();

			tools2D.pixel=colorBorder;
			tools2D.lineClip(lightning_axPos1,lightning_ayPos1,lightning_axPos2,lightning_ayPos2);
			tools2D.lineClip(lightning_axPos1+1,lightning_ayPos1+1,lightning_axPos2+1,lightning_ayPos2+1);

			tools2D.lineClip(lightning_bxPos1,lightning_byPos1,lightning_bxPos2,lightning_byPos2);
			tools2D.lineClip(lightning_bxPos1+1,lightning_byPos1+1,lightning_bxPos2+1,lightning_byPos2+1);

			tools2D.pixel=colorFill;
			lightningLine(lightning_axPos2,lightning_ayPos2,lightning_bxPos2,lightning_byPos2);
			
			lightning_axPos1=lightning_axPos2;
			lightning_ayPos1=lightning_ayPos2;

			lightning_bxPos1=lightning_bxPos2;
			lightning_byPos1=lightning_byPos2;

		}
		tools2D.pixel=colorBorder;
		tools2D.lineClip(lightning_axPos1,lightning_ayPos1,ax2,ay2);
		tools2D.lineClip(lightning_axPos1+1,lightning_ayPos1+1,ax2+1,ay2+1);

		tools2D.lineClip(lightning_bxPos1,lightning_byPos1,bx2,by2);
		tools2D.lineClip(lightning_bxPos1+1,lightning_byPos1+1,bx2+1,by2+1);
	}

	private int getRND() {
		return (int)(Random.random()*30)-15;
	}

}
