package gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;

import model.CourtDeuxBoules;

public class ModeDeuxBoules extends ModeDeJeu {
    
    private final Circle ball_2;

    public ModeDeuxBoules(Pane root, CourtDeuxBoules court, SceneAll sceneAll) {
        super(root, court, sceneAll);
        mode = "DeuxBoules";
        
        ball_2 = new Circle();
        ball_2.setRadius(ball.getFitHeight()/2);
        ball_2.setFill(Color.GREEN);    
        ball_2.setCenterX(court.getBallX_2() * scale + xMargin);
        ball_2.setCenterY(court.getBallY_2() * scale);

        gameRoot.getChildren().clear();
        gameRoot.getChildren().addAll(raquette, racketB, ball, ball_2, score,  backToMenu, rScore, pause, argent);
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
                ball.setX(court.getBallX() * scale + xMargin);
                ball.setY(court.getBallY() * scale);

                // pour la deuxième balle
                ball_2.setCenterX(court.getBallX_2() * scale + xMargin);
                ball_2.setCenterY(court.getBallY_2() * scale);

                score.setText(court.getScorePlayerA() + " - " + court.getScorePlayerB());
                argent.setText(String.valueOf(Conn.argent()));
            }
        }.start();
    }
}
