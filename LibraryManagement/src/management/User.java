package management;
import java.util.ArrayList;

public class User {
	int id;
	String name;
	String password;
	ArrayList<Book> booksDetail = new ArrayList<>();
	
	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.booksDetail = new ArrayList<>();
	}
}
