/**
* TP n° V n° : TP4-V01
* Titre du TP : Blocks Merge Join
* Date : 20/ 01 / 2025
* Nom : TAIBI
* Prénom : Younes
* N° d'étudiant : 22222182
* email : younesnzt95@gmail.com
* */
package FreeList;


import java.io.IOException;

public interface FreeList {
	
		
		public String getBlock() throws IOException;
		public void putBlock(String idBlock) throws IOException;

	

}