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
        g.drawString(Play.number.format(Play.player.score), 5, 5);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        
    }
    
}
