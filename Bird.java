
package strategictoastinsertion;
import org.newdawn.slick.*;
/*
Creators: Matthew Godfrey, Seth Thomson, Jonah Monaghan
Created: May 18th, 2016
Purpose: player character class for the game "Strategic Toast Insertion"
Version: 1.2
*/
public class Bird extends STIObject{
    int score, width, height;
    String birdShoot;
    public Bird(){
        xPos = 100;
        yPos = 100;
        speed = 3;
        imageString = "res/images/oh noes.jpg";
        score = 0;
        width = 75;
        height = 75;
    }
    public Bird(String birdImage, String birdShoot){
        this();
        this.imageString = birdImage;
        this.birdShoot = birdShoot;
    }
    public Bird(String birdType, String birdShoot, int x, int y, int width, int height){
        this(birdType, birdShoot);
        System.out.println(this.imageString);
        xPos = x;
        yPos = y;
        this.width = width;
        this.height = height;
    }  

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBirdShoot() {
        return birdShoot;
    }

    public void setBirdShoot(String birdShoot) {
        this.birdShoot = birdShoot;
    }
    
    public void moveUp(){
        yPos -= speed;
    }
    
    public void moveDown(){
        yPos += speed;
    }
    
    public void shoot(){
        
    }
}