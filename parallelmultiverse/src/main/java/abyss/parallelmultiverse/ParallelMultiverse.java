package abyss.parallelmultiverse;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

import abyss.lunarengine.Commander;
import abyss.lunarengine.ICommanderAction;
import abyss.lunarengine.LunarEngine;
import abyss.lunarengine.LunarEngineCallback;
import abyss.lunarengine.LunarEngineTools;
import abyss.parallelmultiverse.part01textintro.MainTextIntro;
import abyss.parallelmultiverse.part01textintro.PartTextIntro;
import abyss.parallelmultiverse.part02abysslogo.MainAbyssLogo;
import abyss.parallelmultiverse.part02abysslogo.PartAbyssLogo;
import abyss.parallelmultiverse.part03mosaicbackground.PartMosaicBackground;
import abyss.parallelmultiverse.part04bobspiral.MainBobSpiral;
import abyss.parallelmultiverse.part04bobspiral.PartBobSpiral;
import abyss.parallelmultiverse.part05bobbackground.PartBobBackground;
import abyss.parallelmultiverse.part06lightningobject.MainLightningObject;
import abyss.parallelmultiverse.part06lightningobject.PartLightningObject;
import abyss.parallelmultiverse.part07turnbars.MainTurnBars;
import abyss.parallelmultiverse.part07turnbars.PartTurnbars;
import abyss.parallelmultiverse.part08ribbons.PartRibbons;
import abyss.parallelmultiverse.part09bounce.MainBounce;
import abyss.parallelmultiverse.part09bounce.PartBounce;
import abyss.parallelmultiverse.part10confetti.PartConfetti;
import abyss.parallelmultiverse.part11bobscubes.MainBobCubes;
import abyss.parallelmultiverse.part11bobscubes.PartBobCubes;
import abyss.parallelmultiverse.part12eyewave.PartEyeWave;
import abyss.parallelmultiverse.part13tunnel.MainTunnel;
import abyss.parallelmultiverse.part13tunnel.PartTunnel;
import abyss.parallelmultiverse.part14lightningfields.MainLightningFields;
import abyss.parallelmultiverse.part14lightningfields.PartLightningFields;
import abyss.parallelmultiverse.part15rgb.MainRGB;
import abyss.parallelmultiverse.part15rgb.PartRGB;
import abyss.parallelmultiverse.part16dottyobject.PartDottyObject;
import abyss.parallelmultiverse.part17splinescroller.MainSplineScroller;
import abyss.parallelmultiverse.part17splinescroller.PartSplineScroller;
import abyss.parallelmultiverse.part18creditsbobs.PartCreditsBobs;
import abyss.parallelmultiverse.part19creditsscroller.MainCreditsScroller;
import abyss.parallelmultiverse.part19creditsscroller.PartCreditsScroller;
import abyss.parallelmultiverse.part20lasercutter.MainLaserCutter;
import abyss.parallelmultiverse.part20lasercutter.PartLaserCutter;
import oggvorbisplayer.ExamplePlayer;

/*
 * Parallel Multiverse
 * 
 * is Abyss´ contribution to the demo competition of the Revision 2020 Demoscene event.
 * 
 * Credits:
 * 
 * - Music by Neurodancer
 *   'Drop your baggage' taken from the album 'znooQ´d'
 *   https://neurowerx.bandcamp.com/album/znooqd
 * 
 * - Abyss Logo by Celtic/Axis
 *   Used before in the Abyss Amiga demo 'Pulstar'. So once more: Thank you, Niels, again it fits perfect.
 *   
 * - Eyeball by Tyshdomos
 *   Used before in the Abyss Amiga demo 'Drugstore', cut-out from your great title pic.
 *   I think we´ve never met or had personal contact, anyway, thanks Thomas for all your great work.
 *   
 * - Sans Forgetica font developed by RMIT University in Melbourne, Australia.
 *   Creative Commons Attribution-NonCommercial license.
 *   https://creativecommons.org/licenses/by-nc/4.0/
 *   
 * - Ogg Vorbis Player by Jon Kristensen
 *   http://www.jcraft.com/jorbis/tutorial/Tutorial.html
 *   Creative Commons Attribution-ShareAlike 3.0
 *   https://creativecommons.org/licenses/by/3.0/
 *   
 * - Java Code by Moon
 * 
 */
public class ParallelMultiverse implements LunarEngineCallback{
	private static ExamplePlayer oggVorbisPlayer;//Ogg Vorbis Player by Jon Kristensen

