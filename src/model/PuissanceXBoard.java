package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;

/**
 * Plateau de jeu pour Puissance X.
 * Grille de taille variable (choisie par le joueur).
 * Permet de marquer les cases vides atteignables,
 * de trouver la première case vide d'une colonne,
 * et de vérifier si le plateau est entièrement rempli.
 */
public class PuissanceXBoard extends ContainerElement {

    public PuissanceXBoard(int x, int y, int nbCols, int nbRows, GameStageModel gameStageModel) {
        // Appelle le constructeur parent pour créer la grille
        super("puissanceXBoard", x, y, nbCols, nbRows, gameStageModel);
    }

    /**
     * Rend atteignables toutes les cases vides du plateau.
     */
    public void setValidCells() {
        resetReachableCells(false); // Réinitialise toutes les cases comme non atteignables
        for (int i = 0; i < getNbRows(); i++) {
            for (int j = 0; j < getNbCols(); j++) {
                if (isEmptyAt(i, j)) { // Si la case est vide
                    reachableCells[i][j] = true; // On la rend atteignable
                }
            }
        }
    }

    /**
     * Trouve la première ligne vide d'une colonne (du bas vers le haut).
     * @param col La colonne choisie
     * @return L'indice de la ligne vide, ou -1 si la colonne est pleine
     */
    public int getFirstEmptyRow(int col) {
        for (int row = getNbRows() - 1; row >= 0; row--) { // Part du bas
            if (isEmptyAt(row, col)) { // Si vide
                return row;
            }
        }
        return -1; // Colonne pleine
    }

    /**
     * Vérifie si toutes les cases du plateau sont remplies.
     * @return true si le plateau est plein, false sinon
     */
    public boolean isFull() {
        for (int i = 0; i < getNbRows(); i++) {
            for (int j = 0; j < getNbCols(); j++) {
                if (isEmptyAt(i, j)) { // S'il reste au moins une case vide
                    return false;
                }
            }
        }
        return true; // Aucune case vide : plateau plein
    }
}
