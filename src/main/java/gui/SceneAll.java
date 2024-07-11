package gui;

import java.awt.Dimension;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;

public class SceneAll {

    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    private Stage stage;
    private double heigth = dimension.getHeight();
    private double width = dimension.getWidth()-100;
    private Pane root;
    private Scene scene;
    private Player playerA, playerB, playerA2, playerB2;

    public SceneAll(Stage stage) {
        this.stage = stage;
        stage.setFullScreen(true);
        stage.initStyle(StageStyle.DECORATED);
        
        playerA = new Player();
        playerB = new Player();
        playerA2 = new Player();
        playerB2 = new Player();
        
        var up1 = KeyCode.valueOf(Fichiers.readLigne(2, Conn.fichier));
        var down1 = KeyCode.valueOf(Fichiers.readLigne(3, Conn.fichier));
        var up2 = KeyCode.valueOf(Fichiers.readLigne(4, Conn.fichier));
        var down2 =KeyCode.valueOf(Fichiers.readLigne(5, Conn.fichier));

        root = new Pane();
        scene = new Scene(root, width, heigth); // affichage de la fenêtre 
        scene.setOnKeyPressed(ev -> {
            switch (ev.getCode()) {
                case Q:
                    playerA2.state = RacketController.State.GOING_LEFT;
                    break;
                case D:
                    playerA2.state = RacketController.State.GOING_RIGHT;
                    break;
                case K:
                    playerB2.state = RacketController.State.GOING_LEFT;
                    break;
                case M:
                    playerB2.state = RacketController.State.GOING_RIGHT;
                    break;
            }
            if(ev.getCode() == up1){
                playerA.state = RacketController.State.GOING_UP;
            }
            if(ev.getCode() == down1){
                playerA.state = RacketController.State.GOING_DOWN;
            }
            if(ev.getCode() == up2){
                playerB.state = RacketController.State.GOING_UP;
            }
            if(ev.getCode() == down2){
                playerB.state = RacketController.State.GOING_DOWN;
            }
        });
        scene.setOnKeyReleased(ev -> {
            switch (ev.getCode()) {
                case Q:
                    if (playerA2.state == RacketController.State.GOING_LEFT) playerA2.state = RacketController.State.IDLE;
                    break;
                case D:
                    if (playerA2.state == RacketController.State.GOING_RIGHT) playerA2.state = RacketController.State.IDLE;
                    break;
                case K:
                    if (playerB2.state == RacketController.State.GOING_LEFT) playerB2.state = RacketController.State.IDLE;
                    break;
                case M:
                    if (playerB2.state == RacketController.State.GOING_RIGHT) playerB2.state = RacketController.State.IDLE;
                    break;
            }
            if(ev.getCode() == up1){
                if (playerA.state == RacketController.State.GOING_UP) playerA.state = RacketController.State.IDLE;
            }
            if(ev.getCode() == down1){
                if (playerA.state == RacketController.State.GOING_DOWN) playerA.state = RacketController.State.IDLE;
            }
            if(ev.getCode() == up2){
                if (playerB.state == RacketController.State.GOING_UP) playerB.state = RacketController.State.IDLE;
            }
            if(ev.getCode() == down2){
                if (playerB.state == RacketController.State.GOING_DOWN) playerB.state = RacketController.State.IDLE;
            }

        });     
    }

    public Scene getScene(){return scene;}
    public Stage getStage(){return stage;}
    public Parent getRoot(){return root;}

    public void goMenu(Pane mainRoot) {
        if(mainRoot != null) mainRoot.getChildren().clear();
        var menuView = new MenuView(root, this, "Pong");
        stage.setScene(scene);
        stage.show();
        menuView.animate();
    }

    public void goParameter(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var parameterView = new ParameterView(root, this, "Parametres");
        stage.setScene(scene);
        stage.show();
        parameterView.animate();
    }

    public void goModesDeJeu(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var modeView = new ModeView(root, this, "Tous les modes de jeu");
        stage.setScene(scene);
        stage.show();
        modeView.animate();
    }

    public void goModeClassique(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var court = new Court(playerA, playerB, width, heigth);
        var classique = new ModeClassique(root, court, this);
        stage.setScene(scene);
        classique.animate();
    }

    public void goModeGrandeRaquettes(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var court = new CourtGrandesRaquettes(playerA, playerB, width, heigth);
        var modeView = new ModeGrandesRaquettes(root, court, this);
        stage.setScene(scene);
        stage.show();
        modeView.animate();
    }

    public void goModeDeuxBoules(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var court = new CourtDeuxBoules(playerA, playerB, width, heigth);
        var mode2View = new ModeDeuxBoules(root, court, this);
        stage.setScene(scene);
        mode2View.animate();
    }

