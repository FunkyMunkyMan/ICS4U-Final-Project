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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

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
    static Music themeSong, menuSong, creditsSong;
    Sound fireSound, explosionSound;
    static Image bg, playerIcon, dead, pew;
    Shape box;
    Input input;
    static Bird player;
    static Animation bird;
    int bgX1, bgX2, bgY;
    static boolean arrayMade = false;
    static DecimalFormat number;
    static ArrayList<ToastBullet> bullets = new ArrayList();
    boolean fired = false;
    static int bulletHeight = Menu.birdHeight / 2, bulletWidth = Menu.birdWidth / 2, difficulty = 0;
    TrueTypeFont ttf, ttfTwo;
    static boolean isAlive = true;
    static ArrayList<ToasterBlock> toasters = new ArrayList();
    static ArrayList<Shape> bulletCollision = new ArrayList();
    static ArrayList<Shape> toastersCollision = new ArrayList();
    int shapeX, shapeY, rndY, rndGen, toasterGen, percentChance = 1;
    static long deathTime = -1;
    static long fireTime = -1;
    static long stringDisplayTime = -1;
    Shape birdRect;
    int recentScore;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuSong = new Music("res/audio/thiefInTheNight.wav");
        themeSong = new Music("res/audio/portent.wav");
        creditsSong = new Music("res/audio/upbeatForever.wav");
        fireSound = new Sound("res/audio/bulletShot.wav");
        explosionSound = new Sound("res/audio/boom.wav");
        number = new DecimalFormat("###,###");
        Font font = new Font("Palatino Linotype", Font.BOLD, 26);
        Font fontTwo = new Font("Palatino LinoType", Font.BOLD, 22);
        ttf = new TrueTypeFont(font, true);
        ttfTwo = new TrueTypeFont(fontTwo, true);
        background();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        if (isAlive) {
            if (Menu.currentTime % 3 == 0) {
                player.score++;
            }
            bgX1 -= 2;
            bgX2 -= 2;
        }
        if (!isAlive) {
            player.moveDown();
        }
        bg.draw(bgX1, bgY);
        bg.draw(bgX2, bgY);
        if (bgX1 < -Menu.width) {
            bgX1 = Menu.width - 3;
        } else if (bgX2 < -Menu.width) {
            bgX2 = Menu.width - 3;
        }
        if (isAlive) {
            bird.draw(player.getxPos(), player.getyPos(), Menu.birdHeight, Menu.birdWidth);
        } else {
            dead.draw(player.getxPos(), player.getyPos(), Menu.birdHeight, Menu.birdWidth);
        }

        for (ToastBullet currentBullet : bullets) {
            pew = new Image(currentBullet.getImageString());
            pew.draw(currentBullet.getxPos(), currentBullet.getyPos(), bulletHeight, bulletWidth);
        }
        for (ToasterBlock toaster : toasters) {
            toaster.toasterImg.draw(toaster.xPos, toaster.yPos, Menu.birdHeight, Menu.birdWidth);
        }
        ttf.drawString(SettingUp.width - 200, 10, "Score: " + number.format(player.score));
        if(!(Menu.currentTime > stringDisplayTime + 500)){
             ttfTwo.drawString(SettingUp.width - 200, 40, " +" +  recentScore);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        input = gc.getInput();
        Menu.currentTime = System.currentTimeMillis();
        if (deathTime != -1) {
            if (Menu.currentTime > (deathTime + 3000)) {
                themeSong.stop();
                sbg.enterState(SettingUp.credits);
            }
        }

        if (arrayMade == false) {
            setBirdArray();
        }

        bird.draw(player.getxPos(), player.getyPos());

        if (input.isKeyDown(Input.KEY_UP)) {
            if (isAlive) {
                player.moveUp();
            }
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            if (isAlive) {
                player.moveDown();
            }
        }

        if (input.isKeyDown(Input.KEY_SPACE)) {
            if (isAlive) {
                bird.setCurrentFrame(1);
                if (Menu.currentTime > fireTime + 500) {
                    if (input.isKeyPressed(Input.KEY_SPACE)) {
                        ToastBullet temp = player.shoot();
                        fireSound.play();
                        bullets.add(temp);
                        box = temp.getShape();
                        bulletCollision.add(box);
                        fireTime = System.currentTimeMillis();
                    }
                }
            }
        } else {
            bird.setCurrentFrame(0);
        }

        for (int j = 0; j < bullets.size(); j++) {
            ToastBullet currentBullet = bullets.get(j);
            Shape currentShape = bulletCollision.get(bullets.indexOf(currentBullet));
            if (isAlive) {
                currentBullet.move();
                currentShape.setLocation(currentBullet.getxPos(), currentBullet.getyPos());
                if (currentBullet.getxPos() > Menu.width) {
                    bullets.remove(currentBullet);
                    bulletCollision.remove(currentShape);
                }
            } else {
                bullets.remove(currentBullet);
                bulletCollision.remove(currentShape);
            }
        }

        if (isAlive) {
            for (int j = 0; j < toasters.size(); j++) {
                ToasterBlock currentToaster = toasters.get(j);
                Shape currentShape = toastersCollision.get(toasters.indexOf(currentToaster));
                if (isAlive) {
                    currentToaster.move();
                    currentShape.setLocation(currentToaster.xPos, currentToaster.yPos);
                }
                if (currentToaster.xPos < 0 - (Menu.birdWidth * 2)) {
                    toasters.remove(currentToaster);
                    toastersCollision.remove(currentShape);
                }
                if (birdRect.intersects(currentShape)) {
                    player.die();
                    Credits.start = true;
                    deathTime = System.currentTimeMillis();
                }
                for (int g = 0; g < bulletCollision.size(); g++) {
                    System.out.println(bulletCollision.size());
                    Shape currentBullet = bulletCollision.get(g);
                    if (currentBullet.intersects(currentShape)) {
                        currentToaster.damage();
                        bullets.remove(bulletCollision.indexOf(currentBullet));
                        bulletCollision.remove(currentBullet);
                        if (currentToaster.getHealth() <= 0) {
                            recentScore = currentToaster.getScoreToAdd();
                            player.score += recentScore;
                            toasters.remove(currentToaster);
                            toastersCollision.remove(currentShape);
                            explosionSound.play();
                            stringDisplayTime = System.currentTimeMillis();
                        }

                    }
                }

            }
            rndGen = (int) (Math.random() * 99) + 1;
            if (rndGen <= percentChance) {
                rndY = (((int) (Math.random() * 6) + 0) * Menu.birdWidth) + 5;
                if (difficulty < 50) {
                    toasters.add(new ToasterBlock(Menu.width - 10, rndY, 1));
                    toastersCollision.add(new Rectangle(Menu.width - 10, rndY, Menu.birdWidth, Menu.birdHeight));
                    difficulty++;
                } else if (difficulty > 50 && difficulty < 100) {
                    toasterGen = (int) (Math.random() * 2) + 1;
                    toasters.add(new ToasterBlock(Menu.width - 10, rndY, toasterGen));
                    toastersCollision.add(new Rectangle(Menu.width - 10, rndY, Menu.birdWidth, Menu.birdHeight));
                    difficulty++;
                } else if (difficulty > 100 && difficulty < 150) {
                    toasterGen = (int) (Math.random() * 3) + 1;
                    toasters.add(new ToasterBlock(Menu.width - 10, rndY, toasterGen));
                    toastersCollision.add(new Rectangle(Menu.width - 10, rndY, Menu.birdWidth, Menu.birdHeight));
                    difficulty++;
                } else if (difficulty > 150 && difficulty < 200) {
                    toasterGen = (int) (Math.random() * 2) + 2;
                    toasters.add(new ToasterBlock(Menu.width - 10, rndY, toasterGen));
                    toastersCollision.add(new Rectangle(Menu.width - 10, rndY, Menu.birdWidth, Menu.birdHeight));
                    difficulty++;
                } else if (difficulty > 200) {
                    toasterGen = (int) (Math.random() * 1) + 3;
                    toasters.add(new ToasterBlock(Menu.width - 10, rndY, toasterGen));
                    toastersCollision.add(new Rectangle(Menu.width - 10, rndY, Menu.birdWidth, Menu.birdHeight));
                    difficulty++;
                }
            }
        }
        if (difficulty % 50 == 0) {
            difficulty++;
            ToasterBlock.accelerate();
        }

        if (difficulty == 150) {
            percentChance++;
        }

        if (!input.isKeyPressed(Input.KEY_R)) {
        } else {
            background();
        }

        shapeX = player.getxPos();
        shapeY = player.getyPos();
        birdRect.setLocation(shapeX, shapeY);
        
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
        dead = new Image(player.getBirdDead());
        Image[] images = {new Image(player.getImageString()), new Image(player.getBirdShoot()), new Image(player.getBirdDead())};
        int[] duration = {300, 300, 300};
        bird = new Animation(images, duration, false);
        birdRect = new Rectangle(player.getxPos(), player.getyPos(), player.getWidth(), player.getHeight());
    }

}