
void endGameScreen() {
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
