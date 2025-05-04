package view;

public class PuissanceXTokenLook {
    // Retourne le caractère à afficher selon la valeur du pion
    // 0 :vide, 1 : rouge, 2 : jaune
    public static char getLook(int pion) {
        switch (pion) {
            case 1:
                return 'O'; // Rouge
            case 2:
                return 'X'; // Jaune
            default:
                return '.'; // Vide
        }
    }
}
