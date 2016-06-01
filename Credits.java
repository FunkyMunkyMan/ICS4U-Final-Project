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
    static ArrayList<String> names = new ArrayList();
    static ArrayList<Integer> scores = new ArrayList();
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        bg = new Image("res/images/creditsScreen.png");
        start = true;
        Play.player.score = 1;
    }
    //Font font = new Font("Palatino Linotype", Font.BOLD, 32);
    //TrueTypeFont ttf = new TrueTypeFont(font,true);
    boolean start = false;
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0,0);
        //ttf.drawString(32.0f, 32.0f, (Play.player.playerName + ": " + Play.number.format(Play.player.score)), Color.white);
        for(int ss = 0; ss < savesCounter; ss++){
            g.drawString( names.get(ss)+scores.get(ss), SettingUp.width/10, ((SettingUp.height/6)+(ss*100)) );
        }
        g.drawString( Play.player.playerName+": "+Play.player.score, SettingUp.width/10, SettingUp.height-(2*(SettingUp.height/6)));
        if(prevScore == 0){}else{
               g.drawString("Previous: "+prevScore, SettingUp.width/10, SettingUp.height-(SettingUp.height/6)); 
        }
    }
    int  prevScore, savesCounter = 0;
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(start){
            loadSaves();
            prevScore = linearSearch(names, Play.player.playerName);
            Play.player.playerName+=": ";
            names.add(0,Play.player.playerName);
            scores.add(0,Play.player.score);
            AscendingSorter.quickSort(scores);
            scores = AscendingSorter.getArray();
            start = false;
        }
    }
    static int linearCounter = 0;
    public static int linearSearch(ArrayList<String> A, String x){
    //examine each element in the array
        for (int i = 0; i < A.size(); i++){
            linearCounter++;
            //is this the one we’re looking for?
            if((A.get(i)).equals(x)){
                return scores.get(i);
            }
        }
        return -1;
    }
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
                names.add(n);
                scores.add(Integer.parseInt(s));
            }
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
}
