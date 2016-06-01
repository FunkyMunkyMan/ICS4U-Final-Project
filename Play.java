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
import java.awt.Font;
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
    static Image pew;
    TrueTypeFont ttf;
    ArrayList<ToasterBlock> toasters = new ArrayList();
    int difficulty = 0, toasterGen, rndGen, rndY, percentChance = 1;
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuSong = new Music("res/audio/thiefInTheNight.wav");
        themeSong = new Music("res/audio/portent.wav");
        number = new DecimalFormat("###,###");
        //Font font = new Font("Palatino Linotype", Font.BOLD, 26);
        //ttf = new TrueTypeFont(font, true;
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
        //ttf.drawString("Score: "+number.format(player.socre), SettingUp.width - 200, 30);
        bird.draw(player.getxPos(), player.getyPos(), Menu.birdHeight, Menu.birdWidth);
        bulletHeight = Menu.birdHeight/2;
        bulletWidth = Menu.birdWidth/2;
            for(int q = 0; q < (bullets.size()); q++){
                pew = new Image(bullets.get(q).getImageString());
                bullets.get(q).move();
                pew.draw( ((bullets.get(q)).getxPos()), ((bullets.get(q)).getyPos()), bulletHeight, bulletWidth );
                if(bullets.get(q).getxPos() > Menu.width){
                    bullets.remove(q);
                }
            }
        for (ToasterBlock currentToaster : toasters) {
            currentToaster.toasterImg.draw(currentToaster.xPos, currentToaster.yPos, Menu.birdHeight, Menu.birdWidth);
        }
        
        
    }
    int counter = 0, bulletHeight, bulletWidth;
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Menu.currentTime = System.currentTimeMillis();
        rndGen = (int) (Math.random() * 99) + 1;

        if (rndGen <= percentChance) {
            rndY = (((int) (Math.random() * 5) + 1) * Menu.birdWidth) + 5;
            if (difficulty < 50) {
                toasters.add(new ToasterBlock(Menu.width - 10, rndY, 1));
                difficulty++;
            } else if (difficulty > 50 && difficulty < 100) {
                toasterGen = (int)(Math.random() * 2) + 1;
                toasters.add(new ToasterBlock(Menu.width - 10, rndY, toasterGen));
                difficulty++;
            } else if(difficulty > 100 && difficulty < 150){
                toasterGen = (int)(Math.random() * 3) + 1;
                toasters.add(new ToasterBlock(Menu.width - 10, rndY, toasterGen));
                difficulty++;
            }else if(difficulty > 150 && difficulty < 200){
                toasterGen = (int)(Math.random() * 2) + 2;
                toasters.add(new ToasterBlock(Menu.width - 10, rndY, toasterGen));
                difficulty++;
            }else if(difficulty > 200 && difficulty < 250){
                toasterGen = (int)(Math.random() * 1) + 3;
                toasters.add(new ToasterBlock(Menu.width - 10, rndY, toasterGen));
                difficulty++;
            }
        }
        
        if(difficulty % 50 == 0){
        difficulty++;
        ToasterBlock.accelerate();
        }
        
        if(difficulty == 150){
            percentChance++;
        }
        
        for (ToasterBlock currentToaster : toasters) {
            if (currentToaster.xPos > Menu.width) {
                toasters.remove(currentToaster);
            }
            currentToaster.move();
        }
        if (arrayMade == false) {
            setBirdArray();
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
            if (input.isKeyPressed(Input.KEY_SPACE)) {
                bullets.add(player.shoot());
            }
        }else{
            bird.setCurrentFrame(0);
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