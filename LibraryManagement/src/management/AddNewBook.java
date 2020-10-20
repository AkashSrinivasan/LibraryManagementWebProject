package management;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewBook
 */
@WebServlet("/addNewBook")
public class AddNewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public AddNewBook() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
//		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		response.setContentType("text/html");
		response.getWriter().print("<form action=\"updateBook\" align=\"center\" method=\"POST\">\r\n"
				+ "            <label>Book Id : </label> \r\n"
				+ "            <input type=\"text\" placeholder=\"Enter Book Id\" name=\"bookId\" required><br>\r\n"
				+ "            <label>Book Name : </label> \r\n"
				+ "            <input type=\"text\" placeholder=\"Enter Book Name\" name=\"bookName\" required><br>\r\n"
				+ "            <button type=\"submit\">Add</button><br> \r\n"
				+ "        </div> \r\n"
				+ "    </form><br>"
				+ "		<a href=\"adminHome\">Cancel</a>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
