package strategictoastinsertion;

//Imports
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.io.*;
import org.newdawn.slick.Font;
import org.newdawn.slick.gui.TextField;
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
    String birdSelected;
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//        Play.themeSong.pause();
        bg = new Image("res/images/menu.png");
        options = new Image("res/images/options.png");
        optionsHover = new Image("res/images/optionsHighlighted.png");
        playNow = new Image("res/images/play.png");
        playNowHover = new Image("res/images/playHighlighted.png");
        try{
            FileReader fr = new FileReader("res/birdFiles.txt");
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
    Input input;
    String birdShoot;
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Play.themeSong.pause();
        getCoordinates();
        bg.draw(0,0);
        for(int j = 0; j < 7; j++){
            input = gc.getInput();
            if((input.getMouseX() > coords[j][0] && input.getMouseX() < (coords[j][0]+100)) 
                    && (input.getMouseY() > coords[j][1] && input.getMouseY() < (coords[j][1] + 100))){
                birdsHover[j].draw(coords[j][0], coords[j][1], 100, 100);
                birdSelected = birds[j].getResourceReference();
                birdShoot = birdsHover[j].getResourceReference();
            }else{
                birds[j].draw(coords[j][0], coords[j][1], 100, 100);
            }
        }
        
        if(input.getMouseX() > 750 && input.getMouseY() > 400){
            optionsHover.draw(750, 400);
            playNow.drawCentered(500, 300);
        }else if( (input.getMouseX() > 450 && input.getMouseX() < 550) && (input.getMouseY() > 250 && input.getMouseY() < 350) ){
            playNowHover.drawCentered(500, 300);
            options.draw(750, 400);
            if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
                Play.player = new Bird(birdSelected, birdShoot, 250, 10);
                sbg.enterState(SettingUp.play);
                Play.themeSong.loop();
            }
        }else{
            options.draw(750, 400);
            playNow.drawCentered(500, 300);
        }
        
        
    }
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
    
    }
    
}
