package abyss.parallelmultiverse.part06lightningobject;

import java.util.Vector;

import abyss.lunarengine.gfx.Point3D;

public class LinePoint3D extends Point3D{
	public Vector<LineTo> vecLinesTo=new Vector<LineTo>();

	public LinePoint3D(int x, int y, int z) {
		super(x, y, z);
	}

	public void addLineTo(LineTo lineTo) {
		vecLinesTo.add(lineTo);
	}
	
}
