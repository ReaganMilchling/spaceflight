import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class spaceflight extends PApplet {

/*
TO DO
highscores
*/

//initialize objects
Asteroid asteroids[] = new Asteroid[16];
Coin coins[] = new Coin[8];
Star stars[] = new Star[80];
Spaceship spaceship;

int livesLeft;
int playerScore;
int globalScore;
int gameState;
int ptsScore;

public void setup() {
  //fullScreen();
  
  
  //object setup
  for (int i = 0; i < asteroids.length; i++) {
   asteroids[i] = new Asteroid(); 
  }
  for (int i = 0; i < coins.length; i++) {
   coins[i] = new Coin(); 
  }
  for (int i = 0; i < stars.length; i++) {
   stars[i] = new Star(); 
  }
  spaceship = new Spaceship();
  
  livesLeft = 3;
  playerScore = 0;
  gameState = 0;
  ptsScore = 15;
}

public void draw() {
  background(0);
  
  if (gameState == 0) {
   drawWelcome(); 
  }
  
  if (gameState == 1) {
    drawObjects();
    hitBoxes();
    showScores();
    gameOver();
    livesLeft();
    
    println(livesLeft);
    println(playerScore);
  }
  
  if (gameState == 2) {
    endGameScreen();
  }
}

public void keyPressed() {
  if (key == ' ' && gameState == 0){
   gameState = 1;  
  }
  if (key == ' ' && gameState == 2){
   gameState = 0;
   livesLeft = 3;
   playerScore = 0;
   for (int i = 0; i < coins.length; i++) {
      coins[i].coiny = -(PApplet.parseInt(random(height)));
      coins[i].coinx = PApplet.parseInt(random(width));
   }
   for (int i = 0; i < asteroids.length; i++) {
      asteroids[i].asteroidy = -(PApplet.parseInt(random(height)));
      asteroids[i].asteroidx = PApplet.parseInt(random(width));
   }
   spaceship.x = width / 2 - width / 40;
  }
  if (key == 'a') {
    spaceship.spaceshipMoveLeft = true;
  }
  if (key == 'd') {
    spaceship.spaceshipMoveRight = true; 
  }
}

public void keyReleased() {
  if (key == 'a') {
    spaceship.spaceshipMoveLeft = false;
  }
  if (key == 'd') {
   spaceship.spaceshipMoveRight = false; 
  }
}
public void hitBoxes() {
  //Spaceship vertical hitbox
  for (int i = 0; i < coins.length; i++) {  
    if (coins[i].coinx > spaceship.x - 18) {  
      if (coins[i].coinx < spaceship.x + 18) {  
        if (coins[i].coiny > spaceship.y) {
         playerScore += ptsScore;
          coins[i].coiny = PApplet.parseInt(random(1100, 1500));
          coins[i].coinx = PApplet.parseInt(random(width));
          asteroids[i].asteroidSpeed1 += .2f;
          asteroids[i].asteroidSpeed2 += .2f;
          ptsScore += 1;
        }
      }
    }
  }
  
  //Spaceship horizontal hitbox
   for (int i = 0; i < coins.length; i++) {  
    if (coins[i].coinx > spaceship.x - 46) {  
      if (coins[i].coinx < spaceship.x + 46) {  
        if (coins[i].coiny > spaceship.y) {
          playerScore += ptsScore;
          coins[i].coiny = PApplet.parseInt(random(1100, 1500));
          coins[i].coinx = PApplet.parseInt(random(width));
          asteroids[i].asteroidSpeed1 += .2f;
          asteroids[i].asteroidSpeed2 += .2f;
          ptsScore += 1;
        }
      }
    }
  }
    //Spaceship vertical hitbox
  for (int i = 0; i < asteroids.length; i++) {  //spaceship.x == 100    92 < x < 108
    if (asteroids[i].asteroidx > spaceship.x - 28) {  //x > 92
      if (asteroids[i].asteroidx < spaceship.x + 28) {  //x < 108
        if (asteroids[i].asteroidy > spaceship.y) {
          livesLeft -= 1;
          asteroids[i].asteroidy = PApplet.parseInt(random(1100, 1500));
          asteroids[i].asteroidx = PApplet.parseInt(random(width));
        }
      }
    }
  }
  
  //Spaceship horizontal hitbox
   for (int i = 0; i < asteroids.length; i++) {  //spaceship.x == 100    64 < x < 136
    if (asteroids[i].asteroidx > spaceship.x - 56) {  //x > 64
      if (asteroids[i].asteroidx < spaceship.x + 56) {  //x < 136
        if (asteroids[i].asteroidy > spaceship.y) {
          livesLeft -= 1;
          asteroids[i].asteroidy = PApplet.parseInt(random(1100, 1500));
          asteroids[i].asteroidx = PApplet.parseInt(random(width));
        }
      }
    }
  }
}

public void showScores() {
 fill(255);
 textSize(width/40);
 textAlign(LEFT);
 text("Score: " + playerScore, width - 2 * width/9, height/15);
}
boolean blink;

public void drawWelcome() {
 background(0);
 //RENDERING STARS
 for (int i = 0; i < stars.length; i++) {
   stars[i].renderStar();
 }
 fill(100, 200, 230);
 textSize(height/10);
 textAlign(CENTER);
 text("Welcome to", width/2, height/5);
 textSize(height/4);
 text("SPACEFLIGHT", width/2, height/2 + height/45);
 textSize(height/20);
 text("Use 'A' and 'D' to move left and right.", width/2, .65f*height);
 text("Catch the coins and watch out for the rocks!", width/2, .75f*height);
 if (frameCount% 30==0) {
    blink = !blink;
 }
 if (blink == true) {
   textSize(height/12);
   text("Press Spacebar to begin...", width/2, .9f*height);
 }
}

class Asteroid {
  
  //attributes/fields
  int asteroidx, asteroidy;
  float asteroidSpeed, asteroidSpeed1, asteroidSpeed2;
  PImage asteroidSprite;
  
  //constructor class
  Asteroid() {
    asteroidSpeed1 = 6.00f;
    asteroidSpeed2 = 8.00f;
    asteroidSprite = loadImage("asteroidfinal.gif");
    asteroidx = PApplet.parseInt(random(width));
    asteroidy = -(PApplet.parseInt(random(height)));
  }
  
  //methods
  public void renderAsteroid() {
    fill(100);
    shapeMode(CENTER);
    image(asteroidSprite, asteroidx, asteroidy);
    asteroidy += asteroidSpeed;
    asteroidSpeed = PApplet.parseInt(random(asteroidSpeed1, asteroidSpeed2));
    
    if (asteroidy >= height) {
      asteroidy = -(PApplet.parseInt(random(height)));
      asteroidx = PApplet.parseInt(random(width));
    }
    
  }
}

class Star {
  
  //attributes/fields
  int starH, starW;
  int x, y;
  float starSpeed;
  
  //constructor class
  Star() {
    x = PApplet.parseInt(random(width));
    y = PApplet.parseInt(random(height));
    starW = width / 120;
    starH = height / 75;
    starSpeed = PApplet.parseInt(random(3.0f, 5.0f));
  }
  
  //methods
  public void renderStar() {
    fill(255, 255, 0);
    ellipse(x, y, starW, starH);
    y += starSpeed;
    
    if (y >= height) {
      y = -(PApplet.parseInt(random(height)));
    }
  }
}

class Coin {
  
  //attributes/fields
  int snowballH, snowballW;
  int coinx, coiny;
  float coinSpeed;
  PImage coinSprite;
  
  //constructor class
  Coin() {
    coinx = PApplet.parseInt(random(width));
    coiny = -(PApplet.parseInt(random(height)));
    coinSprite = loadImage("Coinfinal.gif");
    coinSpeed = PApplet.parseInt(random(6.00f, 10.00f));
  }
  
  //methods
  public void renderCoin() {
    fill(255);
    shapeMode(CENTER);
    image(coinSprite, coinx, coiny);
    coiny += coinSpeed;
    
    if (coiny >= height) {
      coiny = -(PApplet.parseInt(random(height)));
      coinx = PApplet.parseInt(random(width));
    }
  }
}

class Spaceship {
  
  //attributes/fields
  int x, y;
  float spaceshipSpeed;
  boolean spaceshipMoveRight;
  boolean spaceshipMoveLeft;
  int moveSize;
  PImage spaceshipSprite;
  
  //constructor class
  Spaceship() {
    spaceshipSprite = loadImage("SpaceshipFinal.gif");
    x = width / 2 - width / 40;
    y = height - 75;
    spaceshipSpeed = PApplet.parseInt(6);
    spaceshipMoveRight = false;
    spaceshipMoveLeft = false;
    moveSize = 8;
  }
  
  //methods
  public void renderSpaceship() {
    fill(255);
    if (spaceshipMoveRight == true) {
      x += moveSize;
    }
    if (spaceshipMoveLeft == true) {
      x -= moveSize;
    }
    shapeMode(CENTER);
    x = constrain(x, -50, width - 29);
    image(spaceshipSprite, x, y);
    
  }
}

public void drawObjects() {
    //RENDERING STARS
    for (int i = 0; i < stars.length; i++) {
      stars[i].renderStar();
    }
    
    //RENDERING ASTEROIDS
    for (int i = 0; i < asteroids.length; i++) {
      asteroids[i].renderAsteroid();
    }
    
    //RENDERING COINS
    for (int i = 0; i < coins.length; i++) {
      coins[i].renderCoin();
    }
    
    //RENDERING SPACESHIP
    spaceship.renderSpaceship();
  }

public void endGameScreen() {
    fill(100, 200, 230);
    stroke(255);
    rect(width / 10, height / 2 - height / 4, width / 2 + width / 3, height / 2);
    fill(255);
    textSize(width / 11);
    textAlign(LEFT);
    text("Your Score: " + playerScore, width / 8, height / 2);
    textSize(width / 40);
    text("Press Escape to exit or spacebar to go the welcome screen.", width / 7, height / 3 + height / 4);
}

public void gameOver() {
 if (livesLeft == 0){
   gameState = 2;
 }
}

public void livesLeft() {
  textAlign(LEFT);
  textSize(width/35);
  text("Lives Left: " + livesLeft, width - 2 * width/12, height - height/30);
}
  public void settings() {  size(1060, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "spaceflight" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
