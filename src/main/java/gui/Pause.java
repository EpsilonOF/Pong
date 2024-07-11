package gui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;


public class Pause extends View {
    private final Button menu, reprendre, rScore, restart;
    private final Text score;
    public static int scoreA, scoreB;
    public boolean reset = false;
    protected final Rectangle racketB, racketA2, racketB2;
    protected final ImageView racketA, ball;
    public void setScoreA(int x){ scoreA=x;}
    public void setScoreB(int x){ scoreB=x;}
    public Pause(Pane root, SceneAll sceneAll, String titre){
        super(root, sceneAll, titre);
        racketA = new ImageView(Conn.skinActuelRaquette);
        racketA.setFitWidth(Temporaire.racketThickness);
        racketA.setFitHeight(Temporaire.racketSize);
        racketA.setX(xMargin - Temporaire.racketThickness);
        racketA.setY(Temporaire.posRacketA);

        racketB = new Rectangle();
        racketB.setHeight(Temporaire.racketSize);
        racketB.setWidth(Temporaire.racketThickness);
        racketB.setFill(Color.BLUE);
        racketB.setX(Temporaire.courtLargeur * scale + xMargin);
        racketB.setY(Temporaire.posRacketB);

        racketA2 = new Rectangle();
        racketA2.setHeight(Temporaire.racketThickness);
        racketA2.setWidth(Temporaire.racketSize);
        racketA2.setFill(Color.RED);
        racketA2.setX(Temporaire.posRacketA2);
        racketA2.setY(0);

        racketB2 = new Rectangle();
        racketB2.setHeight(Temporaire.racketThickness);
        racketB2.setWidth(Temporaire.racketSize);
        racketB2.setFill(Color.ORANGE);
        racketB2.setX(Temporaire.posRacketB2);
        racketB2.setY(Temporaire.courtLongueur - Temporaire.racketThickness);

        ball = new ImageView(Conn.skinActuelBalle);
        ball.setFitWidth(30);
        ball.setFitHeight(30);
        ball.setX(Temporaire.ballPosX * scale + xMargin);
        ball.setY(Temporaire.ballPosY * scale);

        menu = new Button("Menu");
        menu.setLayoutX(580);
        menu.setLayoutY(600 * scale / 2);
        menu.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        menu.setOnAction(event -> {
            sceneAll.goMenu(this.root);
        });
        reprendre = new Button("Reprendre");
        reprendre.setLayoutX(390);
        reprendre.setLayoutY(600 * scale / 2);
        reprendre.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        reprendre.setOnAction(event -> {
            sceneAll.goReprendre(this.root);
        });
        score = new Text();
        score.setY(250-100);
        score.setX(410);
        score.setFont(Font.font("calibry" , 60));
        score.setText(scoreA+" - " + scoreB);

        rScore=new Button("Reset Score");
        rScore.setFont(Font.font("calibry" , 20));
        rScore.setLayoutX(250);
        rScore.setLayoutY(205);
        rScore.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                setScoreA(0);
                setScoreB(0);
                Temporaire.scoreA=0;
                Temporaire.scoreB=0;
                score.setText(scoreA+" - " + scoreB);
                reset=true;
            }
        });
        restart = new Button("Recommencer");
        restart.setLayoutX(160);
        restart.setLayoutY(600 * scale / 2);
        restart.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        restart.setOnAction(event -> {
            sceneAll.goRestart(this.root);
        });
        root.getChildren().addAll(title, menu, reprendre, score, rScore, restart, racketA, racketB, racketA2, racketB2, ball);
    }
}