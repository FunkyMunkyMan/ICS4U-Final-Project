package strategictoastinsertion;

//Imports
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
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
    ArrayList<String> saves = new ArrayList();
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        bg = new Image("res/images/creditsScreen.png");
        start = true;
    }
    //Font font = new Font("Palatino Linotype", Font.BOLD, 32);
    //TrueTypeFont ttf = new TrueTypeFont(font,true);
    boolean start = false;
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        if(start){
            loadSaves();
            AscendingSorter.quickSort(saves);
            saves = AscendingSorter.getArray();
            start = false;
        }
        bg.draw(0,0);
        Play.player.score = 123456789;
        //ttf.drawString(32.0f, 32.0f, (Play.player.playerName + ": " + Play.number.format(Play.player.score)), Color.white);
        for(int ss = 0; ss < savesCounter; ss++){
        g.drawString( saves.get(ss), SettingUp.width/10, (SettingUp.height-(SettingUp.height/6)));
        }
    
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
    }
    
    int savesCounter = 0;
    public void loadSaves(){
        boolean eof = false;
        String n = "", s = "";
        try{
            FileReader file = new FileReader("res/save.txt");
            BufferedReader buff = new BufferedReader(file);
            while(!eof){
                n = buff.readLine();
                if(n == null){
                    eof = true;
                }else{
                    n += ": ";
                    s = buff.readLine();
                    savesCounter++;
                }
                saves.add(n+s);
            }
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
}
