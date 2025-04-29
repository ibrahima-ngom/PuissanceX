package model;

import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;
import boardifier.model.TextElement;

/**
 * PuissanceXStageFactory doit créer tous les éléments de jeu définis dans PuissanceXStageModel.
 *
 */
public class PuissanceXStageFactory extends StageElementsFactory {

    private PuissanceXStageModel stageModel;

    public PuissanceXStageFactory(GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (PuissanceXStageModel) gameStageModel;
    }

    @Override
    public void setup() {
        // Crée le texte affichant le nom du joueur, positionné en (0,0)
        TextElement text = new TextElement(stageModel.getCurrentPlayerName(), stageModel);
        text.setLocation(0, 0);
        stageModel.setPlayerName(text);

        // Crée le plateau PuissanceX, positionné en (0,1)
        int nbCols = stageModel.getNbCols();
        int nbRows = stageModel.getNbRows();
        PuissanceXBoard board = new PuissanceXBoard(0, 1, nbCols, nbRows, stageModel);
        stageModel.setBoard(board);

        // Calcule le nombre de jetons par joueur
        int nbTokens = (int) Math.ceil((nbCols * nbRows) / 2.0); // Taille grille / 2 arrondi supérieur

        // Crée les pots de jetons rouges et jaunes
        PuissanceXTokenPot redPot = new PuissanceXTokenPot(20, 0, nbTokens, stageModel);
        PuissanceXTokenPot yellowPot = new PuissanceXTokenPot(27, 0, nbTokens, stageModel);
        stageModel.setRedPot(redPot);
        stageModel.setYellowPot(yellowPot);

        // Crée les jetons rouges
        PuissanceXToken[] redTokens = new PuissanceXToken[nbTokens];
        for (int i = 0; i < nbTokens; i++) {
            redTokens[i] = new PuissanceXToken(PuissanceXToken.TOKEN_RED, stageModel);
        }
        stageModel.setRedTokens(redTokens);

        // Crée les jetons jaunes
        PuissanceXToken[] yellowTokens = new PuissanceXToken[nbTokens];
        for (int i = 0; i < nbTokens; i++) {
            yellowTokens[i] = new PuissanceXToken(PuissanceXToken.TOKEN_YELLOW, stageModel);
        }
        stageModel.setYellowTokens(yellowTokens);

        // Place les jetons rouges et jaunes dans leur pot respectif
        for (int i = 0; i < nbTokens; i++) {
            redPot.addElement(redTokens[i], i, 0);
            yellowPot.addElement(yellowTokens[i], i, 0);
        }
    }
}
