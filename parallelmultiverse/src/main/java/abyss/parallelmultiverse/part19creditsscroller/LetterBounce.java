package abyss.parallelmultiverse.part19creditsscroller;

import abyss.lunarengine.gfx.Bob;
import abyss.parallelmultiverse.common.PointSpread;

public class LetterBounce extends PointSpread{
	private boolean bord;
	private int stopAtLine;
	private int sleepFrameCount;

	public LetterBounce(int x, int y, int z, int data, int stopAtLine, int sleepFrameCount) {
		super(x, y, z, data);
		this.stopAtLine=stopAtLine;
		this.sleepFrameCount=sleepFrameCount;
	}

	public boolean move(int border) {
		if(bord) {
			super.move();
		}else {
			if(dy>stopAtLine) {
				sy-=(1<<Bob.SHIFT)*(3.5-0.5);
				dy=sy>>Bob.SHIFT;
				bord=dy<border;
			}else {
				if(--sleepFrameCount>0) {
					return true;
				}else {
					stopAtLine=0;
				}
			}
		}
		return false;
	}
	
}
