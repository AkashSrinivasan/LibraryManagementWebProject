package management;
import java.io.File;
import java.io.IOException;

public class LoadAllFile {
	public static void createAllFile(){
		String path = System.getProperty("user.home") + "\\Desktop\\Library";
		File file = new File(path);
		file.mkdir();
		File studentFile = new File(path+"\\Student.txt");
		try {
			studentFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File staffFile = new File(path+"\\Staff.txt");
		try {
			staffFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File allBooksFile = new File(path+"\\AllBooks.txt");
		try {
			allBooksFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
