package management;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/displayUser")
public class DisplayUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DisplayUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		response.setContentType("text/html");
		String allStudentDetailsAsString = DataBase.getDetailsFromFile("Student");
		if(allStudentDetailsAsString!="") {
			response.getWriter().println(allStudentDetailsAsString);
			response.getWriter().println();
		}
		String allStaffDetailsAsString = DataBase.getDetailsFromFile("Staff");
		if(allStaffDetailsAsString!="") {
			response.getWriter().println(allStaffDetailsAsString);
			response.getWriter().println();
		}
		if(allStaffDetailsAsString=="" && allStudentDetailsAsString=="") {
			response.getWriter().println("<p>No User..!</p>");
		}
		response.getWriter().println("<a href=\"adminHome\">back</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
