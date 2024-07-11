
package gui;

import model.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.animation.AnimationTimer;
import javafx.event.*;

public class ModeDeJeu {

    protected final Court court;
    protected final Pane gameRoot;
    protected final double scale, xMargin, racketThickness;
    public static String mode;

    protected final Rectangle racketB;
    protected final ImageView ball,raquette;
    protected final Text score,argent;
    protected final Button  backToMenu, rScore, pause;
    protected final Image image;
    protected final ImageView coin;

    public void stock(Court c){
        Temporaire.ballSpeedX = c.getBallSpeedX();
        Temporaire.ballSpeedY = c.getBallSpeedY();
        Temporaire.ballPosX = c.getBallX();
        Temporaire.ballPosY = c.getBallY();
        Temporaire.scoreA = c.getScorePlayerA();
        Temporaire.scoreB = c.getScorePlayerB();
        Temporaire.posRacketA = c.getRacketA();
        Temporaire.posRacketB = c.getRacketB();
        Temporaire.racketSize = c.getRacketSize();
        if(c instanceof Court4Raquettes){
            Temporaire.posRacketA2 = c.getRacketA2();
            Temporaire.posRacketB2 = c.getRacketB2();
        }
        if(c instanceof CourtDeuxBoules){
            CourtDeuxBoules c2 = (CourtDeuxBoules) c;
            Temporaire.ballPosX2 = c2.getBallX_2();
            Temporaire.ballPosY2 = c2.getBallY_2();
            Temporaire.ballSpeedX2 = c2.getBallSpeedX2();
            Temporaire.ballSpeedY2 = c2.getBallSpeedY2();
        }
        Temporaire.racketSize = c.getRacketSize();
        Temporaire.mode = mode;
        Temporaire.ballRadius = c.getBallRadius();
        Temporaire.racketThickness = racketThickness;
        Temporaire.courtLargeur = c.getWidth();
        Temporaire.courtLongueur = c.getHeight();
        Pause.scoreA = c.getScorePlayerA();
        Pause.scoreB = c.getScorePlayerB();
    }
    public ModeDeJeu(Pane root, Court court, SceneAll sceneAll) {
        this.court = court;
        gameRoot = root;
        scale = 1.0;
        xMargin = 50;
        racketThickness = 15;
        root.setMinWidth(court.getWidth() * scale + 2 * xMargin);
        root.setMinHeight(court.getHeight() * scale);
        mode = "";
        raquette = new ImageView(Conn.skinActuelRaquette);
        raquette.setFitHeight(court.getRacketSize() * scale);
        raquette.setFitWidth(racketThickness);
        raquette.setX(xMargin - racketThickness);
        raquette.setY(court.getRacketA() * scale);

        racketB = new Rectangle();
        racketB.setHeight(court.getRacketSize() * scale);
        racketB.setWidth(racketThickness);
        racketB.setFill(Color.BLUE);
        racketB.setX(court.getWidth() * scale + xMargin);
        racketB.setY(court.getRacketB() * scale);

        ball = new ImageView(Conn.skinActuelBalle);
        ball.setFitWidth(30);
        ball.setFitHeight(ball.getFitWidth());
        ball.setX(court.getBallX() * scale + xMargin);
        ball.setY(court.getBallY() * scale);

        score = new Text();
        score.setY(court.getHeight()/5-100);
        score.setX(court.getWidth() / 2);
        score.setFont(Font.font("calibry" , 60)); // visuel du score
        score.setText(this.court.getScorePlayerA2()+"\n"+this.court.getScorePlayerA()+"  "+this.court.getScorePlayerB()+"\n"+this.court.getScorePlayerB2());

        argent = new Text(String.valueOf(Conn.argent()));
        argent.setY(50);
        argent.setX(court.getWidth()-100);
        argent.setFont(Font.font("calibry" , 30)); 
        
        backToMenu = new Button("Menu");
        backToMenu.setLayoutX(5);
        backToMenu.setLayoutY(5);
        backToMenu.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        backToMenu.setOnAction(event -> {
            sceneAll.goMenu(this.gameRoot);
        });
        pause = new Button("||");
        pause.setLayoutX(100);
        pause.setLayoutY(5);
        pause.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        pause.setOnAction(event -> {
            stock(court);
            sceneAll.goPause(this.gameRoot);
        });
        rScore=new Button("Reset Score");
        rScore.setFont(Font.font("calibry" , 20));
        rScore.setLayoutX(court.getWidth()/2);
        rScore.setLayoutY(court.getHeight()*1/7);
        rScore.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                court.setScorePlayerA(0);
                court.setScorePlayerB(0);
                court.setBallY(court.getHeight()/2);
                court.setBallX(court.getWidth()/2);
                raquette.setX(xMargin - racketThickness);
                raquette.setY(court.getRacketA() * scale);
                racketB.setX(court.getWidth() * scale + xMargin);
                racketB.setY(court.getRacketB() * scale);

            }
        });

        this.image = new Image("file:images/coin.png");
        this.coin = new ImageView(image);
        coin.setFitWidth(50);
        coin.setFitHeight(50);
        coin.setPreserveRatio(true);
        coin.setX(court.getWidth()+15);
        coin.setY(5);

        gameRoot.getChildren().addAll(raquette, racketB, ball, score, rScore, backToMenu, pause, argent ,coin);
    }

    public void animate() {
        new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                court.update((now - last) * 1.0e-9); // convert nanoseconds to seconds
                last = now;
                raquette.setY(court.getRacketA() * scale);
                racketB.setY(court.getRacketB() * scale);
                ball.setX(court.getBallX() * scale + xMargin);
                ball.setY(court.getBallY() * scale);
                score.setText(court.getScorePlayerA() + " - " + court.getScorePlayerB());
                argent.setText(String.valueOf(Conn.argent()));
            }
        }.start();
    }
}