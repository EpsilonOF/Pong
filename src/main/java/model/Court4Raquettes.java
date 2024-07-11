package model;

import java.util.Random;

public class Court4Raquettes extends Court {
    
    protected double racketA2, racketB2; // m
    protected final double racketThickness = 15;
    private final RacketController playerA2, playerB2;

    public Court4Raquettes(RacketController playerA, RacketController playerB, RacketController playerA2, RacketController playerB2, double width, double height) {
        super(playerA, playerB, width, height);
        this.playerA2 = playerA2;
        this.playerB2 = playerB2;
    }

    @Override
    public boolean updateBall(double deltaT) {
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;
        double diametre = getBallRadius()*2;
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
        if((nextBallY < racketThickness && nextBallX > racketA2-40 && nextBallX < racketA2-40 + racketSize) 
                || (nextBallY > height-racketThickness-diametre && nextBallX > racketB2-40 && nextBallX < racketB2-40 + racketSize)){
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedX;
        }else if(nextBallY < racketThickness){
            ScorePlayerB++;
            return true;
        }else if(nextBallY > height-racketThickness){
            ScorePlayerA++;
            return true;
        }
        ballX = nextBallX;
        ballY = nextBallY;
        return false;
    }
    @Override
    public void update(double deltaT) {
        super.update(deltaT);
        switch (playerA2.getState()) {
            case GOING_UP:
                break;
            case GOING_DOWN:
                break;
            case GOING_LEFT:
                racketA2 -= racketSpeed*2 * deltaT;
                if (racketA2 < racketSize) racketA2 = racketSize;
                break;
            case IDLE:
                break;
            case GOING_RIGHT:
                racketA2 += racketSpeed*2 * deltaT;
                if (racketA2 > width) racketA2 = width ;
            break;
            
        }
        switch (playerB2.getState()) {
            case GOING_UP:
                break;
            case GOING_DOWN:
                break;
            case GOING_LEFT:
                racketB2 -= racketSpeed*2 * deltaT;
                if (racketB2 < 0.0) racketB2 = 0.0;
                break;
            case IDLE:
                break;
            case GOING_RIGHT:
                racketB2 += racketSpeed*2 * deltaT;
                if (racketB2 + racketSize > width) racketB2 = width - racketSize;
                break;
            

        }
        if (updateBall(deltaT)) reset();
    }
    
    @Override
    public void reset() {
        racketA = height / 2;
        racketB = height / 2;
        racketA2 = width / 2;
        racketB2 = width / 2;
        // la première direction de la balle est aléatoire
        if (new Random().nextInt(2) == 1) ballSpeedX = -100;
        else ballSpeedX = 100;
        ballSpeedY = new Random().nextInt(-150, 150);

        ballX = width / 2;
        ballY = height / 2;

    }

    @Override
    public double getRacketA2() {return this.racketA2;}
    @Override
    public double getRacketB2() {return this.racketB2;}
    public void setRacketA2(double x){this.racketA2=x;}
    public void setRacketB2(double x){this.racketB2=x;}
    @Override
    public double getBallX() {return ballX;}
    @Override
    public double getBallY() {return ballY;}
}

