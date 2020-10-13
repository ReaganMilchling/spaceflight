
class Asteroid {
  
  //attributes/fields
  int asteroidx, asteroidy;
  float asteroidSpeed, asteroidSpeed1, asteroidSpeed2;
  PImage asteroidSprite;
  
  //constructor class
  Asteroid() {
    asteroidSpeed1 = 6.00;
    asteroidSpeed2 = 8.00;
    asteroidSprite = loadImage("asteroidfinal.gif");
    asteroidx = int(random(width));
    asteroidy = -(int(random(height)));
  }
  
  //methods
  void renderAsteroid() {
    fill(100);
    shapeMode(CENTER);
    image(asteroidSprite, asteroidx, asteroidy);
    asteroidy += asteroidSpeed;
    asteroidSpeed = int(random(asteroidSpeed1, asteroidSpeed2));
    
    if (asteroidy >= height) {
      asteroidy = -(int(random(height)));
      asteroidx = int(random(width));
    }
    
  }
}
