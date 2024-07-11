package model;

import java.io.IOException;
import java.util.Random;

import gui.Conn;
import gui.Fichiers;

public class Court {

    private final RacketController playerA, playerB;
    protected final double width, height; // m
    protected double racketSpeed = 300.0, racketSize = 100.0; // m
    private double ballRadius = 10.0; // m
    
    protected double racketA, racketB; // m
    protected double ballX, ballY; // m
    protected double ballSpeedX, ballSpeedY; // m
    protected double coeffA = 0.3, coeffB = 0.3;//m
    
    protected int ScorePlayerA, ScorePlayerB, ScorePlayerA2, ScorePlayerB2;

    public Court(RacketController playerA, RacketController playerB, double width, double height) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.width = width;
        this.height = height;

        reset();
    }    

    public void update(double deltaT) {
        switch (playerA.getState()) {
            case GOING_UP:
                racketA -= racketSpeed * deltaT;
                if (racketA < 0.0) racketA = 0.0;
                break;
            case IDLE:
                break;
            case GOING_DOWN:
                racketA += racketSpeed * deltaT;
                if (racketA + racketSize > height) racketA = height - racketSize;
                break;
            case GOING_LEFT:
                break;
            case GOING_RIGHT:
                break;
        }
        switch (playerB.getState()) {
            case GOING_UP:
                racketB -= racketSpeed * deltaT;
                if (racketB < 0.0) racketB = 0.0;
                break;
            case IDLE:
                break;
            case GOING_DOWN:
                racketB += racketSpeed * deltaT;
                if (racketB + racketSize > height) racketB = height - racketSize;
                break;
                case GOING_LEFT:
                break;
            case GOING_RIGHT :
                break;    
        }
        if (updateBall(deltaT)) reset();
    }

    public boolean updateBall(double deltaT) {
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;
        double diametre = ballRadius*2;
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
            ballSpeedX = -ballSpeedX;
            nextBallX = ballX + deltaT * ballSpeedX;
                
        } else if (nextBallX < 0) {
            ScorePlayerB++;
            return true;
        } else if (nextBallX > width-diametre) {
            ScorePlayerA++;
            try {
                int x = Conn.argent()+10;
                Fichiers.remplacerLigne(6, "Monnaie : "+ x , Conn.fichier);
                
            } catch (IOException e) {
                System.out.println("Pblm de fichier");
            }
            return true;
        }
        ballX = nextBallX;
        ballY = nextBallY;
        return false;
    }

    public void reset() {
        racketA = height / 2;
        racketB = height / 2;

        // la première direction de la balle est aléatoire
        if (new Random().nextInt(2) == 1) ballSpeedX = -300;
        else ballSpeedX = 300;
        ballSpeedY = new Random().nextInt(-300, 300);
        
        ballX = width / 2;
        ballY = height / 2;
    }

    public void SpeedUp(double time) { // on augmente la vitesse par rapport au temps
        this.coeffA+=time*2;
        this.coeffB+=time*2;
    }

    public double getWidth() {return width;}
    public double getHeight() {return height;}
    public double getRacketSize() {return racketSize;}
    public double getRacketSpeed() {return racketSpeed;}
    public double getRacketA() {return racketA;}
    public void setRacketA(double x){this.racketA=x;}
    public double getRacketB() {return racketB;}
    public void setRacketB(double x){this.racketB=x;}
    public double getRacketA2() {return racketA;}
    public double getRacketB2() {return racketB;}
    public double getBallX() {return ballX;}
    public double getBallX_2() {return -1;}
    public double getBallY_2() {return -1;}
    public double getBallY() {return ballY;}
    public void setBallX(double x){this.ballX=x;}
    public void setBallY(double y){this.ballY=y;}
    public double getBallSpeedX(){return this.ballSpeedX;}
    public void setBallSpeedX(double x){this.ballSpeedX=x;}
    public double getBallSpeedY(){return this.ballSpeedY;}
    public void setBallSpeedY(double x){this.ballSpeedY=x;}
    public double getBallRadius() {return ballRadius;}
    public RacketController getPlayerA(){return playerA;}
    public RacketController getPlayerB(){return playerB;}
    public int getScorePlayerA() {return ScorePlayerA;}
    public int getScorePlayerB() {return ScorePlayerB;}
    public int getScorePlayerA2() {return ScorePlayerA2;}
    public int getScorePlayerB2() {return ScorePlayerB2;}
    public int getVitesse(){return (int)(Math.abs(ballSpeedX));}
    public void setScorePlayerA(int scorePlayerA) {ScorePlayerA = scorePlayerA;}
    public void setScorePlayerB(int scorePlayerB) {ScorePlayerB = scorePlayerB;}
    public double getCoefA(){ return coeffA;}
    public double getCoefB(){ return coeffB;}
    public void setCoefA(double coefA) { this.coeffA=coefA;}
    public void setCoefB(double coefB){this.coeffB=coefB;}
}