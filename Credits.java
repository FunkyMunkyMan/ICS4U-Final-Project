package strategictoastinsertion;

//Imports
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

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
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0,0);
//        Play.player.playerName = "GADREY";
        Play.player.score = 123456789;
        g.drawString((Play.player.playerName + ": " + Play.number.format(Play.player.score)), SettingUp.width/10, (SettingUp.height-(SettingUp.height/6)));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        
    }
    
}
