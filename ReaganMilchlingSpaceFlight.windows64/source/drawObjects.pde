
void drawObjects() {
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
