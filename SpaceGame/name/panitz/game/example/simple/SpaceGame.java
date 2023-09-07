package name.panitz.game.example.simple;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import name.panitz.game.framework.AbstractGame;
import name.panitz.game.framework.Button;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.KeyCode;
import name.panitz.game.framework.SoundObject;
import name.panitz.game.framework.SteuerbarImage;
import name.panitz.game.framework.TextObject;
import name.panitz.game.framework.Vertex;


public class SpaceGame<I, S> extends AbstractGame<I, S> {
	
	 JFrame f;  
		JPanel p;  
	
	List<Meteorite<I>> meteorites = new ArrayList<>();
	List<Meteorite2<I>> meteorites2 = new ArrayList<>();
	List<Gold<I>> golds = new ArrayList<>();
	Spaceship<I> spaceship;
	List<GameObject<I>> hintergrund = new ArrayList<>();
	List<GameObject<I>> hintergrund2 = new ArrayList<>();
	
	static final int GRID_WIDTH=1;
	int score=0;
	int energy=3;
	int level=1;
	TextObject<I> infoText
    = new TextObject<>(new Vertex(500,335),
       "Du Hast Verloren","Helvetica",30);
	TextObject<I> infoText2
    = new TextObject<>(new Vertex(505,375),
       "(Created by Bahadir Hacisalihoglu)","Helvetica",14);
	TextObject<I> infoText3
    = new TextObject<>(new Vertex(500,335),
       "Du Hast Gewonnen","Helvetica",30);

	    
	    
	
	
	 public SpaceGame() {
		 
		    super(new Spaceship<>(new Vertex(600,220)), GRID_WIDTH*1500, GRID_WIDTH*800);
		
		    WahlFrame wf = new WahlFrame();
		     
		    
		    getGOss().add(hintergrund);
		    spaceship = (Spaceship<I>) getPlayer();
		    getGOss().add(golds);
		    getGOss().add(meteorites);
		    getGOss().add(meteorites2);
		    getGOss().add(hintergrund2);
		    getButtons().add(new Button("Start", ()-> start()));
		    getButtons().add(new Button("pause",()->pause()));
		    hintergrund.add(new ImageObject<>("Space.jpg"));
		    for(int x =1;x<=2;x++)
		    golds.add(new Gold<>(new Vertex(Math.random()*1000,Math.random()*500)));
			
		    for(int x =1;x<4;x++)
		    meteorites.add(new Meteorite<>(new Vertex(x*Math.random()*0,x*Math.random()*390)));
		   
		   
		  
					
                 
	 }
	 SoundObject<S> collision = new SoundObject<S>("Collision1.wav");
	 
	 SoundObject<S> lose = new SoundObject<S>("lose.wav");
	 SoundObject<S> levelup = new SoundObject<S>("levelup.wav");
	 
	 public void paintTo(GraphicsTool<I> g) {
		    super.paintTo(g);
		
		    g.drawString(60, 20, "Energy: " + energy);
		    g.drawString(60, 40, "Score: " + score);
		    g.drawString(60, 60, "Level: " + level);
		    
		  }
	 
