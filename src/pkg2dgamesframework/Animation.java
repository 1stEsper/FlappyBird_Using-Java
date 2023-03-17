
package pkg2dgamesframework;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author DucMai
 */
public class Animation {
    
    private long beginTime = 0;
    
    private long mesure = 20;
    
    private AFrameOnImage[] frames;
    private int NumOfFrame = 0;
    private int CurrentFrame = 0;
    
    //Mesure dde tinh thoi gian chuyen hoat anh (theo mili second)
    public Animation(long mesure){
        this.mesure = mesure;
    }
    
    public void Update_Me(long deltaTime){
        if(NumOfFrame>0){
            if(deltaTime - beginTime > mesure){
                CurrentFrame++;
                if(CurrentFrame>=NumOfFrame) 
                    CurrentFrame = 0;
                beginTime = deltaTime;
            }
        }
    }
    public void AddFrame(AFrameOnImage sprite){
        AFrameOnImage[] bufferSprites = frames;
        frames = new AFrameOnImage[NumOfFrame+1];
        for(int i = 0;i<NumOfFrame;i++) frames[i] = bufferSprites[i];
        frames[NumOfFrame] = sprite;
        NumOfFrame++;
    }
    //rotation la chi huong cua con chim, neu =0 thi no giu nguyen huong, con neu = -1 thi no se huong dau len 
    public void PaintAnims(int x, int y, BufferedImage image, Graphics2D g2, int anchor, float rotation){
        frames[CurrentFrame].Paint(x, y, image, g2, anchor, rotation);
    }
}
