package ranim.servlets;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.Duration;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.json.JsonArray;
import javax.json.*;
import javax.websocket.EncodeException;

/******************************** WEBSOCKET *****************************************/

@ServerEndpoint("/websocket")
public class WebSocket {

    @OnMessage
    public void onMessage(String message, Session session) throws IOException,
            InterruptedException {
        boolean envoyer =true ;
        System.out.println("User input: " + message);

        // Recuperer les cookies et ranger leur valeurs
        String[] cookies = message.split("@");
        ArrayList<String[]> InfoCookies = new ArrayList<String[]>();

        for(String c : cookies)
        {
          InfoCookies.add(c.split("!"));
        }

        session.getBasicRemote().sendText(message);
        // Sending message to client each 1 second
        while(envoyer == true)
        {
          // Calculer la difference avec diff et stocker le resultat
          ArrayList<String[]> donnees = new ArrayList<String[]>();
          //String[] pour stocker le nom et la valeur de la cookie

          for(String[] c : InfoCookies)
          {
            // decouper c[1] (contient la valeur de c)
            String[] donnee = c[1].split(" ");
            donnee[0] = c[0];
            donnee[1] = diff(donnee[1],donnee[2]);
            donnees.add(donnee);
          }

          // Convertir en Json
          String text = toJson(donnees);

          session.getBasicRemote().sendText(text);
          Thread.sleep(1000);
        }
    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }

    // Calculer la difference d'heures...
    private String diff(String date, String locale){
    		String theDate = date;
    		String pattern = "MM-dd-yyyy//HH:mm:ss";
    		Date date2 = null;
    		try {
    			date2 = new SimpleDateFormat(pattern).parse(theDate);
    		} catch (ParseException e) {
    			return "server error...";
    		}

        LocalDateTime d2 = date2.toInstant().atZone(ZoneId.of(locale)).toLocalDateTime();
        LocalDateTime d1 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        Duration duration = Duration.between(d1,d2);

        long diff = duration.getSeconds();
    		long diffSeconds = diff % 60;
    		long diffMinutes = diff / (60) % 60;
    		long diffHours = diff / (60 * 60) % 24;
    		long diffDays = diff / (24 * 60 * 60);
    		return diffDays+" jour(s) "+diffHours+" heure(s) "+diffMinutes+" minute(s) "+diffSeconds+" seconde(s)";

    	}

    // Convertir un tableau à un fichier JSON
    private String toJson(ArrayList<String[]> Donnees)
    {
      int longueur = Donnees.size();
      String text = "[ ";
      int i=0;

      for(String[] d :Donnees)
      {
        if(i == longueur-1)
        {
          text +="{ "+"\"Id\": \""+d[0]+"\","+"\"Difference\": \""+d[1] +"\" }";
        }else {
          text += "{ "+"\"Id\": \""+d[0]+"\","+"\"Difference\": \""+d[1] +"\" },";
        }
        i++;
      }
      text += " ]";
      return text;
    }
}
