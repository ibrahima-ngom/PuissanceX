package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

/**
 * Un jeton basique pour le jeu Puissance X, avec seulement une couleur fixe.
 * Il n'y a pas de setters car la couleur d'un jeton ne change jamais.
 */
public class PuissanceXToken extends GameElement {

    private int color; // 0 = rouge, 1 = jaune

    public static final int TOKEN_RED = 0;
    public static final int TOKEN_YELLOW = 1;

    public PuissanceXToken(int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        // Enregistrement du type "token" avec la valeur 50
        ElementTypes.register("token", 50);
        type = ElementTypes.getType("token");
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
