package settingup;

//Imports
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * 
 * @author Jonah Monaghan
 * @version 1.01
 */
public class SettingUp extends StateBasedGame {
 
    static int width = 640;
    static int height = 480;
   
    static boolean fullscreen = false;
    static boolean showFPS = true;
    
    static final String title = "Strategic Toast Insertion (STI)";
    static final int fpslimit = 60;
    static final int menu = 0;
    static final int play = 1;
    static final int credits = 2;
    
    public SettingUp(String title) {
        super(title);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new Menu(credits));
    }
 
     @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(credits).init(gc, this);
        
        this.enterState(menu);
    }
   
    public static void main(String[] args) throws SlickException {
      
        AppGameContainer app; 
        try{
            app = new AppGameContainer(new SettingUp(title));
            app.setDisplayMode(width, height, fullscreen);
            app.setSmoothDeltas(true);
            app.setTargetFrameRate(fpslimit);
            app.setShowFPS(showFPS);
            app.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

   
   
}
