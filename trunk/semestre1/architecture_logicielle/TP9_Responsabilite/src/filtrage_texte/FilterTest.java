package filtrage_texte;
import java.io.*;

public class FilterTest {
	public static IFilter test1() {
		Filter temp = new SkipFilter(null, 3, 3);
		temp.setSuccessor(new UpperCase(null));
		return temp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(
			new InputStreamReader(
				new FileInputStream("src/filtrage_texte/fichier1.txt")));
		String line;

		// Get the Filter to apply on each line :		
		IFilter f=test1();	// Test 1

		// Process each line :
		while ((line = in.readLine()) != null) {
			System.out.println(f.filter(line)); // <- To be filtered !
		}
		
	}
}
