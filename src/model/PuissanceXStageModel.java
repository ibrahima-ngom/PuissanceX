package model;

import boardifier.model.*;

/**
 * Modèle de jeu pour le Puissance X.
 * Il contient tous les éléments nécessaires à la partie (plateau, jetons, pots),
 * ainsi que les variables d’état pour suivre le déroulement de la partie.
 *
 * Les éléments sont créés dans la factory, pas ici.
 */
public class PuissanceXStageModel extends GameStageModel {

    // Nombre de jetons à aligner pour gagner (choisi par le joueur entre 3 et min(lignes, colonnes))
    private int nbAlign;

    // Numéro du joueur courant : 0 (rouge) ou 1 (jaune)
    private int currentPlayer;

    // Éléments de jeu
    private PuissanceXBoard board;
    private PuissanceXTokenPot redPot;
    private PuissanceXTokenPot yellowPot;
    private PuissanceXToken[] redTokens;
    private PuissanceXToken[] yellowTokens;
    private TextElement textPlayerName;

    public PuissanceXStageModel(String name, Model model) {
        super(name, model);
        nbAlign = -1; // À définir en début de partie par le contrôleur
        currentPlayer = (int)(Math.random() * 2); // Joueur 0 ou 1 au hasard
        setupCallbacks();
    }

    // Getters et setters

    public PuissanceXBoard getBoard() { return board; }
    public void setBoard(PuissanceXBoard board) {
        this.board = board;
        addContainer(board);
    }

    public PuissanceXTokenPot getRedPot() { return redPot; }
    public void setRedPot(PuissanceXTokenPot redPot) {
        this.redPot = redPot;
        addContainer(redPot);
    }

    public PuissanceXTokenPot getYellowPot() { return yellowPot; }
    public void setYellowPot(PuissanceXTokenPot yellowPot) {
        this.yellowPot = yellowPot;
        addContainer(yellowPot);
    }

    public PuissanceXToken[] getRedTokens() { return redTokens; }
    public void setRedTokens(PuissanceXToken[] redTokens) {
        this.redTokens = redTokens;
        for (PuissanceXToken token : redTokens) addElement(token);
    }

    public PuissanceXToken[] getYellowTokens() { return yellowTokens; }
    public void setYellowTokens(PuissanceXToken[] yellowTokens) {
        this.yellowTokens = yellowTokens;
        for (PuissanceXToken token : yellowTokens) addElement(token);
    }

    public TextElement getPlayerName() { return textPlayerName; }
    public void setPlayerName(TextElement textPlayerName) {
        this.textPlayerName = textPlayerName;
        addElement(textPlayerName);
    }

    public int getNbAlign() { return nbAlign; }
    public void setNbAlign(int nbAlign) { this.nbAlign = nbAlign; }

    public int getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(int currentPlayer) { this.currentPlayer = currentPlayer; }

    public int getNbCols() { return board.getNbCols(); }
    public int getNbRows() { return board.getNbRows(); }

    /**
     * Définit les callbacks à déclencher lorsqu’un élément est placé sur le plateau.
     */
    private void setupCallbacks() {
        onPutInContainer((element, containerDest, rowDest, colDest) -> {
            if (containerDest != board) return;
            computePartyResult(rowDest, colDest);
        });
    }

    /**
     * Vérifie si le dernier jeton posé permet de gagner ou si la grille est pleine.
     */
    private void computePartyResult(int row, int col) {
        PuissanceXToken lastToken = (PuissanceXToken) board.getElement(row, col);
        int color = lastToken.getColor();

        // Vérifie les alignements
        if (checkAlignment(row, col, color)) {
            model.setIdWinner(color); // 0 = rouge, 1 = jaune
            model.stopStage();
            return;
        }

        // Si la grille est pleine → match nul
        if (board.isFull()) {
            model.setIdWinner(-1);
            model.stopStage();
        }
    }

    /**
     * Vérifie si un alignement ininterrompu de nbAlign jetons a été réalisé.
     */
    private boolean checkAlignment(int row, int col, int color) {
        return (countAligned(row, col, 1, 0, color) + countAligned(row, col, -1, 0, color) + 1 == nbAlign) || // Vertical
                (countAligned(row, col, 0, 1, color) + countAligned(row, col, 0, -1, color) + 1 == nbAlign) || // Horizontal
                (countAligned(row, col, 1, 1, color) + countAligned(row, col, -1, -1, color) + 1 == nbAlign) || // Diagonale \
                (countAligned(row, col, 1, -1, color) + countAligned(row, col, -1, 1, color) + 1 == nbAlign);   // Diagonale /
    }

    /**
     * Compte les jetons alignés de la même couleur dans une direction donnée.
     */
    private int countAligned(int row, int col, int dRow, int dCol, int color) {
        int count = 0;
        int r = row + dRow;
        int c = col + dCol;

        while (r >= 0 && r < board.getNbRows() && c >= 0 && c < board.getNbCols()) {
            GameElement e = board.getElement(r, c);
            if (e instanceof PuissanceXToken && ((PuissanceXToken) e).getColor() == color) {
                count++;
                r += dRow;
                c += dCol;
            } else break;
        }

        return count;
    }

    @Override
    public StageElementsFactory getDefaultElementFactory() {
        return new PuissanceXStageFactory(this);
    }
}
