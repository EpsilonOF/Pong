package gui;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import model.Court4Raquettes;
import model.Court4Raquettes4;

public class Mode4Raquettes extends ModeDeJeu {
    protected final Rectangle racketA2, racketB2; // m
    private final double yMargin = 50;

    public Mode4Raquettes(Pane root, Court4Raquettes court, SceneAll sceneAll) {
        super(root, court, sceneAll);
        if(court instanceof Court4Raquettes4) mode="4Raquettes4";
        else mode="4Raquettes";
        racketA2 = new Rectangle();
        racketA2.setWidth(court.getRacketSize() * scale);
        racketA2.setHeight(racketThickness);
        if(court instanceof Court4Raquettes4) racketA2.setFill(Color.RED);
        else racketA2.setFill(Color.BLACK);
        racketA2.setX(yMargin - (court.getWidth() / 2));
        racketA2.setY(0);

        racketB2 = new Rectangle();
        racketB2.setWidth(court.getRacketSize() * scale);
        racketB2.setHeight(racketThickness);
        if(court instanceof Court4Raquettes4) racketB2.setFill(Color.ORANGE);
        else racketB2.setFill(Color.BLUE);
        racketB2.setX(court.getWidth() / 2 + yMargin);
        racketB2.setY(court.getHeight()-racketThickness);
        gameRoot.getChildren().clear();
        gameRoot.getChildren().addAll(raquette, racketB, racketA2, racketB2, ball, score, backToMenu, rScore, pause, argent);
    }

    @Override
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
                racketA2.setX(court.getRacketA2() * scale);
                racketB2.setX(court.getRacketB2() * scale);
                ball.setX(court.getBallX() * scale + xMargin);
                ball.setY(court.getBallY() * scale);
                if(court instanceof Court4Raquettes4) score.setText("    "+court.getScorePlayerA2()+"\n"+court.getScorePlayerA() + " + " + court.getScorePlayerB()+"\n    "+court.getScorePlayerB2());
                else score.setText(court.getScorePlayerA() + " - " + court.getScorePlayerB());
                argent.setText(String.valueOf(Conn.argent()));
            }
        }.start();
    }
}