    public void goModeVitesse(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var court = new CourtModeVitesse(playerA, playerB, width, heigth);
        var mode1View = new ModeVitesse(root, court, this);
        stage.setScene(scene);
        mode1View.animate();
    }
    public void goMode4Raquettes(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var court = new Court4Raquettes(playerA, playerB,playerA2, playerB2, width, heigth);
        var mode3View = new Mode4Raquettes(root, court, this);
        stage.setScene(scene);
        stage.show();
        mode3View.animate();
    }
    public void goMode4Raquettes4(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var court = new Court4Raquettes4(playerA, playerB,playerA2, playerB2, width, heigth);
        var mode3View = new Mode4Raquettes(root, court, this);
        System.out.println(court.getClass().getName());
        stage.setScene(scene);
        stage.show();
        mode3View.animate();
    }

    public void goModeRobots(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var court = new CourtR(playerA, width, heigth);
        var modeRview = new ModeR(root,court,this);
        stage.setScene(scene);
        modeRview.animate();
    }
    public void goCreerProfil(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var creerProfil = new CreerProfil(root, this, "Profil");
        stage.setScene(scene);
        stage.show();
        creerProfil.animate();
    }

    public void goProfil(Pane mainRoot){
        mainRoot.getChildren().clear();
        var profil = new Profil(this.root, this,"Profil");
        stage.setScene(scene);
        stage.show();
        profil.animate(); 
    }

    public void goConnexion(Pane mainRoot){
        mainRoot.getChildren().clear();
        var conn = new Connexion(root, this, "Connexion");
        stage.setScene(scene);
        stage.show();
        conn.animate();
    }
    public void goPause(Pane mainRoot) {
        mainRoot.getChildren().clear();
        var pauseView = new Pause(root, this, "Pause");
        stage.setScene(scene);
        stage.show();
        pauseView.animate();
    }

    public void goBoutiqueBalle(Pane mainRoot) {
        mainRoot.getChildren().clear();
            var boutiqueBalleView = new BoutiqueBalle(root, this, "Boutique Balle");
            stage.setScene(scene);
            stage.show();
            boutiqueBalleView.animate();
    }
    public void goBoutiqueRaquette(Pane mainRoot) {
        mainRoot.getChildren().clear();
            var boutiqueRaquetteView = new BoutiqueRaquette(root, this, "Boutique Raquette");
            stage.setScene(scene);
            stage.show();
            boutiqueRaquetteView.animate();
    }

    public void goReprendre(Pane mainRoot){
        mainRoot.getChildren().clear();
        switch(Temporaire.mode){
            case "Classique":
                var court = new Court(playerA, playerB, width, heigth);
                Temporaire.reprendre(court);
                var classique = new ModeClassique(root, court, this);
                stage.setScene(scene);
                classique.animate();
                return;
            case "GrandesRaquettes":
                var courtGR = new CourtGrandesRaquettes(playerA, playerB, width, heigth);
                Temporaire.reprendreGR(courtGR);
                var modeView = new ModeGrandesRaquettes(root, courtGR, this);
                stage.setScene(scene);
                stage.show();
                modeView.animate();
                return;
            case "DeuxBoules":
                var courtDB = new CourtDeuxBoules(playerA, playerB, width, heigth);
                Temporaire.reprendreDB(courtDB);
                var mode2View = new ModeDeuxBoules(root, courtDB, this);
                stage.setScene(scene);
                mode2View.animate();
                return;
            case "4Raquettes":
                var court4 = new Court4Raquettes(playerA, playerB, playerA2, playerB2, width, heigth);
                Temporaire.reprendre4(court4);
                var mode3View = new Mode4Raquettes(root, court4, this);
                stage.setScene(scene);
                stage.show();
                mode3View.animate();
                return;
            case "4Raquettes4":
                Court4Raquettes4 court44 = new Court4Raquettes4(playerA, playerB, playerA2, playerB2, width, heigth);
                Mode4Raquettes mode4View = new Mode4Raquettes(root, court44, this);
                Temporaire.reprendre44(court44, mode4View);
                stage.setScene(scene);
                stage.show();
                mode4View.animate();
                return;
            case "Vitesse":
                var courtV = new CourtModeVitesse(playerA, playerB, width, heigth);
                Temporaire.reprendre(courtV);
                var mode1View = new ModeVitesse(root, courtV, this);
                stage.setScene(scene);
                mode1View.animate();
                return;
            case "Robot":
                var courtR = new CourtR(playerA,width, heigth);
                Temporaire.reprendre(courtR);
                var modeRView = new ModeR(root,courtR,this);
                stage.setScene(scene);
                stage.show();
                modeRView.animate();
                return;
        }
    }
    public void goRestart(Pane mainRoot){
        switch(Temporaire.mode){
            case "Classique": 
                goModeClassique(mainRoot);
                return;
            case "GrandesRaquettes": 
                goModeGrandeRaquettes(mainRoot);
                return;
            case "Vitesse": 
                goModeVitesse(mainRoot);
                return;
            case "DeuxBoules": 
                goModeDeuxBoules(mainRoot);
                return;
            case "4Raquettes": 
                goMode4Raquettes(mainRoot);
                return;
            case "Robot":
                goModeRobots(mainRoot);
                return;
        }
    }
    
}
