package flappybird;

import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

import java.awt.*;

public class Bird extends Objects {
	////vitesse de chute actuel de l'oiseau vt=0, vt >0 l'oiseau descend et vt<0 l'oiseau monte
	private float vt=0; 
	
	private boolean isFlying = false; 
	
	//"Rectangle" peut v�rifier l'intersection des objets dans le jeu
	private Rectangle rect; 
	
	private boolean isLive =true; 
	
	private SoundPlayer flapSound, bupSound, getMoneySound;
	
	
	//Obtenir les coordonn�es de l'oiseau
	public Bird(int x, int y, int w, int h) {
		super(x,y,w,h);
		rect =new Rectangle(x, y, w, h); 
	}
	
	public void setLive(boolean b) {
		isLive = b; 
	}
	
	public boolean getLive() {
		return isLive;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	//ajoutez cette m�thode pour que la m�thode resetGame de la classe FlappyBird puisse se mettre � jour � la position d'origine = 0
	public void setVt(float vt) {
		this.vt=vt; 
	}
	
	//A chaque mise � jour, cette vitesse augmente de 1 valeur �gale � la variable "g" de la class FlappyBird
	public void update(long deltaTime) {
		//puisque la vitesse de chute a une acc�l�ration, elle doit changer avec le temps
		vt+=FlappyBird.g;
		//donc la position doit aussi augmenter de "vt"
		this.setPosY(this.getPosY()+vt);
		
		//Mettre � jour l'emplacement "rect" de l'oiseau en continu
		this.rect.setLocation((int)this.getPosX(), (int)this.getPosY());
		
		//Lorsque la vitesse vt<0, elle monte ~vole et vice versa
		if(vt<0) isFlying = true; 
		else isFlying = false;
		
	}
	
	public void fly() {
		//montera d'abord, puis ralentira � cause de la force de gravit� "g"
		vt= -3; 
		
	}
	
	//cette m�thode pour v�rifier que l'oiseau vole ou non?
	public boolean getIsFlying() {
		return isFlying;
	}

}
