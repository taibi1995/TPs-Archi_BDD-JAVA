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

import java.io.BufferedReader;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;  
import java.io.FileReader;
import java.io.IOException;



public class TaibiYounesTP1NestedLoopTest {

	// Test pour vérifier que le fichier S.txt existe
    @Test
    public void testFichier_StxtExistant() {
        File file = new File("S.txt");
        assertTrue("Le fichier S.txt doit exister.", file.exists());
    }

    // Test pour vérifier que le fichier R.txt existe
    @Test
    public void testFichier_RtxtExistant() {
        File file = new File("R.txt");
        assertTrue("Le fichier R.txt doit exister.", file.exists());
    }

    // Test pour vérifier la construction de la jointure
    @Test
    public void testConstructionJointure() {
        TaibiYounesTP1Jointure jointure = new TaibiYounesTP1NestedLoop("R.txt", "S.txt");
        assertNotNull("La jointure ne doit pas être nulle.", jointure);
    }
    @Test
    public void testJointureSouleveExceptionPourFichierNonExistant() {
        // Fichiers inexistants
        TaibiYounesTP1Jointure jointure = new TaibiYounesTP1NestedLoop("R_inexistant.txt", "S_inexistant.txt");
        
        // On vérifie qu'une exception IOException est lancée lors de la tentative de jointure
        assertThrows(IOException.class, () -> jointure.join("RS.txt"));
    }
    @Test
    public void testJointureCreeUnFichierDeSortie() throws IOException {
        File file = new File("RS.txt");
        
        // Assure-toi que le fichier n'existe pas avant la jointure
        file.delete();
        
        // Créer la jointure avec les fichiers R.txt et S.txt
        TaibiYounesTP1Jointure jointure = new TaibiYounesTP1NestedLoop("R.txt", "S.txt");
        
        // Vérifier que le fichier RS.txt n'existe pas avant la jointure
        assertFalse("Le fichier RS.txt ne doit pas exister avant la jointure.", file.exists());
        
        // Effectuer la jointure et créer le fichier de sortie
        jointure.join("RS.txt");
        
        // Vérifier que le fichier RS.txt existe après la jointure
        assertTrue("Le fichier RS.txt doit exister après la jointure.", file.exists());
        
        // Nettoyer : supprimer le fichier RS.txt après le test
        file.delete();
    }
    private static boolean areFilesEqual(String filePath1, String filePath2) {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(filePath1));
             BufferedReader reader2 = new BufferedReader(new FileReader(filePath2))) {

            String line1, line2;

            // Lire les lignes des deux fichiers et comparer
            while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
                if (!line1.equals(line2)) {
                    return false; // Si une ligne diffère, les fichiers ne sont pas égaux
                }
            }

            // Vérifier si les deux fichiers ont le même nombre de lignes
            if (reader1.readLine() != null || reader2.readLine() != null) {
                return false; // Si un fichier a plus de lignes que l'autre, ils sont différents
            }

            return true; // Les fichiers sont égaux

        } catch (IOException e) {
            e.printStackTrace(); // Afficher l'erreur si la lecture échoue
            return false; // Retourner false en cas d'erreur
        }
    }

    @Test
    public void jointureSimpleEntreRetS() throws IOException {
        // Créer la jointure entre R.txt et S.txt
        TaibiYounesTP1Jointure jointure = new TaibiYounesTP1NestedLoop("./test/test_jointure_simple/R.txt", "./test/test_jointure_simple/S.txt");
        
        // Effectuer la jointure et enregistrer le résultat dans RS.txt
        jointure.join("./test/test_jointure_simple/RS.txt");
        
        // Vérifier que le fichier RS.txt est égal au fichier de résultats attendu
        assertTrue("Le fichier de jointure doit correspondre au résultat attendu.",
                areFilesEqual("./test/test_jointure_simple/RS.txt", "./test/test_jointure_simple/RS_attendu.txt"));
    }
    
    @Test
    public void pas_de_jointure() throws IOException {
        // Créer la jointure entre R.txt et S.txt
        TaibiYounesTP1Jointure jointure = new TaibiYounesTP1NestedLoop("./test/test_pas_de_jointure/R.txt", "./test/test_pas_de_jointure/S.txt");
        
        // Effectuer la jointure et enregistrer le résultat dans RS.txt
        jointure.join("./test/test_pas_de_jointure/RS.txt");
        
        // Vérifier que le fichier RS.txt existe
        File rsFile = new File("./test/test_pas_de_jointure/RS.txt");

        // Vérifier que le fichier existe
        assertTrue("Le fichier RS.txt doit exister.", rsFile.exists());
         // Vérifier si le caractère lu est un caractère visible autre qu'un espace ou un saut de ligne.
        // La méthode isWhitespace() retourne true pour les espaces, les tabulations, les retours à la ligne, etc.
        // Si un caractère visible est trouvé (autre qu'un espace ou un saut de ligne), nous échouons le test.
        try (BufferedReader reader = new BufferedReader(new FileReader(rsFile))) {
            int character;
            while ((character = reader.read()) != -1) {
                // Si un caractère visible (autre que espace ou saut de ligne) est trouvé, le test échoue
                if (!Character.isWhitespace(character)) {
                    fail("Le fichier RS.txt ne doit pas contenir de caractères visibles.");
                }
            }
        }
    }


    

    
    
    @Test
    public void testFichierRManquant() {
        // Fichier R.txt manquant, mais S.txt présent
        try {
            TaibiYounesTP1Jointure jointure = new TaibiYounesTP1NestedLoop("./test/test_fichier_R_manquant/ManqueR.txt", "./test/test_fichier_R_manquant/S.txt");
            jointure.join("./test/test_fichier_R_manquant/RS.txt");
            fail("Une exception devait être levée car R.txt est manquant.");
        } catch (IOException e) {
            // Vérifier que l'exception est bien une IOException
            assertTrue(e.getMessage().contains("R.txt"));
        }
    }

    @Test
    public void testFichierSManquant() {
        // Fichier S.txt manquant, mais R.txt présent
        try {
            TaibiYounesTP1Jointure jointure = new TaibiYounesTP1NestedLoop("./test/test_fichier_S_manquant/R.txt", "./test/test_fichier_S_manquant/S.txt");
            jointure.join("./test/test_fichier_S_manquant/RS.txt");
            fail("Une exception devait être levée car S.txt est manquant.");
        } catch (IOException e) {
            // Vérifier que l'exception est bien une IOException
            assertTrue(e.getMessage().contains("S.txt"));
        }
    }

}