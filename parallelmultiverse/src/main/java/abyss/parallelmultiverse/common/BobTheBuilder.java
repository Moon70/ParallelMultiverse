package abyss.parallelmultiverse.common;

import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.LordFader;
import abyss.lunarengine.gfx.Point3D;
import abyss.lunarengine.gfx.PolarBear;

public class BobTheBuilder {

	public static final int PERSP_DELTA_Y=400;
	public static Bobs3D createCuboid(int bobDelta, int objectSizeX, int objectSizeY,int objectSizeZ) {
		Bobs3D bobs3d=new Bobs3D();
		int step=bobDelta;
		for(int z=-(objectSizeZ*step/2);z<(objectSizeZ*step/2);z+=step) {
			int color=0;
			for(int y=0;y<(objectSizeY*step);y+=step) {
				color++;
				for(int x=-(objectSizeX*step/2);x<(objectSizeX*step/2);x+=step) {
					bobs3d.addBob(new Point3D(x, y+PERSP_DELTA_Y, z,color%6));
				}
			}
		}
		bobs3d.createArrays();
		return bobs3d;
	}
	
	public static Bobs3D createObject2(int bobDelta, int objectSize) {
		Bobs3D bobs3d=new Bobs3D();
		int size=1;
		for(int i=0;i<objectSize;i++) {
			bobLine(bobs3d,bobDelta,0,	-size,	-size,	0,	size,	-size,	0);
			bobLine(bobs3d,bobDelta,1,	size,	-size,	0,	size,	size,	0);
			bobLine(bobs3d,bobDelta,0,	size,	size,	0,	-size,	size,	0);
			bobLine(bobs3d,bobDelta,1,	-size,	size,	0,	-size,	-size,	0);

			bobLine(bobs3d,bobDelta,2,	0,	-size,	-size,	0,	size,	-size);
			bobLine(bobs3d,bobDelta,3,	0,	size,	-size,	0,	size,	size);
			bobLine(bobs3d,bobDelta,2,	0,	size,	size,	0,	-size,	size);
			bobLine(bobs3d,bobDelta,3,	0,	-size,	size,	0,	-size,	-size);

			bobLine(bobs3d,bobDelta,4,	-size,	0,	-size,	size,	0,	-size);
			bobLine(bobs3d,bobDelta,5,	size,	0,	-size,	size,	0,	size);
			bobLine(bobs3d,bobDelta,4,	size,	0,	size,	-size,	0,	size);
			bobLine(bobs3d,bobDelta,5,	-size,	0,	size,	-size,	0,	-size);
			size+=bobDelta;
		}
		bobs3d.createArrays();
		return bobs3d;
	}
	
	public static Bobs3D createCubes(int bobDelta, int objectSize) {
		Bobs3D bobs3d=new Bobs3D();
		int color=0;
		for(int i=objectSize-18;i<objectSize;i+=6) {
			createCube(bobs3d,bobDelta,i,(color++)%6);
		}
		bobs3d.createArrays();
		return bobs3d;
	}

	private static void createCube(Bobs3D bobs3d,int bobDelta,int size,int bobIndex) {
		addSquareZ(bobs3d,bobDelta,size,-size,bobIndex);
		addSquareZ(bobs3d,bobDelta,size,size,bobIndex);

		addSquareX(bobs3d,bobDelta,size,-size,bobIndex);
		addSquareX(bobs3d,bobDelta,size,size,bobIndex);

		addSquareY(bobs3d,bobDelta,size,-size,bobIndex);
		addSquareY(bobs3d,bobDelta,size,size,bobIndex);
	}
	
	private static void addSquareZ(Bobs3D bobs3d,int bobDelta,int size, int deltaZ, int bobIndex) {
		int step=bobDelta;
		for(int y=-size*step/2;y<=size*step/2;y+=step) {
			for(int x=-size*step/2;x<=size*step/2;x+=step) {
				bobs3d.addBob(new Point3D(x, y, deltaZ*step/2,bobIndex));
			}
		}
	}

	private static void addSquareX(Bobs3D bobs3d,int bobDelta,int size, int deltaX, int bobIndex) {
		int step=bobDelta;
		for(int y=-size*step/2;y<=size*step/2;y+=step) {
			for(int z=-size*step/2;z<=size*step/2;z+=step) {
				bobs3d.addBob(new Point3D(deltaX*step/2, y, z,bobIndex));
			}
		}
	}

