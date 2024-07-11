package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.awt.Dimension;
import model.Court;

public class GameView {

    // class parameters
    private final Court court;
    private final Pane gameRoot; // main node of the game
    private final double scale;
    private final double xMargin = 50.0, racketThickness = 10.0; // pixels

    // children of the game main node
    private final Rectangle racketA, racketB;
    public static Circle ball;
    private final Text score;

    public GameView(Court court, Pane root, double scale) {
        this.court = court;
        this.gameRoot = root;
        this.scale = scale;

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        int height = (int)dimension.getHeight();

        int width = (int)dimension.getWidth();

        root.setMinWidth(width);
        root.setMinHeight(height);

        racketA = new Rectangle();
        racketA.setHeight(court.getRacketSize() * scale);
        racketA.setWidth(racketThickness);
        racketA.setFill(Color.BLACK);

        racketA.setX(xMargin - racketThickness);
        racketA.setY(court.getRacketA() * scale);

        racketB = new Rectangle();
        racketB.setHeight(court.getRacketSize() * scale);
        racketB.setWidth(racketThickness);
        racketB.setFill(Color.BLACK);

        racketB.setX(court.getWidth() * scale + xMargin);
        racketB.setY(court.getRacketB() * scale);

        ball = new Circle();
        ball.setRadius(court.getBallRadius());
        ball.setFill(Color.BLACK);

        ball.setCenterX(court.getBallX() * scale + xMargin);
        ball.setCenterY(court.getBallY() * scale);

        score = new Text();
        score.setLayoutY(court.getHeight()/4);
        score.setLayoutX(court.getWidth() / 2);
        score.setFont(Font.font("calibry", 60)); // visuel du score
        score.setText(" "+this.court.getScorePlayerA2()+"\n"+this.court.getScorePlayerA()+"  "+this.court.getScorePlayerB()+"\n"+"  "+this.court.getScorePlayerB2());


        gameRoot.getChildren().addAll(racketA, racketB, ball, score);
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
                racketA.setY(court.getRacketA() * scale);
                racketB.setY(court.getRacketB() * scale);
                ball.setCenterX(court.getBallX() * scale + xMargin);
                ball.setCenterY(court.getBallY() * scale);
                score.setText(court.getScorePlayerA() + " - " + court.getScorePlayerB()); //actualisation du score
            }
        }.start();
    }
    public Circle getBall(){ return ball; }
}
