void hitBoxes() {
  //Spaceship vertical hitbox
  for (int i = 0; i < coins.length; i++) {  
    if (coins[i].coinx > spaceship.x - 18) {  
      if (coins[i].coinx < spaceship.x + 18) {  
        if (coins[i].coiny > spaceship.y) {
         playerScore += ptsScore;
          coins[i].coiny = int(random(1100, 1500));
          coins[i].coinx = int(random(width));
          asteroids[i].asteroidSpeed1 += .2;
          asteroids[i].asteroidSpeed2 += .2;
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
          coins[i].coiny = int(random(1100, 1500));
          coins[i].coinx = int(random(width));
          asteroids[i].asteroidSpeed1 += .2;
          asteroids[i].asteroidSpeed2 += .2;
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
          asteroids[i].asteroidy = int(random(1100, 1500));
          asteroids[i].asteroidx = int(random(width));
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
          asteroids[i].asteroidy = int(random(1100, 1500));
          asteroids[i].asteroidx = int(random(width));
        }
      }
    }
  }
}
