package ranim.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/*********** AJOUT ******************/
public class Ajouter extends HttpServlet {

  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response )
	 throws ServletException, IOException {
	  this.getServletContext().getRequestDispatcher( "/WEB-INF/Ajouter.jsp" ).forward( request, response );
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
     doGet(request, response);
 }

}
