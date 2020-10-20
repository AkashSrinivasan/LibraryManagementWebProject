package management;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayAllBook")
public class DisplayAllBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DisplayAllBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		response.setContentType("text/html");
		String allBookDetailsAsString = DataBase.getDetailsFromFile("AllBooks");
		if(allBookDetailsAsString!="") {
			response.getWriter().println(allBookDetailsAsString);
			response.getWriter().println();
		}
		else {
			response.getWriter().println("<p>No User..!</p>");
		}
		response.getWriter().println("<a href=\"adminHome\">back</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