	 private void collisionCheck() {
		 for(GameObject<I>meteorit:meteorites) {
			 if(meteorit.getPos().x>=getWidth()) {
				 meteorit.getPos().y=Math.random()*610;
			     meteorit.getPos().x=0;
			 }
		 if(meteorit.getPos().x+meteorit.getWidth()<=0) {
			 meteorit.getPos().y=Math.random()*610;
			 meteorit.getPos().x=getWidth();
		 }
		 if(meteorit.getPos().y>=getHeight()) {
			 meteorit.getPos().y=0;
		     meteorit.getPos().x=Math.random()*610;
		 }
	 if(meteorit.getPos().y+meteorit.getHeight()<=0) {
		 meteorit.getPos().x=Math.random()*610;
	     meteorit.getPos().y=getHeight();
	 }
	   
		 }
		 
	 for(GameObject<I>meteorit2:meteorites2) {
			 if(meteorit2.getPos().x>=getWidth()) {
				 meteorit2.getPos().y=Math.random()*450;
			     meteorit2.getPos().x=0;
			 }
		 if(meteorit2.getPos().x+meteorit2.getWidth()<=0) {
			 meteorit2.getPos().y=Math.random()*450;
			 meteorit2.getPos().x=getWidth();
		 }
		 if(meteorit2.getPos().y>=getHeight()) {
			 meteorit2.getPos().y=0;
		     meteorit2.getPos().x=Math.random()*450;
		 }
	 if(meteorit2.getPos().y+meteorit2.getHeight()<=0) {
		 meteorit2.getPos().x=Math.random()*450;
	     meteorit2.getPos().y=getHeight();
				 
	 }
	 }
	 }
	 
	 
	private void collision_meteoriteCheck() {
		GameObject<I> g1;
		GameObject<I> g2;
		 double xt;
		 double yt;
		for(int x = 0; x< meteorites.size();x++) {
			g1 =meteorites.get(x);
		 for(int j = 0;j<meteorites2.size();j++){
			 g2 = meteorites2.get(j);
			 if(g1.touches(g2)){
				  xt=g1.getVelocity().x;
	              yt=g1.getVelocity().y;
	             g1.getVelocity().x=g2.getVelocity().x;
	             g1.getVelocity().y=g2.getVelocity().y;
	             g2.getVelocity().x=xt;
	             g2.getVelocity().y=yt;
				 
			 }
	 }
	}
	}
	private void playermeteorite_collisioncheck() {
		for(GameObject<I> meteorite : meteorites) {
		if(player.touches(meteorite)) {
			energy--;
			playSound(collision);
			meteorite.getPos().x=Math.random()*300;
			meteorite.getPos().y=Math.random()*10;
	}
		}
		for(GameObject<I> meteorite2 : meteorites2) {
			if(player.touches(meteorite2)) {
				energy--;
				playSound(collision);
			    
				meteorite2.getPos().x=Math.random()*10;
				meteorite2.getPos().y=Math.random()*300;
		}
		}
		
			}
	private void levelcheck() {
		if(score==2 && meteorites2.size()==0 && meteorites.size()==3) {
			playSound(levelup);
			level++;
			for(int x =1;x<=2;x++) {
			 meteorites.add(new Meteorite<>(new Vertex(x*Math.random()*0,x*Math.random()*390)));
			 
			}
		     for(int x =1;x<=2;x++)
				    golds.add(new Gold<>(new Vertex(Math.random()*1200,Math.random()*600)));
		}
		if(score==4 && meteorites2.size()==0 && meteorites.size()==5) {
			playSound(levelup);
			
			level++;
			for(int x =1;x<=2;x++) { 
		     meteorites2.add(new Meteorite2<>(new Vertex(getWidth(),x*Math.random()*880)));
			}
		     for(int x =1;x<=3;x++)
				    golds.add(new Gold<>(new Vertex(Math.random()*1200,Math.random()*600)));
	}
		if(score==7 && meteorites2.size()==2 && meteorites.size()==5) {
			playSound(levelup);
			level++;
			for(int x =1;x<=2;x++) {
			 meteorites.add(new Meteorite<>(new Vertex(x*Math.random()*610,x*Math.random()*0)));
		     meteorites2.add(new Meteorite2<>(new Vertex(x*Math.random()*610,getHeight())));
			}
		     for(int x =1;x<=2;x++)
				    golds.add(new Gold<>(new Vertex(Math.random()*1200,Math.random()*600)));
		     energy++;
		     
	}
		if(score==9 && meteorites2.size()==4 && meteorites.size()==7) {
			playSound(levelup);
			level++;
		
			for(int x =1;x<=1;x++) {
		     meteorites2.add(new Meteorite2<>(new Vertex(x*Math.random()*580,x*Math.random()*0)));
			}
		     for(int x =1;x<=3;x++)
				    golds.add(new Gold<>(new Vertex(Math.random()*1200,Math.random()*600)));
	}
		
	}
	private void verlorencheck(){
		
		if(energy==0) {
		    hintergrund2.add(infoText);
		    hintergrund2.add(infoText2);
		    if(!isStopped())
		    playSound(lose);
		    this.pause();
		}
		    if(score==12) {
		    hintergrund2.add(infoText3);
		    hintergrund2.add(infoText2);
		    this.pause();
		    }
		   
		}
		
		

	private void collisioncheck2(){
		if(player.getPos().x<=0)
            spaceship.stopleft();
        if(player.getPos().x+player.getWidth()>=getWidth())
           spaceship.stopright();
        if(player.getPos().y<=0)
           spaceship.stopup();;
        if(player.getPos().y+player.getHeight()>=getHeight())
            spaceship.stopdown();
	
	}
	private void  goldcheck() {
		for(GameObject<I> gold : golds) {
			if(player.touches(gold)) {
				golds.remove(gold);
				score++;
				return;
	}
			
		}
	}
	
		
		
	
	
	 public void doChecks() {
		 collisioncheck2();
		 collision_meteoriteCheck();
		 collisionCheck();
		 playermeteorite_collisioncheck();
		 goldcheck();
		 verlorencheck();
		 levelcheck();
		    }
	 
	 public void keyPressedReaction(KeyCode keycode) {
		    if (keycode != null)
		      switch (keycode) {
		      case VK_A:
		        spaceship.moveleft();
		        break;
		      case VK_D:
		        spaceship.moveright();
		        break;
		      case VK_W:
		        spaceship.moveup();
		        break;
		      case VK_S:
		        spaceship.movedown();
		        break;
		        default:;
		      }
		    
	 
}
	 public void keyReleasedReaction(KeyCode keycode) {
		 if (keycode != null)
		      switch (keycode) {
		      case VK_A:
		        spaceship.stopleft();
		        break;
		      case VK_D:
		        spaceship.stopright();
		        break;
		      case VK_W:
		        spaceship.stopup();
		        break;
		      case VK_S:
		        spaceship.stopdown();
		        break;
		        default:;
		      }
		 
	 }


	 
	 
	 
	 
	 
}
