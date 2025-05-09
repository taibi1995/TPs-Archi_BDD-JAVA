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

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TaibiYounesDumpTests {
	
	@Test
	public void testChargerBlock() throws IOException {
	    String blockPath = "tests/fichier_dump_correct/B01.txt";

	    TaibiYounesDiskBlocksDump dump = new TaibiYounesDiskBlocksDump("");
	    String[] memoire = new String[10];
	    
	    int linesRead = dump.chargerBlock(blockPath, memoire);

	    assertEquals(3, linesRead); 
	    assertEquals("A", memoire[0]); 
	    assertEquals("C", memoire[1]); 
	    assertEquals("E", memoire[2]); 
	}
	
	

	    private boolean areFilesEqual(String filePath1, String filePath2) throws IOException {
	        return Files.readAllLines(Paths.get(filePath1)).equals(Files.readAllLines(Paths.get(filePath2)));
	    }

	    @Test
	    public void testDump() throws IOException {
	        TaibiYounesDiskBlocksDump dump = new TaibiYounesDiskBlocksDump("tests/fichier_dump_correct/mbr.txt");

	        dump.chargerFichier("tests/fichier_dump_correct/output.txt", 2, "tests/fichier_dump_correct/", ".txt");

	        assertTrue(areFilesEqual("tests/fichier_dump_correct/output.txt", "tests/fichier_dump_correct/resultat_attendu.txt"));
	    }

	

	
}	
