package name.panitz.game.example.simple;
import name.panitz.game.framework.SteuerbarImage;
import name.panitz.game.framework.Vertex;
import name.panitz.game.framework.*;

public class Spaceship <I> extends SteuerbarImage<I> {
	  public Spaceship(Vertex corner) {
		    super(corner);
		  }
	  void moveup() {
			 getVelocity().y = -2.6;
		 }
		 void movedown() {
			 getVelocity().y=2.6;
			
		 }
		 void moveright() {
			 getVelocity().x=2.6;
		 }
		 void moveleft() {
			 getVelocity().x=-2.6;
			 
		 }
		 void stopup() {
			 getVelocity().y = 0;
			 
		 }
	     void stopdown() {
	    	 getVelocity().y = 0;
		 }
	     void stopright() {
	    	 getVelocity().x = 0;
	 }
	     void stopleft() {
	    	 getVelocity().x = 0;
	 }
}

