package abyss.parallelmultiverse.part07turnbars;

import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.sinatra.Sinatra;
import abyss.lunarengine.tools.Random;

public abstract class Turnbar {
	public Bob bob;
	public Sinatra sinatraMove1;
	public Sinatra sinatraMove2;
	public int positionOffset;
	public Sinatra sinatraAnim1;
	public Sinatra sinatraAnim2;
	public int animPos=(int)(Random.random()*128);
	public volatile int barStart;
	public volatile int barEnd;
	public boolean animate;
	public boolean fadeIn;
	public boolean fadeOut;

	public static int[] render_screendataWorking;
	
	public abstract void vbi();
	
	public abstract void render();

}
