package Noyau ;

//import javafx.scene.paint.Color;
import java.awt.Color;
public class Catégorie {
    private String categorie;
    private java.awt.Color couleur;

   
  public String toFXColor(){ //CONVERTS FROM JAVA.AWT.COLOR TO JAVAFX.SCENE.PAINT.COLOR AND RETURNS THE RGB STRING VALUE TO USE IT IN THE SETSTYLE METODE
    java.awt.Color awtColor = this.couleur ;
    int r = awtColor.getRed();
    int g = awtColor.getGreen();
    int b = awtColor.getBlue();
    int a = awtColor.getAlpha();
    double opacity = a / 255.0 ;
     javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
     return String.format("#%02X%02X%02X",
            (int) (fxColor.getRed() * 255),
            (int) (fxColor.getGreen() * 255),
            (int) (fxColor.getBlue() * 255));
    
  }

    
    public String getCategorieName(){
        return this.categorie ;
    }

    public Catégorie(String categorie, Color couleur) {
            this.categorie = categorie;
            this.couleur = couleur;
        }
   
public void setCategorie(String categorie) {
    this.categorie = categorie;
}
public Color getCouleur() {
    return couleur;
}
public void setCouleur(Color couleur) {
    this.couleur = couleur;
}
@Override
public String toString() {
    return "Catégorie [" + categorie + "]";//, couleur=" + couleur + "]";
} 
}
