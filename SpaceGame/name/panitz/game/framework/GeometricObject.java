package name.panitz.game.framework;

public class GeometricObject<I> {
	
	int x ; int y; Vertex corner; Vertex movement;
	public GeometricObject(int x , int y, Vertex corner, Vertex movement) {
		
		this.x = x;
		this.y =y;
		this.corner = corner;
		this.movement = movement;
		
	}
}
