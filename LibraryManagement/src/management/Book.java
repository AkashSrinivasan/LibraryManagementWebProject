package management;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletContext;

public class Book {
	int id;
	String name;
	int barrowerId;
	LocalDateTime borrowedOn;
	LocalDateTime dueDate;
	
	Book(int id,String name,int barrowerId,LocalDateTime borrowedOn,LocalDateTime dueDate ){
		this.id = id;
		this.name = name;
		this.barrowerId = barrowerId;
		this.borrowedOn = borrowedOn;
		this.dueDate = dueDate;
	}
	Book(){
		
	}
	
	public static ArrayList<Book> convertAllBookToObject(BufferedReader bookReader){
		ArrayList<Book> allBookList = new ArrayList<>();
		String line;
		try {
			line = bookReader.readLine();
			while(line != null) {
				String[] word = line.split(" - ");
				if(line.contains("bookId")) {
					Book book = new Book();
					book.id =Integer.parseInt(word[1]) ;
					line = bookReader.readLine();
					word = line.split(" - ");
					book.name = word[1];
					line = bookReader.readLine();
					word = line.split(" - ");
					book.barrowerId =Integer.parseInt(word[1]);
					line = bookReader.readLine();
					word = line.split(" - ");
					if(!word[1].equals("null")) {
						book.borrowedOn = LocalDateTime.parse(word[1]);
					}else {
						book.borrowedOn = null;
					}
					line = bookReader.readLine();
					word = line.split(" - ");
					if(!word[1].equals("null")) {
						book.dueDate =LocalDateTime.parse(word[1]);
					}else {
						book.dueDate = null;
					}
					allBookList.add(book);
				}
				line = bookReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allBookList;
	}
	
	public static StringBuffer displayAvailableBookFromLibrary(ArrayList<Book> allBooks) {
		ArrayList<Book> availableBooks = new ArrayList<>();
		StringBuffer result = new StringBuffer();
		allBooks.stream().forEach((i)-> {
			if(i.barrowerId == 0) {
				availableBooks.add(i);
			}
		});
		if(availableBooks.size()==0) {
			
			return result;
		}else {
			result.append("<p>Only these books are available right now.! </p> \r\n");
			int[] bookCount = {0};
			allBooks.stream().forEach((i)-> {
				if(i.barrowerId == 0) {
					result.append("<p>Book - "+ (bookCount[0]+1)+"</p>\r\n");
					bookCount[0] = bookCount[0]+1;
					result.append("<p>----------------------------------------</p> \r\n");
					availableBooks.add(i);
					result.append("<p>Book id = "+i.id+"</p>\r\n");
					result.append("<p>Book Name = "+i.name+"</p>\r\n\r\n");
				}
			});
		}
		return result;
	}
	
	
	public static boolean isBookAvailable(ArrayList<Book> allBooks) {
		ArrayList<Book> availableBooks = new ArrayList<>();
		allBooks.stream().forEach((i)-> {
			if(i.barrowerId == 0) {
				availableBooks.add(i);
			}
		});
		if(availableBooks.size()==0) {
			return false;
		}
		return true;
	}
	
	public static String convertBookDetailsToString(ArrayList<Book> bookDetails,Book newlyAddedBook) {
		String detailsAsString = "book - ";
		if(bookDetails!=null) {
			for(Book i: bookDetails) {
			detailsAsString+=i.id+" -> "+i.name+" -> "+i.barrowerId+" -> "+i.borrowedOn+" -> "+i.dueDate+",";
			}
		}
		if(newlyAddedBook != null) {
			detailsAsString+=newlyAddedBook.id+" -> "+newlyAddedBook.name+" -> "+newlyAddedBook.barrowerId+" -> "+newlyAddedBook.borrowedOn+" -> "+newlyAddedBook.dueDate+",";
		}
		if(bookDetails !=null && newlyAddedBook == null ) {
			detailsAsString+="null";
		}
		System.out.println("in book class");
		System.out.println(detailsAsString);
		return detailsAsString;
	}
}
