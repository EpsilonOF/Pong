package gui;

import java.awt.Dimension;

import javafx.animation.AnimationTimer;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class View {

	protected final SceneAll sceneAll;
	protected final Pane root;
	protected final double scale, xMargin;
	protected final Text title;
    protected int height, width;

	public View(Pane root, SceneAll sceneAll, String titre) {
		this.root = root;
		this.sceneAll = sceneAll;
		scale = 1;
		xMargin = 50;

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
         height = (int)dimension.getHeight();
         width = (int)dimension.getWidth();

		root.setMinWidth(height);
        root.setMinHeight(width);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

        title = new Text(titre);
        title.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 85));
        title.setEffect(dropShadow);
        title.setFill(Color.BLACK);
        title.setX(1200 * scale / 2 - 110);
        title.setY(800 * scale / 2 - 150);
	}

	public void animate() {
        new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) {
                    last = now;
                    return;
                }
                last = now;
            }
        }.start();
    }
}