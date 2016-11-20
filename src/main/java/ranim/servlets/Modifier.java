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
import java.util.ArrayList;
import java.lang.Object;

public class Modifier extends HttpServlet {

  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response )
	 throws ServletException, IOException {

     // Chercher le cookie de  nom = request.getParameter("modifier") et l'envoyer la page Modifier.jsp
     Cookie[] cookies = request.getCookies();
     Cookie cookieAModifier = new Cookie("1"," lkn") ;
      for ( Cookie c : cookies )
      {
        if(c.getName().equals(request.getParameter("modifier")))
        {
          cookieAModifier = c;
        }
      }
    request.setAttribute("cookie_a_modifier",cookieAModifier);
	  this.getServletContext().getRequestDispatcher( "/WEB-INF/Modifier.jsp" ).forward( request, response );
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
     doGet(request, response);
 }

}
