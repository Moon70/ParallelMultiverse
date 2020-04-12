package abyss.parallelmultiverse.common;

import java.util.Vector;

import abyss.lunarengine.Screen;
import abyss.lunarengine.gfx.Bob;
import abyss.lunarengine.gfx.Point3D;

public class BobsBounce {
	private final Vector<Point3D> points=new Vector<Point3D>();
	public int frame;
	
	public void addBob(Point3D point) {
		points.add(new PointBounce(point.dx+Screen.screenCenterX,point.dy+Screen.screenCenterY,point.dz,point.data));
	}
	
	public void vbi() {
		for(int i=0;i<points.size();i++) {
			points.get(i).move();
		}
	}
	
	private Point3D render_point;
	public void render(Bob[] bobData) {
		int bobIndex;
		for(int i=0;i<points.size();i++) {
			render_point=points.get(i);
			if(render_point.enabled) {
				bobIndex=render_point.data;
				bobData[bobIndex].frame=frame;
				bobData[bobIndex].render(render_point.dx, render_point.dy);
			}
		}
	}
	
}
