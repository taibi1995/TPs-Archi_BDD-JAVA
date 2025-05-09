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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implémentation d'une jointure Nested Loop pour deux fichiers de caractères.
 *
 * Cette classe permet d'effectuer une jointure en boucle imbriquée sur deux fichiers d'entrée 
 * et d'enregistrer le résultat dans un fichier de sortie.
 */
public class TaibiYounesTP1NestedLoop implements TaibiYounesTP1Jointure {
    
    private String inputFile1;
    private String inputFile2;

    /**
     * Initialisation de l'objet avec deux fichiers d'entrée.
     * 
     * @param filename1 Nom du premier fichier d'entrée.
     * @param filename2 Nom du second fichier d'entrée.
     */
    public TaibiYounesTP1NestedLoop(String filename1, String filename2) {
        this.inputFile1 = filename1;
        this.inputFile2 = filename2; 
    }
    
    /**
     * Exécute la jointure en boucle imbriquée et enregistre le résultat.
     * 
     * @param output Nom du fichier de sortie pour le résultat de la jointure.
     * @throws IOException en cas d'erreurs d'entrée/sortie.
     */
    public void join(String output) throws IOException  {
        char[] table1 = new char[10];  // Stocke le contenu du premier fichier
        char[] table2 = new char[10];  // Stocke le contenu du second fichier
        char[] result = new char[10];  // Stocke les résultats de la jointure

        // Lit les fichiers d'entrée pour remplir les tableaux de caractères
        readFile(this.inputFile1, table1);
        readFile(this.inputFile2, table2);

        // Effectue la jointure et récupère le nombre de correspondances trouvées
        int resultCount = nestedLoop(table1, table2, result);
        
        // Écrit le résultat de la jointure dans le fichier de sortie
        writeFile(result, resultCount, output);
    }
    
    /**
     * Effectue la jointure en comparant chaque élément de deux tables.
     * 
     * @param table1 Tableau de caractères du premier fichier.
     * @param table2 Tableau de caractères du second fichier.
     * @param output Tableau pour stocker le résultat de la jointure.
     * @return Nombre de caractères communs écrits dans le tableau output.
     */
    private int nestedLoop(char[] table1, char[] table2, char[] output) {
        int resultCount = 0;

        // Parcourt les deux tables pour identifier les correspondances
        for (int i = 0; i < table1.length; i++) {
            for (int j = 0; j < table2.length; j++) {
                // Ajoute les caractères communs au tableau de sortie
                if (table1[i] == table2[j] && resultCount < output.length) {
                    output[resultCount++] = table1[i];
                }
            }
        }
        return resultCount;
    }
    
    /**
     * Lit un fichier et remplit le tableau donné avec ses caractères.
     * 
     * @param filename Nom du fichier à lire.
     * @param table Tableau où sont stockés les caractères du fichier.
     * @throws IOException en cas d'erreurs de lecture.
     */
    private void readFile(String filename, char[] table) throws IOException {
        File file = new File(filename);
        
        // Utilisation de try-with-resources pour fermer automatiquement le fichier
        try (FileReader fr = new FileReader(file)) {
            int charsRead = fr.read(table, 0, table.length);
            
            // Remplit les positions restantes avec des espaces s'il y a moins de 10 caractères
            for (int i = charsRead; i < table.length; i++) {
                table[i] = ' ';
            }
        }
    }
    
    /**
     * Écrit les caractères d'un tableau dans un fichier de sortie.
     * 
     * @param table Tableau contenant les données à écrire.
     * @param count Nombre de caractères du tableau à écrire.
     * @param filename Nom du fichier de sortie.
     * @throws IOException en cas d'erreurs d'écriture.
     */
    private void writeFile(char[] table, int count, String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename, false)) {
            fw.write(table, 0, count);  // Écriture des résultats de la jointure
        }
    }
}
