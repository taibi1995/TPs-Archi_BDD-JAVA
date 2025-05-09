/**
* TP n° V n° : TP4-V01
* Titre du TP : Blocks Merge Join
* Date : 20/ 01 / 2025
* 
* Nom : TAIBI
* Prénom : Younes
* N° d'étudiant : 22222182
* email : younesnzt95@gmail.com
* */
package FreeList;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class FreeListBM implements FreeList {

    private char[] map; 
    private int nbBlocks;
    private String path; 
    private String extension;
    private String prefix; 
    private String freeListBlock; 

    public FreeListBM(String mbr, int tailleBlock, String path, String extension, String prefix) throws IOException {
        this.nbBlocks = tailleBlock;
        this.path = path;
        this.extension = extension;
        this.prefix = prefix;
        this.freeListBlock = path + "FL" + extension;

       
        this.map = new char[nbBlocks];

        initFreeBlocks();
    }

   
    private void initFreeBlocks() throws IOException {
        for (int i = 0; i < nbBlocks; i++) {
            String blockName = prefix + String.format("%02d", i) + extension;
            File blockFile = new File(path + blockName);

            if (!blockFile.exists()) {
                blockFile.createNewFile();
                map[i] = 0;
            } else {
                if (blockFile.length() == 0) {
                    map[i] = 0; 
                } else {
                    map[i] = 1; 
                }
            }
        }

        saveFreeList();
    }

   
    private void saveFreeList() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(freeListBlock))) {
            for (int i = 0; i < nbBlocks; i++) {
                writer.write(map[i] + "\n");
            }
        }
    }

   
    @Override
    public String getBlock() throws IOException {
        for (int i = 0; i < nbBlocks; i++) {
            if (map[i] == 0) { 
                map[i] = 1; 
                saveFreeList(); 
                return prefix + String.format("%02d", i); 
            }
        }
        throw new IOException("Aucun bloc libre disponible !");
    }

  
    @Override
    public void putBlock(String block) throws IOException {
        int index = Integer.parseInt(block.substring(prefix.length()));
        if (index >= 0 && index < nbBlocks) {
            map[index] = 0; 
            saveFreeList(); 
        } else {
            throw new IOException("Nom de bloc invalide : " + block);
        }
    }
}