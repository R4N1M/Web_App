package ranim.servlets;

import java.util.Date;

/******************** COMPTEUR *********************/

public class Compteur {

  static int id = 0;
  String titre;
  String langue;
  String date;

  public Compteur() {
    id++;
    this.titre = "";
    this.langue = "";
    this.date = "";
  }

  public int getid() {
    return id;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre ;
  }

  public String getdate() {
    return date;
  }

  public void setdate(String date) {
    this.date = date ;
  }

  public String getlangue() {
    return langue;
  }

  public void setlangue(String langue) {
    this.langue = langue ;
  }

}
