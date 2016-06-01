package strategictoastinsertion;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/*
 Creators: Matthew Godfrey, Seth Thomson, Jonah Monaghan
 Created: May 18th, 2016
 Purpose: player character class for the game "Strategic Toast Insertion"
 Version: 1.2
 */

public class Bird extends STIObject {
    static int score;
    int width, height;
    String birdShoot;
    static String playerName = "GADFREY";

    public Bird() {
        xPos = 25;
        yPos = 100;
        speed = 5;
        imageString = "res/images/oh noes.jpg";
        score = 0;
        width = 75;
        height = 75;
    }

    public Bird(String birdImage, String birdShoot, String birdScreech) {
        this();
        this.imageString = birdImage;
        this.birdShoot = birdShoot;
    }

    public Bird(String birdType, String birdShoot, int x, int y, int width, int height, String birdScreech) {
        this(birdType, birdShoot, birdScreech);
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

    public void moveUp() {
        if (yPos > 0) {
            yPos -= speed;
        }
    }

    public void moveDown() {
        if (yPos < Menu.height - height) {
            yPos += speed;
        }
    }
    
    public ToastBullet shoot() throws SlickException{
        ToastBullet bullet = new ToastBullet(xPos+Menu.birdWidth+1, yPos+5);
        return bullet;
    }
}