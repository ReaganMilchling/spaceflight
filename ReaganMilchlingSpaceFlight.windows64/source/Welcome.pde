boolean blink;

void drawWelcome() {
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
 text("Use 'A' and 'D' to move left and right.", width/2, .65*height);
 text("Catch the coins and watch out for the rocks!", width/2, .75*height);
 if (frameCount% 30==0) {
    blink = !blink;
 }
 if (blink == true) {
   textSize(height/12);
   text("Press Spacebar to begin...", width/2, .9*height);
 }
}
