// file path : "C:\Users\VIPIN\Desktop\Pokemon data - Sheet1.csv"

//importing the packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCSVFile {

	public static void main(String args) {
		String fileName = new String("C:/Users/VIPIN/Desktop/Pokemon data - Sheet1.csv");
		File file = new File(fileName);
		try {
			Scanner inputStream = new Scanner(file);
			// hasNext loops line by line
			while (inputStream.hasNext()) {
				// read single line, put in string
				String data = inputStream.next();
				System.out.println(data);
			}
			// after loop, close scanner
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
