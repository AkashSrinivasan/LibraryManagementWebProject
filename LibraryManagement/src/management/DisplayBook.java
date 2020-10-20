package management;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/displayBook")
public class DisplayBook extends HttpServlet {
    public DisplayBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		User user = (User) getServletContext().getAttribute("user");
		response.setContentType("text/html");
		if(user.booksDetail != null && user.booksDetail.size()>0) {
			String bookAsString = user.booksDetail.size() >1 ? "books":"book";
			response.getWriter().print("<p>You have borrowed "+user.booksDetail.size()+" "+ bookAsString+"</p>");
			user.booksDetail.forEach(book -> {
				try {
					response.getWriter().print("<p>Book Name = "+book.name+"</p>");
					response.getWriter().print("<p>Return the book before "+ book.dueDate.toLocalDate()+"</p>" );
					response.getWriter().print("<p>Valid only for "+ ChronoUnit.DAYS.between(LocalDateTime.now(), book.dueDate)+" days</p>");
					response.getWriter().print("</br>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
		}else {
			response.getWriter().println("<h1>No books to display..!</h1>");
		}
		response.getWriter().println("<a href=\"userHome\">back</a>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
