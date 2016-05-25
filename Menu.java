package sti;

//Imports
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.io.*;
/**
 *
 * @author Jonah Monaghan
 * @version 1.00
 */
public class Menu extends BasicGameState {

    public Menu(int state){
        
    }
    
    @Override
    public int getID() {
        return 0;
    }
    Image bg, options, optionsHover, playNow, playNowHover;
    Image[] birds = new Image[7];
    Image[] birdsHover = new Image[7];
    Image[] animations;
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        bg = new Image("resources/images/menu.png");
        options = new Image("resources/images/options.png");
        optionsHover = new Image("resources/images/optionsHighlighted.png");
        playNow = new Image("resources/images/play.png");
        playNowHover = new Image("resources/images/playHighlighted.png");
        try{
            FileReader fr = new FileReader("resources/birdFiles.txt");
            BufferedReader br = new BufferedReader(fr);
            for(int l = 0; l < 7; l++){
                birds[l] = new Image(br.readLine());
                birdsHover[l] = new Image(br.readLine());
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    float[][] coords = new float[7][7]; 
    public void getCoordinates(){
        coords[0][0] = 350;
        coords[0][1] = 155;
        
        coords[1][0] = 450;
        coords[1][1] = 135;
        
        coords[2][0] = 550;
        coords[2][1] = 155;
        //
        coords[3][0] = 330;
        coords[3][1] = 255;
        
        coords[4][0] = 555;
        coords[4][1] = 255;
        //
        coords[5][0] = 375;
        coords[5][1] = 350;
        
        coords[6][0] = 500;
        coords[6][1] = 350;
    }
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Play.themeSong.pause();
        getCoordinates();
        bg.draw(0,0);
        playNow.drawCentered(500, 300);
        for(int j = 0; j < 7; j++){
            birds[j].draw(coords[j][0], coords[j][1], 100, 100);
        }
        
    }
    Input input;
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        input = gc.getInput();
        
    }
    
}
