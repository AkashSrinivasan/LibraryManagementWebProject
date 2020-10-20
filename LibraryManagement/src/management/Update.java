package management;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		ArrayList<Book> allBooks = DataBase.getAllBooks();
		int bookId = Integer.parseInt(request.getParameter("bookid"));
		Book book= null;
		User user = (User) getServletContext().getAttribute("user");
		for(Book b : allBooks) {
			if(b.id==bookId ) {
				book = b;
				break;
			}
		}
		if(book != null) {
			allBooks.remove(book);
			
			book.barrowerId = user.id;
			book.borrowedOn = LocalDateTime.now();
			book.dueDate = book.borrowedOn.plusDays(15);
			Iterator<Book> itr = allBooks.iterator();
			while (itr.hasNext()) {
	            Book bookToDelete = itr.next();
	            if (bookToDelete.id == user.id) {
	                itr.remove();
	                break;
	            }
	        }
			allBooks.add(book);
			
			DataBase.addBookToFile(allBooks);
			Cookie[] cookies = request.getCookies();
			String userType = null;
			for(Cookie cookie : cookies){
			    if("userType".equals(cookie.getName())){
			    	userType = cookie.getValue();
			    }
			}
			DataBase.repleceBookInUserFile(user.id,user.booksDetail,book,userType);
			System.out.println(user.id);
			User currentUser = new User(user.id, user.name, user.password);
			if(user.booksDetail != null) {
				currentUser.booksDetail = user.booksDetail;
			}
			
			currentUser.booksDetail.add(book);
			getServletContext().removeAttribute("user");
			
			
//			user.booksDetail.add(book);
			getServletContext().setAttribute("user", currentUser);
			
			System.out.println("repleced sucessfully");
		}else {
			System.out.println("please enter the vaild book id");
		
		}
		response.sendRedirect("userHome");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
