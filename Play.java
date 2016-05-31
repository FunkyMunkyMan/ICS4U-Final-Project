package strategictoastinsertion;
/*
 Creators: Matthew Godfrey, Seth Thomson, Jonah Monaghan
 Created: May 18th, 2016
 Purpose: The state for basic gameplay
 */

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Jonah Monaghan & Matthew Godfrey
 * @version 1.00
 */
public class Play extends BasicGameState {

    public Play(int state) {

    }

    @Override
    public int getID() {
        return 1;
    }
    /**
     *
     * @author Matthew Godfrey & Jonah Monaghan
     * @version 1.20
     */
    static Music themeSong, menuSong;
    static Image bg, playerIcon;
    Input input;
    static Bird player;
    Animation bird;
    int bgX1, bgX2, bgY;
    boolean arrayMade = false;
    static DecimalFormat number;
    static ArrayList<ToastBullet> bullets = new ArrayList();
    boolean fired = false;
    Image pic;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuSong = new Music("res/audio/thiefInTheNight.wav");
        themeSong = new Music("res/audio/portent.wav");
        number = new DecimalFormat("###,###");
        menuSong.loop();
        background();
         
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        if(Menu.currentTime % 3 == 0){
            player.score++;
        }
        bgX1 -= 2;
        bgX2 -= 2;
        bg.draw(bgX1, bgY);
        bg.draw(bgX2, bgY);
        if (bgX1 < -1000) {
            bgX1 = 996;
        } else if (bgX2 < -1000) {
            bgX2 = 996;
        }
        grphcs.drawString("Score: " + number.format(player.score), SettingUp.width - 200, 30);
        bird.draw(player.getxPos(), player.getyPos(), Menu.birdHeight, Menu.birdWidth);
        if(!fired){
            
        }else{
            player.shoot().draw((bullets.get(bullets.size()-1)).getxPos()+Menu.birdWidth, (bullets.get(bullets.size()-1)).getyPos()+5, Menu.birdHeight/2, Menu.birdWidth/2); 
        }
    }
    int counter = 0;

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Menu.currentTime = System.currentTimeMillis();
        if (arrayMade == false) {
            setBirdArray();
            System.out.println(player.imageString);
            System.out.println(player.birdShoot);
        }
        input = gc.getInput();
        if (!input.isKeyPressed(Input.KEY_R)) {
        } else {
            background();
        }
        bird.draw(player.getxPos(), player.getyPos());
        if (input.isKeyDown(Input.KEY_UP)) {
            player.moveUp();
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            player.moveDown();
        }
        if (input.isKeyDown(Input.KEY_SPACE)) {
            bird.setCurrentFrame(1);
            fired = true;  

        }else{
            bird.setCurrentFrame(0);
            fired = false;
        }
        
        counter++;
    }

    public void background() throws SlickException {
        String imagePath = "res/images/cave.png";
        switch (((int) ((Math.random() * 4) - 0))) {
            case 0:
                imagePath = "res/images/cave.png";
                break;
            case 1:
                imagePath = "res/images/sunset.png";
                break;
            case 2:
                imagePath = "res/images/starry.png";
                break;
            case 3:
                imagePath = "res/images/beach.png";
                break;
        }
        bg = new Image(imagePath);
        bgX1 = 0;
        bgX2 = 1000;
        bgY = 0;
    }

    public void setBirdArray() throws SlickException {
        arrayMade = true;
        themeSong.loop();
        Image[] images = {new Image(player.getImageString()), new Image(player.getBirdShoot())};
        int[] duration = {300, 300};
        bird = new Animation(images, duration, false);
    }

}