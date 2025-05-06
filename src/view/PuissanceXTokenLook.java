package view;

import boardifier.model.GameElement;
import boardifier.view.ElementLook;
import model.PuissanceXToken;
import boardifier.view.ConsoleColor;

public class PuissanceXTokenLook extends ElementLook {

    public PuissanceXTokenLook(GameElement element) {
        super(element, 1, 1); // 1x1 case
    }

    @Override
    protected void render() {
        PuissanceXToken token = (PuissanceXToken) element;
        if (token.getColor() == PuissanceXToken.TOKEN_RED) {
            shape[0][0] = ConsoleColor.WHITE + ConsoleColor.RED_BACKGROUND + "R" + ConsoleColor.RESET;
        } else {
            shape[0][0] = ConsoleColor.BLACK + ConsoleColor.YELLOW_BACKGROUND + "J" + ConsoleColor.RESET;
        }
    }
}