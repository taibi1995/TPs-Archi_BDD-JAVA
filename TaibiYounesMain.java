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

import java.io.IOException;

public class TaibiYounesMain {
    public static void main(String[] args) throws IOException {
    	
        TaibiYounesDiskBlocksDump i = new TaibiYounesDiskBlocksDump("filedicskblocks-master/R/B00.txt");

        i.chargerFichier("out.txt", 2, "filedicskblocks-master/R/", ".txt");
    }
}