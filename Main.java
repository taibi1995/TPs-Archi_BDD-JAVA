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

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
     

        Jointure j = new Jointure("B00", 4, "BDD/", ".txt","B");
        System.out.println("Nombre d'opérations d'E/S réalisées : " + j.join(1, 2));
    }
}