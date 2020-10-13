
class Star {
  
  //attributes/fields
  int starH, starW;
  int x, y;
  float starSpeed;
  
  //constructor class
  Star() {
    x = int(random(width));
    y = int(random(height));
    starW = width / 120;
    starH = height / 75;
    starSpeed = int(random(3.0, 5.0));
  }
  
  //methods
  void renderStar() {
    fill(255, 255, 0);
    ellipse(x, y, starW, starH);
    y += starSpeed;
    
    if (y >= height) {
      y = -(int(random(height)));
    }
  }
}