	private static void addSquareY(Bobs3D bobs3d,int bobDelta,int size, int deltaY, int bobIndex) {
		int step=bobDelta;
		for(int x=-size*step/2;x<=size*step/2;x+=step) {
			for(int z=-size*step/2;z<=size*step/2;z+=step) {
				bobs3d.addBob(new Point3D(x,deltaY*step/2, z,bobIndex));
			}
		}
	}

	private static void bobLine(Bobs3D bobs3d,int bobSize,int bobIndex, int x1, int y1, int z1, int x2, int y2, int z2) {
		int posX=x1 << Bob.SHIFT;
		int posY=y1 << Bob.SHIFT;
		int posZ=z1 << Bob.SHIFT;
		int deltaX=(x2-x1) << Bob.SHIFT;
		int deltaY=(y2-y1) << Bob.SHIFT;
		int deltaZ=(z2-z1) << Bob.SHIFT;
		int points;
		if(Math.abs(deltaX)>Math.abs(deltaY)) {
			if(Math.abs(deltaX)>Math.abs(deltaZ)) {
				points=(deltaX/bobSize)>>Bob.SHIFT;
			}else {
				points=(deltaZ/bobSize)>>Bob.SHIFT;
			}
		}else {
			if(Math.abs(deltaY)>Math.abs(deltaZ)) {
				points=(deltaY/bobSize)>>Bob.SHIFT;
			}else {
				points=(deltaZ/bobSize)>>Bob.SHIFT;
			}
		}
		if(points==0) {
			points=1;
		}else {
			points=Math.abs(points);
		}
		deltaX/=points;
		deltaY/=points;
		deltaZ/=points;
		
		for(int i=0;i<points;i++) {
			bobs3d.addBob(new Point3D(posX>>Bob.SHIFT, posY>>Bob.SHIFT, posZ>>Bob.SHIFT,bobIndex));
			posX+=deltaX;
			posY+=deltaY;
			posZ+=deltaZ;
		}
	}

	public static void reColor(Bob bob,int bobIndex,int color) {
		int pixel=0;
		int r=0;
		int g=0;
		int b=0;
		int rn=0;
		int gn=0;
		int bn=0;

		rn=color >> 16;
		gn=(color >> 8) & 0xff;
		bn=color & 0xff;
		for(int y=0;y<bob.bobSizeY;y++) {
			for(int x=0;x<bob.bobSizeX;x++) {
				pixel=bob.bobdata[bobIndex][x+y*bob.bobSizeX];
				r=(pixel >> 16) & 0xff;
				g=(pixel >> 8) & 0xff;
				b=pixel & 0xff;
				
				r=(r*rn)>>8;
				g=(g*gn)>>8;
				b=(b*bn)>>8;

				pixel=(r<<16)+(g<<8)+b;
				if(pixel>0) {
					pixel+=255 << 24;
				}
				bob.bobdata[bobIndex][x+y*bob.bobSizeX]=pixel;
			}
		}
	}

	public static void reColorFade(Bob bob,int color) {
		int[][] bobdata=bob.bobdata;
		bob.bobdata=new int[256][bobdata[0].length];
		for(int z=0;z<bob.bobSizeX*bob.bobSizeY;z++) {
			for(int i=0;i<256;i++){
				bob.bobdata[i][z]=bobdata[0][z];
			}
		}
		LordFader fader=new LordFader(color,0x000000,256);
		for(int i=0;i<256;i++) {
			BobTheBuilder.reColor(bob, i,fader.colors[i]);
		}
	}

	public static Bobs3D createLaser(int bobSize) {
		Bobs3D bobs3d=new Bobs3D();
		int deltaY=-50;
		int headLength1=50;
		int headLength2=100;
		int r=20;
		int r2=7;
		for(int a=0;a<360;a+=20) {
			int x=(int)(Math.cos(PolarBear.DEG2RAD*a)*r);
			int z=(int)(Math.sin(PolarBear.DEG2RAD*a)*r);
			int x2=(int)(Math.cos(PolarBear.DEG2RAD*a)*r2);
			int z2=(int)(Math.sin(PolarBear.DEG2RAD*a)*r2);
			bobLine(bobs3d,bobSize,5, 	0, 	0+deltaY+PERSP_DELTA_Y, 			0,	 	x,		deltaY-headLength1+PERSP_DELTA_Y,				z);
			bobLine(bobs3d,bobSize,6, 	x,	deltaY-headLength1+PERSP_DELTA_Y,	z,	 	x2,		deltaY-headLength1-headLength2+PERSP_DELTA_Y,	z2);
		}
		bobs3d.createArrays();
		return bobs3d;
	}

}
