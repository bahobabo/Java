package name.panitz.game.example.simple;
import name.panitz.game.framework.FallingImage;

import name.panitz.game.framework.Vertex;

public class Meteorite<I> extends FallingImage<I> {
	  public Meteorite(Vertex corner) {
		    super("meteorite.png", corner, new Vertex(2, 2));
		  } 
	
}
