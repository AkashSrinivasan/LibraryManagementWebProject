package management;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/returnBookToLibrary")
public class ReturnBookToLibrary extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ReturnBookToLibrary() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		User user = (User) getServletContext().getAttribute("user");
		ArrayList<Book> allBooks = user.booksDetail;
		int id = Integer.parseInt(request.getParameter("bookid"));
		Book book= null;
		for(Book b : allBooks) {
			if(b.id==id ) {
				book = b;
				break;
			}
		}
		if(book!=null) {
			allBooks.remove(book);
			Cookie[] cookies = request.getCookies();
			String userType = null;
			for(Cookie cookie : cookies){
			    if("userType".equals(cookie.getName())){
			    	userType = cookie.getValue();
			    }
			}
			DataBase.repleceBookInUserFile(user.id,allBooks,null,userType);
			DataBase.deleteUserIdFromBookFile(user.id);
			
			System.out.println("updated sucessfully");
		}else {
			System.out.println("This book id is not valid");
		}
		User currentUser = new User(user.id, user.name, user.password);
		currentUser.booksDetail = allBooks;
		getServletContext().removeAttribute("user");
		getServletContext().setAttribute("user", currentUser);
		response.sendRedirect("userHome");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
