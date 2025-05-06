package view;

import boardifier.model.GameStageModel;
import boardifier.view.*;
import model.*;

/**
 * Vue du stage pour le jeu Puissance X.
 * Associe un look visuel à chaque élément du modèle.
 */
public class PuissanceXStageView extends GameStageView {

    public PuissanceXStageView(String name, GameStageModel gameStageModel) {
        super(name, gameStageModel);
    }

    @Override
    public void createLooks() {
        PuissanceXStageModel model = (PuissanceXStageModel) gameStageModel;

        // Texte pour le nom du joueur
        addLook(new TextLook(model.getPlayerName()));

        // Plateau avec coordonnées, bordures, et cases de taille 2x4
        addLook(new ClassicBoardLook(2, 4, model.getBoard(), 2, 4, true));

        // Looks pour les deux pots de jetons
        addLook(new TableLook(model.getRedPot(), 0, 1)); // 0 = profondeur, 1 = bordure
        addLook(new TableLook(model.getYellowPot(), 0, 1));

        // Look pour les jetons rouges
        for (PuissanceXToken token : model.getRedTokens()) {
            addLook(new PuissanceXTokenLook(token));
        }

        // Look pour les jetons jaunes
        for (PuissanceXToken token : model.getYellowTokens()) {
            addLook(new PuissanceXTokenLook(token));
        }
    }
}