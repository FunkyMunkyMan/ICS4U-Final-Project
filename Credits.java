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

    public Credits(int state) {

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
    boolean creditsSoundPlayed = false;
    static int linearCounter = 0;
    static File file;
    TrueTypeFont ttf;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        file = new File("res/save.txt");
        bg = new Image("res/images/creditsScreen.png");
        backButton = new Image("res/images/back.png");
        hover = new Image("res/images/resolutionHighlight.png");
        Font font = new Font("Palatino Linotype", Font.PLAIN , 28);
        ttf = new TrueTypeFont(font, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0, 0);
        ttf.drawString((SettingUp.width / 5), (SettingUp.height - (2 * (SettingUp.height / 6))), (Play.player.playerName + ": " + Play.number.format(Play.player.score)), Color.white);
        for (int ss = 0; ss < 5; ss++) {
            ttf.drawString(SettingUp.width / 5, ((SettingUp.height / 6) + (ss * 55)), (names.get(ss) + ": " + Play.number.format(scores.get(ss))), Color.white);
        }
        ttf.drawString(SettingUp.width / 5, SettingUp.height - (SettingUp.height / 6), prevScore, Color.white);
        if (mouseX < 200 && mouseY < 100) {
            backButton.draw(0, 0);
            hover.draw(0, 0);
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) == true) {
                file.delete();
                reload();
                Menu.menuSoundPlayed = false;
                sbg.enterState(SettingUp.menu);
            }
        } else {
            backButton.draw(0, 0);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
         if (!creditsSoundPlayed) {
            playSound();
        }
        if (start) {
            loadSaves();
            scoreSearch = linearSearch(names, Play.player.playerName);
            names.add(Play.player.playerName);
            scores.add(Play.player.score);
            savesCounter++;
            if (scoreSearch == -1) {
                prevScore = "No previous scores found";
            } else {
                prevScore = "Previous Score: " + scores.get(scoreSearch);
            }
            AscendingSorter.quickSort(scores);
            scores = AscendingSorter.getArray();
            start = false;
        }

       

        input = gc.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();

    }

    public void reload() {
        rewrite();
        Play.isAlive = true;
        Play.deathTime = -1;
        Play.arrayMade = false;
        Play.toasters = new ArrayList();
        Play.difficulty = 0;
        Play.player.score = 0;
        Play.player.setxPos(25);
        Play.player.setyPos(100);
        ToasterBlock.CURRENT_SPEED = 2;
        names = new ArrayList();
        scores = new ArrayList();
        start = true;
    }

    public static int linearSearch(ArrayList<String> A, String x) {
        for (int i = 0; i < A.size(); i++) {
            linearCounter++;
            if (((A.get(i)).equals(x))) {
                return i;
            }
        }
        return -1;
    }

    public void loadSaves() {
        boolean eof = false;
        String n, s;
        try {
            BufferedReader buff = new BufferedReader(new FileReader("res/save.txt"));
            while (!eof) {
                n = buff.readLine();
                if (n == null) {
                    eof = true;
                } else {
                    s = buff.readLine();
                    names.add(n);
                    scores.add(Integer.parseInt(s));
                    savesCounter++;
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void rewrite() {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (int wr = 0; wr < names.size(); wr++) {
                writer.println(names.get(wr));
                writer.println(scores.get(wr));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        Play.creditsSong.loop();
        creditsSoundPlayed = true;
    }

}