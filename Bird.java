package sti;
import org.newdawn.slick.*;
/*
Creators: Matthew Godfrey, Seth Thomson, Jonah Monaghan
Created: May 18th, 2016
Purpose: player character class for the game "Strategic Toast Insertion"
*/
public class Bird extends STIObject{
    int score;
    public Bird(){
        xPos = 25;
        yPos = 25;
        speed = 3;
        type = "null";
        imageString = "null";
        score = 0;
    }
    public Bird(String birdType){
        this();
        this.type = birdType;
    }
    public Bird(String birdType, int x, int y){
        this(birdType);
        xPos = x;
        yPos = y;
    }  
    
    public void moveUp(){
        yPos += speed;
    }
    
    public void moveDown(){
        yPos -= speed;
    }
    
    public void shoot(){
        
    }
    
    public void birth() throws SlickException{
        Play.playerIcon = new Image(type);
        Play.playerIcon.draw(xPos, yPos, 100, 100);
    }
}