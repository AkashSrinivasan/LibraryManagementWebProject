package management;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userHome")
public class UserHome extends HttpServlet {
	
    public UserHome() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
			User user = (User) getServletContext().getAttribute("user");
			response.getWriter().print("<h1>Hi "+user.name+".!</h1><br>"
					+ "					<a href=\"displayBook\">Display borrowed book</a><br>"
					+ "					<a href=\"getBook\">Get Book from Library</a><br>"
					+ "					<a href=\"returnBook\">Return a book to Library</a><br>");
			response.getWriter().print("<form action =\"logout\" method = \"POST\">\r\n"
					+ "         <input type = \"submit\" value = \"Logout\" />\r\n"
					+ "      </form> ");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
