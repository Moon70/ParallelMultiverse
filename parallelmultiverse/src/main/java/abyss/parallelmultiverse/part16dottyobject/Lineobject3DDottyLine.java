package abyss.parallelmultiverse.part16dottyobject;

import java.util.Arrays;
import java.util.Vector;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.Point3D;
import abyss.lunarengine.gfx.Point3DzComparator;
import abyss.lunarengine.gfx.Tools2D;

public class Lineobject3DDottyLine extends Lineobject3D{

	private LineTo lineTo;
	private Vector<LineTo> vecLinesTo;
	
	public void render(Tools2D tools2D,int offsetX,int ringPosZ) {
		int z;
		int ringDistance;
		int lineZ;
		for(int i=0;i<points.length;i++) {
			vecLinesTo=((LinePoint3D)points[i]).vecLinesTo;
			if(vecLinesTo.size()==0) {
				continue;
			}
			z=(points[i].dz+500)>>2;
			if(z>255) {
				z=255;
			}else if(z<0) {
				z=0;
			}

			for(int j=0;j<vecLinesTo.size();j++) {
				lineTo=vecLinesTo.get(j);
				tools2D.pixel=lineTo.colors[z];
				
				lineZ=(points[i].sz+lineTo.linePoint3D.sz)>>(Bob.SHIFT+1);
			
				ringDistance=Math.abs(ringPosZ-lineZ);
				if(lineZ<ringPosZ) {
					tools2D.rnd=110>>2;
				}else if(ringDistance>100) {
					if(lineZ>ringPosZ) {
						tools2D.rnd=2;
					}else {
						tools2D.rnd=100>>2;
					}
				}else {
					tools2D.rnd=(110-ringDistance)>>2;
				}
	
				tools2D.dottyLine(points[i].dx+offsetX,points[i].dy+Screen.screenCenterY,lineTo.linePoint3D.dx+offsetX,lineTo.linePoint3D.dy+Screen.screenCenterY);
			}
		}
	}
	
	public void rotateCALC() {
		super.rotateCALC();
		Arrays.sort(points, new Point3DzComparator<Point3D>());
	}

}
