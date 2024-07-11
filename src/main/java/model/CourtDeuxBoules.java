package model;

import java.util.Random;

public class CourtDeuxBoules extends Court {
    
    private double ballX_2, ballY_2;
    private double ballSpeedX_2, ballSpeedY_2;
    
    public CourtDeuxBoules(RacketController playerA, RacketController playerB, double width, double height) {
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
        double nextBallX_2 = ballX_2 + deltaT * ballSpeedX_2;
        double nextBallY_2 = ballY_2 + deltaT * ballSpeedY_2;
        double diametre = getBallRadius()*2;
        if (nextBallY < 0 || nextBallY > height-diametre) { // quand la balle 1 touche le haut ou le bas
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedY;
        }
        if (nextBallY_2 < 0 || nextBallY_2 > height-diametre) { // quand la balle 2 touche le haut ou le bas
            ballSpeedY_2 = -ballSpeedY_2;
            nextBallY_2 = ballY_2 + deltaT * ballSpeedY_2;
        }
        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + racketSize)
                || (nextBallX > width-diametre && nextBallY > racketB && nextBallY < racketB + racketSize)) {
            ballSpeedX = -ballSpeedX;
            nextBallX = ballX + deltaT * ballSpeedX;
        } else if (nextBallX < 0) {
            ScorePlayerB++;
            return true;
        } else if (nextBallX > width-diametre) {
            ScorePlayerA++;
            return true;
        }
        if ((nextBallX_2 < 0 && nextBallY_2 > racketA && nextBallY_2 < racketA + racketSize)
                || (nextBallX_2 > width-diametre && nextBallY_2 > racketB && nextBallY_2 < racketB + racketSize)) {
            ballSpeedX_2 = -ballSpeedX_2;
            nextBallX_2 = ballX_2 + deltaT * ballSpeedX_2;
        } else if (nextBallX_2 < 0) {
            ScorePlayerB++;
            return true;
        } else if (nextBallX_2 > width-diametre) {
            ScorePlayerA++;
            return true;
        }
        ballX = nextBallX;
        ballY = nextBallY;
        ballX_2 = nextBallX_2;
        ballY_2 = nextBallY_2;
        return false;
    }

    @Override
    public void reset() {
        racketA = height / 2;
        racketB = height / 2;
        
        // la première direction est aléatoire uniquement pour la direction en Y car on envoie une boule de chaque côté
        boolean x = false;
        if(new Random().nextInt(2)==1) ballSpeedX = 300;
        else {
            ballSpeedX = -300;
            x = true;
        }
        if(x) ballSpeedX_2 = 300;
        else ballSpeedX_2 = -300;
        ballSpeedY = new Random().nextInt(-300, 300);
        ballSpeedY_2 = new Random().nextInt(-300, 300);

        ballX = width / 2;
        ballY = height / 2;
        ballX_2 = width / 2;
        ballY_2 = height / 2;
    }
    
    public double getBallX_2() {return ballX_2;}
    public void setBallX2(double x){this.ballX_2=x;}
    public double getBallY_2() {return ballY_2;}
    public void setBallY2(double x){this.ballY_2=x;}
    public void setBallSpeedX2(double x){this.ballSpeedX_2=x;}
    public void setBallSpeedY2(double x){this.ballSpeedY_2=x;}
    public double getBallSpeedX2() {return this.ballSpeedX_2;}
    public double getBallSpeedY2() {return this.ballSpeedY_2;}
}

