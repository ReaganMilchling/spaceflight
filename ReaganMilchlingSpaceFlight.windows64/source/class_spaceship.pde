
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
    spaceshipSpeed = int(6);
    spaceshipMoveRight = false;
    spaceshipMoveLeft = false;
    moveSize = 8;
  }
  
  //methods
  void renderSpaceship() {
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
