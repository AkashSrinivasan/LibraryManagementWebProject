package management;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/returnBook")
public class ReturnBook extends HttpServlet {
    
    public ReturnBook() {
        super();
        // TODO Auto-generated constructor stu
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		response.setContentType("text/html");
		User user = (User) getServletContext().getAttribute("user");
		ArrayList<Book> allbooks = user.booksDetail;
		if(user.booksDetail != null) {
			user.booksDetail.forEach(i -> {
				
				try {
					response.getWriter().print("<p>Book Id = "+i.id+" || ");
					response.getWriter().println("Valid till = "+ ChronoUnit.DAYS.between(LocalDateTime.now(), i.dueDate)+"</p>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			response.getWriter().print("<form action=\"returnBookToLibrary\">"
					+ "Enter the book id :<input type=\"text\" placeholder=\"Enter book Id\" name=\"bookid\" required>"
					+ "<button type=\"submit\">remove</button> "
					+ "</form>");
		}else {
			System.out.println("no books in you stack");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
