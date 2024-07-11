package model;

import java.util.Random;

public class CourtGrandesRaquettes extends Court {
     
    public CourtGrandesRaquettes(RacketController playerA, RacketController playerB, double width, double height) {
        super(playerA, playerB, width, height);
    }
    public void setRacketSize(double x){
        this.racketSize=x;
    }
    /**
      * @return true if a player lost
      */
    @Override
    public boolean updateBall(double deltaT) {
        // first, compute possible next position if nothing stands in the way
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;
        double diametre = getBallRadius()*2;
        if (nextBallY < 0 || nextBallY > height-diametre) { // quand la balle touche le haut ou le bas
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedY;
        }
        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + racketSize)
                || (nextBallX > width-diametre && nextBallY > racketB && nextBallY < racketB + racketSize)) {
            ballSpeedX = -ballSpeedX;
            nextBallX = ballX + deltaT * ballSpeedX;
            racketSize *= 0.95; // les raquettes deviennent de plus en plus petite dans ce mode de jeu
        } else if (nextBallX < 0) {
            ScorePlayerB++;
            return true;
        } else if (nextBallX > width-diametre) {
            ScorePlayerA++;
            return true;
        }
        ballX = nextBallX;
        ballY = nextBallY;
        return false;
    }  
 
    @Override
    public void reset() {
        racketA = height / 2;
        racketB = height / 2;
        racketSize = 300; // les raquettes sont très grandes au début

        // la première direction de la balle est aléatoire
        if (new Random().nextInt(2) == 1) ballSpeedX = -300;
        else ballSpeedX = 300;
        ballSpeedY = new Random().nextInt(-300, 300);
        
        ballX = width / 2;
        ballY = height / 2;
    }
}
