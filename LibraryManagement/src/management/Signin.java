package management;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name ="Signin",urlPatterns = { "/signIn"})
public class Signin extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoadAllFile.createAllFile();
		response.setContentType("text/html");
		response.getWriter().print("<style> \r\n"
				+ "Body {\r\n"
				+ "  font-family: sans-serif;\r\n"
				+ "}\r\n"
				+ "button { \r\n"
				+ "       \r\n"
				+ "       width: 100%;\r\n"
				+ "        padding: 15px; \r\n"
				+ "        margin: 10px 0px; \r\n"
				+ "        border: none; \r\n"
				+ "        cursor: pointer; \r\n"
				+ "         } \r\n"
				+ " form { \r\n"
				+ "        border: 3px solid black; \r\n"
				+ "        margin-left: 25%;\r\n"
				+ "        margin-right: 25%;\r\n"
				+ "    } \r\n"
				+ " input[type=text], input[type=password] { \r\n"
				+ "        width: 100%; \r\n"
				+ "        margin: 8px 0;\r\n"
				+ "        padding: 12px 20px; \r\n"
				+ "        display: inline-block; \r\n"
				+ "       \r\n"
				+ "        box-sizing: border-box; \r\n"
				+ "    }\r\n"
				+ " button:hover { \r\n"
				+ "        opacity: 0.7; \r\n"
				+ "    } \r\n"
				+ "  .cancelbtn { \r\n"
				+ "        width: auto; \r\n"
				+ "        padding: 10px 18px;\r\n"
				+ "        margin: 10px 5px;\r\n"
				+ "    } \r\n"
				+ "      \r\n"
				+ "   \r\n"
				+ " .container { \r\n"
				+ "        padding: 25px; \r\n"
				+ "    } \r\n"
				+ "</style> \r\n");
		response.getWriter().print("<center><h1>Signin Form</h1></center>\r\n"
				+ "<form action=\"AddUser\" method=\"POST\">\r\n"
				+ "        <div class=\"container\"> \r\n"
				+ "            <label>Id : </label> \r\n"
				+ "            <input type=\"text\" placeholder=\"Enter Id\" name=\"id\" required><br>\r\n"
				+ "            <label>Username : </label> \r\n"
				+ "            <input type=\"text\" placeholder=\"Enter Username\" name=\"username\" required><br>\r\n"
				+ "            <label>Password : </label> \r\n"
				+ "            <input type=\"password\" placeholder=\"Enter Password\" name=\"password\" required><br>\r\n"
				+ "            <label>Select : </label>\r\n"
				+ "            <select name=\"signinas\" id=\"signinas\">\r\n"
				+ "			    <option value=\"Student\">Student</option>\r\n"
				+ "			    <option value=\"Staff\">Staff</option>\r\n"
				+ "			 	</select><br>\r\n"
				+ "            <button type=\"submit\">Signin</button><br> \r\n"
				+"				Already have account ? <a href=\"index.html\">login</a>"
				+ "        </div> \r\n"
				+ "    </form><br>"
				+ "		");
		
	}
}
