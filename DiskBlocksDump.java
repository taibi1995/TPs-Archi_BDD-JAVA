/**
* TP n° V n° : TP4-V01
* Titre du TP : Blocks Merge Join
* Date : 28 / 11 / 2024
* Nom : TAIBI
* Prénom : Younes
* N° d'étudiant : 22222182
* email : younesnzt95@gmail.com
* */
package BlockDump;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class DiskBlocksDump {
	public static String rechrcheFileDescriptor(int indexFileDescriptor, String mbr) throws IOException {
		
		BufferedReader in  = new BufferedReader(new FileReader(mbr));
		
		String line = in.readLine();
		int i = 0; 
		
		while (i++ < indexFileDescriptor) line = in.readLine();
		
		in.close();
		
		return line;
	}
	

public static  int chargerBlock(String block, String[] memoire, int tailleBlock ) throws IOException {
	
	BufferedReader in  = new BufferedReader(new FileReader(block));
	
	int i = 0;
	
	String line = in.readLine();
	while( line != null && i < tailleBlock) {
		memoire[i++] = line;
		line = in.readLine();
	}
	
	in.close();
	
	return i;
	
}




public static void dechargerMemoire(BufferedWriter out, String[] memoire,  int k) throws IOException {
		
		int i = 0;
		while( i<k ) out.write(memoire[i++]+"\n");
		
}

}

