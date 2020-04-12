package abyss.parallelmultiverse.common;

import java.util.Arrays;
import java.util.Vector;

import abyss.lunarengine.gfx.Object3D;
import abyss.lunarengine.gfx.Point3D;
import abyss.lunarengine.gfx.Point3DzComparator;

public class Bobs3D extends Object3D{
	private Vector<Point3D> vecBobPositions=new Vector<Point3D>();
	private final Point3DzComparator<Point3D> point3DzComparator=new Point3DzComparator<Point3D>();
	public int arrayIndex;

	public void addBob(Point3D point) {
		vecBobPositions.add(point);
	}

	public void createArrays() {
		points=new Point3D[vecBobPositions.size()];
		for(int i=0;i<vecBobPositions.size();i++) {
			Point3D point=vecBobPositions.get(i);
			point.shiftLeft();
			points[i]=point;
		}
		arrayIndex=vecBobPositions.size();
		vecBobPositions=null;
	}

	@Override
	public void rotateCALC() {
		super.rotateCALC();
		sort();
	}

	public void rotateCALCnosort() {
		super.rotateCALC();
	}

	private void sort() {
		int index=0;
		for(int i=0;i<arrayIndex;i++) {
			if(points[i].enabled) {
				points[index++]=points[i];
			}
		}
		arrayIndex=index;
		Arrays.sort(points, 0,index,point3DzComparator);
	}

}
