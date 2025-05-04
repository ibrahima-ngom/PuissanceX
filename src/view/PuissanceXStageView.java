package view;

public class PuissanceXStageView {
    // Affiche la grille complète à partir d'une matrice de pions
    public void afficherGrille(int[][] grille) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                System.out.print(PuissanceXTokenLook.getLook(grille[i][j]) + " ");
            }
            System.out.println();
        }
    }

    // Affichage de  message de victoire
    public void afficherMessageVictoire(int joueur) {
        System.out.println("Victoire du joueur " + joueur + " !");
    }

    //  un message d'égalité
    public void afficherMessageEgalite() {
        System.out.println("Match nul !");
    }

    // Affichage d'un message générique
    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
