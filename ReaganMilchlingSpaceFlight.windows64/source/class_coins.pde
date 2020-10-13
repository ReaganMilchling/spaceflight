
class Coin {
  
  //attributes/fields
  int snowballH, snowballW;
  int coinx, coiny;
  float coinSpeed;
  PImage coinSprite;
  
  //constructor class
  Coin() {
    coinx = int(random(width));
    coiny = -(int(random(height)));
    coinSprite = loadImage("Coinfinal.gif");
    coinSpeed = int(random(6.00, 10.00));
  }
  
  //methods
  void renderCoin() {
    fill(255);
    shapeMode(CENTER);
    image(coinSprite, coinx, coiny);
    coiny += coinSpeed;
    
    if (coiny >= height) {
      coiny = -(int(random(height)));
      coinx = int(random(width));
    }
  }
}
