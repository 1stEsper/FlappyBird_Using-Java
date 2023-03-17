package flappybird;

import pkg2dgamesframework.Objects;
import java.awt.*;

public class Chimney extends Objects {
	private Rectangle rect; 
	
	//déterminer si l'oiseau a volé à travers la cheminée ou non, si il est "false",  il n'a pas volé
	private boolean isBehindBird = false;
	
	public Chimney(int x, int y, int w, int h) {
		super(x, y, w, h);
		rect = new Rectangle(x, y, w, h);
	}
	
	public void update() {
		//set -2 pour définir la vitesse de déplacement de la cheminée, c'est aussi la vitesse du sol que nous avons définie auparavant (devrait donner 2 valeurs égales)
		setPosX(getPosX()-2);

		//mise à jour constante de l'emplacement de la cheminée
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
