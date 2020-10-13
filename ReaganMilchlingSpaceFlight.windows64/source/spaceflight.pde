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

void setup() {
  //fullScreen();
  size(1060, 600);
  
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

void draw() {
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

void keyPressed() {
  if (key == ' ' && gameState == 0){
   gameState = 1;  
  }
  if (key == ' ' && gameState == 2){
   gameState = 0;
   livesLeft = 3;
   playerScore = 0;
   for (int i = 0; i < coins.length; i++) {
      coins[i].coiny = -(int(random(height)));
      coins[i].coinx = int(random(width));
   }
   for (int i = 0; i < asteroids.length; i++) {
      asteroids[i].asteroidy = -(int(random(height)));
      asteroids[i].asteroidx = int(random(width));
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

void keyReleased() {
  if (key == 'a') {
    spaceship.spaceshipMoveLeft = false;
  }
  if (key == 'd') {
   spaceship.spaceshipMoveRight = false; 
  }
}
