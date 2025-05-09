/**
* TP n° V n° : TP2-V01

* Titre du TP : R File Disk Blocks Dump

* Date : 21 / 11 / 2024

* Nom : TAIBI
* Prénom : Younes
* N° d'étudiant : 22222182

* email : younesnzt95@gmail.com
*/


package TaibiYounes_TP2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaibiYounesDiskBlocksDump {

    private String mbr;
    private static final int TAILLE_BLOCK = 10;

    public TaibiYounesDiskBlocksDump(String source) {
        this.mbr = source;
    }
     // Recherche le file descriptor d'un fichier via son indice dans le mbr
    private String rechercherFileDescriptor(int indexFileDescriptor) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(mbr));
        String line;
        int currentIndex = 0;

        while ((line = reader.readLine()) != null) {
            if (currentIndex == indexFileDescriptor) {
                reader.close();
                return "B" + line.trim();  
            }
            currentIndex++;
        }

        reader.close();
        return null;  
    }
     // Charge un fichier dans un tableau
    
    int chargerBlock(String block, String[] memoire) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(block));
        int index = 0;
        String line = in.readLine();
        while (line != null && index < TAILLE_BLOCK) {
            memoire[index++] = line;
            line = in.readLine();
        }
        in.close();
        return index;  
    }

    public void chargerFichier(String fileOut, int indexFD, String path, String extension) throws IOException {
        String fileDescriptorPath = path + rechercherFileDescriptor(indexFD) + extension;

        
        BufferedWriter out = new BufferedWriter(new FileWriter(fileOut));
        String[] descriptor = new String[TAILLE_BLOCK];
        String[] blockData = new String[TAILLE_BLOCK];

        int numberOfDescriptors = chargerBlock(fileDescriptorPath, descriptor);

        for (int i = 0; i < numberOfDescriptors; i++) {
            String blockPath = path + descriptor[i] + extension;

            File blockFile = new File(blockPath);
            if (!blockFile.exists()) {
                System.out.println("Erreur: Le fichier du bloc " + blockPath + " n'existe pas.");
                continue;
            }

            int numberOfLinesLoaded = chargerBlock(blockPath, blockData);
            dechargerMemoire(out, blockData, numberOfLinesLoaded);
        }

        out.close();
    }

    private void dechargerMemoire(BufferedWriter out, String[] memoire, int nombreDeLignesChargees) throws IOException {
        for (int i = 0; i < nombreDeLignesChargees; i++) {
            out.write(memoire[i]);
            out.newLine(); 
        }
    }
}