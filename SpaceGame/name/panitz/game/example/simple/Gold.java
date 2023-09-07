package name.panitz.game.example.simple;

import name.panitz.game.framework.FallingImage;
import name.panitz.game.framework.GeometricObject;

import name.panitz.game.framework.Vertex;

public class Gold<I> extends FallingImage<I> {
	  public Gold(Vertex corner) {
		    super("gold.png", corner, new Vertex(0, 0));
		  } 
	
}
