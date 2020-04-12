package abyss.parallelmultiverse.part13tunnel;

public class Ring {
	public int[] pointsX;
	public int[] pointsY;
	
	public int radiusIndex;
	public int radiusDelta;
	public int angleDelta;
	public int x;
	public int y;
	
	public Ring(int pointCount) {
		pointsX=new int[pointCount];
		pointsY=new int[pointCount];
	}
}
