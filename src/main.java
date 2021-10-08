import java.sql.SQLOutput;

public class main {

    public static void main (String[] args) {
        System.out.println("Hello Word");
        fournisseur personne = new fournisseur(1);
        agent p1 = new agent(1);

        System.out.println(p1.id);
        System.out.println(personne.id);
        System.out.println(personne.nom);

        p1.run();
    }
}

