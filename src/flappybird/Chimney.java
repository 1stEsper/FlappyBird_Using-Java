package flappybird;

import pkg2dgamesframework.Objects;
import java.awt.*;

public class Chimney extends Objects {
	private Rectangle rect; 
	
	//d�terminer si l'oiseau a vol� � travers la chemin�e ou non, si il est "false",  il n'a pas vol�
	private boolean isBehindBird = false;
	
	public Chimney(int x, int y, int w, int h) {
		super(x, y, w, h);
		rect = new Rectangle(x, y, w, h);
	}
	
	public void update() {
		//set -2 pour d�finir la vitesse de d�placement de la chemin�e, c'est aussi la vitesse du sol que nous avons d�finie auparavant (devrait donner 2 valeurs �gales)
		setPosX(getPosX()-2);

		//mise � jour constante de l'emplacement de la chemin�e
		rect.setLocation((int)this.getPosX(), (int)this.getPosY()); 
		
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void setIsBehindBird(boolean b) {
		isBehindBird = b; 
	}
	public boolean getIsBehindBird() {
		return isBehindBird; 
	}

}
