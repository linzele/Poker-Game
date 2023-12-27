import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class AdminTxt {
	public static void main(String[] args) {
		// Write players to the txt file
		String outputFileName = "admin.txt";

		try {
			PrintWriter pw = new PrintWriter(outputFileName);

			pw.write("0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e");
			pw.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File is unable to open for writing");
		}
	}
}