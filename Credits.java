package strategictoastinsertion;

//Imports
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.awt.Font;
/**
 *
 * @author Jonah Monaghan
 * @version 1.00
 */
public class Credits extends BasicGameState {

    public Credits(int state){
        
    }
    
    
    @Override
    public int getID() {
        return 2;
    }
    Image bg;
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        bg = new Image("res/images/creditsScreen.png");
    }
    //Font font = new Font("Palatino Linotype", Font.BOLD, 32);
    //TrueTypeFont ttf = new TrueTypeFont(font,true);
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0,0);
        Play.player.score = 123456789;
        //ttf.drawString(32.0f, 32.0f, (Play.player.playerName + ": " + Play.number.format(Play.player.score)), Color.white);
        g.drawString( (Play.player.playerName + ": " + Play.number.format(Play.player.score)), SettingUp.width/10, (SettingUp.height-(SettingUp.height/6)));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        
    }
    
}
