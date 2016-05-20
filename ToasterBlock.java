/*
Creators: Matthew Godfrey, Seth Thomson, Jonah Monaghan
Created: May 18th, 2016
Purpose: box class for the game "STI"
*/
public class ToasterBlock {
    int health, hits, xPos, yPos, CURRENT_SPEED, MAX_SPEED;
    String toasterType, imageName;
//constructors
    public ToasterBlock(String t, String i){
        toasterType = t;
        imageName = i;
    }
    public ToasterBlock(int health, int hits, int xPos, int yPos, String toasterType, String imageName) {
        this.health = health;
        this.hits = hits;
        this.xPos = xPos;
        this.yPos = yPos;
        this.toasterType = toasterType;
        this.imageName = imageName;
    }
//getters and setters for instance variables
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHits() {
        return hits;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }
    public int getxPos() {
        return xPos;
    }
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
    public int getyPos() {
        return yPos;
    }
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
    public String getToasterType() {
        return toasterType;
    }
    public void setToasterType(String toasterType) {
        this.toasterType = toasterType;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
//getters and setters for class variables
    public int getCURRENT_SPEED() {
        return CURRENT_SPEED;
    }
    public void setCURRENT_SPEED(int CURRENT_SPEED) {
        this.CURRENT_SPEED = CURRENT_SPEED;
    }
    public int getMAX_SPEED() {
        return MAX_SPEED;
    }
    public void setMAX_SPEED(int MAX_SPEED) {
        this.MAX_SPEED = MAX_SPEED;
    }
//removal of toaster-block from game
    public void destroy(){
        
    }
//collision detector for the player character
    public void birdCollide(){
        
    }
//collision detector for a toast-bullet
    public void toastCollide(){
        
    }
//movement speed changer
    public void accelerate(){
        
    }
}