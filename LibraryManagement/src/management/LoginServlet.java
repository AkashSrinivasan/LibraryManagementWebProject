package management;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
    public LoginServlet() {
        super();
       
    }
    
    public static User ConvertUserStringToObject(String userDetails) {
		String[] lines = userDetails.split("\\r?\\n", -1);
		User student = new User(0, null, null);
		String[] splitedId = lines[0].split(" - ");
		student.id = Integer.parseInt(splitedId[1]);
		String[] splitedName = lines[1].split(" - ");
		student.name = splitedName[1];
		String[] splitedBookDetails = lines[3].split(" - ");
		String onlyBookDetails = splitedBookDetails[1];
		if(!onlyBookDetails.equals("null")) {
			String[] multipleBook = onlyBookDetails.split(",");
			for(int i=0;i<multipleBook.length;i++) {
				String[] indigualBookDetail = multipleBook[i].split(" -> ");
				int bookid = Integer.parseInt(indigualBookDetail[0]);
				String bookname = indigualBookDetail[1];
				int bid = Integer.parseInt(indigualBookDetail[2]);
				LocalDateTime bon = LocalDateTime.parse(indigualBookDetail[3]);
				LocalDateTime duedate= LocalDateTime.parse(indigualBookDetail[4]);
				student.booksDetail.add(new Book(bookid,bookname,bid,bon,duedate));
			}
		}else {
			student.booksDetail = null;
		}
		return student;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		Cookie cookie = new Cookie("userType", request.getParameter("user"));
		response.addCookie(cookie);
//		HttpSession session = request.getSession();
		if(request.getParameter("user").equals("Student") || request.getParameter("user").equals("Staff")) {
			File userFile = new File(System.getProperty("user.home") + "\\Desktop\\Library\\"+request.getParameter("user")+".txt");
			String userDetailsAsString = DataBase.loginDetails(userFile,id,password);
			System.out.println(userDetailsAsString);
			if(userDetailsAsString != "") {
				User currentuser = ConvertUserStringToObject(userDetailsAsString);
				getServletContext().setAttribute("user", currentuser);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("userName", request.getParameter("id"));
				System.out.println(httpSession.getAttribute("userName"));
				
				response.sendRedirect("userHome");
			}else {
//				session.invalidate();
				response.sendRedirect("index.html");
			}
		}else {
			HttpSession httpSession = request.getSession();
			if(Integer.parseInt(request.getParameter("id"))== DataBase.ADMIN_ID && request.getParameter("password").equals(DataBase.ADMIN_PASSWORD)) {
				httpSession.setAttribute("userName", "admin");
				response.sendRedirect("adminHome");
//				HttpSession session = request.getSession();
				
				
//					session.setAttribute("password",  request.getParameter("password"));
			}else {
				httpSession.invalidate();
				response.sendRedirect("index.html");
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
