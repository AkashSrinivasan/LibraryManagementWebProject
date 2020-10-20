package management;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetBook
 */
@WebServlet("/getBook")
public class GetBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public GetBook() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		ArrayList<Book> allBooks = DataBase.getAllBooks();
		response.setContentType("text/html");
		if(allBooks==null) {
			response.getWriter().println("<h1>No book are available right now.!</h1>");
		}
		else {
			if(Book.isBookAvailable(allBooks)) {
				if(request.getSession().getAttribute("userName") == null ) {
					response.sendRedirect("index.html");
				}
				response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
				response.getWriter().print(Book.displayAvailableBookFromLibrary(allBooks));
//				response.setContentType("text);
				response.getWriter().print("<form action=\"update\">"
						+ "Enter the book id :<input type=\"text\" placeholder=\"Enter book Id\" name=\"bookid\" required>"
						+ "<button type=\"submit\">add</button> "
						+ "</form>");
			}else {
				response.getWriter().println("<h1>No book are available right now.!</h1><br>\r\n"
						+ "						<a href=\"userHome\">back</a>");
			}
		}
//		User user = (User) getServletContext().getAttribute("user");
//		user.booksDetail = allBooks;
//		getServletContext().setAttribute("user", user);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
