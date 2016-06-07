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
    Image bg, backButton, hover;
    String prevScore; 
    int savesCounter = 0, mouseX, mouseY, scoreSearch = 0;
    Input input;
    static ArrayList<String> names = new ArrayList();
    static ArrayList<Integer> scores = new ArrayList();
    static boolean start = false;
    static int linearCounter = 0;
    static File file;
    //Font font = new Font("Palatino Linotype", Font.BOLD, 32);
    //TrueTypeFont ttf = new TrueTypeFont(font,true);
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        file = new File("res/save.txt");
        bg = new Image("res/images/creditsScreen.png");
        backButton = new Image("res/images/back.png");
        hover = new Image("res/images/resolutionHighlight.png"); 
        Play.player.playerName = "GADFREY";
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0,0);
        //ttf.drawString(32.0f, 32.0f, (Play.player.playerName + ": " + Play.number.format(Play.player.score)), Color.white);
        g.drawString( Play.player.playerName+": "+Play.player.score, SettingUp.width/10, SettingUp.height-(2*(SettingUp.height/6))+5);
        for(int ss = 0; ss < 5; ss++){
            g.drawString( (names.get(ss)+": "+scores.get(ss)), SettingUp.width/10, ((SettingUp.height/6)+(ss*50)) );
        }
        g.drawString(prevScore, SettingUp.width/10, SettingUp.height-(SettingUp.height/6));//previous score drawing on screen
        
        if(mouseX < 200 && mouseY < 100){
            backButton.draw(0,0);
            hover.draw(0,0);
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) == true) {
                file.delete();
                reload();
                sbg.enterState(SettingUp.menu);
            }
        }else{ 
            backButton.draw(0,0);
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(start){
            loadSaves();
            scoreSearch = linearSearch(names, Play.player.playerName);
            names.add(Play.player.playerName); 
            scores.add(Play.player.score);
            savesCounter++;
            if(scoreSearch == -1){
                prevScore = "No previous scores found";
            }else{
                prevScore = "Previous Score: " + scores.get(scoreSearch);
            }
            System.out.println(prevScore);
            AscendingSorter.quickSort(scores);
            scores = AscendingSorter.getArray();
            start = false;
        }
        input = gc.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();
        
    }
    
    public void reload(){
        rewrite();
        Play.isAlive = true;
        Play.deathTime = -1;
        Play.arrayMade = false;
        Play.toasters = new ArrayList();
        Play.player.playerName = "GADFREY";
        Play.difficulty = 0;
        Play.player.score = 0;
        names = new ArrayList();
        scores = new ArrayList();
        start = true;
    }
    public static int linearSearch(ArrayList<String> A, String x){
        for (int i = 0; i < A.size(); i++){
            linearCounter++;
            if(((A.get(i)).equals(x))) return i;
        }
        return -1;
    }
    public void loadSaves(){
        boolean eof = false;
        String n, s;
        try{
            BufferedReader buff = new BufferedReader(new FileReader("res/save.txt"));
            while(!eof){
                n = buff.readLine();
                if(n == null){
                    eof = true;
                }else{
                    s = buff.readLine();
                    names.add(n);
                    scores.add(Integer.parseInt(s));
                    savesCounter++;
                }
                
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
/*Cutten
777
Fowler
666
Thomson
555
GADFREY
452
Oehler
444
Stephenson
333
Baird
222
Monaghan
111*/
    public void rewrite(){
        try(PrintWriter writer = new PrintWriter(file)){
            for(int wr = 0; wr < names.size(); wr++){
                writer.println(names.get(wr));
                System.out.println(names.get(wr));
                writer.println(scores.get(wr));
                System.out.println(scores.get(wr));
            }
            writer.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
