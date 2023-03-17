package flappybird;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;

public class Ground {
	private BufferedImage groundImage; 
	
	private int x1,y1, x2, y2;
	
	public Ground() {
		//Parce que ".read" est �crit d'une mani�re qui est � l'�cart "try ... catch" est n�cessaire pour attraper la r�gle ici
		try {
			groundImage=ImageIO.read(new File("Assets/ground.png"));
		} catch (IOException e) {}
		x1=0;
		y1=500;
		x2=x1+830;
		y2=500; 
	}
	
	//appliquer pour mettre � jour les photos en continu
	public void Update() {
		x1-=2;
		x2-=2;
		if (x2<0) x1=x2+830; 
		if(x1<0)
			x2=x1+830;
	}
	//utilisez x1, y1, x2, y2 dans le but que lorsque la 1�re image est termin�e, la 2�me image sera affich�e et elle se r�p�tera tout le temps depuis lors l'image se d�placera en continu
	public void Paint(Graphics2D g2) {
		g2.drawImage(groundImage, x1, y1, null);
		g2.drawImage(groundImage, x2, y2, null);
	}
	
	public int getYGround() {
		return y1;
	}
}
