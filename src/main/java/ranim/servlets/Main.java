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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main extends HttpServlet {

  /******* doGet **********************/
  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response )
  	throws ServletException, IOException {

      boolean redirect = false; //si on ajoute un cookie ou non pour rafrechir la page

      /******************* AJOUT , SUPPRESSION, MODIFICATION OU VIDE *************************/

      if( (request.getParameter("titre") != null)|| (request.getParameter("date") != null) || (request.getParameter("Langues") != null))
      {
        // Traiter deux cas : si le cookie a été modifié ou si il a été ajouté
        if(request.getParameter("modifier")!= null)
        {
          /************  MODIFICATION *********/

          Cookie[] cookies = request.getCookies();
          for(Cookie c: cookies)
          {
            if(c.getName().equals(request.getParameter("modifier")))
            {
              modifierCookie(response, c,request.getParameter("titre"),request.getParameter("date"), request.getParameter("Langues"));
            }
          }
        }else {

          /***********  AJOUT **************/

          Compteur nouveau_compteur = AjouterCompteur(request.getParameter("titre"),request.getParameter("date"), request.getParameter("Langues"));

          if(!CookieExiste(request.getCookies(), nouveau_compteur))
          {
            AddNewCookie(response, nouveau_compteur);
            redirect = true;
          }
        }
         request.setAttribute( "nouveau_compteur", request.getCookies());

      }else if(request.getParameter("supprimer")!= null){

        /********** SUPPRESSION ************/

        removeCookie(request, response, request.getParameter("supprimer") );
        request.setAttribute( "nouveau_compteur", request.getCookies());

      } else{
        if(request.getCookies() == null)
        {
          /********** PAGE VIDE (SANS COOKIES) ************/
          request.setAttribute( "nouveau_compteur","false" );
        }else{
          /*********** AFFICHAGE DES COOKIES **********/

          request.setAttribute( "nouveau_compteur",request.getCookies());
        }
      }

      /******************** RAFFRAICHIR  ********************/
      if(!redirect)
      {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Compteur.jsp" ).forward( request, response );
      }else {
        response.sendRedirect("/Application/main");
      }
  }

  /*************** doPost *****************************/
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
 	  throws ServletException, IOException {
 	    doGet(request, response);
   }

 /**************************** LES METHODES ******************************************************/


   // modifier un cookie
   private void modifierCookie(HttpServletResponse response, Cookie cookie, String titre, String date, String langue )
   {
    cookie.setValue(titre+" "+date+" "+langue);
    response.addCookie(cookie);
   }

    // supprimer un cookie de la liste des cookies
    private void removeCookie(HttpServletRequest request,HttpServletResponse response, String nom)
    {
      Cookie[] cookies = request.getCookies();

      for ( Cookie c : cookies)
      {
        if(c.getName().equals(nom))
        {
          c.setMaxAge(0);
          c.setValue("");
          response.addCookie(c);
        }
      }
    }

    // chercher si un cookie existe deja
    private boolean CookieExiste(Cookie[] cookies, Compteur compteur )
    {
      String chaine = compteur.getTitre()+" "+compteur.getdate()+" "+compteur.getlangue();
      for ( Cookie c : cookies)
      {
        if(c.getValue() == chaine)
        {
          return true;
        }
      }
      return false;
    }

    // Ajouter un nouveau compteur dans les cookies
    private void AddNewCookie(HttpServletResponse response, Compteur compteur)
    {
      response.addCookie( new Cookie(compteur.getid()+"", compteur.getTitre()+" "+compteur.getdate()+" "+compteur.getlangue()) );
    }

    // Declarer un nouveau compteur
    private Compteur AjouterCompteur(String titre, String date, String langue) {
      Compteur c1 = new Compteur();
      c1.setTitre(titre);
      c1.setdate(date);
      c1.setlangue(langue);
      return c1;
    }

}
