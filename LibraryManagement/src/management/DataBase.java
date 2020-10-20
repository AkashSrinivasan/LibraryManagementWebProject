package management;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class DataBase {
	static final int ADMIN_ID = 999;
	static final String ADMIN_PASSWORD = "main";
	
	
	public static ArrayList<Book> getAllBooks() {
		File file = new File(System.getProperty("user.home")+"\\Desktop\\Library\\AllBooks.txt");
		ArrayList<Book> allBooks = null;
		try {
			BufferedReader BookReader = new BufferedReader(new FileReader(file));			
			allBooks= Book.convertAllBookToObject(BookReader);
			BookReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allBooks;
	}
	
	public static  void setVariable(int lineNumber, String data, String filepath) throws IOException {
	    Path path = Paths.get(filepath);
	    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
	    lines.set(lineNumber, data);
	    Files.write(path, lines, StandardCharsets.UTF_8);
	}
	
	
	public static void repleceBookInUserFile(int id,ArrayList<Book> bookDetails,Book newlyAddedBook, String userType) {
		
		String newString = Book.convertBookDetailsToString(bookDetails,newlyAddedBook);
		System.out.println(newString);
		File studentFile = new File(System.getProperty("user.home")+"\\Desktop\\Library\\"+userType+".txt");
		FileReader filereader;
		try {
			filereader = new FileReader(studentFile);
			BufferedReader bufferedReader =  new BufferedReader(filereader);
			try {
				String line = bufferedReader.readLine();
				String oldString = "";
				int linenumber = 0;
				while(line != null) {
					String[] word = line.split(" - ");
					if(line.contains("id") && Integer.parseInt(word[1])==id) {
						linenumber++;
						line = bufferedReader.readLine();
						linenumber++;
						line = bufferedReader.readLine();
						linenumber++;
						line = bufferedReader.readLine();
						oldString = line;
						setVariable(linenumber,newString,System.getProperty("user.home")+"\\Desktop\\Library\\"+userType+".txt");
						break;
					}
					linenumber++;
					line = bufferedReader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean checkStudentAlreadyPresent(User student,String user) {
		File studentFile = new File(System.getProperty("user.home") + "\\Desktop\\Library\\"+user+".txt");
		FileReader filereader;
		try {
			filereader = new FileReader(studentFile);
			BufferedReader bufferedReader =  new BufferedReader(filereader);
			try {
				String line = bufferedReader.readLine();
				ArrayList<Integer> allStudentId = new ArrayList<>();
				while(line != null) {
					String[] word = line.split(" - ");
					if(line.contains("id")) {
						allStudentId.add(Integer.parseInt(word[1]));
					}
					line = bufferedReader.readLine();
				}
				filereader.close();
				if(allStudentId.contains(student.id)) {
					return true;
				}
				FileWriter fileWriter = new FileWriter(studentFile,true);
				BufferedWriter bufferedWriter  = new BufferedWriter(fileWriter);
				bufferedWriter.write("id - "+student.id + "\n");
				bufferedWriter.write("name - "+student.name + "\n");
				bufferedWriter.write("password - "+student.password + "\n");
				bufferedWriter.write("book - null"+"\n");
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}	
	
	public static void addBookToFile(ArrayList<Book> allBooks) {
		File file = new File(System.getProperty("user.home")+"\\Desktop\\Library\\AllBooks.txt");
		try {
			file.delete();
			file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			for(Book i:allBooks) {
				try {
					bufferedWriter.write("bookId - "+i.id+"\n");
					bufferedWriter.write("bookName - "+i.name+"\n");
					bufferedWriter.write("barrowerId - "+i.barrowerId+"\n");
					bufferedWriter.write("borrowedOn - "+i.borrowedOn+"\n");
					bufferedWriter.write("dueDate - "+i.dueDate+"\n");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addNewBook(Book book) {
		File file = new File(System.getProperty("user.home")+"\\Desktop\\Library\\AllBooks.txt");
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
			bufferedWriter.write("bookId - "+book.id+"\n");
			bufferedWriter.write("bookName - "+book.name+"\n");
			bufferedWriter.write("barrowerId - "+book.barrowerId+"\n");
			bufferedWriter.write("borrowedOn - "+book.borrowedOn+"\n");
			bufferedWriter.write("dueDate - "+book.dueDate+"\n");
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String loginDetails(File file,int id,String password){
		FileReader fileReader;
		String result = "";
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			try {
				String line = bufferedReader.readLine();
				while(line != null) {
					String[] word = line.split(" - ");
					if(line.contains("id") && Integer.parseInt(word[1])==id) {
						result +=line+"\n";
						line = bufferedReader.readLine();
						result +=line+"\n";
						line = bufferedReader.readLine();
						word = line.split(" - ");
						if(line.contains("password") && word[1].equals(password)) {
							result +=line+"\n";
							line = bufferedReader.readLine();
							result +=line+"\n";
							return result;
						}
						return "";
						
					}
					line = bufferedReader.readLine();
				}
				fileReader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void deleteUserIdFromBookFile(int id) {
		System.out.println("id==="+id);
		
		ArrayList<Book> allBooks = getAllBooks();
		
		for(Book book:allBooks) {
			System.out.println("id+"+book.id);
			if(id == book.barrowerId) {
				book.barrowerId = 0;
				book.borrowedOn = null;
				book.dueDate = null;
				break;
			}
		}
		
		for(Book book:allBooks) {
			System.out.println("id+"+book.id);
			System.out.println("barrowerId"+book.barrowerId);
			System.out.println("borrowedOn"+book.borrowedOn);
			System.out.println("duedate"+book.dueDate);
			System.out.println("");
		}
		addBookToFile(allBooks);
	}
	
	public static String getDetailsFromFile(String user) {
		File studentFile = new File(System.getProperty("user.home")+"\\Desktop\\Library\\"+user+".txt");
		FileReader fileReader;
		BufferedReader studentBufferedReader = null;
		try {
			fileReader = new FileReader(studentFile);
			studentBufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String allDetails = null;
		String line;
		try {
			line = studentBufferedReader.readLine();
			if(line != null) {
				while(line != null) {
					if(!line.contains("password")) {
						allDetails +="<p>"+line+"</p>"; 
					}
					line = studentBufferedReader.readLine();
				}
				studentBufferedReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allDetails;
	}

}