	public static void main(String[] args){
		try{
			LunarEngine.clientName="Parallel Multiverse by Abyss";
			System.out.println(LunarEngine.clientName);
			final ParallelMultiverse parallelMultiverse=new ParallelMultiverse();
			parallelMultiverse.initializeMusic(parallelMultiverse);
			LunarEngine.fullscreenExclusiveMode=true;
			LunarEngine.initializeEngine(parallelMultiverse);
			Commander.addCommanderAction(1000,new ICommanderAction(){
				public void doAction(Object object){
					parallelMultiverse.startMusic();
				}
			});
			
			final PartTextIntro partTextIntro=new PartTextIntro();
			final PartMosaicBackground partMosaicBackground=new PartMosaicBackground();
			final PartAbyssLogo partAbyssLogo=new PartAbyssLogo();
			final PartBobSpiral partBobSpiral=new PartBobSpiral();
			final PartBobBackground partBobBackground=new PartBobBackground();
			final PartLightningObject partLightningObject=new PartLightningObject();
			final PartTurnbars partTurnbars=new PartTurnbars();
			final PartRibbons partRibbons=new PartRibbons();
			final PartBounce partBounce=new PartBounce();
			final PartConfetti partConfetti=new PartConfetti();
			final PartBobCubes partBobsCubes=new PartBobCubes();
			final PartEyeWave partEyeWave=new PartEyeWave();
			final PartTunnel partTunnel=new PartTunnel();
			final PartRGB partRGB=new PartRGB();
			final PartLightningFields partLightningFields=new PartLightningFields();
			final PartDottyObject partDottyObject=new PartDottyObject();
			final PartSplineScroller partSplineScroller=new PartSplineScroller();
			final PartCreditsBobs partCreditsBobs=new PartCreditsBobs();
			final PartCreditsScroller partCreditsScroller=new PartCreditsScroller();
			final PartLaserCutter partLaserCutter=new PartLaserCutter();

			
			partTextIntro.precalc();
			MainTextIntro.addCommands(partTextIntro);
			
			partMosaicBackground.precalc();
			partAbyssLogo.precalc();
			MainAbyssLogo.addCommands(partAbyssLogo,partMosaicBackground);
			
			partBobSpiral.precalc();
			MainBobSpiral.addCommands(partBobSpiral, partMosaicBackground);
			
			partBobBackground.precalc();
			partLightningObject.precalc();
			MainLightningObject.addCommands(partLightningObject,partMosaicBackground,partBobBackground);
			
			partTurnbars.precalc();
			MainTurnBars.addCommands(partTurnbars,partBobBackground);
			
			partBounce.precalc();
			partRibbons.precalc();
			MainBounce.addCommands(partBounce,partRibbons);
			
			partBobsCubes.precalc();
			partConfetti.precalc();
			MainBobCubes.addCommands(partBobsCubes,partRibbons,partConfetti);
			
			partEyeWave.precalc();
			partTunnel.precalc();
			MainTunnel.addCommands(partTunnel,partEyeWave);
			
			partRGB.precalc();
			partLightningFields.precalc();
			MainLightningFields.addCommands(partLightningFields, partRGB);
			
			partDottyObject.precalc();
			MainRGB.addCommands(partRGB,partDottyObject);
			
			partSplineScroller.precalc();
			MainSplineScroller.addCommands(partSplineScroller,partRGB);
			
			partCreditsBobs.precalc();
			partCreditsScroller.precalc();
			MainCreditsScroller.addCommands(partCreditsScroller,partCreditsBobs);

			partLaserCutter.precalc();
			MainLaserCutter.addCommands(partLaserCutter,partConfetti);
			
			Commander.start(3);
			LunarEngine.startEngine();
		}catch(Throwable throwable){
			LunarEngine.throwableHandler(throwable);
		}
	}

	private void initializeMusic(ParallelMultiverse parallelMultiverse) {
		try {
			URL url = LunarEngineTools.getResourceUrl(this,"data/Drop your baggage.ogg");
			BufferedInputStream bufferedInputStream=new BufferedInputStream(url.openStream());
			byte[] buffer=new byte[1024];
			int count;
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			while((count=bufferedInputStream.read(buffer))!=-1) {
				baos.write(buffer,0,count);
			}
			bufferedInputStream.close();
			ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
			oggVorbisPlayer = new ExamplePlayer(bais);
		} catch (Exception e) {
			throw new RuntimeException("error initializing music",e);
		}
	}
	
	private void startMusic() {
		oggVorbisPlayer.start();
	}

	private void stopMusic() {
		oggVorbisPlayer.stopPlayer=true;
	}

	public void lunarEngineShutdown() {
		stopMusic();
	}
	
}
