package management;


import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    public AddUser() {
        super();
    }
    
    boolean add(int id, String userName, String password,String user) {
		User student = new User(id,userName,password);
		if(DataBase.checkStudentAlreadyPresent(student,user)) {
			return false;
		}
		return true;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userName") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		int id = Integer.parseInt(request.getParameter("id"));
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String user = request.getParameter("signinas");
		System.out.println(id+" "+userName+" "+password+" "+user);
		if(add(id,userName,password,user)) {
			response.getWriter().print("<div align=\"center\">"
					+ "					<h3>Signed in sucessfully, please <a href=\"index.html\">login</a>"
					+ "					</div>");
		}else {
			response.getWriter().print("<div align=\"center\">"
					+ "					<h3>User already exsist, please <a href=\"index.html\">login</a>"
					+ "					</div>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
