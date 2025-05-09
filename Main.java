/**
* TP n° V n° : TP1-V01
*
* Titre du TP : Nested Loop Join
*
* Date : 14 / 11 / 2024
*
* Nom : TAIBI
* Prénom : Younes
* N° d'étudiant : 22222182
*
* email : younesnzt95@gmail.com
*
* Remarques :
*/


package TaibiYounesTP1;
import java.io.IOException;


public class Main {
	
	 public static void main(String[] args) {
	        try {
	            // Initialise la jointure avec les fichiers d'entrée
	        	
	            TaibiYounesTP1NestedLoop join = new TaibiYounesTP1NestedLoop("R.txt", "S.txt");

	            // Exécute la jointure et écrit le résultat dans output.txt
	            
	            join.join("RS.txt");

	            System.out.println("La jointure a été exécutée et sauvegardée dans RS.txt.");
	        } catch (IOException e) {
	            System.err.println("Erreur d'E/S : " + e.getMessage());
	        }
	    }

}
