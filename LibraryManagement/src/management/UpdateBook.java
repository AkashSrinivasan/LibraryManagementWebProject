package management;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateBook")
public class UpdateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateBook() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		
		Book book = new Book();
		book.id =Integer.parseInt(request.getParameter("bookId"));
		book.name = request.getParameter("bookName");
		DataBase.addNewBook(book);
		System.out.println("Book added sucessfully");
		response.sendRedirect("adminHome");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
