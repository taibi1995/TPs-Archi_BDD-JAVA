/**
* TP n° V n° : TP4-V01
* Titre du TP : Blocks Merge Join
* Date : 20/ 01 / 2025
* Nom : TAIBI
* Prénom : Younes
* N° d'étudiant : 22222182
* email : younesnzt95@gmail.com
* */
package Jointure;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import FreeList.FreeList;
import FreeList.FreeListBM;
import BlockDump.DiskBlocksDump;

public class Jointure {

    private final int tailleBlock = 10;

    private final String mbr; 
    private final int indexFolder;
    private final String path; 
    private final String extension; 
    private final String prefix;

    private FreeList freeList; 
    private String block; 
    private BufferedWriter out;
    private String[] RSTd; 
    private int iRSTd; 
    private int cptIO; 

    public Jointure(String mbr, int indexFolder, String path, String extension, String prefix) throws IOException {
        this.mbr = mbr;
        this.indexFolder = indexFolder;
        this.path = path;
        this.extension = extension;
        this.prefix = prefix;

        this.freeList = new FreeListBM(mbr, tailleBlock, path, extension, prefix);
    }

    public int join(int indexFdR, int indexFdS) throws IOException {
        iRSTd = 0;
        cptIO = 0;

        RSTd = new String[tailleBlock]; 
        String[] RTd = new String[tailleBlock];
        String[] STd = new String[tailleBlock]; 
        String[] FTd = new String[tailleBlock]; 
        String[] RTj = new String[tailleBlock]; 
        String[] STj = new String[tailleBlock]; 
        String[] RSTj = new String[tailleBlock]; 

        String folderPath = path + DiskBlocksDump.rechrcheFileDescriptor(indexFolder, path + mbr + extension) + extension;
        String fdR = path + DiskBlocksDump.rechrcheFileDescriptor(indexFdR, folderPath) + extension;
        String fdS = path + DiskBlocksDump.rechrcheFileDescriptor(indexFdS, folderPath) + extension;

        int iRtd = DiskBlocksDump.chargerBlock(fdR, RTd, tailleBlock); 
        int iStd = DiskBlocksDump.chargerBlock(fdS, STd, tailleBlock); 
        int iFTd = DiskBlocksDump.chargerBlock(folderPath, FTd, tailleBlock); 

        
        int i = 0, j = 0; 
        int indiceR = 0, indiceS = 0;
        int k = 0; 

        
        int iRTj = 0, iSTj = 0;
        if (i < iRtd) {
            iRTj = DiskBlocksDump.chargerBlock(path + RTd[i++] + extension, RTj, tailleBlock);
            cptIO++;
        }
        if (j < iStd) {
            iSTj = DiskBlocksDump.chargerBlock(path + STd[j++] + extension, STj, tailleBlock);
            cptIO++;
        }

        while (iRTj > 0 && iSTj > 0) {
           
            if (indiceR >= iRTj || indiceS >= iSTj) {
                break; 
            }

            if (RTj[indiceR].compareTo(STj[indiceS]) == 0) {
                if (k == 0) {
                    this.block = freeList.getBlock();
                    this.RSTd[this.iRSTd++] = block;
                    this.out = new BufferedWriter(new FileWriter(path + block + extension));
                }
                RSTj[k++] = RTj[indiceR]; 
                if (k == tailleBlock) {
                    DiskBlocksDump.dechargerMemoire(out, RSTj, k);
                    out.flush();
                    cptIO++;
                    k = 0;
                }
                indiceR++;
                indiceS++;
            } else if (RTj[indiceR].compareTo(STj[indiceS]) < 0) {
                indiceR++;
            } else {
                indiceS++;
            }

            if (indiceR >= iRTj && i < iRtd) {
                indiceR = 0;
                iRTj = DiskBlocksDump.chargerBlock(path + RTd[i++] + extension, RTj, tailleBlock);
                cptIO++;
            }
            if (indiceS >= iSTj && j < iStd) {
                indiceS = 0;
                iSTj = DiskBlocksDump.chargerBlock(path + STd[j++] + extension, STj, tailleBlock);
                cptIO++;
            }
        }

        if (k > 0) {
            DiskBlocksDump.dechargerMemoire(out, RSTj, k);
            out.flush();
            cptIO++;
        }

        block = freeList.getBlock();
        this.out = new BufferedWriter(new FileWriter(path + block + extension));
        DiskBlocksDump.dechargerMemoire(out, RSTd, iRSTd);
        out.flush();

        FTd[iFTd++] = block;
        this.out = new BufferedWriter(new FileWriter(folderPath));
        DiskBlocksDump.dechargerMemoire(out, FTd, iFTd);
        out.flush();

        out.close();
        return cptIO; 
    }
}