package model;
import gui.*;

import java.io.IOException;


public class CourtModeVitesse extends Court {

    public CourtModeVitesse(RacketController playerA, RacketController playerB, double width, double height) {
        super(playerA, playerB, width, height);
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
        if (nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + racketSize){
            try {
                int x = Conn.argent()+5;
                Fichiers.remplacerLigne(6, "Monnaie : "+ x , Conn.fichier);
                
            } catch (IOException e) {
                System.out.println("Pblm de fichier");
            }
        }
        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + racketSize)
                || (nextBallX > width-diametre && nextBallY > racketB && nextBallY < racketB + racketSize)) {
            
            // on augment la vitesse de la balle (et des raquettes)
            if (ballSpeedX < 700){
                ballSpeedX = 1.1 * ballSpeedX;
                racketSpeed *= 1.05;
            }

            ballSpeedX = -ballSpeedX;
            nextBallX = ballX + deltaT * ballSpeedX;
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
}