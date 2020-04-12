package abyss.parallelmultiverse.part06lightningobject;

import java.util.Arrays;
import java.util.Vector;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Object3D;
import abyss.lunarengine.gfx.Point3D;
import abyss.lunarengine.gfx.Point3DzComparator;
import abyss.lunarengine.gfx.Tools2D;

public class Lineobject3D  extends Object3D{
	private Vector<LinePoint3D> vecPoints=new Vector<LinePoint3D>();

	public void addPoint(LinePoint3D point) {
		vecPoints.add(point);
	}

	public void addLine(Line line) {
		LinePoint3D linePoint3D=vecPoints.get(line.pointIndex1);
		linePoint3D.addLineTo(new LineTo(vecPoints.get(line.pointIndex2),line.colors));
	}

	public void createArrays() {
		points=new LinePoint3D[vecPoints.size()];
		for(int i=0;i<vecPoints.size();i++) {
			LinePoint3D point=vecPoints.get(i);
			point.shiftLeft();
			points[i]=point;
		}
		vecPoints=null;
	}

	public void render(Tools2D tools2D,int offsetX) {
		LineTo lineTo;
		Vector<LineTo> vecLinesTo;
		for(int i=0;i<points.length;i++) {
			vecLinesTo=((LinePoint3D)points[i]).vecLinesTo;
			if(vecLinesTo.size()==0) {
				continue;
			}
			int z=(points[i].dz+500)>>2;
			if(z>255) {
				z=255;
			}else if(z<0) {
				z=0;
			}
			for(int j=0;j<vecLinesTo.size();j++) {
				lineTo=vecLinesTo.get(j);
				tools2D.pixel=lineTo.colors[z];
				tools2D.lightningLine(points[i].dx+offsetX,points[i].dy+Screen.screenCenterY,lineTo.linePoint3D.dx+offsetX,lineTo.linePoint3D.dy+Screen.screenCenterY);
			}
		}
	}
	
	public void rotateCALC() {
		super.rotateCALC();
		Arrays.sort(points, new Point3DzComparator<Point3D>());
	}

}
