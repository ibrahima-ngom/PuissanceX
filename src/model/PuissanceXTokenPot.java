package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;

/**
 * Réservoir de jetons pour PuissanceX, représentant la zone où sont stockés les jetons au début de la partie.
 * Un simple ContainerElement avec nbTokens lignes et 1 colonne est nécessaire.
 * NB : nbTokens correspond au nombre de jetons attribués à chaque joueur, déterminé par la moitié de la taille de la grille choisie.
 */
public class PuissanceXTokenPot extends ContainerElement {
    public PuissanceXTokenPot(int x, int y, int nbTokens, GameStageModel gameStageModel) {
        // Appelle le super-constructeur pour créer une grille de nbTokens x 1, nommée "tokenpot", aux coordonnées (x,y) dans l'espace
        super("tokenpot", x, y, nbTokens, 1, gameStageModel);
    }
}
