package model;

import java.util.Random;

public class Court4Raquettes4 extends Court4Raquettes {
    protected double racketA2; // m
    protected double racketB2; // m
    protected int ScorePlayerA2, ScorePlayerB2;
    private final RacketController playerA2, playerB2;
    private String dernier;
    public Court4Raquettes4(RacketController playerA, RacketController playerB, RacketController playerA2, RacketController playerB2, double width, double height) {
        super(playerA, playerB, playerA2, playerB2, width, height);
        this.playerA2 = playerA2;
        this.playerB2 = playerB2;
        dernier = "";
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
                racketA2 -= racketSpeed *2 * deltaT;
                if (racketA2 < racketSize) racketA = racketSize;
                break;
            case IDLE:
                break;
            case GOING_RIGHT:
                racketA2 += racketSpeed *2 * deltaT;
                if (racketA2 > width) racketA2 = width ;
            break;
        }
        switch (playerB2.getState()) {
            case GOING_UP:
                break;
            case GOING_DOWN:
                break;
            case GOING_LEFT:
                racketB2 -= racketSpeed *2 * deltaT;
                if (racketB2 < 0.0) racketB2 = 0.0;
                break;
            case IDLE:
                break;
            case GOING_RIGHT:
                racketB2 += racketSpeed *2 * deltaT;
                if (racketB2 + racketSize > width) racketB2 = width - racketSize;
                break;
            

        }
        if (updateBall(deltaT)) reset();
    }
    public void ajouterScore(){
        switch(dernier){
            case "A":
                ScorePlayerA++;
                return;
            case "B":
                ScorePlayerB++;
                return;
            case "A2":
                ScorePlayerA2++;
                return;
            case "B2":
                ScorePlayerB2++;
                return;
        }
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
            if (nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + racketSize) dernier="A";
            else if (nextBallX > width-diametre && nextBallY > racketB && nextBallY < racketB + racketSize) dernier="B";
        } else if (nextBallX < 0) {
            ajouterScore();
            return true;
        } else if (nextBallX > width-diametre) {
            ajouterScore();
            return true;
        }
        if((nextBallY < racketThickness && nextBallX > racketA2-40 && nextBallX < racketA2-40 + racketSize) 
                || (nextBallY > height-racketThickness-diametre && nextBallX > racketB2-70 && nextBallX < racketB2-70 + racketSize)){
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedX;
            if (nextBallY < racketThickness && nextBallX > racketA2-40 && nextBallX < racketA2-40 + racketSize) dernier="A2";
            else if (nextBallY > height-racketThickness-diametre && nextBallX > racketB2-40 && nextBallX < racketB2-40 + racketSize) dernier="B2";
        }else if(nextBallY < racketThickness){
            ajouterScore();
            return true;
        }else if(nextBallY > height-racketThickness){
            ajouterScore();
            return true;
        }
       // System.out.println(dernier);
        ballX = nextBallX;
        ballY = nextBallY;
        return false;
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
        ballSpeedY = new Random().nextInt(-300, 300);

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
