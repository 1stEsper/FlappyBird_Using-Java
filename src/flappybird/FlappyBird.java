package flappybird;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

public class FlappyBird extends GameScreen {
	private BufferedImage birds;
	private Animation bird_anim;
	
	private BufferedImage chimney; 
	
	public static float g=0.15f; // créer un accéléromètre
	
	private Bird bird; 
	private Ground ground; 
	
	private ChimneyGroup chimneyGroup; 
	
	private int Point=0; 
	
	
	private int BEGIN_SCREEN=0; 
	private int GAMEPLAY_SCREEN = 1;
	private int GAMEOVER_SCREEN =2;
	
	private int CurrentScreen = BEGIN_SCREEN; 
	
	public FlappyBird() {
		super(800,600); 
		try {
			birds =ImageIO.read(new File("Assets/bird_sprite.png"));
		}catch(IOException ex) {}
		
		bird_anim=new Animation(100); 
		AFrameOnImage f; 
		f =new AFrameOnImage(0, 0, 60, 60);
		bird_anim.AddFrame(f); 
		f =new AFrameOnImage(60, 0, 60, 60);
		bird_anim.AddFrame(f); 
		f =new AFrameOnImage(120, 0, 60, 60);
		bird_anim.AddFrame(f); 
		f =new AFrameOnImage(60, 0, 60, 60);
		bird_anim.AddFrame(f); 
		
		bird =new Bird(350,250,50,50);
		ground =new Ground(); 
		
		chimneyGroup = new ChimneyGroup();
		
		BeginGame();
	}
	public static void main(String[] args) {
		new FlappyBird(); 
	}
	
	//réinitialiser les coordonnées de l'oiseau à l'original
	private void resetGame() {
		bird.setPos(350, 250);
		bird.setVt(0);
		bird.setLive(true);
		Point=0;
		chimneyGroup.resetChimneys();
	}

	@Override
	public void GAME_UPDATE(long deltaTime) {
		if(CurrentScreen==BEGIN_SCREEN) {
			resetGame(); 
		}
		else if(CurrentScreen==GAMEPLAY_SCREEN) {
			if(bird.getLive())
				bird_anim.Update_Me(deltaTime); 
			bird.update(deltaTime);
			ground.Update();
			
			chimneyGroup.update(); 
			
			for(int i=0; i<ChimneyGroup.SIZE; i++) {
				if(bird.getRect().intersects(chimneyGroup.getChimney(i).getRect()))
					bird.setLive(false);
					
			}
			
			for(int i=0; i<ChimneyGroup.SIZE; i++) {
				//i%2==0 ici viser à n'augmenter que de 1 point lorsque l'oiseau survole une paire de cheminées, 
				//sinon, chaque fois que vous volerez à travers une paire de cheminées, cela augmentera de 2 points
				if(bird.getPosX() > chimneyGroup.getChimney(i).getPosX() && !chimneyGroup.getChimney(i).getIsBehindBird()&& i%2==0) {
					Point++; 
					chimneyGroup.getChimney(i).setIsBehindBird(true); 
				}
					
					
			}
			
			//vérifier la position de l'oiseau s'il touche le sol imprimera l'écran "Game Over + Point"
			if(bird.getPosY() + bird.getH() > ground.getYGround())
				CurrentScreen = GAMEOVER_SCREEN;
			
		}
		else {
			
		}
		
		
		
	}
	
	//Cette méthode pour dessiner des objets sur l'écran de jeu
	@Override
	public void GAME_PAINT(Graphics2D g2) {
		g2.setColor(Color.decode("#b8daef"));
		g2.fillRect(0,0,MASTER_WIDTH,MASTER_HEIGHT);		
		//Dessiner la cheminée
		chimneyGroup.paint(g2);
		
		//Dessiner le sol 
		ground.Paint(g2);
		
		
		if(bird.getIsFlying())
			bird_anim.PaintAnims((int)bird.getPosX(), (int)bird.getPosY(), birds, g2, 0, -1);
		else 
			bird_anim.PaintAnims((int)bird.getPosX(), (int)bird.getPosY(), birds, g2, 0, 0);
		
		
		if(CurrentScreen==BEGIN_SCREEN) {
			g2.setColor(Color.red);
			g2.drawString("Press space to play game ", 200,300); 
		}
		if (CurrentScreen==GAMEOVER_SCREEN) {
			g2.setColor(Color.red);
			g2.setFont((new Font("Arial", Font.BOLD, 20)));
			g2.drawString("GAME OVER!!",  350,220);
			g2.drawString("POINTS : "+Point,370,240);
			g2.drawString("Press space turn back begin screen ", 250,290); 
		}
		
		g2.setColor(Color.black);
		g2.drawString("Point: "+Point,20,50); 
		
	}

	@Override
	public void KEY_ACTION(KeyEvent e, int Event) {
		if(Event ==KEY_PRESSED) {
			if(CurrentScreen==BEGIN_SCREEN) {
				CurrentScreen = GAMEPLAY_SCREEN;
			}
			else if (CurrentScreen==GAMEPLAY_SCREEN) {
				if(bird.getLive())
					bird.fly();  
			}
			else if (CurrentScreen==GAMEOVER_SCREEN){
				CurrentScreen = BEGIN_SCREEN;
			}
			
		}
	}

}
