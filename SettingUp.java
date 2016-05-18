package settingup;
import org.newdawn.slick.*;


public class SettingUp extends BasicGame {
 
    static int width = 640;
    static int height = 480;
   
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "Slick2D Basic Game Template";
    static int fpslimit = 40;

    public SettingUp(String title) {
        super(title);
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException {
       
    }
 
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
       
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
       
    }
   
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new SettingUp(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();
    }
   
}